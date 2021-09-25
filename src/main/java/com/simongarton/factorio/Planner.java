package com.simongarton.factorio;

import com.simongarton.factorio.exceptions.RecipeNotFoundException;
import com.simongarton.factorio.model.*;
import com.simongarton.factorio.model.makers.Assembler1;
import com.simongarton.factorio.model.makers.ChemicalPlant;
import com.simongarton.factorio.model.makers.Maker;
import com.simongarton.factorio.model.makers.OilRefinery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Planner {

    private final List<Item> items;

    private final Map<String, Double> neededQuantities;

    private final Map<ItemType, Maker> makers = new HashMap<>();

    public Planner() {
        // this is the lookup for the items
        this.items = ItemLoader.getItems();
        // these are for the overall recipe
        this.neededQuantities = new HashMap<>();
        //
        this.setupDefaultMakers();
    }

    private void setupDefaultMakers() {
        this.makers.put(ItemType.ASSEMBLING_MACHINE_1, new Assembler1());
//        this.makers.put(ItemType.ASSEMBLING_MACHINE_2, new Assembler2());
//        this.makers.put(ItemType.ASSEMBLING_MACHINE_3, new Assembler3());

//        this.makers.put(ItemType.STONE_FURNACE, new StoneFurnace());
//        this.makers.put(ItemType.STEEL_FURNACE, new SteelFurnace());
//        this.makers.put(ItemType.ELECTRIC_FURNACE, new ElectricFurnace());

        this.makers.put(ItemType.OIL_REFINERY, new OilRefinery());
        this.makers.put(ItemType.CHEMICAL_PLANT, new ChemicalPlant());
    }

    public Plan plan(final ItemType itemType, final double quantity) {
        // how do I make this item ? I need to find a recipe. This first call gets a generic recipe with no details
        final Item item = this.getItemForItemType(itemType);
        // now I turn that into a recipe. I may extend here to restrict to different makers
        // The recipe is a standard "one-iteration" of the maker, and will normally - but not always - produce one item
        // but I need to consider the time as well.
        final Recipe recipe = this.buildRecipeForItem(item);
        return this.buildPlan(itemType, recipe, quantity);
    }

    private Recipe buildRecipeForItem(final Item item) {
        final Recipe recipe = item.getRecipe();
        // this isn't in the JSON so I set it explicitly
        recipe.setItemType(item.getItemType());
        recipe.setMaker(this.getMaker(item.getItemType()));
        return recipe;
    }

    private Item getItemForItemType(final ItemType itemType) {
        return this.items.stream()
                .filter(i -> i.getItemType().equals(itemType))
                .findFirst().
                orElseThrow(() -> new RecipeNotFoundException(itemType.getId()));
    }

    private void setupPlansForIngredients(final Plan plan) {
        final Recipe recipe = plan.getRecipe();
        for (final Ingredient ingredient : recipe.getIngredients()) {
            this.setupPlanForIngredient(plan, ingredient, plan.getRecipeFactor());
        }
    }

    private void setupPlanForIngredient(final Plan plan, final Ingredient ingredient, final double recipeFactor) {
        final ItemType neededItemType = ingredient.getItemType();
        final double quantityNeeded = ingredient.getQuantity() * recipeFactor;
        final Item item = this.getItemForItemType(neededItemType);
        // now I turn that into a recipe. I may extend here to restrict to different makers
        // The recipe is a standard "one-iteration" of the maker, and will normally - but not always - produce one item
        // but I need to consider the time as well.
        final Plan planForIngredient = new Plan(neededItemType, recipeFactor);
        final Recipe recipeForIngredient = this.buildRecipeForItem(item);
        planForIngredient.setRecipe(recipeForIngredient);
        final Maker maker = this.getMaker(neededItemType);
        if (maker != null && item.getRecipe() != null && recipeForIngredient.getTime() != null) {
            planForIngredient.setRecipeFactor(
                    maker.getCraftingSpeed() * quantityNeeded
                            / (recipeForIngredient.getYield() * recipeForIngredient.getTime()));
        }
        planForIngredient.setQuantity(quantityNeeded);
        planForIngredient.getRecipe().setMaker(maker);
        if (maker != null) {
            this.setupPlansForIngredients(planForIngredient);
        }
        plan.getIngredientPlans().put(neededItemType, planForIngredient);
    }

    private Plan buildPlan(final ItemType itemType, final Recipe recipe, final double quantity) {
        final Plan plan = new Plan(itemType, quantity);
        plan.setRecipe(recipe);
        final Maker maker = recipe.getMaker();
        plan.setRecipeFactor(maker.getCraftingSpeed() * quantity / (recipe.getYield() * recipe.getTime()));
        this.setupPlansForIngredients(plan);
        return plan;
    }

    private Maker getMaker(final ItemType itemType) {
        return this.makers.values().stream().filter(m -> m.makes(itemType)).findFirst().orElse(null);
    }

    public void describe(final Plan plan) {
        // TODO go figure out SLF4j
        System.out.printf("This is a plan to make %s %s per second%n", plan.getQuantity(), plan.getItemType().getType());
        if (plan.getRecipe().getMaker() == null) {
            System.out.printf("I can't make it, it's a raw ingredient.%n");
        } else {
            System.out.printf("To make it, I will use a %s.%n", plan.getRecipe().getMaker().getItemType().getType());
            System.out.printf("The recipe produces %s items in %s seconds, with a crafting speed of %s, so I have a recipe factor of %s.%n",
                    plan.getRecipe().getYield(),
                    plan.getRecipe().getTime(),
                    plan.getRecipe().getMaker().getCraftingSpeed(),
                    plan.getRecipeFactor());
        }
        if (!plan.getRecipe().getIngredients().isEmpty()) {
            System.out.printf("It uses the following ingredients:%n");
            for (final Ingredient ingredient : plan.getRecipe().getIngredients()) {
                System.out.printf("%s of %s%n", ingredient.getQuantity(), ingredient.getItemType().getType());
            }
        }
        this.showNeededStuff(plan);
    }

    private void showNeededStuff(final Plan plan) {
        this.neededQuantities.clear();
        this.updateNeededQuantitiesFromPlan(plan);
        for (final Map.Entry<String, Double> entry : this.neededQuantities.entrySet()) {
            System.out.printf("Of %s I need %s%n", entry.getKey(), entry.getValue());
        }
    }

    private void updateNeededQuantitiesFromPlan(final Plan plan) {
        if (plan.getRecipe() == null) {
            return;
        }
        final Maker maker = plan.getRecipe().getMaker();
        final String item = plan.getRecipe().getItemType().getType();
        String key = "raw:" + item;
        if (maker != null) {
            key = maker.getItemType().getType() + ":" + item;
        }
        if (this.neededQuantities.containsKey(key)) {
            this.neededQuantities.put(key, this.neededQuantities.get(key) + plan.getRecipeFactor());
        } else {
            this.neededQuantities.put(key, plan.getRecipeFactor());
        }
        for (final Plan childPlan : plan.getIngredientPlans().values()) {
            this.updateNeededQuantitiesFromPlan(childPlan);
        }
    }

    public void dumpGraph() {
        final List<String> lines = new ArrayList<>();
        lines.add("digraph d {");
        lines.add("rankdir=LR;");

        final Set<String> done = new HashSet<>();
        for (final Item item : this.items) {
            final String result = item.getItemType().getType();
            for (final Ingredient ingredient : item.getRecipe().getIngredients()) {
                final String source = ingredient.getItemType().getType();
                final String key = "\"" + source + "\"" + "->" + "\"" + result + "\"";
                if (!(done.contains(key))) {
                    lines.add(key);
                }
                done.add(key);
            }
        }
        lines.add("}");
        final Path path = Paths.get("factorio.dot");
        try {
            Files.write(path, lines);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}

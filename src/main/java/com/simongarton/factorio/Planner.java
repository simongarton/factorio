package com.simongarton.factorio;

import com.simongarton.factorio.exceptions.RecipeNotFoundException;
import com.simongarton.factorio.model.*;
import com.simongarton.factorio.model.makers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Planner {

    private final List<Item> items;

    private final Map<ItemType, Double> neededQuantities;

    private final List<Maker> makers = List.of(
            new Assembler1(),
            new Assembler2(),
            new Assembler3(),
            new OilRefinery(),
            new ChemicalPlant(),
            new StoneFurnace(),
            new SteelFurnace(),
            new ElectricFurnace()
    );

    public Planner() {
        this.items = ItemLoader.getItems();
        this.neededQuantities = new HashMap<>();
    }

    public Recipe getRecipe(final ItemType itemType) {
        return this.items.stream().filter(i -> i.getId().equals(itemType)).map(Item::getRecipe).findFirst().orElseThrow(() -> new RecipeNotFoundException(itemType.getId()));
    }

    public Optional<RecursiveRecipe> getRecursiveRecipe(final ItemType itemType, final double need) {
        final RecursiveRecipe recursiveRecipe = new RecursiveRecipe();
        recursiveRecipe.setItem(itemType);
        recursiveRecipe.setMakeWith(this.getMaker(itemType));
        final Recipe topRecipe = this.items.stream().filter(i -> i.getId().equals(itemType)).map(Item::getRecipe).findFirst().orElseThrow(() -> new RecipeNotFoundException(itemType.getId()));
        recursiveRecipe.setNeed(need);
        recursiveRecipe.setIngredients(topRecipe.getIngredients());
        if (topRecipe.getTime() == null) {
            return Optional.of(recursiveRecipe);
        }
        recursiveRecipe.setTime(topRecipe.getTime());
        recursiveRecipe.setYield(topRecipe.getYield());
        for (final Ingredient ingredient : topRecipe.getIngredients()) {
            final ItemType ingredientType = ItemType.from(ingredient.getId());
            final Optional<RecursiveRecipe> subRecipe = this.getRecursiveRecipe(ingredientType, ingredient.getAmount());
            subRecipe.ifPresent(recipe -> recursiveRecipe.getRecipes().add(recipe));
        }
        return Optional.of(recursiveRecipe);
    }

    private Maker getMaker(final ItemType itemType) {
        return this.makers.stream().filter(m -> m.makes(itemType)).findFirst().orElse(null);
    }

    public void displayAsTree(final RecursiveRecipe recursiveRecipe, final String indent) {
        System.out.println(indent + recursiveRecipe.getDetails());
        for (final RecursiveRecipe recipe : recursiveRecipe.getRecipes()) {
            this.displayAsTree(recipe, indent + "  ");
        }
    }

    public void listTotalItems(final RecursiveRecipe recursiveRecipe) {
        this.neededQuantities.clear();
        this.addItemQuantitiesRecursively(recursiveRecipe);
        for (Map.Entry<ItemType, Double> entry : this.neededQuantities.entrySet()) {
            System.out.println(entry.getKey().getId() + " : " + entry.getValue());
        }
    }

    private void addItemQuantitiesRecursively(RecursiveRecipe recursiveRecipe) {
        for (RecursiveRecipe recipe : recursiveRecipe.getRecipes()) {
            if (this.neededQuantities.containsKey(recipe.getItem())) {
                this.neededQuantities.put(recipe.getItem(), this.neededQuantities.get(recipe.getItem()) + recipe.getNeed());
            } else {
                this.neededQuantities.put(recipe.getItem(), recipe.getNeed());
            }
        }
        for (RecursiveRecipe recipe : recursiveRecipe.getRecipes()) {
            this.addItemQuantitiesRecursively(recipe);
        }
    }

    public void describe(RecursiveRecipe recursiveRecipe) {
        System.out.println("To make " + recursiveRecipe.getNeed() + " per second of " + recursiveRecipe.getItem().getType());
        System.out.println("I will need " + recursiveRecipe.getNeed() / recursiveRecipe.getYield() + " " + recursiveRecipe.getMakeWith().getMakerType().getType() + "(s)");
        System.out.println("which will need :");
        for (RecursiveRecipe recipe : recursiveRecipe.getRecipes()) {
            System.out.println("  " + recipe.getItem().getType());
        }
        System.out.println();
    }

    public Request planRequest(ItemType itemType, double quantity) {
        Request request = new Request();
        request.setItemType(itemType);
        request.setQuantity(quantity);
        RecursiveRecipe recipe = getRecursiveRecipe(itemType, quantity).orElse(null);
        request.setRecipe(recipe);
        return request;
    }

    public void describe(Request request) {
        describeAtLevel(0, request);
    }

    private void describeAtLevel(int level, Request request) {
        String indent =
        System.out.println("To make " + recursiveRecipe.getNeed() + " per second of " + recursiveRecipe.getItem().getType());
        System.out.println("I will need " + recursiveRecipe.getNeed() / recursiveRecipe.getYield() + " " + recursiveRecipe.getMakeWith().getMakerType().getType() + "(s)");
        System.out.println("which will need :");
        for (RecursiveRecipe recipe : recursiveRecipe.getRecipes()) {
            System.out.println("  " + recipe.getItem().getType());
        }
        System.out.println();
    }
}

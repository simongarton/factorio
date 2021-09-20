package com.simongarton.factorio;

import com.simongarton.factorio.exceptions.RecipeNotFoundException;
import com.simongarton.factorio.model.Item;
import com.simongarton.factorio.model.ItemType;
import com.simongarton.factorio.model.Plan;
import com.simongarton.factorio.model.Recipe;
import com.simongarton.factorio.model.makers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // this is the lookup for the items
        this.items = ItemLoader.getItems();
        // these are for the overall recipe
        this.neededQuantities = new HashMap<>();
    }

    public Plan plan(final ItemType itemType, final double quantity) {
        // how do I make this item ? I need to find a recipe. This first call gets a generic recipe with no details
        // and I don't think I actually use it, I just need to show it exists. TODO
        final Item item = this.items.stream()
                .filter(i -> i.getItemType().equals(itemType))
                .findFirst().
                orElseThrow(() -> new RecipeNotFoundException(itemType.getId()));
        // now I turn that into a recipe. I may extend here to restrict to different makers
        // The recipe is a standard "one-iteration" of the maker, and will normally - but not always - produce one item
        final Recipe recipe = item.getRecipe();
        // this isn't in the JSON
        recipe.setItemType(itemType);
        recipe.setMakeWith(this.getMaker(itemType));
        return this.buildPlan(itemType, recipe, quantity);
    }

    private Plan buildPlan(ItemType itemType, Recipe recipe, double quantity) {
        final Plan plan = new Plan(itemType, quantity);
        plan.setRecipe(recipe);
        // so I need to add in a recipe factor, e.g. for quantity 2 of a 1 item recipe I need a recipeFactor of 2
        plan.setRecipeFactor(quantity / recipe.getYield());
        return plan;
    }

     private Maker getMaker(final ItemType itemType) {
        return this.makers.stream().filter(m -> m.makes(itemType)).findFirst().orElse(null);
    }

    public void describe(final Plan plan) {
        // TODO go figure out SLF4j
        System.out.printf("Plan %s (%s)%n", plan.getItemType().getType(), plan.getQuantity());
        System.out.printf("  will use %s of recipe %s%n", plan.getRecipeFactor(), plan.getRecipe());
    }
}

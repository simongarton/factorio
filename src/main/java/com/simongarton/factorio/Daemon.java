package com.simongarton.factorio;

import com.simongarton.factorio.exceptions.MoreThanOneMakerException;
import com.simongarton.factorio.exceptions.RecipeNotFoundException;
import com.simongarton.factorio.model.*;
import com.simongarton.factorio.model.makers.Maker;

import java.util.*;

public class Daemon {

    private final RecipeBook recipeBook;

    List<Maker> availableMakers;

    public Daemon(final RecipeBook recipeBook) {
        this.recipeBook = recipeBook;
        this.availableMakers = new ArrayList<>();
    }

    public Plan getPlan(final ItemType itemType, final double itemsPerSecond) {
        final Recipe recipe;
        try {
            recipe = this.recipeBook.getRecipe(itemType);
        } catch (final RecipeNotFoundException e) {
            final Plan plan = new Plan(itemType, itemsPerSecond);
            return plan;
        }

        final Optional<Maker> optionalMaker = this.getMaker(recipe.getCategory());

        if (optionalMaker.isEmpty()) {
            return new Plan(itemType, itemsPerSecond);
        }

        final Maker maker = optionalMaker.get();
        final double madeInOneSecond = maker.getCraftingSpeed() * recipe.getYield() / recipe.getTime();
        final double makerCount = itemsPerSecond / madeInOneSecond;

        final Plan plan = new Plan(itemType, itemsPerSecond);
        plan.setMakerType(maker.getItemType());
        plan.setMakerCount(makerCount);
        final Map<ItemType, Double> ingredients = new HashMap<>();
        final Map<ItemType, Plan> plans = new HashMap<>();
        for (final Recipe.Ingredient ingredient : recipe.getIngredients()) {
            final double amountNeeded = ingredient.getAmount() * makerCount / recipe.getTime();
            final ItemType ingredientType = ItemType.from(ingredient.getName());
            ingredients.put(ingredientType, amountNeeded);
            final Plan subPlan = this.getPlan(ingredientType, amountNeeded);
            plans.put(ingredientType, subPlan);
        }
        plan.setIngredients(ingredients);
        plan.setPlans(plans);
        return plan;
    }

    private Optional<Maker> getMaker(final CategoryType categoryType) {
        if (this.availableMakers.stream().noneMatch(m -> m.makes(categoryType))) {
            return Optional.empty();
        }
        if (this.availableMakers.stream().filter(m -> m.makes(categoryType)).count() > 1) {
            throw new MoreThanOneMakerException(categoryType.getCategory());
        }
        return this.availableMakers.stream().filter(m -> m.makes(categoryType)).findFirst();
    }

    public void addMaker(final Maker maker) {
        this.availableMakers.add(maker);
    }
}

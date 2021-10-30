package com.simongarton.factorio;

import com.simongarton.factorio.exceptions.MoreThanOneMakerException;
import com.simongarton.factorio.model.*;
import com.simongarton.factorio.model.makers.Maker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Daemon {

    private final RecipeBook recipeBook;

    List<Maker> availableMakers;

    public Daemon(final RecipeBook recipeBook) {
        this.recipeBook = recipeBook;
        this.availableMakers = new ArrayList<>();
    }

    public Plan getPlan(final ItemType itemType, final double itemsPerSecond) {
        final Recipe recipe = this.recipeBook.getRecipe(itemType);

        final Optional<Maker> optionalMaker = this.getMaker(recipe.getCategory());

        if (optionalMaker.isEmpty()) {
            return new Plan();
        }

        final Maker maker = optionalMaker.get();
        final double madeInOneSecond = maker.getCraftingSpeed() * recipe.getYield() / recipe.getTime();
        final double makerCount =  itemsPerSecond / madeInOneSecond;

        final Plan plan = new Plan();
        plan.setMakerType(maker.getItemType());
        plan.setMakerCount(makerCount);
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

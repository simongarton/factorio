package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.MakerType;
import com.simongarton.factorio.model.RecipeType;

public class StoneFurnace extends Maker {

    private final RecipeType itemType = RecipeType.STONE_FURNACE;

    private final double craftingSpeed = 1;

    @Override
    public boolean makes(final RecipeType itemType) {
        return this.stoneFurnaceItems.contains(itemType);
    }

    @Override
    public RecipeType getRecipeType() {
        return this.itemType;
    }

    @Override
    public MakerType getMakerType() {
        return MakerType.FURNACE;
    }

    @Override
    public double getCraftingSpeed() {
        return this.craftingSpeed;
    }
}

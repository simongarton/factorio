package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.RecipeType;
import com.simongarton.factorio.model.MakerType;

public class ElectricFurnace extends Maker {

    private final RecipeType itemType = RecipeType.ELECTRIC_FURNACE;

    private final double craftingSpeed = 2;

    @Override
    public boolean makes(final RecipeType itemType) {
        return this.electricFurnaceItems.contains(itemType);
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

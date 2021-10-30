package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.RecipeType;
import com.simongarton.factorio.model.MakerType;

public class OilRefinery extends Maker {

    private final RecipeType itemType = RecipeType.OIL_REFINERY;

    private final double craftingSpeed = 1.25;

    @Override
    public boolean makes(final RecipeType itemType) {
        return this.oilRefineryItems.contains(itemType);
    }

    @Override
    public RecipeType getRecipeType() {
        return this.itemType;
    }

    @Override
    public MakerType getMakerType() {
        return MakerType.REFINERY;
    }

    @Override
    public double getCraftingSpeed() {
        return this.craftingSpeed;
    }
}

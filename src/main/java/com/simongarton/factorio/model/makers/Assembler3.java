package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.RecipeType;
import com.simongarton.factorio.model.MakerType;

public class Assembler3 extends Maker {

    private final RecipeType itemType = RecipeType.ASSEMBLING_MACHINE_3;

    private final double craftingSpeed = 1.25;

    @Override
    public boolean makes(final RecipeType itemType) {
        return this.assembler1Items.contains(itemType) ||
                this.assembler2AdditionalItems.contains(itemType) ||
                this.assembler3AdditionalItems.contains(itemType);
    }

    @Override
    public RecipeType getRecipeType() {
        return this.itemType;
    }

    @Override
    public MakerType getMakerType() {
        return MakerType.ASSEMBLER;
    }

    @Override
    public double getCraftingSpeed() {
        return this.craftingSpeed;
    }
}

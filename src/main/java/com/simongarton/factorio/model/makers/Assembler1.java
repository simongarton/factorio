package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.RecipeType;
import com.simongarton.factorio.model.MakerType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Assembler1 extends Maker {

    protected RecipeType itemType = RecipeType.ASSEMBLING_MACHINE_1;

    private final double craftingSpeed = 0.5;

    @Override
    public RecipeType getRecipeType() {
        return this.itemType;
    }

    @Override
    public MakerType getMakerType() {
        return MakerType.ASSEMBLER;
    }

    @Override
    public boolean makes(final RecipeType itemType) {
        return this.assembler1Items.contains(itemType);
    }

    @Override
    public double getCraftingSpeed() {
        return this.craftingSpeed;
    }
}

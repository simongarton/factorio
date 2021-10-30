package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.CategoryType;
import com.simongarton.factorio.model.ItemType;

import java.util.Set;

import static com.simongarton.factorio.model.CategoryType.*;

public class Assembler3 extends Maker {

    private final ItemType itemType = ItemType.ASSEMBLING_MACHINE_3;

    private final double craftingSpeed = 1.25;

    @Override
    public ItemType getItemType() {
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

    @Override
    public boolean makes(CategoryType categoryType) {
        return Set.of(CRAFTING, ADVANCED_CRAFTING, CRAFTING_WITH_FLUID).contains(categoryType);
    }
}

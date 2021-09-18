package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.ItemType;
import com.simongarton.factorio.model.MakerType;

public class Assembler2 extends Maker {

    private final ItemType itemType = ItemType.ASSEMBLING_MACHINE_2;

    private final double craftingSpeed = 0.75;

    @Override
    public boolean makes(final ItemType itemType) {
        return this.assembler1Items.contains(itemType) ||
                this.assembler2AdditionalItems.contains(itemType);
    }

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
}

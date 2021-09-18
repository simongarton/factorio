package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.ItemType;
import com.simongarton.factorio.model.MakerType;

public class Assembler1 extends Maker {

    protected ItemType itemType = ItemType.ASSEMBLING_MACHINE_1;

    private final double craftingSpeed = 0.5;

    @Override
    public ItemType getItemType() {
        return this.itemType;
    }

    @Override
    public MakerType getMakerType() {
        return MakerType.ASSEMBLER;
    }

    @Override
    public boolean makes(final ItemType itemType) {
        return this.assembler1Items.contains(itemType);
    }

    @Override
    public double getCraftingSpeed() {
        return this.craftingSpeed;
    }
}

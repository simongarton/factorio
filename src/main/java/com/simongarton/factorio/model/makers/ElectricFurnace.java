package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.ItemType;
import com.simongarton.factorio.model.MakerType;

public class ElectricFurnace extends Maker {

    private final ItemType itemType = ItemType.ELECTRIC_FURNACE;

    private final double craftingSpeed = 2;

    @Override
    public boolean makes(final ItemType itemType) {
        return this.electricFurnaceItems.contains(itemType);
    }

    @Override
    public ItemType getItemType() {
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

package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.ItemType;
import com.simongarton.factorio.model.MakerType;

public class OilRefinery extends Maker {

    private final ItemType itemType = ItemType.OIL_REFINERY;

    private final double craftingSpeed = 1.25;

    @Override
    public boolean makes(final ItemType itemType) {
        return this.oilRefineryItems.contains(itemType);
    }

    @Override
    public ItemType getItemType() {
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

package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.CategoryType;
import com.simongarton.factorio.model.ItemType;

import java.util.Set;

import static com.simongarton.factorio.model.CategoryType.OIL_PROCESSING;
import static com.simongarton.factorio.model.CategoryType.SMELTING;

public class OilRefinery extends Maker {

    private final ItemType itemType = ItemType.OIL_REFINERY;

    private final double craftingSpeed = 1;

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

    @Override
    public boolean makes(CategoryType categoryType) {
        return Set.of(OIL_PROCESSING).contains(categoryType);
    }
}

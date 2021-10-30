package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.CategoryType;
import com.simongarton.factorio.model.ItemType;

import java.util.Set;

import static com.simongarton.factorio.model.CategoryType.CHEMISTRY;
import static com.simongarton.factorio.model.CategoryType.SMELTING;

public class ElectricFurnace extends Maker {

    private final ItemType itemType = ItemType.ELECTRIC_FURNACE;

    private final double craftingSpeed = 2;

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

    @Override
    public boolean makes(CategoryType categoryType) {
        return Set.of(SMELTING).contains(categoryType);
    }
}

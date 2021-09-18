package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.ItemType;
import com.simongarton.factorio.model.MakerType;

import java.util.List;

// I don't have mining here yet

public abstract class Maker {

    public abstract ItemType getItemType();

    public abstract MakerType getMakerType();

    public abstract boolean makes(ItemType itemType);

    public abstract double getCraftingSpeed();

    protected final List<ItemType> assembler1Items = List.of(
            ItemType.ELECTRONIC_CIRCUIT,
            ItemType.COPPER_CABLE
    );

    protected final List<ItemType> assembler2AdditionalItems = List.of(
            ItemType.BATTERY
    );

    protected final List<ItemType> assembler3AdditionalItems = List.of(
    );

    protected final List<ItemType> chemicalPlantItems = List.of(
    );

    protected final List<ItemType> oilRefineryItems = List.of(
    );

    protected final List<ItemType> stoneFurnaceItems = List.of(
            ItemType.COPPER_PLATE,
            ItemType.IRON_PLATE,
            ItemType.STEEL_PLATE
    );

    protected final List<ItemType> steelFurnaceItems = List.of(
            ItemType.COPPER_PLATE,
            ItemType.IRON_PLATE,
            ItemType.STEEL_PLATE
    );

    protected final List<ItemType> electricFurnaceItems = List.of(
            ItemType.COPPER_PLATE,
            ItemType.IRON_PLATE,
            ItemType.STEEL_PLATE
    );
}

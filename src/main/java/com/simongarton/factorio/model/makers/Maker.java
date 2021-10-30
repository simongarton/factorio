package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.RecipeType;
import com.simongarton.factorio.model.MakerType;

import java.util.List;

// I don't have mining here yet

public abstract class Maker {

    public abstract RecipeType getRecipeType();

    public abstract MakerType getMakerType();

    public abstract boolean makes(RecipeType itemType);

    public abstract double getCraftingSpeed();

    protected final List<RecipeType> assembler1Items = List.of(

    );

    protected final List<RecipeType> assembler2AdditionalItems = List.of(
            RecipeType.BATTERY
    );

    protected final List<RecipeType> assembler3AdditionalItems = List.of(
    );

    protected final List<RecipeType> chemicalPlantItems = List.of(
            RecipeType.LUBRICANT,
            RecipeType.SULFUR,
            RecipeType.SULFURIC_ACID,
            RecipeType.PLASTIC_BAR,
            RecipeType.EXPLOSIVES,
            RecipeType.BATTERY,
            RecipeType.SOLID_FUEL_FROM_PETROLEUM_GAS,
            RecipeType.SOLID_FUEL_FROM_LIGHT_OIL,
            RecipeType.SOLID_FUEL_FROM_HEAVY_OIL
    );

    protected final List<RecipeType> oilRefineryItems = List.of(
    );

    protected final List<RecipeType> stoneFurnaceItems = List.of(
            RecipeType.COPPER_PLATE,
            RecipeType.IRON_PLATE,
            RecipeType.STEEL_PLATE,
            RecipeType.STONE_BRICK
    );

    protected final List<RecipeType> steelFurnaceItems = List.of(
            RecipeType.COPPER_PLATE,
            RecipeType.IRON_PLATE,
            RecipeType.STEEL_PLATE,
            RecipeType.STONE_BRICK
    );

    protected final List<RecipeType> electricFurnaceItems = List.of(
            RecipeType.COPPER_PLATE,
            RecipeType.IRON_PLATE,
            RecipeType.STEEL_PLATE,
            RecipeType.STONE_BRICK
    );
}

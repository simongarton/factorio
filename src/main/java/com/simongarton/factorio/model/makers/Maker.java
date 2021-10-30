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
            ItemType.ADVANCED_CIRCUIT,
            ItemType.BATTERY_MK2,
            ItemType.BIG_ELECTRIC_POLE,
            ItemType.BLUEPRINT,
            ItemType.BOILER,
            ItemType.BURNER_INSERTER,
            ItemType.BURNER_MINING_DRILL,
            ItemType.CARGO_WAGON,
            ItemType.CHEMICAL_PLANT,
            ItemType.COMBAT_SHOTGUN,
            ItemType.CONSTRUCTION_ROBOT,
            ItemType.COPPER_CABLE,
            ItemType.CRUDE_OIL_BARREL,
            ItemType.DECONSTRUCTION_PLANNER,
            ItemType.DEFENDER_CAPSULE,
            ItemType.DESTROYER_CAPSULE,
            ItemType.DISCHARGE_DEFENSE,
            ItemType.DISTRACTOR_CAPSULE,
            ItemType.EFFECTIVITY_MODULE_2,
            ItemType.EFFECTIVITY_MODULE_3,
            ItemType.EFFECTIVITY_MODULE,
            ItemType.ELECTRIC_ENGINE_UNIT,
            ItemType.ELECTRIC_FURNACE,
            ItemType.ELECTRIC_MINING_DRILL,
            ItemType.ELECTRONIC_CIRCUIT,
            ItemType.EMPTY_BARREL,
            ItemType.ENERGY_SHIELD_MK2,
            ItemType.ENERGY_SHIELD,
            ItemType.ENGINE_UNIT,
            ItemType.EXPLOSIVE_ROCKET,
            ItemType.EXPRESS_SPLITTER,
            ItemType.EXPRESS_TRANSPORT_BELT,
            ItemType.EXPRESS_UNDERGROUND_BELT,
            ItemType.FAST_INSERTER,
            ItemType.FAST_SPLITTER,
            ItemType.FAST_TRANSPORT_BELT,
            ItemType.FAST_UNDERGROUND_BELT,
            ItemType.FLAMETHROWER_AMMO,
            ItemType.FLAMETHROWER,
            ItemType.FLYING_ROBOT_FRAME,
            ItemType.GATE,
            ItemType.GREEN_WIRE,
            ItemType.GUN_TURRET,
            ItemType.HEAVY_ARMOR,
            ItemType.INSERTER,
            ItemType.IRON_AXE,
            ItemType.IRON_CHEST,
            ItemType.IRON_GEAR_WHEEL,
            ItemType.IRON_STICK,
            ItemType.LAB,
            ItemType.LAMP,
            ItemType.LAND_MINE,
            ItemType.LASER_TURRET,
            ItemType.LOGISTIC_CHEST_ACTIVE_PROVIDER,
            ItemType.LOGISTIC_CHEST_PASSIVE_PROVIDER,
            ItemType.LOGISTIC_CHEST_REQUESTER,
            ItemType.LOGISTIC_CHEST_STORAGE,
            ItemType.LOGISTIC_ROBOT,
            ItemType.LONG_HANDED_INSERTER,
            ItemType.MEDIUM_ELECTRIC_POLE,
            ItemType.OFFSHORE_PUMP,
            ItemType.OIL_REFINERY,
            ItemType.PERSONAL_LASER_DEFENSE,
            ItemType.PIERCING_ROUNDS_MAGAZINE,
            ItemType.PIERCING_SHOTGUN_SHELLS,
            ItemType.PIPE,
            ItemType.PISTOL,
            ItemType.POISON_CAPSULE,
            ItemType.PORTABLE_FUSION_REACTOR,
            ItemType.PORTABLE_SOLAR_PANEL,
            ItemType.POWER_ARMOR_MK2,
            ItemType.POWER_ARMOR,
            ItemType.PROCESSING_UNIT,
            ItemType.PRODUCTIVITY_MODULE_2,
            ItemType.PRODUCTIVITY_MODULE_3,
            ItemType.PRODUCTIVITY_MODULE,
            ItemType.PUMPJACK,
            ItemType.RADAR,
            ItemType.RAIL_SIGNAL,
            ItemType.RED_WIRE,
            ItemType.REPAIR_PACK,
            ItemType.ROBOPORT,
            ItemType.ROCKET_LAUNCHER,
            ItemType.ROCKET,
            ItemType.SCIENCE_PACK_1,
            ItemType.SCIENCE_PACK_2,
            ItemType.SCIENCE_PACK_3,
            ItemType.SHOTGUN_SHELLS,
            ItemType.SHOTGUN,
            ItemType.SLOWDOWN_CAPSULE,
            ItemType.SMALL_ELECTRIC_POLE,
            ItemType.SOLAR_PANEL,
            ItemType.SPEED_MODULE_2,
            ItemType.SPEED_MODULE_3,
            ItemType.SPEED_MODULE,
            ItemType.SPLITTER,
            ItemType.STEAM_ENGINE,
            ItemType.STEEL_AXE,
            ItemType.STEEL_CHEST,
            ItemType.STEEL_FURNACE,
            ItemType.STONE_FURNACE,
            ItemType.STONE_WALL,
            ItemType.STORAGE_TANK,
            ItemType.SUBMACHINE_GUN,
            ItemType.SUBSTATION,
            ItemType.TANK,
            ItemType.TRAIN_STOP,
            ItemType.TRANSPORT_BELT,
            ItemType.UNDERGROUND_BELT,
            ItemType.WOOD,
            ItemType.WOODEN_CHEST,

            // these had no home
            ItemType.ACCUMULATOR,
            ItemType.ARITHMETIC_COMBINATOR,
            ItemType.ARTILLERY_SHELL,
            ItemType.ARTILLERY_TARGETING_REMOTE,
            ItemType.ARTILLERY_TURRET,
            ItemType.ARTILLERY_WAGON,
            ItemType.ASSEMBLING_MACHINE_1,
            ItemType.ASSEMBLING_MACHINE_2,
            ItemType.ASSEMBLING_MACHINE_3,
            ItemType.ATOMIC_BOMB,
            ItemType.BATTERY_MK1,
            ItemType.BEACON,
            ItemType.BLUEPRINT_BOOK,
            ItemType.LOGISTIC_CHEST_BUFFER,
            ItemType.CANNON_SHELL,
            ItemType.CAR,
            ItemType.CENTRIFUGE,
            ItemType.CLIFF_EXPLOSIVES,
            ItemType.CLUSTER_GRENADE,
            ItemType.CONCRETE,
            ItemType.CONSTANT_COMBINATOR,
            ItemType.CRUDE_OIL,
            ItemType.DECIDER_COMBINATOR,
            ItemType.DISCHARGE_DEFENSE_REMOTE,
            ItemType.EXOSKELETON,
            ItemType.EXPLOSIVE_CANNON_SHELL,
            ItemType.EXPLOSIVE_URANIUM_CANNON_SHELL,
            ItemType.FILTER_INSERTER,
            ItemType.FIREARM_MAGAZINE,
            ItemType.FLAMETHROWER_TURRET,
            ItemType.FLUID_WAGON,
            ItemType.GRENADE,
            ItemType.HAZARD_CONCRETE,
            ItemType.HEAT_EXCHANGER,
            ItemType.HEAT_PIPE,
            ItemType.HEAVY_OIL_BARREL,
            ItemType.HIGH_TECH_SCIENCE_PACK,
            ItemType.KOVAREX_ENRICHMENT_PROCESS,
            ItemType.LANDFILL,
            ItemType.LIGHT_ARMOR,
            ItemType.LIGHT_OIL_BARREL,
            ItemType.LOCOMOTIVE,
            ItemType.LOW_DENSITY_STRUCTURE,
            ItemType.LUBRICANT_BARREL,
            ItemType.MILITARY_SCIENCE_PACK,
            ItemType.MODULAR_ARMOR,
            ItemType.NIGHTVISION,
            ItemType.NUCLEAR_FUEL,
            ItemType.NUCLEAR_FUEL_REPROCESSING,
            ItemType.NUCLEAR_REACTOR,
            ItemType.PERSONAL_ROBOPORT,
            ItemType.PERSONAL_ROBOPORT_MK2,
            ItemType.PETROLEUM_GAS_BARREL,
            ItemType.PIPE_TO_GROUND,
            ItemType.POWER_SWITCH,
            ItemType.PRODUCTION_SCIENCE_PACK,
            ItemType.PROGRAMMABLE_SPEAKER,
            ItemType.PUMP,
            ItemType.RAIL,
            ItemType.RAIL_CHAIN_SIGNAL,
            ItemType.REFINED_CONCRETE,
            ItemType.REFINED_HAZARD_CONCRETE,
            ItemType.ROCKET_CONTROL_UNIT,
            ItemType.ROCKET_FUEL,
            ItemType.ROCKET_PART,
            ItemType.ROCKET_SILO,
            ItemType.SATELLITE,
            ItemType.SPACE_SCIENCE_PACK,
            ItemType.STACK_FILTER_INSERTER,
            ItemType.STACK_INSERTER,
            ItemType.STEAM_TURBINE,
            ItemType.SULFURIC_ACID_BARREL,
            ItemType.URANIUM_CANNON_SHELL,
            ItemType.URANIUM_FUEL_CELL,
            ItemType.URANIUM_ORE,
            ItemType.URANIUM_PROCESSING,
            ItemType.URANIUM_ROUNDS_MAGAZINE,
            ItemType.URANIUM_235,
            ItemType.URANIUM_238,
            ItemType.USED_UP_URANIUM_FUEL_CELL,
            ItemType.WATER_BARREL
    );

    protected final List<ItemType> assembler2AdditionalItems = List.of(
            ItemType.BATTERY
    );

    protected final List<ItemType> assembler3AdditionalItems = List.of(
    );

    protected final List<ItemType> chemicalPlantItems = List.of(
            ItemType.LUBRICANT,
            ItemType.SULFUR,
            ItemType.SULFURIC_ACID,
            ItemType.PLASTIC_BAR,
            ItemType.EXPLOSIVES,
            ItemType.BATTERY,
            ItemType.SOLID_FUEL
    );

    protected final List<ItemType> oilRefineryItems = List.of(
            ItemType.HEAVY_OIL,
            ItemType.LIGHT_OIL,
            ItemType.PETROLEUM_GAS
    );

    protected final List<ItemType> stoneFurnaceItems = List.of(
            ItemType.COPPER_PLATE,
            ItemType.IRON_PLATE,
            ItemType.STEEL_PLATE,
            ItemType.STONE_BRICK
    );

    protected final List<ItemType> steelFurnaceItems = List.of(
            ItemType.COPPER_PLATE,
            ItemType.IRON_PLATE,
            ItemType.STEEL_PLATE,
            ItemType.STONE_BRICK
    );

    protected final List<ItemType> electricFurnaceItems = List.of(
            ItemType.COPPER_PLATE,
            ItemType.IRON_PLATE,
            ItemType.STEEL_PLATE,
            ItemType.STONE_BRICK
    );
}

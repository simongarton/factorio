package com.simongarton.factorio.model.obsolete;

import com.google.gson.annotations.SerializedName;
import com.simongarton.factorio.exceptions.ItemNotFoundException;

import java.util.Arrays;

public enum ItemType {

    @SerializedName(value = "accumulator")
    ACCUMULATOR("accumulator", "Accumulator"),
    @SerializedName(value = "logistic-chest-active-provider")
    LOGISTIC_CHEST_ACTIVE_PROVIDER("logistic-chest-active-provider", "Active provider chest"),
    @SerializedName(value = "advanced-circuit")
    ADVANCED_CIRCUIT("advanced-circuit", "Advanced circuit"),
    @SerializedName(value = "arithmetic-combinator")
    ARITHMETIC_COMBINATOR("arithmetic-combinator", "Arithmetic combinator"),
    @SerializedName(value = "artillery-shell")
    ARTILLERY_SHELL("artillery-shell", "Artillery shell"),
    @SerializedName(value = "artillery-targeting-remote")
    ARTILLERY_TARGETING_REMOTE("artillery-targeting-remote", "Artillery targeting remote"),
    @SerializedName(value = "artillery-turret")
    ARTILLERY_TURRET("artillery-turret", "Artillery turret"),
    @SerializedName(value = "artillery-wagon")
    ARTILLERY_WAGON("artillery-wagon", "Artillery wagon"),
    @SerializedName(value = "assembling-machine-1")
    ASSEMBLING_MACHINE_1("assembling-machine-1", "Assembling machine 1"),
    @SerializedName(value = "assembling-machine-2")
    ASSEMBLING_MACHINE_2("assembling-machine-2", "Assembling machine 2"),
    @SerializedName(value = "assembling-machine-3")
    ASSEMBLING_MACHINE_3("assembling-machine-3", "Assembling machine 3"),
    @SerializedName(value = "atomic-bomb")
    ATOMIC_BOMB("atomic-bomb", "Atomic bomb"),
    @SerializedName(value = "battery")
    BATTERY("battery", "Battery"),
    @SerializedName(value = "battery-mk1")
    BATTERY_MK1("battery-mk1", "Battery MK1"),
    @SerializedName(value = "battery-mk2")
    BATTERY_MK2("battery-mk2", "Battery MK2"),
    @SerializedName(value = "beacon")
    BEACON("beacon", "Beacon"),
    @SerializedName(value = "big-electric-pole")
    BIG_ELECTRIC_POLE("big-electric-pole", "Big electric pole"),
    @SerializedName(value = "blueprint")
    BLUEPRINT("blueprint", "Blueprint"),
    @SerializedName(value = "blueprint-book")
    BLUEPRINT_BOOK("blueprint-book", "Blueprint book"),
    @SerializedName(value = "boiler")
    BOILER("boiler", "Boiler"),
    @SerializedName(value = "logistic-chest-buffer")
    LOGISTIC_CHEST_BUFFER("logistic-chest-buffer", "Buffer chest"),
    @SerializedName(value = "burner-inserter")
    BURNER_INSERTER("burner-inserter", "Burner inserter"),
    @SerializedName(value = "burner-mining-drill")
    BURNER_MINING_DRILL("burner-mining-drill", "Burner mining drill"),
    @SerializedName(value = "cannon-shell")
    CANNON_SHELL("cannon-shell", "Cannon shell"),
    @SerializedName(value = "car")
    CAR("car", "Car"),
    @SerializedName(value = "cargo-wagon")
    CARGO_WAGON("cargo-wagon", "Cargo wagon"),
    @SerializedName(value = "centrifuge")
    CENTRIFUGE("centrifuge", "Centrifuge"),
    @SerializedName(value = "chemical-plant")
    CHEMICAL_PLANT("chemical-plant", "Chemical plant"),
    @SerializedName(value = "cliff-explosives")
    CLIFF_EXPLOSIVES("cliff-explosives", "Cliff explosives"),
    @SerializedName(value = "cluster-grenade")
    CLUSTER_GRENADE("cluster-grenade", "Cluster grenade"),
    @SerializedName(value = "coal")
    COAL("coal", "Coal"),
    @SerializedName(value = "combat-shotgun")
    COMBAT_SHOTGUN("combat-shotgun", "Combat shotgun"),
    @SerializedName(value = "concrete")
    CONCRETE("concrete", "Concrete"),
    @SerializedName(value = "constant-combinator")
    CONSTANT_COMBINATOR("constant-combinator", "Constant combinator"),
    @SerializedName(value = "construction-robot")
    CONSTRUCTION_ROBOT("construction-robot", "Construction robot"),
    @SerializedName(value = "copper-cable")
    COPPER_CABLE("copper-cable", "Copper cable"),
    @SerializedName(value = "copper-ore")
    COPPER_ORE("copper-ore", "Copper ore"),
    @SerializedName(value = "copper-plate")
    COPPER_PLATE("copper-plate", "Copper plate"),
    @SerializedName(value = "crude-oil")
    CRUDE_OIL("crude-oil", "Crude oil"),
    @SerializedName(value = "crude-oil-barrel")
    CRUDE_OIL_BARREL("crude-oil-barrel", "Crude oil barrel"),
    @SerializedName(value = "decider-combinator")
    DECIDER_COMBINATOR("decider-combinator", "Decider combinator"),
    @SerializedName(value = "deconstruction-planner")
    DECONSTRUCTION_PLANNER("deconstruction-planner", "Deconstruction planner"),
    @SerializedName(value = "defender-capsule")
    DEFENDER_CAPSULE("defender-capsule", "Defender capsule"),
    @SerializedName(value = "destroyer-capsule")
    DESTROYER_CAPSULE("destroyer-capsule", "Destroyer capsule"),
    @SerializedName(value = "discharge-defense")
    DISCHARGE_DEFENSE("discharge-defense", "Discharge defense"),
    @SerializedName(value = "discharge-defense-remote")
    DISCHARGE_DEFENSE_REMOTE("discharge-defense-remote", "Discharge defense remote"),
    @SerializedName(value = "distractor-capsule")
    DISTRACTOR_CAPSULE("distractor-capsule", "Distractor capsule"),
    @SerializedName(value = "effectivity-module")
    EFFECTIVITY_MODULE("effectivity-module", "Efficiency module"),
    @SerializedName(value = "effectivity-module-2")
    EFFECTIVITY_MODULE_2("effectivity-module-2", "Efficiency module 2"),
    @SerializedName(value = "effectivity-module-3")
    EFFECTIVITY_MODULE_3("effectivity-module-3", "Efficiency module 3"),
    @SerializedName(value = "electric-engine-unit")
    ELECTRIC_ENGINE_UNIT("electric-engine-unit", "Electric engine unit"),
    @SerializedName(value = "electric-furnace")
    ELECTRIC_FURNACE("electric-furnace", "Electric furnace"),
    @SerializedName(value = "electric-mining-drill")
    ELECTRIC_MINING_DRILL("electric-mining-drill", "Electric mining drill"),
    @SerializedName(value = "electronic-circuit")
    ELECTRONIC_CIRCUIT("electronic-circuit", "Electronic circuit"),
    @SerializedName(value = "empty-barrel")
    EMPTY_BARREL("empty-barrel", "Empty barrel"),
    @SerializedName(value = "energy-shield")
    ENERGY_SHIELD("energy-shield", "Energy shield"),
    @SerializedName(value = "energy-shield-mk2")
    ENERGY_SHIELD_MK2("energy-shield-mk2", "Energy shield MK2"),
    @SerializedName(value = "engine-unit")
    ENGINE_UNIT("engine-unit", "Engine unit"),
    @SerializedName(value = "exoskeleton")
    EXOSKELETON("exoskeleton", "Exoskeleton"),
    @SerializedName(value = "explosive-cannon-shell")
    EXPLOSIVE_CANNON_SHELL("explosive-cannon-shell", "Explosive cannon shell"),
    @SerializedName(value = "explosive-rocket")
    EXPLOSIVE_ROCKET("explosive-rocket", "Explosive rocket"),
    @SerializedName(value = "explosive-uranium-cannon-shell")
    EXPLOSIVE_URANIUM_CANNON_SHELL("explosive-uranium-cannon-shell", "Explosive uranium cannon shell"),
    @SerializedName(value = "explosives")
    EXPLOSIVES("explosives", "Explosives"),
    @SerializedName(value = "express-splitter")
    EXPRESS_SPLITTER("express-splitter", "Express splitter"),
    @SerializedName(value = "express-transport-belt")
    EXPRESS_TRANSPORT_BELT("express-transport-belt", "Express transport belt"),
    @SerializedName(value = "express-underground-belt")
    EXPRESS_UNDERGROUND_BELT("express-underground-belt", "Express underground belt"),
    @SerializedName(value = "fast-inserter")
    FAST_INSERTER("fast-inserter", "Fast inserter"),
    @SerializedName(value = "fast-splitter")
    FAST_SPLITTER("fast-splitter", "Fast splitter"),
    @SerializedName(value = "fast-transport-belt")
    FAST_TRANSPORT_BELT("fast-transport-belt", "Fast transport belt"),
    @SerializedName(value = "fast-underground-belt")
    FAST_UNDERGROUND_BELT("fast-underground-belt", "Fast underground belt"),
    @SerializedName(value = "filter-inserter")
    FILTER_INSERTER("filter-inserter", "Filter inserter"),
    @SerializedName(value = "firearm-magazine")
    FIREARM_MAGAZINE("firearm-magazine", "Firearm magazine"),
    @SerializedName(value = "flamethrower")
    FLAMETHROWER("flamethrower", "Flamethrower"),
    @SerializedName(value = "flamethrower-ammo")
    FLAMETHROWER_AMMO("flamethrower-ammo", "Flamethrower ammo"),
    @SerializedName(value = "flamethrower-turret")
    FLAMETHROWER_TURRET("flamethrower-turret", "Flamethrower turret"),
    @SerializedName(value = "fluid-wagon")
    FLUID_WAGON("fluid-wagon", "Fluid wagon"),
    @SerializedName(value = "flying-robot-frame")
    FLYING_ROBOT_FRAME("flying-robot-frame", "Flying robot frame"),
    @SerializedName(value = "gate")
    GATE("gate", "Gate"),
    @SerializedName(value = "green-wire")
    GREEN_WIRE("green-wire", "Green wire"),
    @SerializedName(value = "grenade")
    GRENADE("grenade", "Grenade"),
    @SerializedName(value = "gun-turret")
    GUN_TURRET("gun-turret", "Gun turret"),
    @SerializedName(value = "hazard-concrete")
    HAZARD_CONCRETE("hazard-concrete", "Hazard concrete"),
    @SerializedName(value = "heat-exchanger")
    HEAT_EXCHANGER("heat-exchanger", "Heat exchanger"),
    @SerializedName(value = "heat-pipe")
    HEAT_PIPE("heat-pipe", "Heat pipe"),
    @SerializedName(value = "heavy-armor")
    HEAVY_ARMOR("heavy-armor", "Heavy armor"),
    @SerializedName(value = "heavy-oil")
    HEAVY_OIL("heavy-oil", "Heavy oil"),
    @SerializedName(value = "heavy-oil-barrel")
    HEAVY_OIL_BARREL("heavy-oil-barrel", "Heavy oil barrel"),
    @SerializedName(value = "high-tech-science-pack")
    HIGH_TECH_SCIENCE_PACK("high-tech-science-pack", "High tech science pack"),
    @SerializedName(value = "inserter")
    INSERTER("inserter", "Inserter"),
    @SerializedName(value = "iron-axe")
    IRON_AXE("iron-axe", "Iron axe"),
    @SerializedName(value = "iron-chest")
    IRON_CHEST("iron-chest", "Iron chest"),
    @SerializedName(value = "iron-gear-wheel")
    IRON_GEAR_WHEEL("iron-gear-wheel", "Iron gear wheel"),
    @SerializedName(value = "iron-ore")
    IRON_ORE("iron-ore", "Iron ore"),
    @SerializedName(value = "iron-plate")
    IRON_PLATE("iron-plate", "Iron plate"),
    @SerializedName(value = "iron-stick")
    IRON_STICK("iron-stick", "Iron stick"),
    @SerializedName(value = "kovarex-enrichment-process")
    KOVAREX_ENRICHMENT_PROCESS("kovarex-enrichment-process", "Kovarex enrichment process"),
    @SerializedName(value = "lab")
    LAB("lab", "Lab"),
    @SerializedName(value = "lamp")
    LAMP("lamp", "Lamp"),
    @SerializedName(value = "land-mine")
    LAND_MINE("land-mine", "Land mine"),
    @SerializedName(value = "landfill")
    LANDFILL("landfill", "Landfill"),
    @SerializedName(value = "laser-turret")
    LASER_TURRET("laser-turret", "Laser turret"),
    @SerializedName(value = "light-armor")
    LIGHT_ARMOR("light-armor", "Light armor"),
    @SerializedName(value = "light-oil")
    LIGHT_OIL("light-oil", "Light oil"),
    @SerializedName(value = "light-oil-barrel")
    LIGHT_OIL_BARREL("light-oil-barrel", "Light oil barrel"),
    @SerializedName(value = "locomotive")
    LOCOMOTIVE("locomotive", "Locomotive"),
    @SerializedName(value = "logistic-robot")
    LOGISTIC_ROBOT("logistic-robot", "Logistic robot"),
    @SerializedName(value = "long-handed-inserter")
    LONG_HANDED_INSERTER("long-handed-inserter", "Long handed inserter"),
    @SerializedName(value = "low-density-structure")
    LOW_DENSITY_STRUCTURE("low-density-structure", "Low density structure"),
    @SerializedName(value = "lubricant")
    LUBRICANT("lubricant", "Lubricant"),
    @SerializedName(value = "lubricant-barrel")
    LUBRICANT_BARREL("lubricant-barrel", "Lubricant barrel"),
    @SerializedName(value = "medium-electric-pole")
    MEDIUM_ELECTRIC_POLE("medium-electric-pole", "Medium electric pole"),
    @SerializedName(value = "military-science-pack")
    MILITARY_SCIENCE_PACK("military-science-pack", "Military science pack"),
    @SerializedName(value = "modular-armor")
    MODULAR_ARMOR("modular-armor", "Modular armor"),
    @SerializedName(value = "nightvision")
    NIGHTVISION("nightvision", "Nightvision"),
    @SerializedName(value = "nuclear-fuel")
    NUCLEAR_FUEL("nuclear-fuel", "Nuclear fuel"),
    @SerializedName(value = "nuclear-fuel-reprocessing")
    NUCLEAR_FUEL_REPROCESSING("nuclear-fuel-reprocessing", "Nuclear fuel reprocessing"),
    @SerializedName(value = "nuclear-reactor")
    NUCLEAR_REACTOR("nuclear-reactor", "Nuclear reactor"),
    @SerializedName(value = "offshore-pump")
    OFFSHORE_PUMP("offshore-pump", "Offshore pump"),
    @SerializedName(value = "oil-refinery")
    OIL_REFINERY("oil-refinery", "Oil refinery"),
    @SerializedName(value = "logistic-chest-passive-provider")
    LOGISTIC_CHEST_PASSIVE_PROVIDER("logistic-chest-passive-provider", "Passive provider chest"),
    @SerializedName(value = "personal-laser-defense")
    PERSONAL_LASER_DEFENSE("personal-laser-defense", "Personal laser defense"),
    @SerializedName(value = "personal-roboport")
    PERSONAL_ROBOPORT("personal-roboport", "Personal roboport"),
    @SerializedName(value = "personal-roboport-mk2")
    PERSONAL_ROBOPORT_MK2("personal-roboport-mk2", "Personal roboport MK2"),
    @SerializedName(value = "petroleum-gas")
    PETROLEUM_GAS("petroleum-gas", "Petroleum gas"),
    @SerializedName(value = "petroleum-gas-barrel")
    PETROLEUM_GAS_BARREL("petroleum-gas-barrel", "Petroleum gas barrel"),
    @SerializedName(value = "piercing-rounds-magazine")
    PIERCING_ROUNDS_MAGAZINE("piercing-rounds-magazine", "Piercing rounds magazine"),
    @SerializedName(value = "piercing-shotgun-shells")
    PIERCING_SHOTGUN_SHELLS("piercing-shotgun-shells", "Piercing shotgun shells"),
    @SerializedName(value = "pipe")
    PIPE("pipe", "Pipe"),
    @SerializedName(value = "pipe-to-ground")
    PIPE_TO_GROUND("pipe-to-ground", "Pipe to ground"),
    @SerializedName(value = "pistol")
    PISTOL("pistol", "Pistol"),
    @SerializedName(value = "plastic-bar")
    PLASTIC_BAR("plastic-bar", "Plastic bar"),
    @SerializedName(value = "poison-capsule")
    POISON_CAPSULE("poison-capsule", "Poison capsule"),
    @SerializedName(value = "portable-fusion-reactor")
    PORTABLE_FUSION_REACTOR("portable-fusion-reactor", "Portable fusion reactor"),
    @SerializedName(value = "portable-solar-panel")
    PORTABLE_SOLAR_PANEL("portable-solar-panel", "Portable solar panel"),
    @SerializedName(value = "power-armor")
    POWER_ARMOR("power-armor", "Power armor"),
    @SerializedName(value = "power-armor-mk2")
    POWER_ARMOR_MK2("power-armor-mk2", "Power armor MK2"),
    @SerializedName(value = "power-switch")
    POWER_SWITCH("power-switch", "Power switch"),
    @SerializedName(value = "processing-unit")
    PROCESSING_UNIT("processing-unit", "Processing unit"),
    @SerializedName(value = "production-science-pack")
    PRODUCTION_SCIENCE_PACK("production-science-pack", "Production science pack"),
    @SerializedName(value = "productivity-module")
    PRODUCTIVITY_MODULE("productivity-module", "Productivity module"),
    @SerializedName(value = "productivity-module-2")
    PRODUCTIVITY_MODULE_2("productivity-module-2", "Productivity module 2"),
    @SerializedName(value = "productivity-module-3")
    PRODUCTIVITY_MODULE_3("productivity-module-3", "Productivity module 3"),
    @SerializedName(value = "programmable-speaker")
    PROGRAMMABLE_SPEAKER("programmable-speaker", "Programmable speaker"),
    @SerializedName(value = "pump")
    PUMP("pump", "Pump"),
    @SerializedName(value = "pumpjack")
    PUMPJACK("pumpjack", "Pumpjack"),
    @SerializedName(value = "radar")
    RADAR("radar", "Radar"),
    @SerializedName(value = "rail")
    RAIL("rail", "Rail"),
    @SerializedName(value = "rail-chain-signal")
    RAIL_CHAIN_SIGNAL("rail-chain-signal", "Rail chain signal"),
    @SerializedName(value = "rail-signal")
    RAIL_SIGNAL("rail-signal", "Rail signal"),
    @SerializedName(value = "raw-fish")
    RAW_FISH("raw-fish", "Raw fish"),
    @SerializedName(value = "raw-wood")
    RAW_WOOD("raw-wood", "Raw wood"),
    @SerializedName(value = "red-wire")
    RED_WIRE("red-wire", "Red wire"),
    @SerializedName(value = "refined-concrete")
    REFINED_CONCRETE("refined-concrete", "Refined concrete"),
    @SerializedName(value = "refined-hazard-concrete")
    REFINED_HAZARD_CONCRETE("refined-hazard-concrete", "Refined hazard concrete"),
    @SerializedName(value = "repair-pack")
    REPAIR_PACK("repair-pack", "Repair pack"),
    @SerializedName(value = "logistic-chest-requester")
    LOGISTIC_CHEST_REQUESTER("logistic-chest-requester", "Requester chest"),
    @SerializedName(value = "roboport")
    ROBOPORT("roboport", "Roboport"),
    @SerializedName(value = "rocket")
    ROCKET("rocket", "Rocket"),
    @SerializedName(value = "rocket-control-unit")
    ROCKET_CONTROL_UNIT("rocket-control-unit", "Rocket control unit"),
    @SerializedName(value = "rocket-fuel")
    ROCKET_FUEL("rocket-fuel", "Rocket fuel"),
    @SerializedName(value = "rocket-launcher")
    ROCKET_LAUNCHER("rocket-launcher", "Rocket launcher"),
    @SerializedName(value = "rocket-part")
    ROCKET_PART("rocket-part", "Rocket part"),
    @SerializedName(value = "rocket-silo")
    ROCKET_SILO("rocket-silo", "Rocket silo"),
    @SerializedName(value = "satellite")
    SATELLITE("satellite", "Satellite"),
    @SerializedName(value = "science-pack-1")
    SCIENCE_PACK_1("science-pack-1", "Science pack 1"),
    @SerializedName(value = "science-pack-2")
    SCIENCE_PACK_2("science-pack-2", "Science pack 2"),
    @SerializedName(value = "science-pack-3")
    SCIENCE_PACK_3("science-pack-3", "Science pack 3"),
    @SerializedName(value = "shotgun")
    SHOTGUN("shotgun", "Shotgun"),
    @SerializedName(value = "shotgun-shells")
    SHOTGUN_SHELLS("shotgun-shells", "Shotgun shells"),
    @SerializedName(value = "slowdown-capsule")
    SLOWDOWN_CAPSULE("slowdown-capsule", "Slowdown capsule"),
    @SerializedName(value = "small-electric-pole")
    SMALL_ELECTRIC_POLE("small-electric-pole", "Small electric pole"),
    @SerializedName(value = "solar-panel")
    SOLAR_PANEL("solar-panel", "Solar panel"),
    @SerializedName(value = "solid-fuel")
    SOLID_FUEL("solid-fuel", "Solid fuel"),
    @SerializedName(value = "space-science-pack")
    SPACE_SCIENCE_PACK("space-science-pack", "Space science pack"),
    @SerializedName(value = "speed-module")
    SPEED_MODULE("speed-module", "Speed module"),
    @SerializedName(value = "speed-module-2")
    SPEED_MODULE_2("speed-module-2", "Speed module 2"),
    @SerializedName(value = "speed-module-3")
    SPEED_MODULE_3("speed-module-3", "Speed module 3"),
    @SerializedName(value = "splitter")
    SPLITTER("splitter", "Splitter"),
    @SerializedName(value = "stack-filter-inserter")
    STACK_FILTER_INSERTER("stack-filter-inserter", "Stack filter inserter"),
    @SerializedName(value = "stack-inserter")
    STACK_INSERTER("stack-inserter", "Stack inserter"),
    @SerializedName(value = "steam")
    STEAM("steam", "Steam"),
    @SerializedName(value = "steam-engine")
    STEAM_ENGINE("steam-engine", "Steam engine"),
    @SerializedName(value = "steam-turbine")
    STEAM_TURBINE("steam-turbine", "Steam turbine"),
    @SerializedName(value = "steel-axe")
    STEEL_AXE("steel-axe", "Steel axe"),
    @SerializedName(value = "steel-chest")
    STEEL_CHEST("steel-chest", "Steel chest"),
    @SerializedName(value = "steel-furnace")
    STEEL_FURNACE("steel-furnace", "Steel furnace"),
    @SerializedName(value = "steel-plate")
    STEEL_PLATE("steel-plate", "Steel plate"),
    @SerializedName(value = "stone")
    STONE("stone", "Stone"),
    @SerializedName(value = "stone-brick")
    STONE_BRICK("stone-brick", "Stone brick"),
    @SerializedName(value = "stone-furnace")
    STONE_FURNACE("stone-furnace", "Stone furnace"),
    @SerializedName(value = "stone-wall")
    STONE_WALL("stone-wall", "Stone wall"),
    @SerializedName(value = "logistic-chest-storage")
    LOGISTIC_CHEST_STORAGE("logistic-chest-storage", "Storage chest"),
    @SerializedName(value = "storage-tank")
    STORAGE_TANK("storage-tank", "Storage tank"),
    @SerializedName(value = "submachine-gun")
    SUBMACHINE_GUN("submachine-gun", "Submachine gun"),
    @SerializedName(value = "substation")
    SUBSTATION("substation", "Substation"),
    @SerializedName(value = "sulfur")
    SULFUR("sulfur", "Sulfur"),
    @SerializedName(value = "sulfuric-acid")
    SULFURIC_ACID("sulfuric-acid", "Sulfuric acid"),
    @SerializedName(value = "sulfuric-acid-barrel")
    SULFURIC_ACID_BARREL("sulfuric-acid-barrel", "Sulfuric acid barrel"),
    @SerializedName(value = "tank")
    TANK("tank", "Tank"),
    @SerializedName(value = "train-stop")
    TRAIN_STOP("train-stop", "Train stop"),
    @SerializedName(value = "transport-belt")
    TRANSPORT_BELT("transport-belt", "Transport belt"),
    @SerializedName(value = "underground-belt")
    UNDERGROUND_BELT("underground-belt", "Underground belt"),
    @SerializedName(value = "uranium-cannon-shell")
    URANIUM_CANNON_SHELL("uranium-cannon-shell", "Uranium cannon shell"),
    @SerializedName(value = "uranium-fuel-cell")
    URANIUM_FUEL_CELL("uranium-fuel-cell", "Uranium fuel cell"),
    @SerializedName(value = "uranium-ore")
    URANIUM_ORE("uranium-ore", "Uranium ore"),
    @SerializedName(value = "uranium-processing")
    URANIUM_PROCESSING("uranium-processing", "Uranium processing"),
    @SerializedName(value = "uranium-rounds-magazine")
    URANIUM_ROUNDS_MAGAZINE("uranium-rounds-magazine", "Uranium rounds magazine"),
    @SerializedName(value = "uranium-235")
    URANIUM_235("uranium-235", "Uranium-235"),
    @SerializedName(value = "uranium-238")
    URANIUM_238("uranium-238", "Uranium-238"),
    @SerializedName(value = "used-up-uranium-fuel-cell")
    USED_UP_URANIUM_FUEL_CELL("used-up-uranium-fuel-cell", "Used up uranium fuel cell"),
    @SerializedName(value = "water")
    WATER("water", "Water"),
    @SerializedName(value = "water-barrel")
    WATER_BARREL("water-barrel", "Water barrel"),
    @SerializedName(value = "wood")
    WOOD("wood", "Wood"),
    @SerializedName(value = "wooden-chest")
    WOODEN_CHEST("wooden-chest", "Wooden chest");

    private String id;
    private String name;

    ItemType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ItemType from(String id) {
        return Arrays.stream(values()).filter(i -> i.getId().equals(id)).findFirst().orElseThrow(() -> new ItemNotFoundException(id));
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.name;
    }
}

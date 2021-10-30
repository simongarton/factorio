package com.simongarton.factorio;

import com.simongarton.factorio.exceptions.MakerNotFoundException;
import com.simongarton.factorio.exceptions.RecipeNotFoundException;
import com.simongarton.factorio.model.*;
import com.simongarton.factorio.model.makers.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Foreman {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final List<Item> items;
    private final Map<ItemType, MakerSlot> makers = new HashMap<>();
    private final Map<String, Double> requiredMakers = new HashMap<>();

    private static final double HALF_YELLOW_BELT = 7.5;
    private static final double YELLOW_BELT = 15;
    private static final double HALF_RED_BELT = 15;
    private static final double RED_BELT = 30;
    private static final double HALF_BLUE_BELT = 22.5;
    private static final double BLUE_BELT = 45;

    public Foreman() {
        this.items = ItemLoader.getItems();
        this.setupDefaultMakers();
    }

    private void setupDefaultMakers() {
        this.makers.put(ItemType.ASSEMBLING_MACHINE_1, new MakerSlot(new Assembler1(), true));
        this.makers.put(ItemType.ASSEMBLING_MACHINE_2, new MakerSlot(new Assembler2(), true));
        this.makers.put(ItemType.ASSEMBLING_MACHINE_3, new MakerSlot(new Assembler3(), true));
        this.makers.put(ItemType.STONE_FURNACE, new MakerSlot(new StoneFurnace(), true));
        this.makers.put(ItemType.STEEL_FURNACE, new MakerSlot(new SteelFurnace(), true));
        this.makers.put(ItemType.ELECTRIC_FURNACE, new MakerSlot(new ElectricFurnace(), true));
        this.makers.put(ItemType.OIL_REFINERY, new MakerSlot(new OilRefinery(), true));
        this.makers.put(ItemType.CHEMICAL_PLANT, new MakerSlot(new ChemicalPlant(), true));
    }

    public void enableMaker(final ItemType itemType) {
        this.makers.get(itemType).available = true;
    }

    public void disableMaker(final ItemType itemType) {
        this.makers.get(itemType).available = false;
    }

    public Docket planForJob(final Job job) {
        final Docket docket = new Docket(job);

        final Item item = this.getItemForItemType(job.getItemType());
        final Recipe recipe = item.getRecipe();
        docket.setRecipe(recipe);
        try {
            final Maker maker = this.getMaker(job.getItemType());
            docket.setMaker(maker);
            if (recipe.getYield() != null) {
                docket.setSingleMakerUnitsPerSecond((recipe.getYield() / recipe.getTime())* maker.getCraftingSpeed());
                docket.setMakersNeeded(job.getUnitsToMake() / docket.getSingleMakerUnitsPerSecond());
            }
        } catch (final MakerNotFoundException mnfe) {

        }
        final List<Ingredient> docketIngredients = new ArrayList<>();
        for (final Ingredient ingredient : recipe.getIngredients()) {
            final Ingredient docketIngredient = new Ingredient();
            docketIngredient.setItemType(ingredient.getItemType());
            docketIngredient.setQuantity(ingredient.getQuantity() * docket.getMakersNeeded());
            docketIngredients.add(docketIngredient);
            final Job subJob = new Job(docketIngredient.getQuantity(), docketIngredient.getItemType());
            docket.getDockets().add(this.planForJob(subJob));
        }
        docket.setIngredients(docketIngredients);
        return docket;
    }

    private Maker getMaker(final ItemType itemType) {
        for (final ItemType makerItemType : this.preferredMakers()) {
            final MakerSlot makerSlot = this.makers.get(makerItemType);
            if (makerSlot == null) {
                continue;
            }
            if (!makerSlot.available) {
                continue;
            }
            if (makerSlot.maker.makes(itemType)) {
                return makerSlot.maker;
            }
        }
        throw new MakerNotFoundException(itemType.getType());
    }

    private List<ItemType> preferredMakers() {
        return List.of(
                ItemType.ASSEMBLING_MACHINE_3,
                ItemType.ASSEMBLING_MACHINE_2,
                ItemType.ASSEMBLING_MACHINE_1,
                ItemType.ELECTRIC_FURNACE,
                ItemType.STEEL_FURNACE,
                ItemType.STONE_FURNACE,
                ItemType.OIL_REFINERY,
                ItemType.CHEMICAL_PLANT
        );
    }

    private Item getItemForItemType(final ItemType itemType) {
        return this.items.stream()
                .filter(i -> i.getItemType().equals(itemType))
                .findFirst().
                orElseThrow(() -> new RecipeNotFoundException(itemType.getId()));
    }

    public void explainDocket(final Docket docket) {
        this.explainDocket(docket, "");
    }

    public void explainDocket(final Docket docket, final String indent) {
        final boolean defaultRecipe = false;
        final Job job = docket.getJob();
        this.logger.info(indent + "Well, what you got here, boss, is a job to make {} of {}.",
                String.format("%3.2f", job.getUnitsToMake()),
                job.getItemType().getType());
        if (docket.getMaker() == null) {
            this.logger.info(indent + "This is a raw material. For {}, you'll need at least a {}",
                    String.format("%3.2f", job.getUnitsToMake()),
                    this.minimumBelt(job.getUnitsToMake()));
        } else {
            this.logger.info(indent + "We make those with a {}, crafting speed {}; that recipe yields {} in {} seconds",
                    docket.getMaker().getItemType(),
                    docket.getMaker().getCraftingSpeed(),
                    docket.getRecipe().getYield(),
                    docket.getRecipe().getTime());
            this.logger.info(indent + "so one maker will make {} per second, so we'll need {} machines.",
                    docket.getSingleMakerUnitsPerSecond(),
                    String.format("%3.2f", docket.getMakersNeeded()));
            if (defaultRecipe) {
                this.logger.info(indent + "For 1 unit, we would need this list of ingredients :");
                for (final Ingredient ingredient : docket.getRecipe().getIngredients()) {
                    this.logger.info(indent + "    {} of {}", String.format("%3.2f", ingredient.getQuantity()), ingredient.getItemType().getType());
                }
            }
            this.logger.info(indent + "For {} units, we will need this list of ingredients :", String.format("%3.2f", job.getUnitsToMake()));
            for (final Ingredient ingredient : docket.getIngredients()) {
                this.logger.info(indent + "    {} of {}", String.format("%3.2f", ingredient.getQuantity()), ingredient.getItemType().getType());
            }
            this.logger.info(indent + "and I'll get Jim to tell you how we'll do that.");
            for (final Docket subDocket : docket.getDockets()) {
                this.explainDocket(subDocket, indent + "  ");
            }
        }
    }

    private String minimumBelt(final double unitsToMake) {
        if (unitsToMake <= HALF_YELLOW_BELT) {
            return "half yellow belt.";
        }
        if (unitsToMake <= YELLOW_BELT) {
            return "full yellow belt / half red belt.";
        }
        if (unitsToMake <= HALF_BLUE_BELT) {
            return "half blue belt.";
        }
        if (unitsToMake <= RED_BELT) {
            return "full red belt.";
        }
        if (unitsToMake <= BLUE_BELT) {
            return "full blue belt.";
        }
        return " ... hmm, that could be a problem.";
    }

    public void bom(final Docket docket) {
        this.requiredMakers.clear();
        this.setupBomForDocket(docket);
        final List<String> keys = new ArrayList<>(this.requiredMakers.keySet());
        keys.sort(Comparator.comparing(String::valueOf));
        for (final String key : keys) {
            final Double value = this.requiredMakers.get(key);
            this.logger.info("{} : {}", key, String.format("%3.2f", value));
        }
    }

    private void setupBomForDocket(final Docket docket) {
        if (docket.getMaker() == null) {
            return;
        }
        final String key = docket.getMaker().getItemType().getType() + " (" + docket.getJob().getItemType().getType() + ")";
        if (this.requiredMakers.containsKey(key)) {
            this.requiredMakers.put(key, this.requiredMakers.get(key) + docket.getMakersNeeded());
        } else {
            this.requiredMakers.put(key, docket.getMakersNeeded());
        }
        for (final Docket subDocket : docket.getDockets()) {
            this.setupBomForDocket(subDocket);
        }
    }

    public void disableFurnaces() {
        this.disableMaker(ItemType.STONE_FURNACE);
        this.disableMaker(ItemType.STEEL_FURNACE);
        this.disableMaker(ItemType.ELECTRIC_FURNACE);
    }

    public void listItems() {
        for (final Item item : this.items) {
            try {
                final Maker maker = this.getMaker(item.getItemType());
                //System.out.println(maker.getItemType().getType());
            } catch (final MakerNotFoundException mnfe) {
                System.out.println("Maker not found for ItemType." + item.getItemType() + ",");
            }
        }
        for (final Item item : this.items) {
//            System.out.println("ItemType." + item.getItemType() + ",");
        }
    }

    @Getter
    @AllArgsConstructor
    private class MakerSlot {

        private Maker maker;
        private boolean available;
    }

    public void dumpGraph() {
        final List<String> lines = new ArrayList<>();
        lines.add("digraph d {");
        lines.add("rankdir=LR;");

        final Set<String> done = new HashSet<>();
        for (final Item item : this.items) {
            final String result = item.getItemType().getType();
            for (final Ingredient ingredient : item.getRecipe().getIngredients()) {
                final String source = ingredient.getItemType().getType();
                final String key = "\"" + source + "\"" + "->" + "\"" + result + "\"";
                if (!(done.contains(key))) {
                    lines.add(key);
                }
                done.add(key);
            }
        }
        lines.add("}");
        final Path path = Paths.get("factorio.dot");
        try {
            Files.write(path, lines);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}

package com.simongarton.factorio;

import com.simongarton.factorio.exceptions.MakerNotFoundException;
import com.simongarton.factorio.exceptions.RecipeNotFoundException;
import com.simongarton.factorio.model.*;
import com.simongarton.factorio.model.makers.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Foreman {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final List<Item> items;
    private final Map<ItemType, MakerSlot> makers = new HashMap<>();

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
        final Maker maker = this.getMaker(job.getItemType());
        docket.setMaker(maker);
        docket.setMakerSpeed(recipe.getYield() * maker.getCraftingSpeed());
        docket.setMakerCount(job.getUnitsToMake() / docket.getMakerSpeed());
        List<Ingredient> docketIngredients = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            Ingredient docketIngredient = new Ingredient();
            docketIngredient.setItemType(ingredient.getItemType());
            docketIngredient.setQuantity(ingredient.getQuantity() * docket.getMakerCount());
            docketIngredients.add(docketIngredient);
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
                ItemType.ASSEMBLING_MACHINE_1,
                ItemType.ASSEMBLING_MACHINE_2,
                ItemType.ASSEMBLING_MACHINE_3,
                ItemType.STONE_FURNACE,
                ItemType.STEEL_FURNACE,
                ItemType.ELECTRIC_FURNACE,
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
        final Job job = docket.getJob();
        this.logger.info("Well, what you got here, boss, is a job to make {} of {}.",
                job.getUnitsToMake(),
                job.getItemType().getType());
        this.logger.info("We make those with a {}, which make {} per second, so we'll need {} machines.",
                docket.getMaker().getItemType(),
                docket.getMakerSpeed(),
                String.format("%3.2f", docket.getMakerCount()));
        this.logger.info("For 1 unit, we would need this list of ingredients :");
        for (Ingredient ingredient : docket.getRecipe().getIngredients()) {
            this.logger.info("    {} of {}",String.format("%3.2f", ingredient.getQuantity()), ingredient.getItemType().getType());
        }
        this.logger.info("For {} units, we will need this list of ingredients :",job.getUnitsToMake());
        for (Ingredient ingredient : docket.getIngredients()) {
            this.logger.info("    {} of {}",String.format("%3.2f", ingredient.getQuantity()), ingredient.getItemType().getType());
        }
    }

    @Getter
    @AllArgsConstructor
    private class MakerSlot {

        private Maker maker;
        private boolean available;
    }
}

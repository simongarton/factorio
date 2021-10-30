package com.simongarton.factorio.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Recipe {

    /*

    localised_name is hard. Most of the time it's a single element string ...

    "localised_name" : [
      "entity-name.assembling-machine-1"
    ],

    ... but then there's this - an array of a String, then another array of a String ?!

    "localised_name" : [
      "recipe-name.fill-barrel",
      [
        "fluid-name.crude-oil"
      ]
    ],

     */

    private String name;
    @SerializedName(value = "localised_name")
    private Object localisedName;
    private String category;
    private String order;
    private Group group;
    private Group subgroup;
    private boolean enabled;
    private boolean hidden;
    @SerializedName(value = "hidden_from_player_crafting;")
    private boolean hiddenFromPlayerCrafting;
    @SerializedName(value = "emissions_multiplier;")
    private double emissionsMultiplier;
    private double energy;
    private Ingredient[] ingredients;
    private Product[] products;
    @SerializedName(value = "main_product;")
    private Product mainProduct;

    private Double time;
    private Integer yield;

    @Data
    public static final class Group {

        private String name;
        private String type;
    }

    @Data
    public static final class Ingredient {

        private String name;
        private String type;
        private int amount;
    }

    @Data
    public static final class Product {

        private String name;
        private String type;
        private double probability;
        private int amount;
    }
}

package com.simongarton.factorio.model;

import com.google.gson.annotations.SerializedName;
import com.simongarton.factorio.model.makers.Maker;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Recipe {

    // this is a recipe to make (some of) an Item. It will tell me what it's made with,
    // how long it will take (seconds) and importantly it's yield - some recipes produce more
    // than 1 of the item.
    // It will specify the ingredients needed (ItemType + quantity); and for each ingredient will
    // provide a recipe.

    private Maker makeWith;
    private ItemType itemType;
    private Double time;
    private Double yield;
    private List<Ingredient> ingredients;
    private Map<ItemType, Recipe> recipes = new HashMap<>();

}

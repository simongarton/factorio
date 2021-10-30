package com.simongarton.factorio.model.obsolete;

import com.simongarton.factorio.model.makers.Maker;
import lombok.Data;

import java.util.List;

@Data
public class Recipe {

    // this is a recipe to make (some of) an Item. It will tell me what it's made with,
    // how long it will take (seconds) and importantly it's yield - some recipes produce more
    // than 1 of the item.
    // It will specify the ingredients needed (ItemType + quantity).

    private Maker maker;
    private ItemType itemType;
    private Double time;
    private Double yield;
    private List<Ingredient> ingredients;
}

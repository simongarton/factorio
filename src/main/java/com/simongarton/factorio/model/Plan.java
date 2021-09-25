package com.simongarton.factorio.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Plan {

    private ItemType itemType; // this is what I want to make
    private double quantity; // this is how many (per second) I want to make. 1EC  - 0.5 seconds; 3 cables is 1.5
    private Recipe recipe; // this is how I make them - and this is a STANDARD recipe
    private double recipeFactor; // this is how many of the STANDARD recipes I need to make, given my quantity. A copper cable takes 0.5 seconds so factor is 3.
    private Map<ItemType, Plan> ingredientPlans = new HashMap<>(); // for each ingredient in the recipe, how am I going to make ENOUGH of it

    public Plan(final ItemType itemType, final double quantity) {
        this.itemType = itemType;
        this.quantity = quantity;
    }
}

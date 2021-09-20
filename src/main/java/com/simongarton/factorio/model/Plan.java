package com.simongarton.factorio.model;

import lombok.Data;

@Data
public class Plan {

    private ItemType itemType;
    private double quantity;
    private Recipe recipe;
    private double recipeFactor;

    public Plan(final ItemType itemType, final double quantity) {
        this.itemType = itemType;
        this.quantity = quantity;
    }
}

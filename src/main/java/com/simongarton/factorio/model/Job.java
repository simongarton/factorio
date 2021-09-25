package com.simongarton.factorio.model;

import lombok.Data;

@Data
public class Job {

    private double unitsToMake;
    private ItemType itemType;

    public Job(double unitsToMake, ItemType itemType) {
        this.unitsToMake = unitsToMake;
        this.itemType = itemType;
    }
}

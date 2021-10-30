package com.simongarton.factorio.model;

import lombok.Data;

import java.util.Map;

@Data
public class Plan {

    private ItemType makerType;
    private double makerCount;
    private Map<ItemType, Double> ingredients;
}

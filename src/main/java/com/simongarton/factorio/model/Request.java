package com.simongarton.factorio.model;

import lombok.Data;

@Data
public class Request {

    private ItemType itemType;
    private double quantity;
    private RecursiveRecipe recipe;

}

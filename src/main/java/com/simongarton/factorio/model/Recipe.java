package com.simongarton.factorio.model;

import lombok.Data;

import java.util.List;

@Data
public class Recipe {

    // doubles ?
    private Double time;
    private Double yield;
    private List<Ingredient> ingredients;

}

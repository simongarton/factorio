package com.simongarton.factorio.model;

import com.simongarton.factorio.model.makers.Maker;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Docket {

    private Job job;
    private Maker maker;
    private Recipe recipe;
    private double singleMakerUnitsPerSecond;
    private double makersNeeded;
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<Docket> dockets = new ArrayList<>();

    public Docket(Job job) {
        this.job = job;
    }
}

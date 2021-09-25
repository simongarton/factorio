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
    private double makerSpeed;
    private double makerCount;
    private List<Ingredient> ingredients = new ArrayList<>();

    public Docket(Job job) {
        this.job = job;
    }
}
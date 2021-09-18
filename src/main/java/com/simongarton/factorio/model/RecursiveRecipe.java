package com.simongarton.factorio.model;

import com.simongarton.factorio.model.makers.Maker;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecursiveRecipe {

    private ItemType item;
    private Double time;
    private Double yield;
    private Double need;
    private Maker makeWith;
    private List<Ingredient> ingredients;
    private List<RecursiveRecipe> recipes = new ArrayList<>();

    public String getDetails() {
        if (this.time == null) {
            return this.item.getType() + " (need " + this.need + ")";
        }
        if (this.makeWith == null) {
            return this.item.getType() + " (need " + this.need + ", yield " + this.yield + " in " + this.time + ")";
        }
        return this.item.getType() + " (need " + this.need + ", yield " + this.yield + " by " + this.makeWith.getItemType().getType() + " in " + this.time + ")";
    }
}

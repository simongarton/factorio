package com.simongarton.factorio.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Ingredient {

    @SerializedName(value = "id")
    private ItemType itemType;
    private double amount;
}

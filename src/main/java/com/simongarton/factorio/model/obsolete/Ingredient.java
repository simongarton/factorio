package com.simongarton.factorio.model.obsolete;

import com.google.gson.annotations.SerializedName;
import com.simongarton.factorio.model.obsolete.ItemType;
import lombok.Data;

@Data
public class Ingredient {

    @SerializedName(value = "id")
    private ItemType itemType;
    @SerializedName(value = "amount")
    private double quantity;
}

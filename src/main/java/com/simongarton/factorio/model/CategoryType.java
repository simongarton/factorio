package com.simongarton.factorio.model;

import com.google.gson.annotations.SerializedName;

public enum CategoryType {

    @SerializedName("chemistry")
    CHEMISTRY("chemistry"),
    @SerializedName("rocket-building")
    ROCKET_BUILDING("rocket-building"),
    @SerializedName("crafting")
    CRAFTING("crafting"),
    @SerializedName("advanced-crafting")
    ADVANCED_CRAFTING("advanced-crafting"),
    @SerializedName("oil-processing")
    OIL_PROCESSING("oil-processing"),
    @SerializedName("centrifuging")
    CENTRIFUGING("centrifuging"),
    @SerializedName("crafting-with-fluid")
    CRAFTING_WITH_FLUID("crafting-with-fluid"),
    @SerializedName("smelting")
    SMELTING("smelting");

    private final String category;

    CategoryType(final String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}

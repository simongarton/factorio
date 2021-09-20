package com.simongarton.factorio.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Item {

    @SerializedName(value = "id")
    private ItemType itemType;
    private String name;
    private String type;
    @SerializedName(value = "wiki_link")
    private String wikiLink;
    private String category;
    private Recipe recipe;
}

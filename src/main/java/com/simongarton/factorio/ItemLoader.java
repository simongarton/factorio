package com.simongarton.factorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.simongarton.factorio.model.Item;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class ItemLoader {

    public static List<Item> getItems() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        InputStream inputStream = ItemLoader.class.getClassLoader().getResourceAsStream("data/recipes.json");
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream));

        final Type ITEM_TYPE = new TypeToken<List<Item>>() {
        }.getType();
        return gson.fromJson(reader, ITEM_TYPE);
    }
}

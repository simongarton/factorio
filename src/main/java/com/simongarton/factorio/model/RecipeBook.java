package com.simongarton.factorio.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.simongarton.factorio.exceptions.IncompleteDataException;
import com.simongarton.factorio.exceptions.RecipeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.*;

public class RecipeBook {

    private final Map<String, Recipe> recipeMap;
    private final Gson gson;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public RecipeBook() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        this.recipeMap = this.loadRecipeBook();

        this.updateWithItemTimingsAndYields();

        this.validate();
    }

    private void validate() {
        int errors = 0;
        for (final Map.Entry<String, Recipe> entry : this.recipeMap.entrySet()) {
            if (entry.getValue().getTime() == null) {
                this.logger.warn(entry.getValue().getName() + " has no time set");
                errors++;
            }
            if (entry.getValue().getYield() == null) {
                this.logger.warn(entry.getValue().getName() + " has no yield set");
                errors++;
            }
        }
        this.logger.info("Validated " + this.recipeMap.size() + " recipes and found " + errors + " errors.");
    }

    private Map<String, Recipe> loadRecipeBook() {
        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("recipe-lister/recipe.json");
        final JsonReader reader = new JsonReader(new InputStreamReader(inputStream));

        final Type ITEM_TYPE = new TypeToken<Map<String, Recipe>>() {
        }.getType();
        return this.gson.fromJson(reader, ITEM_TYPE);
    }

    private void updateWithItemTimingsAndYields() {
        /*
        The output from recipe-lister curiously does not include timings or yields for recipes.
        I have most of them extracted from an older data source, and will patch manually.
        I don't know for sure the original ones are still valid.
         */
        this.updateWithItemTimings();
        this.updateWithItemYields();
    }

    private void updateWithItemTimings() {



        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("manual/timings.json");
        final JsonReader reader = new JsonReader(new InputStreamReader(inputStream));

        final Type ITEM_TYPE = new TypeToken<Map<String, Double>>() {
        }.getType();

        final Map<String, Double> timings = this.gson.fromJson(reader, ITEM_TYPE);
        for (final Map.Entry<String, Double> entry : timings.entrySet()) {
            if (this.recipeMap.containsKey(entry.getKey())) {
                this.recipeMap.get(entry.getKey()).setTime(entry.getValue());
            }
        }
    }

    private void updateWithItemYields() {

        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("manual/yields.json");
        final JsonReader reader = new JsonReader(new InputStreamReader(inputStream));

        final Type ITEM_TYPE = new TypeToken<Map<String, Integer>>() {
        }.getType();

        final Map<String, Integer> yields = this.gson.fromJson(reader, ITEM_TYPE);
        for (final Map.Entry<String, Integer> entry : yields.entrySet()) {
            if (this.recipeMap.containsKey(entry.getKey())) {
                this.recipeMap.get(entry.getKey()).setYield(entry.getValue());
            }
        }
    }

    public List<String> getRecipeNames() {
        final List<String> keys = new ArrayList<>(this.recipeMap.keySet());
        Collections.sort(keys);
        return keys;
    }

    public Recipe getRecipe(final ItemType itemType) {
        return this.getRecipe(itemType.id());
    }

    public Recipe getRecipe(final String id) {
        if (!(this.recipeMap.containsKey(id))) {
            throw new RecipeNotFoundException(id);
        }
        final Recipe recipe = this.recipeMap.get(id);
        if (recipe.getTime() == null) {
            throw new IncompleteDataException("No time for " + id);
        }
        if (recipe.getYield() == null) {
            throw new IncompleteDataException("No yield for " + id);
        }
        return recipe;
    }

    public int size() {
        return this.recipeMap.size();
    }

    public Map<CategoryType, List<Recipe>> getCategories() {
        final Map<CategoryType, List<Recipe>> categoryMap = new HashMap<>();
        for (final Recipe recipe : this.recipeMap.values()) {
            if (!categoryMap.containsKey(recipe.getCategory())) {
                categoryMap.put(recipe.getCategory(), new ArrayList<>());
            }
            categoryMap.get(recipe.getCategory()).add(recipe);
        }
        return categoryMap;
    }
}

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.simongarton.factorio.Daemon;
import com.simongarton.factorio.model.*;
import com.simongarton.factorio.model.makers.Assembler1;
import com.simongarton.factorio.model.makers.Assembler2;
import com.simongarton.factorio.model.obsolete.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.*;

public class Main {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public static void main(final String[] args) {

        final Main main = new Main();
        main.run();
    }

    public void run() {

        final RecipeBook recipeBook = new RecipeBook();
        this.logger.info("Found " + recipeBook.size() + " recipes.");

        for (final Map.Entry<CategoryType, List<Recipe>> entry : recipeBook.getCategories().entrySet()) {
            this.logger.info("  category " + entry.getKey() + " has " + entry.getValue().size() + " recipes.");
        }

        if (false) {
            this.getEnumValues(recipeBook);
        }

        if (false) {
            this.generateRecipeTimings();
        }

        if (false) {
            this.generateRecipeYields();
        }

        Daemon daemon = new Daemon(recipeBook);
        daemon.addMaker(new Assembler1());
        Plan plan = daemon.getPlan(ItemType.COPPER_CABLE, 10);
        this.logger.info(plan.toString());

        daemon = new Daemon(recipeBook);
        daemon.addMaker(new Assembler2());
        plan = daemon.getPlan(ItemType.COPPER_CABLE, 10);
        this.logger.info(plan.toString());

    }

    private void generateRecipeTimings() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("data/recipes.json");
        final JsonReader reader = new JsonReader(new InputStreamReader(inputStream));

        final Type ITEM_TYPE = new TypeToken<List<Item>>() {
        }.getType();
        final List<Item> items = gson.fromJson(reader, ITEM_TYPE);
        Collections.sort(items, Comparator.comparing(Item::getId));
        for (Item item : items) {
            if (item.getRecipe().getTime() != null) {
                System.out.println("\"" + item.getItemType().getId() + "\":" + item.getRecipe().getTime() + ",");
            }
        }
    }

    private void generateRecipeYields() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("data/recipes.json");
        final JsonReader reader = new JsonReader(new InputStreamReader(inputStream));

        final Type ITEM_TYPE = new TypeToken<List<Item>>() {
        }.getType();
        final List<Item> items = gson.fromJson(reader, ITEM_TYPE);
        Collections.sort(items, Comparator.comparing(Item::getId));
        for (Item item : items) {
            if (item.getRecipe().getYield() != null) {
                System.out.println("\"" + item.getItemType().getId() + "\":" + item.getRecipe().getYield() + ",");
            }
        }
    }

    private void getEnumValues(final RecipeBook recipeBook) {
        for (final String recipe : recipeBook.getRecipeNames()) {
            final String enumValue = recipe.toUpperCase(Locale.ROOT).replace("-", "_");
            System.out.println(enumValue + "(\"" + recipe + "\"),");
        }
    }
}

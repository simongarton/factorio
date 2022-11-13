import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.simongarton.factorio.Daemon;
import com.simongarton.factorio.model.*;
import com.simongarton.factorio.model.makers.*;
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
        System.out.println();
        this.logger.info("Found " + recipeBook.size() + " recipes.\n");

        for (final Map.Entry<CategoryType, List<Recipe>> entry : recipeBook.getCategories().entrySet()) {
            this.logger.info("  category " + entry.getKey() + " has " + entry.getValue().size() + " recipes.");
        }

        final Daemon daemon = new Daemon(recipeBook);
        daemon.addMaker(new Assembler2());
        daemon.addMaker(new ChemicalPlant());
        daemon.addMaker(new SteelFurnace());

        /*
        Rail : an ass 2 is 0.75 crafting speed
        I want to make 0.75 a second.
        recipe says I make 2 in a half second : only that's actually 2 in (0.5/0.75) = 0.66667 seconds
        so if I said 3 items a second, that is exactly 1 ass 2. - yes
        which takes 2 stone - yes
        which needs 2 steel plates
            1 steel plate takes 8 seconds in one steel furnace
            2 steel plates would take 16 seconds in one steel furnace
            I have 3 seconds, so I need 16/3 = 5.333 furnaces
            My tool says making STEEL_PLATE with 35.00 x STEEL_FURNACE at 2.00/s
                what's the 2 per second ?
         */

        Plan plan = daemon.getPlan(ItemType.UTILITY_SCIENCE_PACK, 1);
        System.out.println();
        this.logger.info("Made " + plan.toString() + ".\n");

        plan.displayToTerminal();
        System.out.println();
        plan.summarizeToTerminal();
    }

    private void generateRecipeTimings() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("data/recipes.json");
        final JsonReader reader = new JsonReader(new InputStreamReader(inputStream));

        final Type ITEM_TYPE = new TypeToken<List<Item>>() {}.getType();
        final List<Item> items = gson.fromJson(reader, ITEM_TYPE);
        Collections.sort(items, Comparator.comparing(Item::getId));

        System.out.println("Recipe times\n");
        for (final Item item : items) {
            if (item.getRecipe().getTime() != null) {
                System.out.println("\"" + item.getItemType().getId() + "\":" + item.getRecipe().getTime() + ",");
            }
        }

        System.out.println("\nRecipe yields\n");
        for (final Item item : items) {
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

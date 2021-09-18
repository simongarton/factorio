import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.simongarton.factorio.Planner;
import com.simongarton.factorio.exceptions.RecipeNotFoundException;
import com.simongarton.factorio.model.Item;
import com.simongarton.factorio.model.ItemType;
import com.simongarton.factorio.model.RecursiveRecipe;
import com.simongarton.factorio.model.Request;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(final String[] args) {

        final Planner planner = new Planner();

        Request oneCircuitRequest = planner.planRequest(ItemType.ELECTRONIC_CIRCUIT, 1);
        planner.describe(oneCircuitRequest);

        Request twoCircuitsRequest = planner.planRequest(ItemType.ELECTRONIC_CIRCUIT, 2);
        planner.describe(twoCircuitsRequest);

//        // display as graph ?`
//        final Optional<RecursiveRecipe> recursiveRecipe = planner.getRecursiveRecipe(ItemType.ELECTRONIC_CIRCUIT, 1);
//        // System.out.println(recursiveRecipe.get());
//        if (recursiveRecipe.isPresent()) {
////            planner.displayAsTree(recursiveRecipe.get(), "");
////            planner.listTotalItems(recursiveRecipe.get());
//            planner.describe(recursiveRecipe.get());
//        } else {
//            throw new RecipeNotFoundException("can't find recipe");
//        }
//
//        // display as graph ?
//        final Optional<RecursiveRecipe> recursiveRecipe2 = planner.getRecursiveRecipe(ItemType.ELECTRONIC_CIRCUIT, 2);
//        // System.out.println(recursiveRecipe.get());
//        if (recursiveRecipe2.isPresent()) {
////            planner.displayAsTree(recursiveRecipe.get(), "");
////            planner.listTotalItems(recursiveRecipe.get());
//            planner.describe(recursiveRecipe2.get());
//        } else {
//            throw new RecipeNotFoundException("can't find recipe");
//        }
    }

    private void loadAndAnalyseRecipes() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("data/recipes.json");
        final JsonReader reader = new JsonReader(new InputStreamReader(inputStream));

        final Type ITEM_TYPE = new TypeToken<List<Item>>() {
        }.getType();
        final List<Item> data = gson.fromJson(reader, ITEM_TYPE);

        System.out.println(data.get(0));
//        System.out.println(data);
//        produceItemEnumerator(data);
//        data.stream().forEach(i -> System.out.println(i.getId().getId()));
    }

    private void produceItemEnumerator(final List<Item> items) {
        for (final Item item : items) {
            System.out.println("@SerializedName(value = \"" + item.getId() + "\")");
            System.out.println(item.getId().getId().replace("-", "_")
                    + "(\"" + item.getId() + "\", \"" + item.getName() + "\"),");
        }
    }
}

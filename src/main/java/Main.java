import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.simongarton.factorio.Foreman;
import com.simongarton.factorio.Planner;
import com.simongarton.factorio.model.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class Main {

    public static void main(final String[] args) {

        final Planner planner = new Planner();

        // creates a GraphViz data file for everything.
        planner.dumpGraph();

        // I want to create X units (per second) of Y. This I will refer to as a Job, and I will give it to a Foreman.
        Foreman foreman = new Foreman();
//        foreman.disableMaker(ItemType.ASSEMBLING_MACHINE_1);
        foreman.disableFurnaces();
        Job job = new Job(1, ItemType.PRODUCTION_SCIENCE_PACK);
        Docket docket = foreman.planForJob(job);
        foreman.explainDocket(docket);
        foreman.bom(docket);
    }

    private void loadAndAnalyseRecipes() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("data/recipes.json");
        final JsonReader reader = new JsonReader(new InputStreamReader(inputStream));

        final Type ITEM_TYPE = new TypeToken<List<Item>>() {
        }.getType();
        final List<Item> data = gson.fromJson(reader, ITEM_TYPE);

        System.out.println(data.get(0));
        System.out.println(data);
        produceItemEnumerator(data);
        data.stream().forEach(i -> System.out.println(i.getItemType().getId()));
    }

    private void produceItemEnumerator(final List<Item> items) {
        for (final Item item : items) {
            System.out.println("@SerializedName(value = \"" + item.getItemType() + "\")");
            System.out.println(item.getItemType().getId().replace("-", "_")
                    + "(\"" + item.getItemType() + "\", \"" + item.getName() + "\"),");
        }
    }
}

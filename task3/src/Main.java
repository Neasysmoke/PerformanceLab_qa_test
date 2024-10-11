import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String valuesPath = "C:\\QA TEST\\task3\\values.json";
        String testsPath = "C:\\QA TEST\\task3\\tests.json";
        String reportPath = "C:\\QA TEST\\task3\\report.json";

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();


            FileReader valuesReader = new FileReader(valuesPath);
            ValuesRoot valuesRoot = gson.fromJson(valuesReader, ValuesRoot.class);
            valuesReader.close();

            Map<Integer, String> valuesMap = new HashMap<>();
            for (ValueItem item : valuesRoot.values) {
                valuesMap.put(item.id, item.value);
            }

            FileReader testsReader = new FileReader(testsPath);
            JsonObject testsJson = JsonParser.parseReader(testsReader).getAsJsonObject();
            testsReader.close();

            fillValues(testsJson, valuesMap);

            FileWriter reportWriter = new FileWriter(reportPath);
            gson.toJson(testsJson, reportWriter);
            reportWriter.close();

            System.out.println("Отчет успешно создан в " + reportPath);

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }

    public static class ValuesRoot {
        public List<ValueItem> values;
    }

    public static class ValueItem {
        public int id;
        public String value;
    }

    private static void fillValues(JsonObject jsonObject, Map<Integer, String> valuesMap) {
        if (jsonObject.has("id")) {
            int id = jsonObject.get("id").getAsInt();
            if (valuesMap.containsKey(id)) {
                jsonObject.addProperty("value", valuesMap.get(id));
            } else {
                jsonObject.addProperty("value", "");
            }
        }
        if (jsonObject.has("children")) {
            for (JsonElement child : jsonObject.getAsJsonArray("children")) {
                fillValues(child.getAsJsonObject(), valuesMap);
            }
        }
    }
}
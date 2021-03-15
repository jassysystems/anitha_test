package helpers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultsAPIJsonHelper {

    private PropertiesHelper propertiesHelper =  new PropertiesHelper();

    public JSONArray getResultsJsonArray(String output) throws IOException {
        JSONObject obj = new JSONObject(output);
        JSONObject mrData = obj.getJSONObject("MRData");
        JSONObject resultsTable = mrData.getJSONObject(propertiesHelper.getPropValues().getProperty("resultsTable"));
        JSONArray arr = resultsTable.getJSONArray(propertiesHelper.getPropValues().getProperty("results"));
        return arr;
    }

    public void getRaceNames(String output) throws IOException {
        String raceNames;
        JSONArray arr = getResultsJsonArray(output);

        for (int i = 0; i < arr.length(); i++) {
            raceNames = arr.getJSONObject(i).getString(propertiesHelper.getPropValues().getProperty("raceName"));
            System.out.println(raceNames);
        }
    }

    public List checkUKCircuitId(String output) throws IOException {
        List circuitIdList = new ArrayList();
        String circuitId;
        JSONArray arr = getResultsJsonArray(output);

        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).getString("raceName").equalsIgnoreCase("British Grand Prix")) {
                JSONObject circuit = arr.getJSONObject(i).getJSONObject("Circuit");
                circuitId = circuit.getString("circuitId");
                circuitIdList.add(circuitId);
            }
        }
        return circuitIdList;
    }
}

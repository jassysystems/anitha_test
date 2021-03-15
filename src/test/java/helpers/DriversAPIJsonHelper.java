package helpers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DriversAPIJsonHelper {

    private PropertiesHelper propertiesHelper =  new PropertiesHelper();

    public JSONArray getDriversJsonArray(String output) throws IOException {
        JSONObject obj = new JSONObject(output);
        JSONObject mrData = obj.getJSONObject("MRData");
        JSONObject driversTable = mrData.getJSONObject(propertiesHelper.getPropValues().getProperty("driversTable"));
        JSONArray arr = driversTable.getJSONArray(propertiesHelper.getPropValues().getProperty("drivers"));
        return arr;
    }

    public void getDriversId(String output) throws IOException {
        String driverId;
        JSONArray arr = getDriversJsonArray(output);

        for (int i = 0; i < arr.length(); i++) {
            driverId = arr.getJSONObject(i).getString(propertiesHelper.getPropValues().getProperty("driverId"));
            System.out.println(driverId);
        }
    }

    public String checkBelgianDriversId(String output) throws IOException {

        String driverId;
        JSONArray arr = getDriversJsonArray(output);

        for (int i = 0; i < arr.length(); i++) {
            if(arr.getJSONObject(i).getString("nationality").equalsIgnoreCase("Belgian")) {
                driverId = arr.getJSONObject(i).getString(propertiesHelper.getPropValues().getProperty("driverId"));
                return driverId;
            }
        }
        return null;
    }

    public List checkBritishDriversId(String output) throws IOException {

        String driverId;
        List driverIds = new ArrayList();
        JSONArray arr = getDriversJsonArray(output);

        for (int i = 0; i < arr.length(); i++) {
            if(arr.getJSONObject(i).getString("nationality").equalsIgnoreCase("British")) {
                driverId = arr.getJSONObject(i).getString(propertiesHelper.getPropValues().getProperty("driverId"));
                driverIds.add(driverId);
            }
        }
        return driverIds;
    }
}

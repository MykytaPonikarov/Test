package diplom.ponikarov.parser;

import diplom.ponikarov.entity.ClimateData;

/**
 * Created by Nikita on 03.11.2015.
 */
public class ClimateDataParser {

    public static ClimateData parse(String data) {
        ClimateData climateData = new ClimateData();
        String[] dataResponseArray = data.split(";");
        if (dataResponseArray.length == 3) {
            String status = getData(dataResponseArray[0], "status");
            String humidity = getData(dataResponseArray[1], "humidity");
            String temperature = getData(dataResponseArray[2], "temperature");
            climateData.setStatus(status);
            climateData.setTemperature(Integer.valueOf(temperature));
            climateData.setHumidity(Integer.valueOf(humidity));
            return climateData;
        }
        throw new IllegalArgumentException();
    }

    private static String getData(String param, String paramName) {
        if (param != null && param.contains(paramName)) {
            String[] statusArray = param.split(":");
            return statusArray[1];
        }
        return null;
    }
}

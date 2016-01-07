package diplom.ponikarov.util;

import diplom.ponikarov.entity.ClimateData;
import org.springframework.stereotype.Component;

@Component("climateDataParser")
public class ClimateDataParser {

    public ClimateData parse(String data) {
        ClimateData climateData = new ClimateData();
        String[] dataResponseArray = data.split(";");
        if (dataResponseArray.length == 4) {
            String status = getData(dataResponseArray[0], "status");
            String humidity = getData(dataResponseArray[1], "humidity");
            String temperature = getData(dataResponseArray[2], "temperature");
            String controller = getData(dataResponseArray[3], "controller");
            climateData.setStatus(status);
            climateData.setTemperature(Float.valueOf(temperature));
            climateData.setHumidity(Float.valueOf(humidity));
            climateData.setControllerNumber(Integer.parseInt(controller));
            return climateData;
        }
        throw new IllegalArgumentException("Invalid controller response format");
    }

    private String getData(String param, String paramName) {
        if (param != null && param.contains(paramName)) {
            String[] statusArray = param.split(":");
            return statusArray[1];
        }
        return null;
    }
}

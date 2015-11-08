package diplom.ponikarov.entity;

import java.io.Serializable;

/**
 * Created by Nikita on 03.11.2015.
 */
public class ClimateData implements Serializable {

    private static final long serialVersionUID = 647895229066704635L;

    private String status;
    private int temperature;
    private int humidity;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClimateData{");
        sb.append("status='").append(status).append('\'');
        sb.append(", temperature=").append(temperature);
        sb.append(", humidity=").append(humidity);
        sb.append('}');
        return sb.toString();
    }
}

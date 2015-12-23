package diplom.ponikarov.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nikita on 03.11.2015.
 */
public class ClimateData implements Serializable {

    private static final long serialVersionUID = 647895229066704635L;

    private String status;
    private float temperature;
    private float humidity;
    private int controllerNumber;
    private Date date;

    public ClimateData() {
    }

    public ClimateData(int controllerNumber, Date date, float temperature, float humidity, String status) {
        this.status = status;
        this.temperature = temperature;
        this.humidity = humidity;
        this.controllerNumber = controllerNumber;
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public int getControllerNumber() {
        return controllerNumber;
    }

    public void setControllerNumber(int controllerNumber) {
        this.controllerNumber = controllerNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClimateData{");
        sb.append("status='").append(status).append('\'');
        sb.append(", temperature=").append(temperature);
        sb.append(", humidity=").append(humidity);
        sb.append(", controllerNumber=").append(controllerNumber);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}

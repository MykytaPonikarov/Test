package diplom.ponikarov.entity;

public class ControllerConfiguration {

    private int controllerNumber;
    private float maxTemperature;
    private float minTemperature;
    private float maxHumidity;
    private float minHumidity;

    public int getControllerNumber() {
        return controllerNumber;
    }

    public void setControllerNumber(int controllerNumber) {
        this.controllerNumber = controllerNumber;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public float getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(float maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public float getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(float minHumidity) {
        this.minHumidity = minHumidity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ControllerConfiguration{");
        sb.append("controllerNumber=").append(controllerNumber);
        sb.append(", maxTemperature=").append(maxTemperature);
        sb.append(", minTemperature=").append(minTemperature);
        sb.append(", maxHumidity=").append(maxHumidity);
        sb.append(", minHumidity=").append(minHumidity);
        sb.append('}');
        return sb.toString();
    }
}

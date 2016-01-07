package diplom.ponikarov.entity;

import java.util.Map;

public class ConfigurationContainer {

    private Map<Integer, ControllerConfiguration> configurationMap;

    public void addConfiguration(int controllerNumber, ControllerConfiguration configuration) {
        configurationMap.put(controllerNumber, configuration);
    }

    public ControllerConfiguration getConfiguration(int controllerNumber) {
        return configurationMap.get(controllerNumber);
    }

    public Map<Integer, ControllerConfiguration> getConfigurationMap() {
        return configurationMap;
    }

    public void setConfigurationMap(Map<Integer, ControllerConfiguration> configurationMap) {
        this.configurationMap = configurationMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConfigurationContainer{");
        sb.append("configurationMap=").append(configurationMap);
        sb.append('}');
        return sb.toString();
    }
}

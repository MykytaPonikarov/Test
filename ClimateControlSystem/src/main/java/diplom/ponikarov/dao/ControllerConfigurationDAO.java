package diplom.ponikarov.dao;

import diplom.ponikarov.entity.ControllerConfiguration;

public interface ControllerConfigurationDAO {

    void add(ControllerConfiguration configuration);

    ControllerConfiguration get(int controllerNumber);

    void update(ControllerConfiguration controllerConfiguration);
}

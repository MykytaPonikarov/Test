package diplom.ponikarov.service;

import diplom.ponikarov.dao.ControllerConfigurationDAO;
import diplom.ponikarov.entity.ControllerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("controllerConfigurationService")
public class ControllerConfigurationService {

    @Autowired
    private ControllerConfigurationDAO controllerConfigurationDAO;

    public ControllerConfiguration get(int controllerNumber) {
        return controllerConfigurationDAO.get(controllerNumber);
    }

    public void update(ControllerConfiguration controllerConfiguration) {
        controllerConfigurationDAO.update(controllerConfiguration);
    }
}

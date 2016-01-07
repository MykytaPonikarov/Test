package diplom.ponikarov.service;

import diplom.ponikarov.dao.ControllerConfigurationDAO;
import diplom.ponikarov.entity.ControllerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("controllerConfigurationService")
public class ControllerConfigurationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerConfigurationService.class);

    @Autowired
    private ControllerConfigurationDAO controllerConfigurationDAO;

    public ControllerConfiguration get(int controllerNumber) {
        ControllerConfiguration controllerConfiguration = controllerConfigurationDAO.get(controllerNumber);
        LOGGER.debug("Get controller configuration by number {}. Configuration: {}", controllerNumber, controllerConfiguration);
        return controllerConfiguration;
    }

    public void update(ControllerConfiguration controllerConfiguration) {
        LOGGER.debug("Update controller configuration. {}", controllerConfiguration);
        controllerConfigurationDAO.update(controllerConfiguration);
    }
}

package diplom.ponikarov.loader;

import diplom.ponikarov.entity.ConfigurationContainer;
import diplom.ponikarov.entity.ControllerConfiguration;
import diplom.ponikarov.service.ControllerConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;

public class ConfigurationLoader implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationLoader.class);

    @Value("#{'${controllersNumber}'.split(',')}")
    private List<Integer> controllersNumber;
    @Autowired
    private ControllerConfigurationService controllerConfigurationService;

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        LOGGER.debug("Init configuration controller");
        if (s.equals("configurationContainer")) {
            ConfigurationContainer configurationContainer = (ConfigurationContainer) o;
            for (Integer controllerNumber : controllersNumber) {
                ControllerConfiguration configuration = controllerConfigurationService.get(controllerNumber);
                configurationContainer.addConfiguration(controllerNumber, configuration);
            }
            LOGGER.debug("Configuration container: {}", configurationContainer.getConfigurationMap());
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }
}

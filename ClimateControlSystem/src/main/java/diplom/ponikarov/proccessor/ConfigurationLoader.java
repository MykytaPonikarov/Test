package diplom.ponikarov.proccessor;

import diplom.ponikarov.entity.ControllerConfiguration;
import diplom.ponikarov.entity.ConfigurationContainer;
import diplom.ponikarov.service.ControllerConfigurationService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;

public class ConfigurationLoader implements BeanPostProcessor {

    @Value("#{'${controllersNumber}'.split(',')}")
    private List<Integer> controllersNumber;

    @Autowired
    ControllerConfigurationService controllerConfigurationService;

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if (s.equals("configurationContainer")) {
            ConfigurationContainer configurationContainer = (ConfigurationContainer) o;
            for (Integer controllerNumber : controllersNumber) {
                ControllerConfiguration configuration = controllerConfigurationService.get(controllerNumber);
                configurationContainer.addConfiguration(controllerNumber, configuration);
            }
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }
}

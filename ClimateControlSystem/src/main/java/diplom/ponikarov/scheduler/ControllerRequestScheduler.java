package diplom.ponikarov.scheduler;

import diplom.ponikarov.service.ArduinoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TimerTask;

@Component
public class ControllerRequestScheduler extends TimerTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerRequestScheduler.class);

    @Autowired
    private ArduinoService arduinoService;
    @Value("#{'${controllersNumber}'.split(',')}")
    private List<Integer> controllersNumber;

    @Override
    public void run() {
        LOGGER.debug("Run scheduler...");
        for (Integer controllerNumber : controllersNumber) {
            arduinoService.sendDataToController(String.valueOf(controllerNumber));
        }
    }
}

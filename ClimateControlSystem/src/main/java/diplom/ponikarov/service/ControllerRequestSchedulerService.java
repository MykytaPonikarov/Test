package diplom.ponikarov.service;

import diplom.ponikarov.scheduler.ControllerRequestScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Timer;

@Service
public class ControllerRequestSchedulerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerRequestSchedulerService.class);

    private static final int SECOND_COEFFICIENT = 1000;
    @Value("${timerDelay}")
    private Integer timerDelay;
    @Value("${timerPeriod}")
    private Integer timerPeriod;
    @Autowired
    private ControllerRequestScheduler controllerRequestScheduler;

    @PostConstruct
    public void startScheduler() {
        LOGGER.debug("Start controller request scheduler. Time delay: {}, time period: {} second", timerDelay, timerPeriod);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(controllerRequestScheduler, SECOND_COEFFICIENT * timerDelay, SECOND_COEFFICIENT * timerPeriod);
    }
}


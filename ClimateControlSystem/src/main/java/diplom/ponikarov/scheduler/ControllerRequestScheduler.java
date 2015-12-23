package diplom.ponikarov.scheduler;

import diplom.ponikarov.controller.MainViewController;

import java.util.TimerTask;

/**
 * Created by Mykyta Ponikarov on 12/23/2015.
 */
public class ControllerRequestScheduler extends TimerTask {

    @Override
    public void run() {
        System.out.println("Scheduler start");
    }
}

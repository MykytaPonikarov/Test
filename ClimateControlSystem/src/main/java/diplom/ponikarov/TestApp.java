package diplom.ponikarov;

import diplom.ponikarov.controller.DataOutOfRangeController;
import diplom.ponikarov.loader.ControllerViewLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class TestApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        String login = "/fxml/login.fxml";
        String dataOutOfRange = "/fxml/dataOutOfRange.fxml";

        DataOutOfRangeController dataOutOfRangeController = (DataOutOfRangeController) ControllerViewLoader.load(dataOutOfRange);

        Parent view = dataOutOfRangeController.getView();

        dataOutOfRangeController.setInitView(view);
        dataOutOfRangeController.initEventHandler();

        ControllerViewLoader.loadAndView(login, "Login");
    }
}

package diplom.ponikarov;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Mykyta Ponikarov on 12/3/2015.
 */
public class TestApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        String fxmlFile = "/fxml/login.fxml";
        ControllerViewLoader.loadAndView(fxmlFile, "Login");

    }

}

package diplom.ponikarov;

import diplom.ponikarov.scheduler.ControllerRequestScheduler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;

/**
 * Created by Nikita on 04.10.2015.
 */
public class MainApp extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
       /* String fxmlFile = "/fxml/main.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setTitle("Climate control");
        stage.setScene(new Scene(root));
        stage.show();*/
        String fxmlFile = "/fxml/login.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }


}

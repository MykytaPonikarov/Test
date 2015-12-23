package diplom.ponikarov;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
//        String fxmlFile = "/fxml/window.fxml";
        String fxmlFile = "/fxml/login.fxml";

        WindowLoader.getInstance().load(fxmlFile, "Login");
//        Parent root = WindowLoader.getInstance().load(fxmlFile);
//        stage.setTitle("Login");
//        stage.setScene(new Scene(root));
//        stage.show();


//        FXMLLoader loader = new FXMLLoader();
//        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
//        stage.setTitle("Login");
//        stage.setScene(new Scene(root));
//        stage.show();
    }

}

package diplom.ponikarov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Mykyta Ponikarov on 12/3/2015.
 */
public class TestApp extends Application {

    private static final ApplicationLoader APPLICATION_LOADER = new ApplicationLoader();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
//        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        String fxmlFile = "/fxml/window.fxml";

        Parent root = (Parent) APPLICATION_LOADER.load(fxmlFile);
        stage.setTitle("Main window");
        stage.setScene(new Scene(root));
        stage.show();

//        String fxmlFile = "/fxml/login.fxml";
//        FXMLLoader loader = new FXMLLoader();
//        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
//        stage.setTitle("Login");
//        stage.setScene(new Scene(root));
//        stage.show();
    }

}

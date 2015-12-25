package diplom.ponikarov;

import diplom.ponikarov.controller.ControllerDetailsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class WindowLoader {

    private static final ApplicationContext APPLICATION_CONTEXT = new ClassPathXmlApplicationContext("config.xml");

    private static WindowLoader windowLoader;

    private WindowLoader() {

    }

    public static WindowLoader getInstance() {
        if (windowLoader == null) {
            windowLoader = new WindowLoader();
        }
        return windowLoader;
    }

    public Parent load(String url) {
        try (InputStream fxmlStream = getClass().getResourceAsStream(url)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(APPLICATION_CONTEXT::getBean);
            return loader.load(fxmlStream);
        } catch (IOException ioException) {
            throw new RuntimeException("Application loader. Exception --->>", ioException);
        }
    }

    public void load(String url, String title) {
        Parent root = load(url);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void load(String url, String title, int controllerNumber) {
        try (InputStream fxmlStream = getClass().getResourceAsStream(url)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(APPLICATION_CONTEXT::getBean);
            Parent root = loader.load(fxmlStream);
            ControllerDetailsController detailsController = loader.getController();
            detailsController.setControllerNumber(controllerNumber);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ioException) {
            throw new RuntimeException("Application loader. Exception --->>", ioException);
        }
    }
}

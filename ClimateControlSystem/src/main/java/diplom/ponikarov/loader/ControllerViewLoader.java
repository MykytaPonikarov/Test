package diplom.ponikarov.loader;

import diplom.ponikarov.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class ControllerViewLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerViewLoader.class);
    private static final ApplicationContext APPLICATION_CONTEXT = new ClassPathXmlApplicationContext("config.xml");

    public static Controller load(String url) {
        LOGGER.debug("Load controller view. Url: {}", url);
        try (InputStream fxmlStream = ControllerViewLoader.class.getResourceAsStream(url)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(APPLICATION_CONTEXT::getBean);
            Parent view = loader.load(fxmlStream);
            Controller controller = loader.getController();
            controller.setView(view);
            return controller;
        } catch (IOException ioException) {
            LOGGER.warn("Exception when load controller. Url: {}, exception: {}", url, ioException);
            throw new RuntimeException("Application loader. Exception --->>", ioException);
        }
    }

    public static void view(Controller controller, String title) {
        LOGGER.debug("Controller view. Title: {}", title);
        Parent view = controller.getView();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(view));
        stage.show();
    }

    public static void loadAndView(String url, String title) {
        LOGGER.debug("Load and view. Url: {}, title: {}", url, title);
        Controller controller = load(url);
        view(controller, title);
    }
}

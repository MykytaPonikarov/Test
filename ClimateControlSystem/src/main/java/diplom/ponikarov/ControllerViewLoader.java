package diplom.ponikarov;

import diplom.ponikarov.controller.Controller;
import diplom.ponikarov.controller.ControllerDetailsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class ControllerViewLoader {

    private static final ApplicationContext APPLICATION_CONTEXT = new ClassPathXmlApplicationContext("config.xml");

//    private static ControllerViewLoader controllerViewLoader;

    public static Controller load(String url) {
        try (InputStream fxmlStream = ControllerViewLoader.class.getResourceAsStream(url)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(APPLICATION_CONTEXT::getBean);
            Parent view = loader.load(fxmlStream);
            Controller controller = loader.getController();
            controller.setView(view);
            return controller;
        } catch (IOException ioException) {
            throw new RuntimeException("Application loader. Exception --->>", ioException);
        }
    }

    public static void view(Controller controller, String title) {
        Parent view = controller.getView();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(view));
        stage.show();
    }

    public static void loadAndView(String url, String title) {
        Controller controller = load(url);
        view(controller, title);
    }

//    public Parent load(String url) {
//        try (InputStream fxmlStream = getClass().getResourceAsStream(url)) {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setControllerFactory(APPLICATION_CONTEXT::getBean);
//            return loader.load(fxmlStream);
//        } catch (IOException ioException) {
//            throw new RuntimeException("Application loader. Exception --->>", ioException);
//        }
//    }
//
//    public void load(String url, String title) {
//        Parent root = load(url);
//        Stage stage = new Stage();
//        stage.setTitle(title);
//        stage.setScene(new Scene(root));
//        stage.show();
//    }
//
//    public void load(String url, String title, int controllerNumber) {
//        try (InputStream fxmlStream = getClass().getResourceAsStream(url)) {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setControllerFactory(APPLICATION_CONTEXT::getBean);
//            Parent root = loader.load(fxmlStream);
//            ControllerDetailsController detailsController = loader.getController();
//            detailsController.setControllerNumberLabel(controllerNumber);
//            Stage stage = new Stage();
//            stage.setTitle(title);
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException ioException) {
//            throw new RuntimeException("Application loader. Exception --->>", ioException);
//        }
//    }
}

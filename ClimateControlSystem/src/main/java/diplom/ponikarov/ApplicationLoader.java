package diplom.ponikarov;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class ApplicationLoader {

    private static final ApplicationContext APPLICATION_CONTEXT = new ClassPathXmlApplicationContext("config.xml");

    public Object load(String url) {
        try (InputStream fxmlStream = getClass().getResourceAsStream(url)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(clazz -> APPLICATION_CONTEXT.getBean(clazz));
            return loader.load(fxmlStream);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

}

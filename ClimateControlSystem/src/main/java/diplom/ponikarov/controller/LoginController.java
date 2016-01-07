package diplom.ponikarov.controller;

import diplom.ponikarov.loader.ControllerViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginController extends AbstractController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private Label msgLoginFailed;
    @FXML
    private TextField inputLogin;
    @FXML
    private PasswordField inputPassword;

    @FXML
    private void btnLoginAction(ActionEvent event) throws IOException {
        String login = inputLogin.getText();
        String password = inputPassword.getText();
        LOGGER.debug("Login action. Input login: {}, input password: {}.", login, password);
        if ("admin".equals(login) && "admin".equals(password)) {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            MainViewController controller = (MainViewController) ControllerViewLoader.load("/fxml/mainWindow.fxml");
            ControllerViewLoader.view(controller, "Main window");

        } else {
            msgLoginFailed.setText("Invalid login data!!!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

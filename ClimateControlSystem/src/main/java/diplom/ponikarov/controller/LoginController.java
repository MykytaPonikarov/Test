package diplom.ponikarov.controller;

import diplom.ponikarov.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable {

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
        if ("admin".equals(login) && "admin".equals(password)) {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            WindowLoader.getInstance().load("/fxml/mainWindow.fxml", "Main window");
//            Stage stage = new Stage();
//            stage.setTitle("Climate control");
//            stage.setScene(new Scene(root));
//            stage.show();


        } else {
            msgLoginFailed.setText("Invalid login data!!!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

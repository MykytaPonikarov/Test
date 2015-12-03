package diplom.ponikarov.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
/*            Stage stage = new Stage();
            Parent root = new FXMLLoader().load(getClass().getResourceAsStream("/fxml/main.fxml"));
            stage.setTitle("Climate control");
            stage.setScene(new Scene(root));
            stage.show();*/
            new LineCharController().loadLineChart(1);
        } else {
            msgLoginFailed.setText("Invalid login data!!!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

package diplom.ponikarov.controller;

import diplom.ponikarov.dao.ClimateDataDAO;
import diplom.ponikarov.dao.mysql.MySqlClimateDataDAO;
import diplom.ponikarov.db.MySqlConnectionManager;
import diplom.ponikarov.entity.ClimateData;
import diplom.ponikarov.service.ClimateDataService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ControllerDetailsController implements Initializable{

    @FXML
    private DatePicker fromDate;
    @FXML
    private DatePicker toDate;
    @FXML
    private Label controllerNumberLabel;
    @FXML
    private TextField maxTemperature;
    @FXML
    private TextField minTemperature;
    @FXML
    private TextField maxHumidity;
    @FXML
    private TextField minHumidity;

    @Autowired
    private ClimateDataService climateDataService;

    private int controllerNumber;

    public ControllerDetailsController() {
        init();
    }

    public void loadLineChart(int controllerData) {
//        Stage stage = new Stage();
//        stage.setTitle("Controller " + controllerData + " monitoring");
//        Group root = new Group();
//
//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
//
//        xAxis.setLabel("Date");
//        yAxis.setLabel("Temperature/Humidity");
//
//        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
//        lineChart.setTitle("Controller " + controllerData + " monitoring");
//
//        XYChart.Series temperature = new XYChart.Series();
//        temperature.setName("Temperature");
//
//        XYChart.Series humidity = new XYChart.Series();
//        humidity.setName("Humidity");
//
//        List<ClimateData> all = climateDataService.getAll();
//
//        draw(temperature, humidity, all);
//
//        lineChart.getData().addAll(temperature, humidity);
//        root.getChildren().add(lineChart);
//
//        stage.setScene(new Scene(root, 500, 400));
//        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerNumberLabel.setText(String.valueOf(controllerNumber));
    }

    @FXML
    public void showInRange() {

    }

    @FXML
    public void generateFile() {

    }

    @FXML
    public void apply() {

    }

    private void draw(XYChart.Series temperature, XYChart.Series humidity, List<ClimateData> climateDataList) {
        for (ClimateData climateData : climateDataList) {
            System.out.println(climateData.getDate());
            System.out.println(climateData.getDate().toString());
            temperature.getData().add(new XYChart.Data(climateData.getDate().toString(), climateData.getTemperature()));
            humidity.getData().add(new XYChart.Data(climateData.getDate().toString(), climateData.getHumidity()));
        }
    }

    private void init() {
//        climateDataDAO = new MySqlClimateDataDAO(new MySqlConnectionManager());
//        climateDataDAO = new MySqlClimateDataDAO();
    }

    public int getControllerNumber() {
        return controllerNumber;
    }

    public void setControllerNumber(int controllerNumber) {
        this.controllerNumber = controllerNumber;
    }
}

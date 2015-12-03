package diplom.ponikarov.controller;

import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LineCharController implements Initializable {

    public void loadLineChart(int controllerData) {
        Stage stage = new Stage();
        stage.setTitle("Controller " + controllerData + " monitoring");
        Group root = new Group();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Month");
        yAxis.setLabel("Value");

        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Controller " + controllerData + " monitoring");

        XYChart.Series temperature = new XYChart.Series();
        temperature.setName("Temperature");

        temperature.getData().add(new XYChart.Data("21-11-1999", 31));
        temperature.getData().add(new XYChart.Data("21-12-1999", 12));
        temperature.getData().add(new XYChart.Data("22-12-1999", 20));
        temperature.getData().add(new XYChart.Data("25-12-1999", 22));

        XYChart.Series humidity = new XYChart.Series();
        humidity.setName("Humidity");

        humidity.getData().add(new XYChart.Data("21-11-1999", 31));
        humidity.getData().add(new XYChart.Data("21-12-1999", 12));
        humidity.getData().add(new XYChart.Data("22-12-1999", 20));
        humidity.getData().add(new XYChart.Data("25-12-1999", 15));
        humidity.getData().add(new XYChart.Data("27-12-1999", 22));


        lineChart.getData().addAll(temperature, humidity);
        root.getChildren().add(lineChart);

        stage.setScene(new Scene(root, 500, 400));
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

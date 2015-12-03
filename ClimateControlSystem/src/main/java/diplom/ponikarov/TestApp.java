package diplom.ponikarov;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * Created by Mykyta Ponikarov on 12/3/2015.
 */
public class TestApp extends Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("java-buddy.blogspot.com");
        Group root = new Group();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Month");
        yAxis.setLabel("Value");

        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("LineChart");
        XYChart.Series series = new XYChart.Series();
        series.setName("XYChart.Series");

        series.getData().add(new XYChart.Data("January", 100));
        series.getData().add(new XYChart.Data("February", 12));
        series.getData().add(new XYChart.Data("March", 50));
        series.getData().add(new XYChart.Data("April", 75));
        series.getData().add(new XYChart.Data("May", 12));
        series.getData().add(new XYChart.Data("June", 12));
        series.getData().add(new XYChart.Data("July", 12));
        series.getData().add(new XYChart.Data("August", 30));
        series.getData().add(new XYChart.Data("September", 75));
        series.getData().add(new XYChart.Data("October", 55));
        series.getData().add(new XYChart.Data("November", 12));
        series.getData().add(new XYChart.Data("December", -100));
        series.getData().add(new XYChart.Data("December1", -100));
        series.getData().add(new XYChart.Data("December2", -100));
        series.getData().add(new XYChart.Data("December3", -100));

        lineChart.getData().add(series);
        root.getChildren().add(lineChart);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }

}

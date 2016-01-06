package diplom.ponikarov.controller;

import diplom.ponikarov.entity.ClimateData;
import diplom.ponikarov.entity.ConfigurationContainer;
import diplom.ponikarov.entity.ControllerConfiguration;
import diplom.ponikarov.service.ClimateDataService;
import diplom.ponikarov.service.ControllerConfigurationService;
import diplom.ponikarov.util.FileGeneratorUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ControllerDetailsController extends AbstractController implements Initializable {

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
    @FXML
    private LineChart lineChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @Autowired
    private ClimateDataService climateDataService;
    @Autowired
    private FileGeneratorUtil fileGenerator;
    @Autowired
    private ConfigurationContainer configurationContainer;
    @Autowired
    private ControllerConfigurationService controllerConfigurationService;
    private ControllerConfiguration configuration;

    private int controllerNumber;

    public void loadLineChart() {
//        Stage stage = new Stage();
//        stage.setTitle("SerialController " + controllerData + " monitoring");
//        Group root = new Group();

//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
//
//        xAxis.setLabel("Date");
//        yAxis.setLabel("Temperature/Humidity");
//
//        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
////        lineChart.setTitle("SerialController " + controllerData + " monitoring");
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
//        LineChart lineChart = initLineChart();
//        root.getChildren().add(lineChart);
//
//        stage.setScene(new Scene(root, 500, 400));
//        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void showInRange() {
        System.out.println("Show in range");
        if (fromDate.getValue() != null && toDate.getValue() != null) {
            Date from = getDateFromDatePicker(fromDate);
            Date to = getDateFromDatePicker(toDate);
            List<ClimateData> allByControllerNumberAndDateRange = climateDataService.getAllByControllerNumberAndDateRange(controllerNumber, from, to);
            lineChart.getData().clear();
            generateLineChart(allByControllerNumberAndDateRange);
        }
    }

    @FXML
    public void generateFile() {
        System.out.println("Generate file");
        if (fromDate.getValue() != null && toDate.getValue() != null) {
            Date from = getDateFromDatePicker(fromDate);
            Date to = getDateFromDatePicker(toDate);
            List<ClimateData> allByControllerNumberAndDateRange = climateDataService.getAllByControllerNumberAndDateRange(controllerNumber, from, to);
            fileGenerator.generateFile(allByControllerNumberAndDateRange);
        }
    }

    @FXML
    public void apply() {
        String maxT = maxTemperature.getText();
        String maxH = maxHumidity.getText();
        String minT = minTemperature.getText();
        String minH = minHumidity.getText();
        if (maxT != null && minT != null && maxH != null && minH != null) {
            configuration.setMaxTemperature(Float.parseFloat(maxT));
            configuration.setMinTemperature(Float.parseFloat(minT));
            configuration.setMaxHumidity(Float.parseFloat(maxH));
            configuration.setMinHumidity(Float.parseFloat(minH));
            controllerConfigurationService.update(configuration);
        }
        setConfigurationFields();
    }

    public void initControllerDetailsController(int controllerNumber) {
        setControllerNumber(controllerNumber);
        initLineChart(controllerNumber);
        initConfigurationField(controllerNumber);
    }

    public void initLineChart(int controllerNumber) {
        List<ClimateData> all = climateDataService.getAllByControllerNumber(controllerNumber);

        xAxis.setLabel("Date");
        yAxis.setLabel("Temperature/Humidity");

        lineChart.setTitle("Controller " + controllerNumber + " monitoring");

        generateLineChart(all);
    }

    public void setControllerNumber(int number) {
        controllerNumber = number;
        controllerNumberLabel.setText(String.valueOf(number));
    }

    public void initConfigurationField(int controllerNumber) {
        configuration = configurationContainer.getConfiguration(controllerNumber);
        setConfigurationFields();
    }

    public LineChart getLineChart() {
        return lineChart;
    }

    private Date getDateFromDatePicker(DatePicker picker) {
        LocalDate localDate = picker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

    private void setConfigurationFields() {
        maxTemperature.setText(String.valueOf(configuration.getMaxTemperature()));
        minTemperature.setText(String.valueOf(configuration.getMinTemperature()));
        maxHumidity.setText(String.valueOf(configuration.getMaxHumidity()));
        minHumidity.setText(String.valueOf(configuration.getMinHumidity()));
    }

    private void generateLineChart(List<ClimateData> climateDataList) {

        XYChart.Series temperature = new XYChart.Series();
        temperature.setName("Temperature");

        XYChart.Series humidity = new XYChart.Series();
        humidity.setName("Humidity");


        draw(temperature, humidity, climateDataList);

        lineChart.getData().addAll(temperature, humidity);
    }

    private void draw(XYChart.Series temperature, XYChart.Series humidity, List<ClimateData> climateDataList) {
        for (ClimateData climateData : climateDataList) {
            temperature.getData().add(new XYChart.Data(climateData.getDate().toString(), climateData.getTemperature()));
            humidity.getData().add(new XYChart.Data(climateData.getDate().toString(), climateData.getHumidity()));
        }
    }

}

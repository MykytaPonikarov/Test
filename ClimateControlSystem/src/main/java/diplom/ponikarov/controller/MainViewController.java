package diplom.ponikarov.controller;

import diplom.ponikarov.entity.ClimateData;
import diplom.ponikarov.service.ClimateDataService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainViewController implements Initializable {

    @FXML
    private TableView<ClimateData> tableId;
    @FXML
    private TableColumn<ClimateData, Integer> tableColumnControllerNumber;
    @FXML
    private TableColumn<ClimateData, Date> tableColumnDate;
    @FXML
    private TableColumn<ClimateData, Float> tableColumnTemperature;
    @FXML
    private TableColumn<ClimateData, Float> tableColumnHumidity;
    @FXML
    private TableColumn<ClimateData, String> tableColumnStatus;
    @FXML
    private ComboBox<Integer> selectControllerNumber;
    @Autowired
    private Integer historyDataCount;
    @Autowired
    private ClimateDataService climateDataService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //fill combo box by controllers number
        List<Integer> controllersNumber = new ArrayList<>();
        controllersNumber.add(1);
        controllersNumber.add(2);
        selectControllerNumber.setItems(FXCollections.observableArrayList(controllersNumber));

        //fill table by climate data from db
        tableColumnControllerNumber.setCellValueFactory(new PropertyValueFactory<>("controllerNumber"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnTemperature.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        tableColumnHumidity.setCellValueFactory(new PropertyValueFactory<>("humidity"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
//        doAction();
        refreshHistoryData();
    }

    @FXML
    public void openControllerDetails() {
        System.out.println(selectControllerNumber.getValue());
    }

    @FXML
    public void refreshHistoryData() {
//        List<ClimateData> data = climateDataService.getAllWithLimit(historyDataCount);

        List<ClimateData> data = new ArrayList<>();
        ClimateData climateData = new ClimateData(1, new Date(), 23, 40, "OK");
        for (int i = 0; i < historyDataCount; i++) {
            data.add(climateData);
        }

        tableId.setItems(FXCollections.observableArrayList(data));
    }

}

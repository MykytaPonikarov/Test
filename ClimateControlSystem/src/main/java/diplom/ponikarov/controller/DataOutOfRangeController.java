package diplom.ponikarov.controller;

import diplom.ponikarov.event.DataOutOfRangeEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class DataOutOfRangeController extends AbstractController {

    private Parent initView;
    @FXML
    private Label errorMessage;

    public void initEventHandler() {

        initView.addEventHandler(DataOutOfRangeEvent.DATA_OUT_OF_RANGE_EVENT_EVENT_TYPE,

                event -> Platform.runLater(() -> {

                    Stage dataOutOfRangeStage = new Stage();
                    dataOutOfRangeStage.setTitle("Error");
                    dataOutOfRangeStage.setScene(new Scene(getView()));
                    errorMessage.setText("Data out of range!!!");
                    dataOutOfRangeStage.show();

                }));
    }

    public Parent getInitView() {
        return initView;
    }

    public void setInitView(Parent initView) {
        this.initView = initView;
    }
}

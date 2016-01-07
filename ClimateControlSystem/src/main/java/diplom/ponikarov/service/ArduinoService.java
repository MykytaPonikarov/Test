package diplom.ponikarov.service;

import diplom.ponikarov.controller.DataOutOfRangeController;
import diplom.ponikarov.entity.ClimateData;
import diplom.ponikarov.entity.ControllerConfiguration;
import diplom.ponikarov.event.DataOutOfRangeEvent;
import diplom.ponikarov.loader.ControllerViewLoader;
import diplom.ponikarov.util.ClimateDataParser;
import gnu.io.*;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Service("arduinoService")
public class ArduinoService implements SerialPortEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoService.class);

    @Value("${serialPortNumber}")
    private String serialPortNumber;
    @Autowired
    private ClimateDataService climateDataService;
    @Autowired
    private ClimateDataParser climateDataParser;
    @Autowired
    private ControllerConfigurationService controllerConfigurationService;

    private SerialPort serialPort;
    private OutputStream serialOut;
    private BufferedReader serialReader;

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        LOGGER.debug("Controller send event... ");
        String response = getResponse(serialPortEvent);

        if (response != null) {

            ClimateData climateData = climateDataParser.parse(response);

            checkClimateData(climateData);

            climateDataService.add(climateData);
        }
    }

    public String getResponse(SerialPortEvent oEvent) {
        String response = null;
        try {
            int eventType = oEvent.getEventType();
            if (eventType == SerialPortEvent.DATA_AVAILABLE) {

                if (serialReader == null) {
                    serialReader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                }
                response = serialReader.readLine();
            }
        } catch (Exception e) {
            LOGGER.warn("Exception when handling serial event. Exception: ", e);
        }
        LOGGER.debug("Response from controller: {}", response);
        return response;
    }

    public void sendDataToController(String data) {
        LOGGER.debug("Send data to controller. Data: {}", data);
        try {
            serialOut = serialPort.getOutputStream();
            serialOut.write(data.getBytes());
        } catch (Exception e) {
            LOGGER.warn("Exception when sending data to controller. Exception: {}", e);
        }
    }

    @PostConstruct
    private void initialize() {
        LOGGER.debug("Init arduino service. Port number: {}", serialPortNumber);
        try {
            CommPortIdentifier port = CommPortIdentifier.getPortIdentifier(serialPortNumber);
            CommPort commPort = port.open(this.getClass().getName(), 2000);
            serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            InputStream serialIn = serialPort.getInputStream();
            serialOut = serialPort.getOutputStream();
            serialReader = new BufferedReader(new InputStreamReader(serialIn));
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            LOGGER.warn("Exception when init arduino communication service. Exception: {}", e);
        }
    }

    @PreDestroy
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    private void checkClimateData(ClimateData climateData) {
        ControllerConfiguration controllerConfiguration = controllerConfigurationService.get(climateData.getControllerNumber());

        float maxTemperatureConfiguration = controllerConfiguration.getMaxTemperature();
        float maxHumidityConfiguration = controllerConfiguration.getMaxHumidity();
        float minTemperatureConfiguration = controllerConfiguration.getMinTemperature();
        float minHumidityConfiguration = controllerConfiguration.getMinHumidity();
        float controllerTemperature = climateData.getTemperature();
        float controlerHumidity = climateData.getHumidity();

        boolean isTemperatureInRange = minTemperatureConfiguration < controllerTemperature &&
                maxTemperatureConfiguration > controllerTemperature;
        boolean isHumidityInRange = minHumidityConfiguration < controlerHumidity &&
                maxHumidityConfiguration > controlerHumidity;

        if (!isTemperatureInRange || !isHumidityInRange) {
            DataOutOfRangeController dataOutOfRangeController = (DataOutOfRangeController) ControllerViewLoader.load("/fxml/dataOutOfRange.fxml");
            Parent initView = dataOutOfRangeController.getInitView();
            initView.fireEvent(new DataOutOfRangeEvent());
        }
    }
}

package diplom.ponikarov.controller;

import diplom.ponikarov.db.MySqlConnectionManager;
import diplom.ponikarov.parser.ClimateDataParser;
import diplom.ponikarov.dao.mysql.MySqlClimateDataDAO;
import gnu.io.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by Nikita on 04.10.2015.
 */
public class Controller implements SerialPortEventListener {

    private SerialPort serialPort;
    private InputStream serialIn;
    private OutputStream serialOut;
    private BufferedReader serialReader;
    private MySqlClimateDataDAO climateDataDAO;

    @FXML
    TextArea text_area;

    @FXML
    TextField text_field;

    public Controller() {
        initialize();
    }

    @FXML
    public void doRequest() {
        System.out.println("doRequest");
        sendData("1");
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        System.out.println("=====================Serial event==========================");
        String response = getResponse(serialPortEvent);
        text_area.setText(response);
        climateDataDAO.add(ClimateDataParser.parse(response));
        System.out.println(response);
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public String getResponse(SerialPortEvent oEvent) {
        String response = null;
        try {
            switch (oEvent.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE:
                    if (serialReader == null) {
                        serialReader = new BufferedReader(
                                new InputStreamReader(
                                        serialPort.getInputStream()));
                    }
                    response = serialReader.readLine();
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            System.err.println("Exception. response: " + response);
            System.err.println(e.toString());
        }
        return response;
    }

    private void initialize() {
        try {
            CommPortIdentifier port = CommPortIdentifier.getPortIdentifier("COM3");
            CommPort commPort = port.open(this.getClass().getName(), 2000);
            serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialIn = serialPort.getInputStream();
            serialOut = serialPort.getOutputStream();
            serialReader = new BufferedReader(new InputStreamReader(serialIn));
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
//            climateDataDAO = new MySqlClimateDataDAO(new MySqlConnectionManager());
            climateDataDAO = new MySqlClimateDataDAO();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendData(String data) {
        try {
            System.out.println("Sending data: '" + data + "'");

            serialOut = serialPort.getOutputStream();
            serialOut.write(data.getBytes());
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(0);
        }
    }
}

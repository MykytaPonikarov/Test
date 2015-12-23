package diplom.ponikarov;

import diplom.ponikarov.db.MySqlConnectionManager;
import diplom.ponikarov.entity.ClimateData;
import diplom.ponikarov.dao.mysql.MySqlClimateDataDAO;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;


public class Test implements SerialPortEventListener {

    SerialPort serialPort = null;

    private static final String PORT_NAMES[] = {
            "/dev/tty.usbmodem", // Mac OS X
            "/dev/usbdev",      // Linux
            "/dev/tty",         // Linux
            "/dev/serial",      // Linux
            "COM3",             // Windows
    };

    private String appName;
    private BufferedReader input;
    private OutputStream output;

    private static final int TIME_OUT = 1000; // Port open timeout
    private static final int DATA_RATE = 9600; // Arduino serial port

    public Test() {
        appName = getClass().getName();
    }

    public boolean initialize() {
        try {
            CommPortIdentifier portId = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

            // Enumerate system ports and try connecting to Arduino over each
            //
            System.out.println("Trying:");
            while (portId == null && portEnum.hasMoreElements()) {
                // Iterate through your host computer's serial port IDs
                //
                CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
                System.out.println("   port" + currPortId.getName());
                for (String portName : PORT_NAMES) {
                    if (currPortId.getName().equals(portName)
                            || currPortId.getName().startsWith(portName)) {

                        // Try to connect to the Arduino on this port
                        //
                        // Open serial port
                        serialPort = (SerialPort) currPortId.open(appName, TIME_OUT);
                        portId = currPortId;
                        System.out.println("Connected on port" + currPortId.getName());
                        break;
                    }
                }
            }

            if (portId == null || serialPort == null) {
                System.out.println("Oops... Could not connect to Arduino");
                return false;
            }

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);

            // Give the Arduino some time
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendData(String data) {
        try {
            System.out.println("Sending data: '" + data + "'");

            output = serialPort.getOutputStream();
            output.write(data.getBytes());
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(0);
        }
    }

    //
    // This should be called when you stop using the port
    //
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    //
    // Handle serial port event
    //
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        String response = getResponse(oEvent);
        System.out.println("qqqqqqqqqqqqqqqqqqqq");
        System.out.println(response);
    }

    public String getResponse(SerialPortEvent oEvent) {
        String response = null;
        try {
            switch (oEvent.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE:
                    if (input == null) {
                        input = new BufferedReader(
                                new InputStreamReader(
                                        serialPort.getInputStream()));
                    }
                    response = input.readLine();
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return response;
    }

    public static void main(String[] args) throws InterruptedException {
        MySqlConnectionManager mySqlConnectionManager = new MySqlConnectionManager();
//        MySqlClimateDataDAO dao = new MySqlClimateDataDAO(mySqlConnectionManager);
        MySqlClimateDataDAO dao = new MySqlClimateDataDAO();
        ClimateData climateData = new ClimateData();
        climateData.setHumidity(1);
        climateData.setStatus("OK");
        climateData.setTemperature(2);
        dao.add(climateData);
    }
}
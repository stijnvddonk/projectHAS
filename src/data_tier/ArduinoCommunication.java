package data_tier;

import java.io.IOException;
import java.util.ArrayList;
import com.fazecast.jSerialComm.SerialPort;

public class ArduinoCommunication {

	static SerialPort sp = SerialPort.getCommPort("COM3");

	// To send data to the arduino:
	public static void main(String[] args) throws IOException, InterruptedException {
		sp.setComPortParameters(9800, 8, 1, 0);
		sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

		// Check if the port is open:
		if (sp.openPort()) {
			System.out.println("Port is openen :)");
		} else {
			System.out.println("Port is closed :(");
			return;
		}

		// Send number 0 to 4 to the arduino:
		for (Integer i = 0; i < 5; ++i) {
			sp.getOutputStream().write(i.byteValue());
			sp.getOutputStream().flush();
			// Check if the number is sent:
			//System.out.println("Sent number: " + i);
			Thread.sleep(1000);
		}

		// Check if the port is closed:
		if (sp.closePort()) {
			System.out.println("Port is closed :)");
		} else {
			System.out.println("Port is NOT closed :(");
		}
	}
	
	// Method to get al the port numbers available. 
	public static ArrayList<String> portNumbersAvailable() {
		ArrayList<String> allPorts = new ArrayList<>();
		SerialPort ports[] = SerialPort.getCommPorts();
		int i = 1;
		String result = "";
		for (SerialPort port : ports) {
			result = "Port Available" + i++ + ". " + port.getSystemPortName();
			allPorts.add(result);
		}
		return allPorts;
	}
	
	/* LET OP: Onderstaande methodes werken nog niet!!!*/
	public String sendToArduino(int number) {
		sp.setComPortParameters(9800, 8, 1, 0);
		sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
		String result = "";

		// Controleren of de poort open is:
		if (sp.openPort()) {
			System.out.println("Port is open :)");
		} else {
			System.out.println("Port is NOT open :(");
		}

		// Cijfers 0 tot 4 sturen naar de Arduino:
			//sp.getOutputStream().write(number.byteValue());
			//sp.getOutputStream().flush();
			//System.out.println("Sent number: " + number);
			//Thread.sleep(1000);

		// Controleren of de poort gesloten is:
		if (sp.closePort()) {
			System.out.println("Port is closed :)");
		} else {
			System.out.println("Port is NOT closed :(");
		}
		return result;
	}
}

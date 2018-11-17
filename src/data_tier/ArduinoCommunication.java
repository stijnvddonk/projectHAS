package data_tier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.event.MouseEvent;

public class ArduinoCommunication {

	private static InputStream serialIn;
	private static OutputStream serialOut;
	private static BufferedReader serialReader;
	private static SerialPort sp;

	// To send data to the arduino:
	public static void main(String[] args) throws IOException, InterruptedException {
		// Set the serial port:
		sp = SerialPort.getCommPort("COM4");
		// Set parameters:
		sp.setComPortParameters(9800, 8, 1, 0);
		// Set Strams:
		serialIn = sp.getInputStream();
		serialOut = sp.getOutputStream();
		serialReader = new BufferedReader(new InputStreamReader(serialIn));

		// Write to arduino:
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
			// sp.getInputStream().read();
			sp.getOutputStream().flush();
			// Check if the number is sent:
			System.out.println("Sent number: " + i);
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

	// Method to read data from the arduino::
	public String dataArduino() {
		// Read Arduino:
		sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		Scanner dataFromArduino = new Scanner(sp.getInputStream());
		// String result = "";
		while (dataFromArduino.hasNextLine()) {
			System.out.println(dataFromArduino.nextLine());

		}
		dataFromArduino.close();
		return null;
	}

	// testmethode If you prefer to use the standardized Java
	// InputStream/OutputStream interfaces to interact with the serial port, you can
	// do so by requesting these interfaces directly from the SerialPort object as
	// follows
	public void test() {
		sp.openPort();
		sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
		InputStream in = sp.getInputStream();
		try {
			for (int j = 0; j < 1000; ++j)
				System.out.print((char) in.read());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sp.closePort();
	}

	// Non-blocking reading is the default mode used by this library. It can be
	// selected simply by opening the COM port with no other options:
	public void NonBlockingReading() {
		sp.openPort();
		try {
			while (true) {
				while (sp.bytesAvailable() == 0)
					Thread.sleep(20);

				byte[] readBuffer = new byte[sp.bytesAvailable()];
				int numRead = sp.readBytes(readBuffer, readBuffer.length);
				System.out.println("Read " + numRead + " bytes.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sp.closePort();
	}

	/* LET OP: Onderstaande methodes werken nog niet!!! */
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
		// sp.getOutputStream().write(number.byteValue());
		// sp.getOutputStream().flush();
		// System.out.println("Sent number: " + number);
		// Thread.sleep(1000);

		// Controleren of de poort gesloten is:
		if (sp.closePort()) {
			System.out.println("Port is closed :)");
		} else {
			System.out.println("Port is NOT closed :(");
		}
		return result;
	}

	// verander de status van de lamp aan de hand van een knop.
	public void sendLampNumber(int lamp, MouseEvent e, JButton button1, JButton button2) {

		if (e.getComponent() == button1) {
			DataLogger.deviceLog("Button 1 released");
			try {
				serialOut.write("lamp1".getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getComponent() == button2) {
			DataLogger.deviceLog("Button 2 released");
			try {
				serialOut.write("lamp2".getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void serialEvent() {
		//DataLogger.deviceLog("serialEvent: " + e.toString());
		try {
			String line = serialReader.readLine();
			DataLogger.deviceLog("READ from serial: " + line);
			if (line.startsWith("SS:") && line.length() == 14) {
				visualization(line);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void visualization(String line) {
		// Parse the information
		String[] values = line.split(":");

		int light = Integer.parseInt(values[1], 16);
		//lightPanel.setValue(light);
		//lightPanel.repaint();

		int potentiometer = Integer.parseInt(values[2], 16);
		//potentiometerPanel.setValue(potentiometer);
		//potentiometerPanel.repaint();

		int buttons = Integer.parseInt(values[3], 16);
		//buttonsinPanel.setValue(buttons);
		//buttonsinPanel.repaint();

		DataLogger.deviceLog("LIGHT=" + light + "  POTENTIOMETER=" + potentiometer + " BUTTONS=" + buttons);

	}
}

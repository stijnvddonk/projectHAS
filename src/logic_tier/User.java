package logic_tier;

import java.sql.Connection;
import java.sql.PreparedStatement;

import data_tier.QueryBuilder;
import data_tier.DatabaseManager;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

	protected DatabaseManager dbm = new DatabaseManager();
	protected QueryBuilder qb = new QueryBuilder();
	protected Connection db;

	protected int userID;
	protected String name;
	protected String email;
	private String password;

	private String[][] convertArrayListToArray(ArrayList<ArrayList<String>> output) {
		String[][] array = new String[output.size()][];
		for (int i = 0; i < output.size(); i++) {
			ArrayList<String> row = output.get(i);
			System.out.println(output.get(i).toString());
			array[i] = row.toArray(new String[row.size()]);
		}
		return array;
	}

	public String[][] Devices() {
		System.out.println("Starting data retrieval");
		ResultSet rs = null;
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		try {
			System.out.println("Start Try");
			rs = qb.Devices();
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				row.add(rs.getString("name"));
				output.add(row);
			}
			System.out.println(output.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		return convertArrayListToArray(output);
	}
	
	public String[][] Users() {
		System.out.println("Starting data retrieval");
		ResultSet rs = null;
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		try {
			System.out.println("Start Try");
			rs = qb.Users();
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				row.add(rs.getString("UserName"));
				output.add(row);
			}
			System.out.println(output.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		return convertArrayListToArray(output);
	}

	public Integer getDeviceTypeID(String deviceName) {
		System.out.println("Device Type ID Database Loaded");
		Integer typeID = null;
		ResultSet rs = null;
		try {
			rs = qb.DevicesTypeID(deviceName);
			System.out.println("# - Updating...");

			while (rs.next()) {
				System.out.println(rs.getInt("typeID"));
				typeID = rs.getInt("typeID");
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
		return typeID;
	}

	public Integer getDeviceEnabledStatus(String deviceName) {
		System.out.println("Device Enabled Status Database Loaded");
		Integer typeID = null;
		ResultSet rs = null;
		try {
			rs = qb.DevicesEnabledStatus(deviceName);
			System.out.println("# - Updating...");

			while (rs.next()) {
				System.out.println(rs.getInt("DeviceEnabled"));
				typeID = rs.getInt("DeviceEnabled");
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
		return typeID;
	}

	public void getDeviceTypes(JComboBox selectBox) {
		System.out.println("Device Type Database Loaded");
		ResultSet rs = null;
		try {
			rs = qb.getDeviceTypes();
			System.out.println("# - Updating...");

			while (rs.next()) {
				selectBox.addItem(rs.getString("Omschrijving"));
				System.out.println(rs.getString("Omschrijving"));
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
	}

	public void enableDisableDevice(int id, String deviceName) {
		try {
			qb.enableDisableDevice(id, deviceName);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Integer getDeviceTimerEnabledStatus(String deviceName) {
		System.out.println("Device Timer Enabled Status Database Loaded");
		Integer typeID = null;
		ResultSet rs = null;
		try {
			rs = qb.DevicesTimerStatus(deviceName);
			System.out.println("# - Updating...");

			while (rs.next()) {
				System.out.println(rs.getInt("timerStatus"));
				typeID = rs.getInt("timerStatus");
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
		return typeID;
	}

	public void enableDisableDeviceTimer(int id, String deviceName) {
		try {
			qb.enableDisableTimerDevice(id, deviceName);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void getOnOffTimer(JComboBox timerOn, JComboBox timerOff, String deviceName) {
		System.out.println("Device Timer Database Loaded");
		ResultSet rs = null;
		try {
			rs = qb.getStartEndTime(deviceName);
			System.out.println("# - Updating...");

			while (rs.next()) {
				timerOn.setSelectedItem(rs.getString("timerOn"));
				timerOff.setSelectedItem(rs.getString("timerOff"));
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
	}

	public void setStartEndTime(String timerOn, String timerOff, String deviceName) {
		try {
			qb.setStartEndTime(timerOn,timerOff, deviceName);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteDevice(String deviceName) {
		try {
			qb.deleteDevice(deviceName);
			JOptionPane.showMessageDialog(null, "Device has been Deleted!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String getTopIP() {
		System.out.println("Device Timer Enabled Status Database Loaded");
		String lastIP = null;
		ResultSet rs = null;
		try {
			rs = qb.getTopIP();
			while (rs.next()) {
				System.out.println(rs.getString("IP"));
				lastIP = rs.getString("IP");
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
		return lastIP;
	}
	
	public Integer getTypeID(String type) {
		Integer typeID = null;
		ResultSet rs = null;
		try {
			rs = qb.getDeviceTypesID(type);
			while (rs.next()) {
				System.out.println(rs.getInt("id"));
				typeID = rs.getInt("id");
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
		return typeID;
	}

	public void addNewDevice(String deviceName, String IPAdres, int typeID) {
		String randomChars = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder randomStr = new StringBuilder();
		String name = "0050";
		Random rnd = new Random();
		while (randomStr.length() < 8) {
			int index = (int) (rnd.nextFloat() * randomChars.length());
			randomStr.append(randomChars.charAt(index));
			}
			String MACAdres = name + randomStr.toString();
			MACAdres = MACAdres.replaceAll("..", "$0:").substring(0, 17);
		try {
			qb.addNewDevice(deviceName, IPAdres, typeID, MACAdres);
			JOptionPane.showMessageDialog(null, "Device has been Added!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

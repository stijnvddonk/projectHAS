package logic_tier;

import data_tier.DataLogger;
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
;
	protected QueryBuilder qb = new QueryBuilder();

	protected Integer userID;
	protected String username;
	protected String password;
	protected Integer role;
	protected String token;
	protected Integer active;

	public void setUser(Integer uid, String uName, String uPass, Integer uRole, String uToken, Integer uAct) {
		userID = uid;
		username = uName;
		password = uPass;
		role = uRole;
		token = uToken;
		active = uAct;
	}

	private String[][] convertArrayListToArray(ArrayList<ArrayList<String>> output) {
		String[][] array = new String[output.size()][];
		for (int i = 0; i < output.size(); i++) {
			ArrayList<String> row = output.get(i);
			DataLogger.log(output.get(i).toString() + "\n");
			array[i] = row.toArray(new String[row.size()]);
		}
		return array;
	}

	public String[][] Devices() {
		DataLogger.deviceLog("Starting data retrieval\n");
		ResultSet rs = null;
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		try {
			DataLogger.deviceLog("Start Try\n");
			rs = qb.Devices();
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				row.add(rs.getString("name"));
				output.add(row);
			}
			DataLogger.deviceLog(output.toString() + "\n");
		} catch (Exception e) {
			DataLogger.errorLog(e);
		}
		return convertArrayListToArray(output);
	}

	public Integer getDeviceTypeID(String deviceName) {
		DataLogger.deviceLog("Device Type ID Database Loaded\n");
		Integer typeID = null;
		ResultSet rs = null;
		try {
			rs = qb.DevicesTypeID(deviceName);

			while (rs.next()) {
				typeID = rs.getInt("typeID");
			}
		} catch (Exception e) {
			DataLogger.errorLog(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
		return typeID;
	}

	public Integer getDeviceEnabledStatus(String deviceName) {
		DataLogger.deviceLog("Device Enabled Status Database Loaded\n");
		Integer typeID = null;
		ResultSet rs = null;
		try {
			rs = qb.DevicesEnabledStatus(deviceName);

			while (rs.next()) {
				typeID = rs.getInt("DeviceEnabled");
			}
		} catch (Exception e) {
			DataLogger.errorLog(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
		return typeID;
	}

	public void getDeviceTypes(JComboBox selectBox) {
		DataLogger.deviceLog("Device Type Database Loaded\n");
		ResultSet rs = null;
		try {
			rs = qb.getDeviceTypes();

			while (rs.next()) {
				selectBox.addItem(rs.getString("Omschrijving"));
			}
		} catch (Exception e) {
			DataLogger.errorLog(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
	}

	public void enableDisableDevice(int id, String deviceName) {
		try {
			qb.enableDisableDevice(id, deviceName);
		} catch (Exception e) {
			DataLogger.errorLog(e);
		}
	}

	public Integer getDeviceTimerEnabledStatus(String deviceName) {
		DataLogger.deviceLog("Device Timer Enabled Status Database Loaded\n");
		Integer typeID = null;
		ResultSet rs = null;
		try {
			rs = qb.DevicesTimerStatus(deviceName);

			while (rs.next()) {
				typeID = rs.getInt("timerStatus");
			}
		} catch (Exception e) {
			DataLogger.errorLog(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
		return typeID;
	}

	public void enableDisableDeviceTimer(int id, String deviceName) {
		try {
			qb.enableDisableTimerDevice(id, deviceName);
		} catch (Exception e) {
			DataLogger.errorLog(e);
		}
	}

	public void getOnOffTimer(JComboBox timerOn, JComboBox timerOff, String deviceName) {
		DataLogger.deviceLog("Device Timer Database Loaded\n");
		ResultSet rs = null;
		try {
			rs = qb.getStartEndTime(deviceName);

			while (rs.next()) {
				timerOn.setSelectedItem(rs.getString("timerOn"));
				timerOff.setSelectedItem(rs.getString("timerOff"));
			}
		} catch (Exception e) {
			DataLogger.errorLog(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
	}

	public void setStartEndTime(String timerOn, String timerOff, String deviceName) {
		try {
			qb.setStartEndTime(timerOn,timerOff, deviceName);
		} catch (Exception e) {
			DataLogger.errorLog(e);
		}
	}

	public void deleteDevice(String deviceName) {
		DataLogger.deviceLog("Deleting Device\n");
		try {
			qb.deleteDevice(deviceName);
			JOptionPane.showMessageDialog(null, "Device has been Deleted!");
		} catch (Exception e) {
			DataLogger.errorLog(e);
		}
	}

	public String getTopIP() {
		DataLogger.deviceLog("Device Timer Enabled Status Database Loaded\n");
		String lastIP = null;
		ResultSet rs = null;
		try {
			rs = qb.getTopIP();
			while (rs.next()) {
				lastIP = rs.getString("IP");
			}
		} catch (Exception e) {
			DataLogger.errorLog(e);
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
				typeID = rs.getInt("id");
			}
		} catch (Exception e) {
			DataLogger.errorLog(e);
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
			DataLogger.errorLog(e);
		}
	}

}

package logic_tier;

import java.sql.Connection;
import java.sql.PreparedStatement;

import data_tier.QueryBuilder;
import data_tier.passwordAuthentication;
import data_tier.DataLogger;
import data_tier.DatabaseManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class User {
	;
	protected QueryBuilder qb;
	protected passwordAuthentication pswa = new passwordAuthentication();

	protected Integer userID;
	protected String username;
	protected String emailaddress;
	protected String fullname;
	protected String password;
	protected Integer role;
	protected String token;
	protected Timestamp lastLogin;
	protected Integer active;
	protected ArrayList<ArrayList<String>> userNameList = null;
	protected List<User> userObject = new ArrayList<>();
	protected ArrayList<ArrayList<String>> deviceNameList = null;
	protected List<Device> deviceObject = new ArrayList<>();
	
	public User(QueryBuilder _qb) {
		qb = _qb;
	}

	public void setUser(Integer uid, String uName, String uPass, Integer uRole, String uToken, Integer uAct) {
		userID = uid;
		username = uName;
		password = uPass;
		role = uRole;
		token = uToken;
		active = uAct;
	}

	public void setAdditionalInfo(String email, String name, Timestamp llog) {
		emailaddress = email;
		fullname = name;
		lastLogin = llog;

	}

	private String[][] convertArrayListToArray(ArrayList<ArrayList<String>> output) {
		String[][] array = new String[output.size()][];
		for (int i = 0; i < output.size(); i++) {
			ArrayList<String> row = output.get(i);
			DataLogger.systemLog(output.get(i).toString());
			array[i] = row.toArray(new String[row.size()]);
		}
		return array;
	}

	public String[][] Devices() {
		DataLogger.systemLog("/n/n-----------------/nStarting userdata retrieval v2/n");
		if (deviceNameList == null) {
			ResultSet rs = null;
			ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
			try {
				rs = qb.Devices();
				while (rs.next()) {
					ArrayList<String> row = new ArrayList<String>();
					Integer _did = rs.getInt("deviceID");
					String _dna = rs.getString("name");
					String _dMAC = rs.getString("MAC");
					String _dIP = rs.getString("IP");
					String _dversion = rs.getString("versionNumber");
					Integer _dtype = rs.getInt("typeID");
					String _ddescription = rs.getString("Omschrijving");
					Integer _dstatus = rs.getInt("DeviceEnabled");
					Integer _dtimerstatus = rs.getInt("timerStatus");
					String _don = rs.getString("timerOn");
					String _doff = rs.getString("timerOff");

					row.add(_dna);
					output.add(row);

					Device tempDevObj = new Device();
					tempDevObj.setDevice(_did, _dna, _dMAC, _dIP, _dversion, _dtype, _dstatus);
					tempDevObj.setAdditionalInfo(_ddescription, _dtimerstatus, _don, _doff);
					deviceObject.add(tempDevObj);

				}
				DataLogger.systemLog(output.toString());
			} catch (Exception e) {
				DataLogger.errorLog(e);
			}
			deviceNameList = output;
		}
		return convertArrayListToArray(deviceNameList);
	}

	public String[][] Users() {
		DataLogger.systemLog("/n/n-----------------/nStarting userdata retrieval v2/n");
		if (userNameList == null) {
			ResultSet rs = null;
			ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
			try {
				rs = qb.Users();
				while (rs.next()) {
					ArrayList<String> row = new ArrayList<String>();
					Integer _uid = rs.getInt("userID");
					String _una = rs.getString("UserName");
					String _ups = rs.getString("Password");
					Integer _uro = rs.getInt("Role");
					String _uto = rs.getString("Token");
					Integer _uac = rs.getInt("Active");
					String _uea = rs.getString("Email");
					String _ufn = rs.getString("Name");
					Timestamp _ull = rs.getTimestamp("lastLogin");

					row.add(_una);
					output.add(row);

					User tempUsrObj = new User(qb);
					tempUsrObj.setUser(_uid, _una, _ups, _uro, _uto, _uac);
					tempUsrObj.setAdditionalInfo(_uea, _ufn, _ull);
					userObject.add(tempUsrObj);

				}
				DataLogger.systemLog(output.toString());
			} catch (Exception e) {
				DataLogger.errorLog(e);
			}
			userNameList = output;
		}
		return convertArrayListToArray(userNameList);
	}

	/*
	 * Method: createNewUser Return: Integer, success (1) / Failed (0)
	 */
	public void createNewUser(String ufn, String uun, String uea, String uro) {
		Integer result = 1; // Standard it will say it successfull.
		ArrayList<String> row = new ArrayList<String>();
		Predicate<User> pun = e -> e.username.contains(uun);
		Predicate<User> pea = e -> e.emailaddress.contains(uea);
		DataLogger.systemLog("uun: " + uun + "\n");

		if (userObject.stream().allMatch(pun)) {
			DataLogger.systemLog("Username " + uun + " found\n");
			result = 0;
		}
		// Emailadres wordt gevonden wanneer deze leeg is
		if (userObject.stream().allMatch(pea)) {
			DataLogger.systemLog("Emailaddress " + uea + " found\n");
			result = 0;
		}

		if (result == 1) {
			String pass = pswa.getPeperString(25);
			Integer role = 1;
			String token = pswa.hash(pass.toCharArray());
			try {
				qb.insertUser(ufn, uun, pass, uea, role, token);
				row.add(uun);
				userNameList.add(row);
				JOptionPane.showMessageDialog(null, "User has been Added!");
			} catch (Exception e) {
				DataLogger.errorLog(e);
			}

		} else {
			DataLogger.systemLog("Error");
		}

	}

	public Integer getDeviceTypeID(String deviceName) {
		DataLogger.systemLog("Device Type ID Database Loaded");
		Integer typeID = null;
		ResultSet rs = null;
		try {
			rs = qb.DevicesTypeID(deviceName);
			DataLogger.systemLog("# - Updating...");

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
		DataLogger.systemLog("Device Enabled Status Database Loaded");
		Integer typeID = null;
		ResultSet rs = null;
		try {
			rs = qb.DevicesEnabledStatus(deviceName);
			DataLogger.systemLog("# - Updating...");

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
		DataLogger.systemLog("Device Type Database Loaded");
		ResultSet rs = null;
		try {
			rs = qb.getDeviceTypes();
			DataLogger.systemLog("# - Updating...");

			while (rs.next()) {
				selectBox.addItem(rs.getString("Omschrijving"));
				DataLogger.systemLog(rs.getString("Omschrijving"));
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
		DataLogger.systemLog("Device Timer Enabled Status Database Loaded");
		Integer typeID = null;
		ResultSet rs = null;
		try {
			rs = qb.DevicesTimerStatus(deviceName);
			DataLogger.systemLog("# - Updating...");

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
		DataLogger.systemLog("Device Timer Database Loaded");
		ResultSet rs = null;
		try {
			rs = qb.getStartEndTime(deviceName);
			DataLogger.systemLog("# - Updating...");

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
			qb.setStartEndTime(timerOn, timerOff, deviceName);
		} catch (Exception e) {
			DataLogger.errorLog(e);
		}
	}

	public void deleteDevice(String deviceName) {
		ArrayList<String> row = new ArrayList<String>();
		try {
			qb.deleteDevice(deviceName);
			row.add(deviceName);
			deviceNameList.remove(row);
			JOptionPane.showMessageDialog(null, "Device has been Deleted!");
		} catch (Exception e) {
			DataLogger.errorLog(e);
		}
	}

	public String getTopIP() {
		DataLogger.systemLog("Device Timer Enabled Status Database Loaded");
		String lastIP = null;
		ResultSet rs = null;
		try {
			rs = qb.getTopIP();
			while (rs.next()) {
				DataLogger.systemLog(rs.getString("IP"));
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

	public void createNewDevice(String dName, String dIP, int dType) {
		Integer result = 1; // Standard it will say it successfull.
		ArrayList<String> row = new ArrayList<String>();
		Predicate<Device> pdn = e -> e.name.contains(dName);
		DataLogger.systemLog("dName: " + dName + "\n");
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
		if (deviceObject.stream().allMatch(pdn)) {
			DataLogger.systemLog("Devicename " + pdn + " found\n");
			result = 0;
		}
		if (result == 1) {
			String pass = pswa.getPeperString(25);
			Integer role = 1;
			String token = pswa.hash(pass.toCharArray());
			try {
				qb.addNewDevice(dName, dIP, dType, MACAdres);
				row.add(dName);
				deviceNameList.add(row);
				JOptionPane.showMessageDialog(null, "Device has been Added!");
			} catch (Exception e) {
				DataLogger.errorLog(e);
			}
		}
	}

}

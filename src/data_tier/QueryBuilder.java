package data_tier;

import java.util.Random;
import java.sql.*;

public class QueryBuilder {

	private DatabaseManager dbm;
	private Config cfg = new Config();
	private Boolean debug = Boolean.parseBoolean(cfg.getProperty("debug"));

	// The call of the QueryBuilder
	public QueryBuilder() {
		if (this.debug) System.out.print("Query builder loaded\n");
		dbm = new DatabaseManager();
	}

	// Get the device data
	public String setDeviceData(int deviceID) {
		if (this.debug) System.out.print("QueryBuilder: setDeviceData\n");
		// Creating a query @Stijn add more info please
		String query = "SELECT deviceID, name, MAC, IP, versionNumber, typeID, Omschrijving, DeviceEnabled, timerStatus, timerOn, timerOff FROM Devices d JOIN DeviceTypes dt ON d.typeID=dt.ID WHERE d.deviceID = " + deviceID;
		if (this.debug) System.out.print(query);
		return query;
	}

	// Get typeID device
	public ResultSet DevicesTypeID(String deviceName) {
	String query = "SELECT typeID FROM Devices WHERE name ='"+deviceName+"'";
	System.out.print(query);
	if (this.debug) System.out.print(query);
	return dbm.execute(query);
}
	// Get a list of devices
	public ResultSet Devices() {
		if (this.debug) System.out.print("QueryBuilder: Devices\n");
		// @Stijn What do you want to do with this??
		String query = "SELECT name FROM Devices";
		if (this.debug) System.out.print(query);
		return dbm.execute(query);
	}
	
	// Get a list of Users
	public ResultSet Users() {
		if (this.debug) System.out.print("QueryBuilder: Devices\n");
		// @Stijn What do you want to do with this??
		String query = "SELECT UserName FROM Users";
		if (this.debug) System.out.print(query);
		return dbm.execute(query);
	}

	// Get last IP device
	public ResultSet getTopIP() {
	String query = "SELECT IP FROM Devices ORDER BY IP DESC LIMIT 1";
	System.out.print(query);
	if (this.debug) System.out.print(query);
	return dbm.execute(query);
}

	// Create new Device
	public Integer addNewDevice(String deviceName, String IPAdres, int typeID, String MACAdres) {
		if (this.debug) System.out.print("QueryBuilder: insertNewDevice\n");
		// Creating a query to creat a new device
		String query = "INSERT INTO Devices (`name`, `MAC`, `IP`, `versionNumber`, `typeID`, `DeviceEnabled`, `timerStatus`, `timerOn`, `timerOff`) VALUES ('" +deviceName+"','"+MACAdres+ "','"+IPAdres+"','v1.1.1',"+typeID+",1,0,null,null)";
		if (this.debug) System.out.print(query);
		return dbm.__insert(query);
	}

	// Get enabledStatus device
	public ResultSet DevicesEnabledStatus(String deviceName) {
	String query = "SELECT DeviceEnabled FROM Devices WHERE name ='"+deviceName+"'";
	System.out.print(query);
	if (this.debug) System.out.print(query);
	return dbm.execute(query);
}

	// Disble Device
	public void enableDisableDevice(int id, String deviceName) {
		if (this.debug) System.out.print("QueryBuilder: disableDevice\n");
		// Creating a query to disable a device
			String query = "UPDATE Devices SET DeviceEnabled = "+id+" WHERE name = '" + deviceName + "'";
			System.out.print(query);
		if (this.debug) System.out.print(query);
		dbm.update(query);
	}

	// Get startEndTime
	public ResultSet getStartEndTime(String deviceName) {
	String query = "SELECT timerOn, timerOff FROM Devices WHERE name ='"+deviceName+"'";
	System.out.print(query);
	if (this.debug) System.out.print(query);
	return dbm.execute(query);
}
	// Set startEndTime
	public void setStartEndTime(String timerOn, String timerOff, String deviceName) {
		// Creating a query to disable a device
			String query = "UPDATE Devices SET timerOn = '"+ timerOn +"', timerOff = '"+ timerOff +"' WHERE name = '" + deviceName + "'";
			System.out.print(query);
		if (this.debug) System.out.print(query);
		dbm.update(query);
	}

	// Get DevicesTimerStatus device
	public ResultSet DevicesTimerStatus(String deviceName) {
	String query = "SELECT timerStatus FROM Devices WHERE name ='"+deviceName+"'";
	System.out.print(query);
	if (this.debug) System.out.print(query);
	return dbm.execute(query);
}

	// Disble enableDisableTimerDevice
	public void enableDisableTimerDevice(int id, String deviceName) {
		// Creating a query to disable a device
			String query = "UPDATE Devices SET timerStatus = "+id+" WHERE name = '" + deviceName + "'";
			System.out.print(query);
		if (this.debug) System.out.print(query);
		dbm.update(query);
	}

	// Remove Device
	public void deleteDevice(String deviceName) {
		if (this.debug) System.out.print("QueryBuilder: deleteDevice\n");
		// Creating a query to remove a device
		String query = "DELETE FROM Devices WHERE name='" + deviceName + "'";
		//System.out.print(query);
		if (this.debug) System.out.print(query);
		dbm.update(query);
	}

	// List Device Types
	public ResultSet getDeviceTypes() {
		String query = "SELECT Omschrijving FROM DeviceTypes";
		System.out.println(query);
		return dbm.execute(query);
	}
	
	public ResultSet getDeviceTypesID(String omschrijving) {
		String query = "SELECT id FROM DeviceTypes WHERE Omschrijving = '" + omschrijving + "'";
		System.out.println(query);
		return dbm.execute(query);
	}

	public String selectSaltedPassword(String user, String pass) {
		if (this.debug) System.out.print("QueryBuilder: selectSaltedPassword\n");
		String query = "SELECT * FROM Peper WHERE peperId=(SELECT userId FROM Users WHERE username='" + user + "' LIMIT 1) LIMIT 1";
		if (this.debug) System.out.print(query + "\n");
		if (this.debug) System.out.print(cfg.getProperty("salt") + pass + "\n");
		return cfg.getProperty("salt") + pass + dbm.getPassPeper(query);
	}

	public ResultSet selectLogin(String user, String peperredPass) {
		if (this.debug) System.out.print("QueryBuilder: selectLogin\n");
		String query = "SELECT * FROM Users WHERE username='" + user + "' AND password='" + peperredPass + "' LIMIT 1";
		System.out.println(query);
		return dbm.execute(query);
	}

}

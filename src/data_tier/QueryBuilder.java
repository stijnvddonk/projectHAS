package data_tier;

import java.sql.*;

public class QueryBuilder {

	private DatabaseManager dbm;
	private Config cfg = new Config();
	private Boolean debug = Boolean.parseBoolean(cfg.getProperty("debug"));

	// The call of the QueryBuilder
	public QueryBuilder() {
		if (this.debug) DataLogger.log("Query builder loaded\n");
		dbm = new DatabaseManager();
	}

	// Get the device data
	public String setDeviceData(int deviceID) {
		if (this.debug) DataLogger.deviceLog("QueryBuilder: setDeviceData\n");
		String query = "SELECT deviceID, name, MAC, IP, versionNumber, typeID, Omschrijving, DeviceEnabled, timerStatus, timerOn, timerOff FROM Devices d JOIN DeviceTypes dt ON d.typeID=dt.ID WHERE d.deviceID = " + deviceID;
		if (this.debug) DataLogger.deviceLog("- Query: " + query + "\n");
		return query;
	}

	// Get typeID device
	public ResultSet DevicesTypeID(String deviceName) {
		if (debug) DataLogger.deviceLog("QueryBuilder: DevicesTypeID\n");
		String query = "SELECT typeID FROM Devices WHERE name ='"+deviceName+"'";
		if (this.debug) DataLogger.deviceLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}
	// Get a list of devices
	public ResultSet Devices() {
		if (this.debug) DataLogger.deviceLog("QueryBuilder: Devices\n");
		String query = "SELECT name FROM Devices";
		if (this.debug) DataLogger.deviceLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// Get last IP device
	public ResultSet getTopIP() {
		if (debug) DataLogger.deviceLog("QueryBuilder: getTopIP\n");
		String query = "SELECT IP FROM Devices ORDER BY IP DESC LIMIT 1";
		if (this.debug) DataLogger.deviceLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// Create new Device
	public Integer addNewDevice(String deviceName, String IPAdres, int typeID, String MACAdres) {
		if (this.debug) DataLogger.deviceLog("QueryBuilder: insertNewDevice\n");
		// Creating a query to create a new device
		String query = "INSERT INTO Devices (`name`, `MAC`, `IP`, `versionNumber`, `typeID`, `DeviceEnabled`, `timerStatus`, `timerOn`, `timerOff`) VALUES ('" +deviceName+"','"+MACAdres+ "','"+IPAdres+"','v1.1.1',"+typeID+",1,0,null,null)";
		if (this.debug) DataLogger.deviceLog("- Query: " + query + "\n");
		return dbm.__insert(query);
	}

	// Get enabledStatus device
	public ResultSet DevicesEnabledStatus(String deviceName) {
		if (debug) DataLogger.deviceLog("QueryBuilder: DevicesEnabledStatus\n");
		String query = "SELECT DeviceEnabled FROM Devices WHERE name ='"+deviceName+"'";
		if (this.debug) DataLogger.deviceLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// disable Device
	public void enableDisableDevice(int id, String deviceName) {
		if (this.debug) DataLogger.deviceLog("QueryBuilder: disableDevice\n");
		// Creating a query to disable a device
			String query = "UPDATE Devices SET DeviceEnabled = "+id+" WHERE name = '" + deviceName + "'";
		if (this.debug) DataLogger.deviceLog("- Query: " + query + "\n");
		dbm.update(query);
	}

	// Get startEndTime
	public ResultSet getStartEndTime(String deviceName) {
		if (debug) DataLogger.deviceLog("QueryBuilder: getStartEndTime\n");
		String query = "SELECT timerOn, timerOff FROM Devices WHERE name ='"+deviceName+"'";
		if (this.debug) DataLogger.deviceLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}
	// Set startEndTime
	public void setStartEndTime(String timerOn, String timerOff, String deviceName) {
			String query = "UPDATE Devices SET timerOn = '"+ timerOn +"', timerOff = '"+ timerOff +"' WHERE name = '" + deviceName + "'";
		if (this.debug) DataLogger.deviceLog("- Query: " + query + "\n");
		dbm.update(query);
	}

	// Get DevicesTimerStatus device
	public ResultSet DevicesTimerStatus(String deviceName) {
		if (debug) DataLogger.deviceLog("QueryBuilder: DevicesTimerStatus\n");
		String query = "SELECT timerStatus FROM Devices WHERE name ='"+deviceName+"'";
		if (this.debug) DataLogger.deviceLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// Disable enableDisableTimerDevice
	public void enableDisableTimerDevice(int id, String deviceName) {
		if (debug) DataLogger.deviceLog("QueryBuilder: enableDisableTimerDevice\n");
		// Creating a query to disable a device
		String query = "UPDATE Devices SET timerStatus = "+id+" WHERE name = '" + deviceName + "'";
		if (this.debug) DataLogger.deviceLog("- Query: " + query + "\n");
		dbm.update(query);
	}

	// Remove Device
	public void deleteDevice(String deviceName) {
		if (this.debug) DataLogger.deviceLog("QueryBuilder: deleteDevice\n");
		// Creating a query to remove a device
		String query = "DELETE FROM Devices WHERE name='" + deviceName + "'";
		if (this.debug) DataLogger.deviceLog("- Query: " + query + "\n");
		dbm.update(query);
	}

	// List Device Types
	public ResultSet getDeviceTypes() {
		if (debug) DataLogger.deviceLog("QueryBuilder: getDeviceTypes\n");
		String query = "SELECT Omschrijving FROM DeviceTypes";
		DataLogger.deviceLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	public ResultSet getDeviceTypesID(String omschrijving) {
		if (debug) DataLogger.deviceLog("QueryBuilder: getDeviceTypesID\n");
		String query = "SELECT id FROM DeviceTypes WHERE Omschrijving = '" + omschrijving + "'";
		DataLogger.deviceLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	public String selectSaltedPassword(String user, String pass) {
		if (this.debug) DataLogger.log("QueryBuilder: selectSaltedPassword\n");
		String query = "SELECT * FROM Peper WHERE peperId=(SELECT userId FROM Users WHERE username='" + user + "' LIMIT 1) LIMIT 1";
		if (this.debug) DataLogger.log("- Query: " + query + "\n");
		if (this.debug) DataLogger.log("Salt: " + cfg.getProperty("salt") + pass + "\n");
		return cfg.getProperty("salt") + pass + dbm.getPassPeper(query);
	}

	public ResultSet selectLogin(String user, String peperredPass) {
		if (this.debug) DataLogger.log("QueryBuilder: selectLogin\n");
		String query = "SELECT * FROM Users WHERE username='" + user + "' AND password='" + peperredPass + "' LIMIT 1";
		DataLogger.log("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	public void updateLogin(Integer userid, Timestamp currentTimeStamp) {
		if (debug) DataLogger.log("QueryBuilder: updateLogin\n");
		String query = "UPDATE users SET lastLogin=? WHERE userId=?";
		dbm.updateLogin(query, userid, currentTimeStamp);
	}

}

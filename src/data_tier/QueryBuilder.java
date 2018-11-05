package data_tier;

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
		if (this.debug) System.out.print("- Query: " + query + "\n");
		return query;
	}

	// Get typeID device
	public ResultSet DevicesTypeID(String deviceName) {
		if (debug) System.out.print("QueryBuilder: DevicesTypeID\n");
		String query = "SELECT typeID FROM Devices WHERE name ='"+deviceName+"'";
		System.out.print("- Query: " + query + "\n");
		if (this.debug) System.out.print("- Query: " + query + "\n");
		return dbm.execute(query);
	}
	// Get a list of devices
	public ResultSet Devices() {
		if (this.debug) System.out.print("QueryBuilder: Devices\n");
		// @Stijn What do you want to do with this??
		String query = "SELECT name FROM Devices";
		if (this.debug) System.out.print("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// Get last IP device
	public ResultSet getTopIP() {
		if (debug) System.out.print("QueryBuilder: getTopIP\n");
		String query = "SELECT IP FROM Devices ORDER BY IP DESC LIMIT 1";
		System.out.print("- Query: " + query + "\n");
		if (this.debug) System.out.print("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// Create new Device
	public Integer addNewDevice(String deviceName, String IPAdres, int typeID, String MACAdres) {
		if (this.debug) System.out.print("QueryBuilder: insertNewDevice\n");
		// Creating a query to create a new device
		String query = "INSERT INTO Devices (`name`, `MAC`, `IP`, `versionNumber`, `typeID`, `DeviceEnabled`, `timerStatus`, `timerOn`, `timerOff`) VALUES ('" +deviceName+"','"+MACAdres+ "','"+IPAdres+"','v1.1.1',"+typeID+",1,0,null,null)";
		if (this.debug) System.out.print("- Query: " + query + "\n");
		return dbm.__insert(query);
	}

	// Get enabledStatus device
	public ResultSet DevicesEnabledStatus(String deviceName) {
		if (debug) System.out.print("QueryBuilder: DevicesEnabledStatus\n");
		String query = "SELECT DeviceEnabled FROM Devices WHERE name ='"+deviceName+"'";
		System.out.print("- Query: " + query + "\n");
		if (this.debug) System.out.print("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// disable Device
	public void enableDisableDevice(int id, String deviceName) {
		if (this.debug) System.out.print("QueryBuilder: disableDevice\n");
		// Creating a query to disable a device
			String query = "UPDATE Devices SET DeviceEnabled = "+id+" WHERE name = '" + deviceName + "'";
			System.out.print("- Query: " + query + "\n");
		if (this.debug) System.out.print("- Query: " + query + "\n");
		dbm.update(query);
	}

	// Get startEndTime
	public ResultSet getStartEndTime(String deviceName) {
		if (debug) System.out.print("QueryBuilder: getStartEndTime\n");
		String query = "SELECT timerOn, timerOff FROM Devices WHERE name ='"+deviceName+"'";
		System.out.print("- Query: " + query + "\n");
		if (this.debug) System.out.print("- Query: " + query + "\n");
		return dbm.execute(query);
	}
	// Set startEndTime
	public void setStartEndTime(String timerOn, String timerOff, String deviceName) {
		// Creating a query to disable a device
			String query = "UPDATE Devices SET timerOn = '"+ timerOn +"', timerOff = '"+ timerOff +"' WHERE name = '" + deviceName + "'";
			System.out.print("- Query: " + query + "\n");
		if (this.debug) System.out.print("- Query: " + query + "\n");
		dbm.update(query);
	}

	// Get DevicesTimerStatus device
	public ResultSet DevicesTimerStatus(String deviceName) {
		if (debug) System.out.print("QueryBuilder: DevicesTimerStatus\n");
		String query = "SELECT timerStatus FROM Devices WHERE name ='"+deviceName+"'";
		System.out.print("- Query: " + query + "\n");
		if (this.debug) System.out.print("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// Disable enableDisableTimerDevice
	public void enableDisableTimerDevice(int id, String deviceName) {
		if (debug) System.out.print("QueryBuilder: enableDisableTimerDevice\n");
		// Creating a query to disable a device
		String query = "UPDATE Devices SET timerStatus = "+id+" WHERE name = '" + deviceName + "'";
		System.out.print("- Query: " + query + "\n");
		if (this.debug) System.out.print("- Query: " + query + "\n");
		dbm.update(query);
	}

	// Remove Device
	public void deleteDevice(String deviceName) {
		if (this.debug) System.out.print("QueryBuilder: deleteDevice\n");
		// Creating a query to remove a device
		String query = "DELETE FROM Devices WHERE name='" + deviceName + "'";
		if (this.debug) System.out.print("- Query: " + query + "\n");
		dbm.update(query);
	}

	// List Device Types
	public ResultSet getDeviceTypes() {
		if (debug) System.out.print("QueryBuilder: getDeviceTypes\n");
		String query = "SELECT Omschrijving FROM DeviceTypes";
		System.out.print("- Query: " + query + "\n");
		return dbm.execute(query);
	}
	
	public ResultSet getDeviceTypesID(String omschrijving) {
		if (debug) System.out.print("QueryBuilder: getDeviceTypesID\n");
		String query = "SELECT id FROM DeviceTypes WHERE Omschrijving = '" + omschrijving + "'";
		System.out.print("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	public String selectSaltedPassword(String user, String pass) {
		if (this.debug) System.out.print("QueryBuilder: selectSaltedPassword\n");
		String query = "SELECT * FROM Peper WHERE peperId=(SELECT userId FROM Users WHERE username='" + user + "' LIMIT 1) LIMIT 1";
		if (this.debug) System.out.print("- Query: " + query + "\n");
		if (this.debug) System.out.print("Salt: " + cfg.getProperty("salt") + pass + "\n");
		return cfg.getProperty("salt") + pass + dbm.getPassPeper(query);
	}

	public ResultSet selectLogin(String user, String peperredPass) {
		if (this.debug) System.out.print("QueryBuilder: selectLogin\n");
		String query = "SELECT * FROM Users WHERE username='" + user + "' AND password='" + peperredPass + "' LIMIT 1";
		System.out.print("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	public void updateLogin(Integer userid, Timestamp currentTimeStamp) {
		if (debug) System.out.print("QueryBuilder: updateLogin\n");
		String query = "UPDATE users SET lastLogin=? WHERE userId=?";
		dbm.updateLogin(query, userid, currentTimeStamp);
	}
	public Integer insertNewUser(int userID, String name, String email, String password) {
		// creating a new user
		String query = "INSERT INTO Users VALUES ('" +userID+"','"+name+ "','"+email+"','"+password+")";
		if (this.debug) System.out.print(query);
		return dbm.__insert(query);
	}
	
	public Integer editUserEmail(int userID, String email) {
		// Edit user email
		String query = "UPDATE Users SET email = "+email+" WHERE userID = '" + userID + "'";
		if (this.debug) System.out.print(query);
		return dbm.__insert(query);
	}
	public Integer editUserPassword(int userID, String password) {
		// Edit user password
		String query = "UPDATE Users SET password = "+password+" WHERE userID = '" + userID + "'";
		if (this.debug) System.out.print(query);
		return dbm.__insert(query);
	}
	
	public Integer editUserName(int userID, String name) {
		// Edit user name
		String query = "UPDATE Users SET name = "+name+" WHERE userID = '" + userID + "'";
		if (this.debug) System.out.print(query);
		return dbm.__insert(query);
	}
	
	public Integer deleteUser(int userID) {
		// Edit user name
		String query = "DELETE FROM Users WHERE userID='" + userID + "'";
		if (this.debug) System.out.print(query);
		return dbm.__insert(query);
	}
	
	public ResultSet checkemail(String email) {
		// Edit user name
		String query = "select FROM Users WHERE email='" + email + "'";
		if (this.debug) System.out.print(query);
		return dbm.execute(query);
	}


}

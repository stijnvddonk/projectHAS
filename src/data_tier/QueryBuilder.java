package data_tier;

import java.sql.*;

public class QueryBuilder {

	private DatabaseManager dbm;
	private Config cfg = new Config();
	private Boolean debug = Boolean.parseBoolean(cfg.getProperty("debug"));

	// The call of the QueryBuilder
	public QueryBuilder() {
		if (this.debug)
			DataLogger.systemLog("Query builder loaded");
		dbm = new DatabaseManager();
	}

	// Get the device data
	public String setDeviceData(int deviceID) {
		if (this.debug)
			DataLogger.systemLog("QueryBuilder: setDeviceData\n");
		String query = "SELECT deviceID, name, MAC, IP, versionNumber, typeID, Omschrijving, DeviceEnabled, timerStatus, timerOn, timerOff FROM Devices d JOIN DeviceTypes dt ON d.typeID=dt.ID WHERE d.deviceID = "
				+ deviceID;
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		return query;
	}

	// Get typeID device
	public ResultSet DevicesTypeID(String deviceName) {
		if (debug)
			DataLogger.systemLog("QueryBuilder: DevicesTypeID\n");
		String query = "SELECT typeID FROM Devices WHERE name ='" + deviceName + "'";
		DataLogger.systemLog("- Query: " + query + "\n");
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// Get a list of devices
	public ResultSet Devices() {
		if (this.debug)
			DataLogger.systemLog("QueryBuilder: Devices\n");
		String query = "SELECT * FROM devices d JOIN devicetypes dt ON d.typeId = dt.ID";
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// Get a list of Users
	public ResultSet Users() {
		if (this.debug)
			DataLogger.systemLog("QueryBuilder: Devices\n");
		String query = "SELECT * FROM users";
		if (this.debug)
			DataLogger.systemLog(query);
		return dbm.execute(query);
	}

	// Get last IP device
	public ResultSet getTopIP() {
		if (debug)
			DataLogger.systemLog("QueryBuilder: getTopIP\n");
		String query = "SELECT IP FROM Devices ORDER BY IP DESC LIMIT 1";
		DataLogger.systemLog("- Query: " + query + "\n");
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// Create new Device
	public Integer addNewDevice(String deviceName, String IPAdres, int typeID, String MACAdres) {
		if (this.debug)
			DataLogger.systemLog("QueryBuilder: insertNewDevice\n");
		String query = "INSERT INTO Devices (`name`, `MAC`, `IP`, `versionNumber`, `typeID`, `DeviceEnabled`, `timerStatus`, `timerOn`, `timerOff`) VALUES ('"
				+ deviceName + "','" + MACAdres + "','" + IPAdres + "','v1.1.1'," + typeID + ",1,0,null,null)";
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		return dbm.__insert(query);
	}

	// Get enabledStatus device
	public ResultSet DevicesEnabledStatus(String deviceName) {
		if (debug)
			DataLogger.systemLog("QueryBuilder: DevicesEnabledStatus\n");
		String query = "SELECT DeviceEnabled FROM Devices WHERE name ='" + deviceName + "'";
		DataLogger.systemLog("- Query: " + query + "\n");
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// disable Device
	public void enableDisableDevice(int id, String deviceName) {
		if (this.debug)
			DataLogger.systemLog("QueryBuilder: disableDevice\n");
		String query = "UPDATE Devices SET DeviceEnabled = " + id + " WHERE name = '" + deviceName + "'";
		DataLogger.systemLog("- Query: " + query + "\n");
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		dbm.update(query);
	}

	// Get startEndTime
	public ResultSet getStartEndTime(String deviceName) {
		if (debug)
			DataLogger.systemLog("QueryBuilder: getStartEndTime\n");
		String query = "SELECT timerOn, timerOff FROM Devices WHERE name ='" + deviceName + "'";
		DataLogger.systemLog("- Query: " + query + "\n");
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// Set startEndTime
	public void setStartEndTime(String timerOn, String timerOff, String deviceName) {
		// Creating a query to disable a device
		String query = "UPDATE Devices SET timerOn = '" + timerOn + "', timerOff = '" + timerOff + "' WHERE name = '"
				+ deviceName + "'";
		DataLogger.systemLog("- Query: " + query + "\n");
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		dbm.update(query);
	}

	// Get DevicesTimerStatus device
	public ResultSet DevicesTimerStatus(String deviceName) {
		if (debug)
			DataLogger.systemLog("QueryBuilder: DevicesTimerStatus\n");
		String query = "SELECT timerStatus FROM Devices WHERE name ='" + deviceName + "'";
		DataLogger.systemLog("- Query: " + query + "\n");
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	// Disable enableDisableTimerDevice
	public void enableDisableTimerDevice(int id, String deviceName) {
		if (debug)
			DataLogger.systemLog("QueryBuilder: enableDisableTimerDevice\n");
		String query = "UPDATE Devices SET timerStatus = " + id + " WHERE name = '" + deviceName + "'";
		DataLogger.systemLog("- Query: " + query + "\n");
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		dbm.update(query);
	}

	// Remove Device
	public void deleteDevice(String deviceName) {
		if (this.debug)
			DataLogger.systemLog("QueryBuilder: deleteDevice\n");
		String query = "DELETE FROM Devices WHERE name='" + deviceName + "'";
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		dbm.update(query);
	}

	// List Device Types
	public ResultSet getDeviceTypes() {
		if (debug)
			DataLogger.systemLog("QueryBuilder: getDeviceTypes\n");
		String query = "SELECT Omschrijving FROM DeviceTypes";
		DataLogger.systemLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	public ResultSet getDeviceTypesID(String omschrijving) {
		if (debug)
			DataLogger.systemLog("QueryBuilder: getDeviceTypesID\n");
		String query = "SELECT id FROM DeviceTypes WHERE Omschrijving = '" + omschrijving + "'";
		DataLogger.systemLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	public String selectSaltedPassword(String user, String pass) {
		if (this.debug)
			DataLogger.systemLog("QueryBuilder: selectSaltedPassword");
		String query = "SELECT * FROM Peper WHERE peperId=(SELECT userId FROM users WHERE username='" + user
				+ "' LIMIT 1) LIMIT 1";
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		if (this.debug)
			DataLogger.systemLog("Salt: " + cfg.getProperty("salt") + pass + "\n");
		return cfg.getProperty("salt") + pass + dbm.getPassPeper(query);
	}

	public ResultSet selectLogin(String user, String peperredPass) {
		if (this.debug)
			DataLogger.systemLog("QueryBuilder: selectLogin\n");
		String query = "SELECT * FROM users WHERE username='" + user + "' AND password='" + peperredPass + "' LIMIT 1";
		DataLogger.systemLog("- Query: " + query + "\n");
		return dbm.execute(query);
	}

	public void updateLogin(Integer userid, Timestamp currentTimeStamp) {
		if (debug)
			DataLogger.systemLog("QueryBuilder: updateLogin\n");
		String query = "UPDATE users SET lastLogin=? WHERE userId=?";
		dbm.updateLogin(query, userid, currentTimeStamp);
	}

	public Integer insertUser(String fullname, String username, String pass, String email, Integer role, String token) {
		DataLogger.systemLog("QueryBuilder: insertUser\n");
		DataLogger.systemLog("Values:\nFullname: " + fullname + "\n- username: " + username + "\n- password: " + pass
				+ "\n- email: " + email + "\n- Role: " + role + "\n- token: " + token + "\n");
		String query = "INSERT INTO users (Name, Email, Role, userName, Password, Active, locked, attempts, lockedUntil, Token, lastLogin) VALUES ( '"
				+ fullname + "', '" + email + "', '1', '" + username + "', '" + pass + "', '1', '0', '0', NULL, '"
				+ token + "', NULL)";
		DataLogger.systemLog(query + "\n");
		return dbm.__insert(query);
	}

	// Disable User
	public void enableDisableUser(int id, String userName) {
		if (this.debug)
			DataLogger.systemLog("QueryBuilder: disableDevice\n");
		String query = "UPDATE users SET Active = " + id + " WHERE Name = '" + userName + "'";
		DataLogger.systemLog("- Query: " + query + "\n");
		if (this.debug)
			DataLogger.systemLog("- Query: " + query + "\n");
		dbm.update(query);
	}
	
	public void editUserEmail(int userID, String email) {
		// Edit user email
		String query = "UPDATE Users SET email = "+email+" WHERE userID = '" + userID + "'";
		if (this.debug) System.out.print(query);
		dbm.update(query);
	}
		
	public void editUserPassword(int userID, String password) {
		// Edit user password
		String query = "UPDATE Users SET password = "+password+" WHERE userID = '" + userID + "'";
		if (this.debug) System.out.print(query);
		dbm.update(query);
	}
	
	public void editUserName(String Uname, String Email, String rights) {
		// Edit user name
		String query = "UPDATE Users SET name = "+Uname+ ", email="+Email+" WHERE userName = '" + Uname + "'";
		if (this.debug) System.out.print(query);
		dbm.update(query);
	}
	
	public void disableUser(String uName) {
		// Edit user name
		String query = "UPDATE Users SET active = 0 WHERE Name = '" + uName + "'";
		if (this.debug) System.out.print(query);
		dbm.update(query);
	}
	
	public void enableUser(String uName) {
		// Edit user name
		String query = "UPDATE Users SET active = 1 WHERE Name = '" + uName + "'";
		if (this.debug) System.out.print(query);
		dbm.update(query);
	}
	
	public ResultSet checkemail(String email) {
		// Edit user name
		String query = "select FROM Users WHERE email='" + email + "'";
		if (this.debug) System.out.print(query);
		return dbm.execute(query);
	}
	public ResultSet getUserID() {
		if (debug) System.out.print("QueryBuilder: get UseriD\n");
		String query = "SELECT id FROM Users";
		System.out.print("- Query: " + query + "\n");
		return dbm.execute(query);
	}


}

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
		String query = "HIER MOET NOG DE GOEDE QUERY KOMEN" + deviceID;
		if (this.debug) System.out.print(query);
		return query;
	}

	// Get a list of devices
	public ResultSet Devices() {
		if (this.debug) System.out.print("QueryBuilder: Devices\n");
		// @Stijn What do you want to do with this??
		String query = "SELECT name FROM Devices";
		if (this.debug) System.out.print(query);
		return dbm.execute(query);
	}

	// Create new Device
	public ResultSet insertNewDevice(String deviceName, String MACAdres, String IPAdres, String versionNumber, int typeID) {
		if (this.debug) System.out.print("QueryBuilder: insertNewDevice\n");
		// Creating a query to creat a new device
		String query = "INSERT INTO Devices VALUES (4,'" +deviceName+"','"+MACAdres+ "','"+IPAdres+"','"+versionNumber+"',"+typeID+",0,null,null)";
		if (this.debug) System.out.print(query);
		return dbm.execute(query);
	}

	// Disble Device
	public ResultSet disableDevice(Integer deviceID) {
		if (this.debug) System.out.print("QueryBuilder: disableDevice\n");
		// Creating a query to disable a device
		String query = "XXXXXXXXXXX WHERE DeviceID=" + deviceID;
		if (this.debug) System.out.print(query);
		return dbm.execute(query);
	}

	// Remove Device
	public ResultSet deleteDevice(Integer deviceID) {
		if (this.debug) System.out.print("QueryBuilder: deleteDevice\n");
		// Creating a query to remove a device
		String query = "DELETE FROM Devices WHERE DeviceID=" + deviceID;
		if (this.debug) System.out.print(query);
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

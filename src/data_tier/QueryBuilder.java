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
		// Creating a query @Stijn add more info please
		String query = "HIER MOET NOG DE GOEDE QUERY KOMEN" + deviceID;
		if (this.debug) System.out.print(query);
		return query;
	}

	// Get a list of devices
	public ResultSet Devices() {
		// @Stijn What do you want to do with this??
		String query = "SELECT name FROM Devices";
		if (this.debug) System.out.print(query);
		return dbm.execute(query);
	}

	// Create new Device
	public ResultSet insertNewDevice(String deviceName, String MACAdres, String IPAdres, String versionNumber, int typeID) {
		// Creating a query to creat a new device
		String query = "INSERT INTO Devices VALUES (4,'" +deviceName+"','"+MACAdres+ "','"+IPAdres+"','"+versionNumber+"',"+typeID+",0,null,null)";
		if (this.debug) System.out.print(query);
		return dbm.execute(query);
	}

	// Disble Device
	public ResultSet disableDevice(Integer deviceID) {
		// Creating a query to disable a device
		String query = "XXXXXXXXXXX WHERE DeviceID=" + deviceID;
		if (this.debug) System.out.print(query);
		return dbm.execute(query);
	}

	// Remove Device
	public ResultSet deleteDevice(Integer deviceID) {
		// Creating a query to remove a device
		String query = "DELETE FROM Devices WHERE DeviceID=" + deviceID;
		if (this.debug) System.out.print(query);
		return dbm.execute(query);
	}

	public String selectSaltedPassword(String user, String pass) {
		String query = "SELECT * FROM Peper WHERE peperId=(SELECT userId FROM Users WHERE username='" + user + "' LIMIT 1) LIMIT 1";
		System.out.println(query);
		System.out.println(cfg.getProperty("salt") + pass);
		return cfg.getProperty("salt") + pass + dbm.getPassPeper(query);
	}

	public ResultSet selectLogin(String user, String peperredPass) {
		System.out.println("Vat die deze code hier of wahh");
		String query = "SELECT * FROM Users WHERE username='" + user + "' AND password='" + peperredPass + "' LIMIT 1";
		System.out.println(query);
		return dbm.execute(query);
	}

}

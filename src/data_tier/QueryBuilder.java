package data_tier;

import java.sql.*;

public class QueryBuilder {

	private DatabaseManager dbm;
	private Config cfg = new Config("config/main.cfg");
	private Boolean debug = Boolean.parseBoolean(cfg.getProperty("debug"));

	// The call of the QueryBuilder
	public QueryBuilder() {
		if (this.debug) System.out.print("Query builder loaded\n");
		dbm = new DatabaseManager();
	}

	// Get the device data
	public String setDeviceData(int deviceID) {
		// Creating a query @Stijn add more info please
		String query = "" + deviceID;
		if (this.debug) System.out.print(query);
		return query;
	}

	// Get a list of devices
	public ResultSet Devices() {
		// @Stijn What do you want to do with this??
		String query = "";
		if (this.debug) System.out.print(query);
		return dbm.execute(query);
	}

	// Create new Device
	public String insertNewDevice() {
		// Creating a query to creat a new device
		String query = "";
		if (this.debug) System.out.print(query);
		return query;
	}

	// Disble Device
	public String disableDevice(Integer deviceID) {
		// Creating a query to disable a device
		String query = "XXXXXXXXXXX WHERE DeviceID=" + deviceID;
		if (this.debug) System.out.print(query);
		return query;
	}

	// Remove Device
	public String deleteDevice(Integer deviceID) {
		// Creating a query to remove a device
		String query = "DELETE FROM Devices WHERE DeviceID=" + deviceID;
		if (this.debug) System.out.print(query);
		return query;
	}

}

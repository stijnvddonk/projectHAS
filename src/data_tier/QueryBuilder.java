package data_tier;

import java.sql.*;

public class QueryBuilder {

	private DatabaseManager dbm;

	public QueryBuilder() {
		dbm = new DatabaseManager();
	}

	// GET DEVICE DATA
	public String setDeviceData(int deviceID) {
		// Query invoegen
		String query = "" + deviceID;
		System.out.println(query);
		return query;
	}

	//List devices
	public ResultSet Devices() {
		// Query invoegen
		String query = "";
		System.out.println(query);
		return dbm.execute(query);
	}

	// Create new Device
	public String insertNewDevice() {
		// Query invoegen
		String query = "";
		System.out.println(query);
		return query;
	}

	// Disble Device
	public String disableDevice(Integer deviceID) {
		// Query invoegen
		String query = "XXXXXXXXXXX WHERE DeviceID=" + deviceID;
		System.out.println(query);
		return query;
	}

	// Remove Device
	public String deleteDevice(Integer deviceID) {
		String query = "DELETE FROM Devices WHERE DeviceID=" + deviceID;
		System.out.println(query);
		return query;
	}

}

package logic_tier;

import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.ResultSet;

import data_tier.DatabaseManager;
import data_tier.QueryBuilder;

public class Device {

	protected DatabaseManager dbm = new DatabaseManager();
	protected QueryBuilder qb = new QueryBuilder();
	protected Connection db;

	private Integer deviceID;
	private String name;
	private String mac;
	private String ip;
	private Integer versionNumber;
	private Integer type;
	private boolean timerStatus;
	private Integer timerOnID;
	private Integer timerOffID;

	public Map<String, String> deviceData = new HashMap<String, String>();

	public Device(int deviceID) {
		this.deviceID = null;
		this.name = null;
		this.mac = null;
		this.ip = null;
		this.versionNumber = null;
		this.type = null;
		//this.timerStatus = null;
		this.timerOnID = null;
		this.timerOffID = null;
		this.setDeviceData(deviceID);
	}

	public void setDeviceData(int deviceID) {
		ResultSet rs = null;
		String query = qb.setDeviceData(deviceID);
		System.out.println("Project Query: " + query + "\n");

		try {
			for (rs = this.dbm.execute(query); rs.next();) {
				this.deviceID = rs.getInt("deviceID");
				this.name = rs.getString("Name");
				this.mac = rs.getString("MAC");
				this.ip = rs.getString("IP");
				this.versionNumber = rs.getInt("versionNumber");
				this.type = rs.getInt("type");
				this.timerStatus = rs.getBoolean("timerStatus");
				this.timerOnID = rs.getInt("timerOn");
				this.timerOffID = rs.getInt("timerOff");
				deviceData.put("Device Name: ", this.name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getDeviceID() {
		return this.deviceID;
	}

	public String getName() {
		return this.name;
	}

	public String getMac() {
		return this.mac;
	}

	public String getIP() {
		return this.ip;
	}

	public int getVersionNumber() {
		return this.versionNumber;
	}

	public int getType() {
		return this.type;
	}

	public boolean getTimerStatus() {
		return this.timerStatus;
	}

	public int getTimerOnID() {
		return this.timerOnID;
	}

	public int getTimerOffID() {
		return this.timerOffID;
	}

}

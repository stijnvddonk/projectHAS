package logic_tier;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import data_tier.DataLogger;
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
	private String versionNumber;
	private Integer typeID;
	private String typeDesc;
	private Integer deviceStatus;
	private Integer timerStatus;
	private Integer timerOnID;
	private Integer timerOffID;

	public Map<String, String> deviceData = new HashMap<String, String>();

	public Device(int deviceID) {
		this.deviceID = null;
		this.name = null;
		this.mac = null;
		this.ip = null;
		this.versionNumber = null;
		this.typeID = null;
		this.typeDesc = null;
		this.deviceStatus = null;
		this.timerStatus = null;
		this.timerOnID = null;
		this.timerOffID = null;
		this.setDeviceData(deviceID);
	}

	public void setDeviceData(int deviceID) {
		ResultSet rs = null;
		String query = qb.setDeviceData(deviceID);
		DataLogger.systemLog("Project Query: " + query + "\n");

		try {
			for (rs = this.dbm.execute(query); rs.next();) {
				this.deviceID = rs.getInt("deviceID");
				this.name = rs.getString("name");
				this.mac = rs.getString("MAC");
				this.ip = rs.getString("IP");
				this.versionNumber = rs.getString("versionNumber");
				this.typeID = rs.getInt("typeID");
				this.typeDesc = rs.getString("Omschrijving");
				this.deviceStatus = rs.getInt("DeviceEnabled");
				this.timerStatus = rs.getInt("timerStatus");
				this.timerOnID = rs.getInt("timerOn");
				this.timerOffID = rs.getInt("timerOff");
				deviceData.put("Device Name: ", this.name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	  public void newDevice(String deviceName, String deviceType) {
//			DataLogger.systemLog("new Device Method Called");
//			try {
//				DataLogger.systemLog("Device Name: " + deviceName);
//				//Resultset query = qb.insertNewDevice(deviceName, mac, ip, versionNumber, deviceType);
//				PreparedStatement pst;
//				pst = db.prepareStatement(query);
//				 pst.setInt(1, deviceID);
//				 pst.setString(2, deviceName);
//				 pst.setString(3, mac);
//				 pst.setString(4, ip);
//				 pst.setString(5, versionNumber);
//					switch (deviceType) {
//					case "Lamp":
//						typeID = 1;
//						break;
//					case "Deur":
//						typeID = 2;
//						break;
//					case "LuchtVochtigheids Lezer":
//						typeID = 3;
//						break;
//					}
//				 pst.setInt(6, typeID);
//				 Resultset query = qb.insertNewDevice(deviceName, mac, ip, versionNumber, deviceType);
//				//pst.executeUpdate();
//			} catch (Exception e) {
//				DataLogger.errorLog(e);
//				JOptionPane.showMessageDialog(null, e);
//			}
//		}

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

	public String getVersionNumber() {
		return this.versionNumber;
	}

	public int getType() {
		return this.typeID;
	}

	public int getTimerStatus() {
		return this.timerStatus;
	}

	public int getTimerOnID() {
		return this.timerOnID;
	}

	public int getTimerOffID() {
		return this.timerOffID;
	}

}

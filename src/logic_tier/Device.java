package logic_tier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import data_tier.DataLogger;
import data_tier.DatabaseManager;
import data_tier.QueryBuilder;

public class Device {

	protected DatabaseManager dbm = new DatabaseManager();
	protected QueryBuilder qb = new QueryBuilder();
	protected Connection db;

	protected Integer deviceID;
	protected String name;
	protected String mac;
	protected String ip;
	protected String versionNumber;
	protected Integer typeID;
	protected String typeDesc;
	protected Integer deviceStatus;
	protected Integer timerStatus;
	protected String timerOn;
	protected String timerOff;

	protected ArrayList<ArrayList<String>> deviceNameList = null;
	protected List<Device> deviceObject = new ArrayList<>();

	public void setDevice(Integer did, String dna, String dMAC, String dIP, String dVersion, Integer dType,
			Integer dStatus) {
		deviceID = did;
		name = dna;
		mac = dMAC;
		ip = dIP;
		versionNumber = dVersion;
		typeID = dType;
		deviceStatus = dStatus;
	}

	public void setAdditionalInfo(String description, int timerstatus, String timeOn, String timeOff) {
		typeDesc = description;
		timerStatus = timerstatus;
		timerOn = timeOn;
		timerOff = timeOff;
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

	public String getIp() {
		return ip;
	}

	public String getVersionNumber() {
		return this.versionNumber;
	}

	public Integer getTypeID() {
		return typeID;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public int getTimerStatus() {
		return this.timerStatus;
	}

	public String getTimerOn() {
		return timerOn;
	}

	public String getTimerOff() {
		return timerOff;
	}

}

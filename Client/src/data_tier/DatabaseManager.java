package data_tier;

import java.sql.*;
import data_tier.Config;

public class DatabaseManager {

	private Connection db;
	private Config cfg = new Config();
	private Boolean debug = Boolean.parseBoolean(cfg.getProperty("debug"));
	private String dbBuild = cfg.getProperty("dbType") + cfg.getProperty("dbHost") + ":" + cfg.getProperty("dbPort")
			+ "/" + cfg.getProperty("dbName") + "?" + cfg.getProperty("dbExtra");

	public DatabaseManager() {
		setConnection();
	}

	// SetConnection makes the connect to the database
	private void setConnection() {
		try {
			db = DriverManager.getConnection(dbBuild, cfg.getProperty("dbUser"), cfg.getProperty("dbPass"));
			if (this.debug)
				DataLogger.systemLog("Connection successfully");
		} catch (Exception e) {
			DataLogger.errorLog(e);
		}
	}

	// Get the live connection
	public Connection getCon() {
		return this.db;
	}

	/*
	 * Execute Type: Select query Input: MySQL Database query in String format
	 * Output: Result with data
	 */
	public ResultSet execute(String query) {
		ResultSet output = null;
		try {
			Statement stmt = db.createStatement();
			output = stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}

	/*
	 * Update Type: update query Input: MySQL Database query in String format
	 * Output: None
	 */
	public void update(String query) {
		try {
			Statement stmt = db.createStatement();
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Insert Type: Insert query Input: MySQL Database query in String format
	 * Output: None
	 */
	public void insert(String query) {
		try {
			Statement stmt = db.createStatement();
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * __insert Type: Insert query Input: MySQL Database query in String format
	 * Output: Returns the id of the inserted item.
	 */
	public Integer __insert(String query) {
		Integer output = null;
		try {
			Statement stmt = db.createStatement();
			output = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				output = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	public String getPassPeper(String query) {
		String peper = null;
		ResultSet rs = execute(query);
		try {
			rs.first();
			peper = rs.getString("peper");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return peper;
	}

	/*
	 * updateLogin Type: Update query Input: MySQL Database query in String format,
	 * UserId as Integer, The currentTimeStamp as Timestamp Output: None
	 */
	public void updateLogin(String query, Integer userid, Timestamp currentTimeStamp) {
		if (debug)
			DataLogger.systemLog(
					"- Query: " + query + "\n- - UserId: " + userid + "\n- - Timestamp: " + currentTimeStamp + "\n");
		try {
			PreparedStatement ppstm = db.prepareStatement(query);
			ppstm.setTimestamp(1, currentTimeStamp);
			ppstm.setInt(2, userid);
			ppstm.executeUpdate();
			ppstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (this.debug)
			DataLogger.systemLog("Update done\n");
	}

}

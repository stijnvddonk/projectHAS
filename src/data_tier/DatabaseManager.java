package data_tier;

import java.sql.*;
import data_tier.Config;

public class DatabaseManager {

	private Connection db;
	private Config cfg = new Config();
	private Boolean debug = Boolean.parseBoolean(cfg.getProperty("debug"));
	private String dbBuild = cfg.getProperty("dbType") + cfg.getProperty("dbHost") + ":" + cfg.getProperty("dbPort") + "/" + cfg.getProperty("dbName") + "?" + cfg.getProperty("dbExtra");

	public DatabaseManager() {
		setConnection();
	}
	
	private void setConnection() {
		try {
			db = DriverManager.getConnection(dbBuild, cfg.getProperty("dbUser"), cfg.getProperty("dbPass"));
			if (this.debug) System.out.print("Connection successfully\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getCon() {
		return this.db;
	}
	
	public ResultSet execute(String query) {
		ResultSet output = null;
		try {
			Statement stmt = db.createStatement();
			output =  stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
		
	}
	
	public void update(String query) {
		try {
			Statement stmt = db.createStatement();
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(String query) {
		try {
			Statement stmt = db.createStatement();
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer __insert(String query) {
		Integer output = null;
		try {
			Statement stmt = db.createStatement();
			output =  stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()){
				output = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	public void updateLogin(String query, Integer userid, Timestamp currentTimeStamp) {		
		try {
			PreparedStatement ppstm = db.prepareStatement(query);
			ppstm.setTimestamp(1, currentTimeStamp);
			ppstm.setInt(2, userid);
			ppstm.executeUpdate();
			ppstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    if (this.debug) System.out.print("Update done\n");
	}

}
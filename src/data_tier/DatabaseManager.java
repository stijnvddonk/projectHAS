package data_tier;

import java.sql.*;
import data_tier.Config;

public class DatabaseManager {

	private Connection db;
	private Config cfg = new Config("config/main.cfg");
	private Boolean debug = Boolean.parseBoolean(cfg.getProperty("debug"));
	private String dbBuild = cfg.getProperty("dbType") + cfg.getProperty("dbHost") + ":" + cfg.getProperty("dbPort") + "/" + cfg.getProperty("dbName") + "?" + cfg.getProperty("dbExtra");

	public DatabaseManager() {
		setConnection();
	}
	
	// SetConnection makes the connect to the database
	private void setConnection() {
		try {
			db = DriverManager.getConnection(dbBuild, cfg.getProperty("dbUser"), cfg.getProperty("dbPass"));
			if (this.debug) System.out.print("Connection successfully\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Get the live connection
	public Connection getCon() {
		return this.db;
	}
	
	/* Execute
	 * Type: Select query
	 * Input: MySQL Database query in String format
	 * Output: Result with data
	 */
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
	
	/* Update
	 * Type: update query
	 * Input: MySQL Database query in String format
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
	
	/* Insert
	 * Type: Insert query
	 * Input: MySQL Database query in String format
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

	/* __insert
	 * Type: Insert query
	 * Input: MySQL Database query in String format
	 * Output: Returns the id of the inserted item.
	 */
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

	/* updateLogin
	 * Type: Update query
	 * Input: MySQL Database query in String format, UserId as Integer, The currentTimeStamp as Timestamp
	 * Output: None
	 */
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

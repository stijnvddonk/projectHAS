package logic_tier;

import data_tier.DataLogger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import GUI.loginScreen;
import GUI.mainScreen;
import data_tier.Config;
import data_tier.QueryBuilder;
import data_tier.passwordAuthentication;

public class Login {

	protected QueryBuilder qb = new QueryBuilder();
	protected passwordAuthentication pswa = new passwordAuthentication();
	private Config cfg = new Config();
	private Boolean debug = Boolean.parseBoolean(cfg.getProperty("debug"));
	protected Connection db = null;

	// Values
	protected Integer userid;
	protected String username;
	protected String password;
	protected Integer role;
	protected String token;
	protected Integer active = 0;
	protected User user = new User(qb);

	public Login() {
		if (debug)
			DataLogger.systemLog("Login loaded\n\n---------------------\n");
	}

	public void login(String user, String pass) {
		ResultSet rs = null;
		if (debug)
			DataLogger.systemLog("The user is: " + user + "\nThe password is: " + pass);
		String saltedPass = qb.selectSaltedPassword(user, pass);
		try {
			rs = qb.selectLogin(user, saltedPass);
			while (rs.next()) {
				this.userid = rs.getInt("userID");
				this.username = rs.getString("UserName");
				this.password = rs.getString("Password");
				this.role = rs.getInt("Role");
				this.token = rs.getString("Token");
				this.active = rs.getInt("Active");
			}
		} catch (Exception e) {
			if (debug)
				DataLogger.errorLog(e);
			// logging.errorLog(e);
		}

		if (debug)
			DataLogger.systemLog("User data:\n- userid: " + this.userid + "\n- role: " + this.role + "\n- username: "
					+ this.username + "\n- password: " + this.password + "\n- token: " + this.token + "\n");

		if (validateUser(user, saltedPass, pass)) {
			// Call Next Class
			// Create userObject
			this.user.setUser(userid, username, password, role, token, active);

			// if (this.debug) logging.log("Call next class\n");
			if (this.role.equals(1) || this.role.equals(2) || this.role.equals(3)) {
				mainScreen ms = new mainScreen(this.user);
				ms.setVisible(true);
			}  else {
				// We will do nothing here
			}
		}

	}

	public boolean validateUser(String user, String saltedPass, String pass) {
		boolean valid = false;

		if (user.isEmpty() || pass.isEmpty()) {
			if (this.debug)
				DataLogger.systemLog("Incorrect not all field filled in.\n");
		} else if (this.active != 1) {
			if (this.debug)
				DataLogger.systemLog("User is deactivated.\n");
		} else if (!user.equals(this.username) || !saltedPass.equals(this.password)) {
			if (this.debug)
				DataLogger.systemLog("Incorrect username or password.\n");
		} else if (user.equals(this.username) && pswa.authenticate(pass.toCharArray(), this.token)) {
			Calendar calendar = Calendar.getInstance();
			java.sql.Timestamp currentTimeStamp = new java.sql.Timestamp(calendar.getTime().getTime());
			valid = true;
			qb.updateLogin(this.userid, currentTimeStamp);
			if (this.debug)
				DataLogger.systemLog("Correct credentials.\n");
		}

		if (debug)
			DataLogger.systemLog("---------------------\n");

		return valid;
	}

}

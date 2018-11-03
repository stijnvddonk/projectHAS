package logic_tier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import GUI.DeviceMenu;
import GUI.loginScreen;
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

	public Login() {
		if (debug)
			System.out.print("Login loaded\n\n---------------------\n");
	}

	public void login(String user, String pass) {
		ResultSet rs = null;
		if (debug)
			System.out.print("The user is: " + user + "\nThe password is: " + pass + "\n");
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
				System.out.print(e.getStackTrace());
//			logging.errorLog(e);
		}

		if (debug)
			System.out.print("User data:\n- userid: " + this.userid + "\n- role: " + this.role + "\n- username: "
					+ this.username + "\n- password: " + this.password + "\n- token: " + this.token + "\n");

		if (validateUser(user, saltedPass, pass)) {
			// Call Next Class
//			if (this.debug) logging.log("Call next class\n");
			if (this.role.equals(1)) {
				DeviceMenu dm = new DeviceMenu();
				dm.setVisible(true);
			} else if (this.role.equals(2) || this.role.equals(3)) {
				DeviceMenu dm = new DeviceMenu();
				dm.setVisible(true);
			} else {
				// We will do nothing here
			}
		}

	}

	public boolean validateUser(String user, String saltedPass, String pass) {
		boolean valid = false;

		if (user.isEmpty() || pass.isEmpty()) {
			if (this.debug)
				System.out.print("Incorrect not all field filled in.\n");
		} else if (this.active != 1) {
			if (this.debug)
				System.out.print("User is deactivated.\n");
		} else if (!user.equals(this.username) || !saltedPass.equals(this.password)) {
			if (this.debug)
				System.out.print("Incorrect username or password.\n");
		} else if (user.equals(this.username) && pswa.authenticate(pass.toCharArray(), this.token)) {
			Calendar calendar = Calendar.getInstance();
			java.sql.Timestamp currentTimeStamp = new java.sql.Timestamp(calendar.getTime().getTime());
			valid = true;
			qb.updateLogin(this.userid, currentTimeStamp);
			if (this.debug)
				System.out.print("Correct credentials.\n");
		}

		if (debug)
			System.out.print("---------------------\n");

		return valid;
	}

}

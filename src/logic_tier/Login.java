package logic_tier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import GUI.loginScreen;
import data_tier.Config;
import data_tier.QueryBuilder;
import data_tier.passwordAuthentication;

public class Login {
	
	protected QueryBuilder qb 					= new QueryBuilder();
	protected passwordAuthentication pswa 		= new passwordAuthentication();
	private Config cfg 							= new Config();
	private Boolean debug 						= Boolean.parseBoolean(cfg.getProperty("debug"));
	protected Connection db						= null;

	public Login() {
		if (debug) System.out.print("Login loaded\n");
	}
	
	public void login(String user, String pass) {
		ResultSet rs = null;
		if (debug) System.out.print("The user is: " + user + "\nThe password is: " + pass + "\n");
		String saltedPass = qb.selectSaltedPassword(user, pass);
		try {
			rs = qb.selectLogin(user, saltedPass);
			while (rs.next()) {
//				tempUsers.userid 	= rs.getInt("userid");
//				tempUsers.username 	= rs.getString("username");
//				tempUsers.password 	= rs.getString("password");
//				tempUsers.role 		= rs.getInt("role");
//				tempUsers.token		= rs.getString("token");
//				tempUsers.active	= rs.getInt("active");
			}
		} catch (Exception e) {
//			logging.errorLog(e);
		}

		
//		if (this.debug) logging.log("userid: " + tempUsers.userid + "\nrole: " + tempUsers.role + "\nusername: " + tempUsers.username + "\npassword: " + tempUsers.password + "\ntoken: " + tempUsers.token);
		
		if (validateUser(user, saltedPass, pass)) {
			// Call Next Class
//			if (this.debug) logging.log("Call next class\n");
//			if (tempUsers.role.equals(1)) {
//				atc = new client(tempUsers.userid, qb);
//				cls.clientDashboard(atc);
//			} else if (tempUsers.role.equals(2) || tempUsers.role.equals(3)) {
//				ate = new employee(tempUsers.userid, qb);
//				cls.employeeDashboard(ate);
//			} else {
				// We will do nothing here
//			}
		}
		
	}
	
	public boolean validateUser(String user, String saltedPass, String pass) {
		boolean valid = false;
//		Map<String, String> loginReturn	= new HashMap<>();
		
//		if (user.isEmpty() || pass.isEmpty()) {
//			loginReturn.put("type", "failed");
//			loginReturn.put("text", "Please fill in username and password");
//			if (this.debug) logging.log("Incorrect not all field filled in.\n");
//		}
//		else if (tempUsers.active != 1) {
//			loginReturn.put("type", "failed");
//			loginReturn.put("text", "User is not active.");
//			if (this.debug) logging.log("User is deactivated.\n");
//		}
//		else if (!user.equals(tempUsers.username) || !saltedPass.equals(tempUsers.password)) {
//			loginReturn.put("type", "failed");
//			loginReturn.put("text", "Username or password is incorrect.");
//			if (this.debug) logging.log("Incorrect username or password.\n");
//		}
//		else if (user.equals(tempUsers.username) && pswa.authenticate(pass.toCharArray(), tempUsers.token)) {
//			Calendar calendar = Calendar.getInstance();
//		    java.sql.Timestamp currentTimeStamp = new java.sql.Timestamp(calendar.getTime().getTime());
//			valid = true;
//			loginReturn.put("type", "success");
//			loginReturn.put("text", "Successfully logged in.\n");
//			qb.updateLogin(tempUsers.userid, currentTimeStamp);
//			if (this.debug) logging.log("Correct credentials.\n");
//		}
//
//		cls.showMessage(loginReturn);
		
		return valid;
	}

}

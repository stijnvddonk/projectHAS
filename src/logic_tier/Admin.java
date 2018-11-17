package logic_tier;

import data_tier.DataLogger;
import data_tier.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.sql.PreparedStatement;

public class Admin extends User{

  public Admin(QueryBuilder _qb) {
		super(_qb);
		// TODO Auto-generated constructor stub
	}

	// add device
	public void createNewDevice(String dName, String dIP, int dType) {
		Integer result = 1; // Standard it will say it successfull.
		ArrayList<String> row = new ArrayList<String>();
		Predicate<Device> pdn = e -> e.name.contains(dName);
		DataLogger.systemLog("dName: " + dName + "\n");
		String randomChars = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder randomStr = new StringBuilder();
		String name = "0050";
		Random rnd = new Random();
		while (randomStr.length() < 8) {
			int index = (int) (rnd.nextFloat() * randomChars.length());
			randomStr.append(randomChars.charAt(index));
			}
			String MACAdres = name + randomStr.toString();
			MACAdres = MACAdres.replaceAll("..", "$0:").substring(0, 17);
		if(deviceObject.stream().allMatch(pdn))
		{
			DataLogger.systemLog("Devicename "+ pdn + " found\n");
			result = 0;
		}
		if (result == 1) {
			String pass = pswa.getPeperString(25);
			Integer role = 1;
			String token = pswa.hash(pass.toCharArray());
			try {
				qb.addNewDevice(dName, dIP, dType, MACAdres);
				row.add(dName);
				deviceNameList.add(row);
				JOptionPane.showMessageDialog(null, "Device "+ dName +"has been Added!");
			} catch (Exception e) {
				DataLogger.errorLog(e);
			}
		}
	}
	
	public void createNewDevice(String ufn, String uun, String uea, String uro) {
		Integer result = 1; // Standard it will say it successfull.
		ArrayList<String> row = new ArrayList<String>();
		Predicate<User> pun = e -> e.username.contains(uun);
		Predicate<User> pea = e -> e.emailaddress.contains(uea);
		DataLogger.systemLog("uun: " + uun + "\n");
		
		if (userObject.stream().allMatch(pun))
		{
			DataLogger.systemLog("Username "+ uun + " found\n");
			result = 0;
		} 
		//Emailadres wordt gevonden wanneer deze leeg is
		if (userObject.stream().allMatch(pea))
		{
			DataLogger.systemLog("Emailaddress "+ uea + " found\n");
			result = 0;
		}
		
		if (result == 1) {
			String pass = pswa.getPeperString(25);
			Integer role = 1;
			String token = pswa.hash(pass.toCharArray());
			try {
				qb.insertUser(ufn, uun, pass, uea, role, token);
				row.add(uun);
				userNameList.add(row);
				JOptionPane.showMessageDialog(null, "User " + uun + " has been Added!");
			} catch (Exception e) {
				DataLogger.errorLog(e);
			}
			
		} else {
			DataLogger.systemLog("Error");
		}

	}
  
  // edit device


  //remove devive
	public void deleteDevice(String deviceName) {
		ArrayList<String> row = new ArrayList<String>();
		try {
			qb.deleteDevice(deviceName);
			row.add(deviceName);
			deviceNameList.remove(row);
			JOptionPane.showMessageDialog(null, "Device has been Deleted!");
		} catch (Exception e) {
			DataLogger.errorLog(e);
		}
	}
	
	/* 
	 * Method: createNewUser
	 * Return: Integer, success (1) / Failed (0)
	 */
	public void createNewUser(String ufn, String uun, String uea, String uro) {
		Integer result = 1; // Standard it will say it successfull.
		ArrayList<String> row = new ArrayList<String>();
		Predicate<User> pun = e -> e.username.contains(uun);
		Predicate<User> pea = e -> e.emailaddress.contains(uea);
		DataLogger.systemLog("uun: " + uun + "\n");
		
		if (userObject.stream().allMatch(pun))
		{
			DataLogger.systemLog("Username "+ uun + " found\n");
			result = 0;
		} 
		//Emailadres wordt gevonden wanneer deze leeg is
		if (userObject.stream().allMatch(pea))
		{
			DataLogger.systemLog("Emailaddress "+ uea + " found\n");
			result = 0;
		}
		
		if (result == 1) {
			String pass = pswa.getPeperString(25);
			Integer role = 1;
			String token = pswa.hash(pass.toCharArray());
			try {
				qb.insertUser(ufn, uun, pass, uea, role, token);
				row.add(uun);
				userNameList.add(row);
				JOptionPane.showMessageDialog(null, "User has been Added!");
			} catch (Exception e) {
				DataLogger.errorLog(e);
			}
			
		} else {
			DataLogger.systemLog("Error");
		}

	}
	
	//Disable User
	public void enableDisableUser(int id, String userName) {
		try {
			qb.enableDisableDevice(id, userName);
		} catch (Exception e) {
			DataLogger.errorLog(e);
		}
	}

}

package logic_tier;

import java.sql.Connection;
import data_tier.QueryBuilder;
import data_tier.DatabaseManager;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.sql.ResultSet;

public class User {

	protected DatabaseManager dbm = new DatabaseManager();
	protected QueryBuilder qb = new QueryBuilder();
	protected Connection db;

	protected int userID;
	protected String name;
	protected String email;
	private String password;

	private String[][] convertArrayListToArray(ArrayList<ArrayList<String>> output) {
		String[][] array = new String[output.size()][];
		for (int i = 0; i < output.size(); i++) {
			ArrayList<String> row = output.get(i);
			System.out.println(output.get(i).toString());
			array[i] = row.toArray(new String[row.size()]);
		}
		return array;
	}

	public String[][] Devices() {
		System.out.println("Starting data retrieval");
		ResultSet rs = null;
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		try {
			System.out.println("Start Try");
			rs = qb.Devices();
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				row.add(rs.getString("name"));
				output.add(row);
			}
			System.out.println(output.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		return convertArrayListToArray(output);
	}

	public Integer getDeviceTypeID(String deviceName) {
		System.out.println("Device Type ID Database Loaded");
		Integer typeID = null;
		ResultSet rs = null;
		try {
			rs = qb.DevicesTypeID(deviceName);
			System.out.println("# - Updating...");

			while (rs.next()) {
				System.out.println(rs.getInt("typeID"));
				typeID = rs.getInt("typeID");
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
		return typeID;
	}

	public void getDeviceTypes(JComboBox selectBox) {
		System.out.println("Device Type Database Loaded");
		ResultSet rs = null;
		try {
			rs = qb.getDeviceTypes();
			System.out.println("# - Updating...");

			while (rs.next()) {
				selectBox.addItem(rs.getString("Omschrijving"));
				System.out.println(rs.getString("Omschrijving"));
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "ERROR");
		}
		System.out.println(selectBox.getItemCount());
	}

	public void deleteDevice(String deviceName) {
		try {
			qb.deleteDevice(deviceName);
			// PreparedStatement pst;
			// pst = db.prepareStatement(query);
			// pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Device has been Deleted!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

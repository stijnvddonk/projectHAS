package logic_tier;

import java.sql.Connection;
import data_tier.QueryBuilder;
import data_tier.DatabaseManager;
import java.util.ArrayList;
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

	public String[][] projects() {
		ResultSet rs = null;
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		try {
			System.out.println("Start Try");
			rs = qb.Devices();
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				row.add(Integer.toString(rs.getInt("projectID")).replaceAll("\\s", ""));
				row.add(rs.getString("customerName").replaceAll("\\s", ""));
				row.add(rs.getString("projectName").replaceAll("\\s", ""));
				row.add(rs.getString("statusOmschrijving").replaceAll("\\s", ""));
				row.add(rs.getString("startDate"));
				row.add(rs.getString("endDate"));
				row.add(rs.getString("userName").replaceAll("\\s", ""));
				output.add(row);
			}
			System.out.println(output.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		return convertArrayListToArray(output);
	}

}

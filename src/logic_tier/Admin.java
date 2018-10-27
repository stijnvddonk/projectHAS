package logic_tier;

import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.sql.PreparedStatement;

public class Admin extends User{

  public Map<String, String> deviceData = new HashMap<String, String>();

  // add device
  public void newDevice(String deviceName, String deviceType) {
		System.out.println("new Device Method Called");
		//Date date = new java.sql.Date(new java.util.Date().getTime());
		try {
			System.out.println("Device Name: " + deviceName);
			String query = qb.insertNewDevice();
			PreparedStatement pst;
			pst = db.prepareStatement(query);
      //AANPASSEN
			// pst.setInt(1, customerID);
			// pst.setString(2, projectName);
			// pst.setInt(3, 1);
			// pst.setDate(4, date);
			// pst.setInt(5, 1);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e);
		}
	}
  // edit device


  // disable device
  public void disableDevice() {
  try {
    int deviceID = Integer.parseInt(deviceData.get("deviceID"));
    String query = qb.deleteDevice(deviceID);
    PreparedStatement pst;
    pst = db.prepareStatement(query);
    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "Device has been Disabled!");
  } catch (Exception e) {
    System.out.println(e);
  }
}


  //remove devive
  public void deleteDevice() {
  try {
    int deviceID = Integer.parseInt(deviceData.get("deviceID"));
    String query = qb.deleteDevice(deviceID);
    PreparedStatement pst;
    pst = db.prepareStatement(query);
    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "Device has been Deleted!");
  } catch (Exception e) {
    System.out.println(e);
  }
}

}

package logic_tier;

import data_tier.DataLogger;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.sql.PreparedStatement;

public class Admin extends User{

  public Map<String, String> deviceData = new HashMap<String, String>();

  // add device
//  public void newDevice(String deviceName, String deviceType) {
//		DataLogger.systemLog("new Device Method Called");
//		try {
//			DataLogger.systemLog("Device Name: " + deviceName);
//			//Resultset query = qb.insertNewDevice(deviceName, MACAdres, IPAdres, versionNumber, deviceType);
//			//PreparedStatement pst;
//			//pst = db.prepareStatement(query);
//			 pst.setInt(1, deviceID);
//			 pst.setString(2, deviceName);
//			 pst.setString(3, MAC);
//			 pst.setString(4, IP);
//			 pst.setString(5, versionNumber);
//
//			 pst.setInt(6, typeID);
//			//pst.executeUpdate();
//		} catch (Exception e) {
//			DataLogger.errorLog(e);
//			JOptionPane.showMessageDialog(null, e);
//		}
//	}
  // edit device


  // disable device
  public void disableDevice() {
  try {
    int deviceID = Integer.parseInt(deviceData.get("deviceID"));
    //ResultSet query = qb.deleteDevice(deviceID);
    //PreparedStatement pst;
    //pst = db.prepareStatement(query);
    //pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "Device has been Disabled!");
  } catch (Exception e) {
    DataLogger.errorLog(e);
  }
}

// public void deleteDevice(String deviceName) {
// try {
//   ResultSet query = qb.deleteDevice(deviceName);
//   // PreparedStatement pst;
//   // pst = db.prepareStatement(query);
//   // pst.executeUpdate();
//   JOptionPane.showMessageDialog(null, "User has been Deleted!");
// } catch (Exception e) {
//   DataLogger.errorLog(e);
// }
// }

  //remove devive
//  public void deleteDevice() {
//  try {
//    int deviceID = Integer.parseInt(deviceData.get("deviceID"));
//    String query = qb.deleteDevice(deviceID);
//    PreparedStatement pst;
//    pst = db.prepareStatement(query);
//    pst.executeUpdate();
//    JOptionPane.showMessageDialog(null, "Device has been Deleted!");
//  } catch (Exception e) {
//    DataLogger.errorLog(e);
//  }
//}

}

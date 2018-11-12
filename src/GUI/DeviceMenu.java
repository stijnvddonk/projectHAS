package GUI;

import java.util.Locale;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import logic_tier.Device;
import logic_tier.User;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.border.LineBorder;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import data_tier.DataLogger;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListSelectionModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeviceMenu extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	User us;
	Device dev;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	DateFormat df = new SimpleDateFormat("HH:mm");
	private JButton btnSaveSettings = new JButton("Save Settings");
	private JToggleButton deviceEnableButton = new JToggleButton("On");
	private JToggleButton deviceTimerStatus = new JToggleButton("On");
	private JLabel lblNewLabel = new JLabel("Device 1");
	private JLabel lblTimer = new JLabel("Timer Status");
	private JLabel lblTimerOn = new JLabel("Timer On");
	private JLabel lblTimerOff = new JLabel("Timer Off");
	private JComboBox<String> comboBoxTimeOn = new JComboBox<>(startTime());
	private JComboBox<String> comboBoxTimeOff = new JComboBox<>(endTime());
	private Color activeMenu = Color.decode("#43B7BA");
	private Color hoverMenu = Color.decode("#ba8243");
	private Color notActiveMenu = Color.decode("#cfa77a");
	DateFormat sdf = new SimpleDateFormat("hh:mm");

	/**
	 * Create the frame.
	 */
	public DeviceMenu(User user) {
		us = user;

		setTitle("Device Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		this.setUndecorated(true);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 350, 720);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(349, 0, 203, 720);
		contentPane.add(panel_1);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 193, 349, 12);
		panel.add(separator_5);

		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(77, 622, 190, 70);
		panel.add(btnNewButton);

		JLabel lblDevices = new JLabel("Devices");
		lblDevices.setForeground(activeMenu);
		lblDevices.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevices.setBounds(6, 207, 190, 55);
		panel.add(lblDevices);

		JLabel lblUsers = new JLabel("Users");
		lblUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserMenu um = new UserMenu(us);
				um.setVisible(true);
				dispose();
			}
		});
		lblUsers.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblUsers.setBounds(6, 267, 190, 55);
		panel.add(lblUsers);

		JLabel lblSystemSettings = new JLabel("System Settings");
		lblSystemSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SystemSettings ss = new SystemSettings(us);
				ss.setVisible(true);
				dispose();
			}
		});
		lblSystemSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSystemSettings.setBounds(6, 327, 300, 55);
		panel.add(lblSystemSettings);

		JButton btnNewDevice = new JButton("New Device");
		btnNewDevice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NewDeviceMenu ndm = new NewDeviceMenu(us);
				ndm.setVisible(true);
				dispose();
			}
		});
		panel_1.setLayout(null);
		btnNewDevice.setBounds(6, 622, 190, 70);
		panel_1.add(btnNewDevice);

		JButton btnRemoveDevice = new JButton("Remove Device");
		btnRemoveDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String deviceName = lblNewLabel.getText();
				JFrame frmDeleteDevice = new JFrame("Cancel");
				if (JOptionPane.showConfirmDialog(frmDeleteDevice, "Confirm you want to Delete " + deviceName,
						"Delete Device", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					DataLogger.systemLog("About to Delete: " + deviceName);
					us.deleteDevice(deviceName);
					createTable();
				}
			}
		});
		btnRemoveDevice.setBounds(1084, 622, 190, 70);
		contentPane.add(btnRemoveDevice);

		JSeparator separator = new JSeparator();
		separator.setBounds(549, 139, 725, 12);
		contentPane.add(separator);

		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblNewLabel.setBounds(601, 39, 277, 55);
		contentPane.add(lblNewLabel);

		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSettings.setBounds(850, 152, 166, 55);
		contentPane.add(lblSettings);

		lblTimer.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblTimer.setBounds(601, 209, 277, 55);
		contentPane.add(lblTimer);

		lblTimerOn.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblTimerOn.setBounds(601, 300, 277, 55);
		contentPane.add(lblTimerOn);

		lblTimerOff.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblTimerOff.setBounds(601, 393, 277, 55);
		contentPane.add(lblTimerOff);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(579, 94, 363, 12);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(579, 252, 310, 12);
		contentPane.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(579, 348, 310, 12);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(579, 436, 310, 12);
		contentPane.add(separator_4);

		comboBoxTimeOn.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		comboBoxTimeOn.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("HH:mm")));
		comboBoxTimeOn.setBounds(1020, 300, 161, 55);
		contentPane.add(comboBoxTimeOn);

		comboBoxTimeOff.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		comboBoxTimeOff.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("HH:mm")));
		comboBoxTimeOff.setBounds(1020, 393, 161, 55);
		contentPane.add(comboBoxTimeOff);

		btnSaveSettings.setBounds(601, 622, 190, 70);
		btnSaveSettings.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String deviceName = lblNewLabel.getText();
				setOnOffTime(comboBoxTimeOn, comboBoxTimeOff, deviceName);
				JOptionPane.showMessageDialog(null, "Settings have been Saved ", "Settings Saved",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(btnSaveSettings);

		deviceTimerStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String deviceName = lblNewLabel.getText();
				int i = us.getDeviceTimerEnabledStatus(deviceName);
				DataLogger.systemLog("getDeviceTimerEnabledStatus: " + i);
				if (i == 0) {
					us.enableDisableDeviceTimer(1, deviceName);
					deviceTimerStatus.setText("On");
					DataLogger.systemLog("Device Status ON");
					deviceTimerStatus.setSelected(true);
					comboBoxTimeOn.setEnabled(true);
					comboBoxTimeOff.setEnabled(true);
				} else {
					us.enableDisableDeviceTimer(0, deviceName);
					deviceTimerStatus.setText("Off");
					DataLogger.systemLog("Device Status OFF");
					deviceTimerStatus.setSelected(false);
					comboBoxTimeOn.setEnabled(false);
					comboBoxTimeOff.setEnabled(false);
				}
			}
		});

		deviceTimerStatus.setBounds(1020, 209, 161, 51);
		contentPane.add(deviceTimerStatus);

		String value = lblNewLabel.getText();
		if (us.getDeviceEnabledStatus(value) == 1) {
			deviceEnableButton.setSelected(true);
			deviceEnableButton.setText("On");
			deviceTimerStatus.setEnabled(true);
			comboBoxTimeOn.setEnabled(true);
			comboBoxTimeOff.setEnabled(true);
		} else {
			deviceEnableButton.setSelected(false);
			deviceEnableButton.setText("Off");
			deviceTimerStatus.setSelected(false);
			deviceTimerStatus.setEnabled(false);
			comboBoxTimeOn.setEnabled(false);
			comboBoxTimeOff.setEnabled(false);
		}
		deviceEnableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String deviceName = lblNewLabel.getText();
				int i = us.getDeviceEnabledStatus(deviceName);
				if (i == 0) {
					us.enableDisableDevice(1, deviceName);
					deviceEnableButton.setText("On");
					DataLogger.systemLog("Device Status ON");
					deviceTimerStatus.setEnabled(true);
					comboBoxTimeOn.setEnabled(true);
					comboBoxTimeOff.setEnabled(true);
				} else {
					us.enableDisableDevice(0, deviceName);
					deviceEnableButton.setText("Off");
					DataLogger.systemLog("Device Status OFF");
					deviceTimerStatus.setSelected(false);
					deviceTimerStatus.setEnabled(false);
					comboBoxTimeOn.setEnabled(false);
					comboBoxTimeOff.setEnabled(false);
				}
			}
		});

		deviceEnableButton.setBounds(1020, 50, 161, 51);
		contentPane.add(deviceEnableButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 10, 190, 600);
		panel_1.add(scrollPane);

		createTable();

	}

	public void setOnOffTime(JComboBox comboBoxTimeOn, JComboBox comboBoxTimeOff, String deviceName) {
		String timeOn = comboBoxTimeOn.getSelectedItem().toString();
		System.out.println(timeOn);
		String timeOff = comboBoxTimeOff.getSelectedItem().toString();
		System.out.println(timeOff);
		us.setStartEndTime(timeOn, timeOff, deviceName);
	}

	public class DateFormattedListCellRenderer extends DefaultListCellRenderer {

		private DateFormat format;

		public DateFormattedListCellRenderer(DateFormat format) {
			this.format = format;
		}

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			if (value instanceof Date) {
				value = format.format((Date) value);
			}
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		}

	}

	private DefaultComboBoxModel startTime() {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.HOUR_OF_DAY, 0);
		startCalendar.set(Calendar.MINUTE, 0);

		Calendar startEnd = Calendar.getInstance();
		startEnd.set(Calendar.HOUR_OF_DAY, 23);
		startEnd.set(Calendar.MINUTE, 59);
		DefaultComboBoxModel<String> startTime = new DefaultComboBoxModel<>();
		do {
			SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
			String formatted = format1.format(startCalendar.getTime());
			startTime.addElement(formatted);
			startCalendar.add(Calendar.MINUTE, 15);
		} while (startCalendar.getTime().before(startEnd.getTime()));
		return startTime;
	}

	private DefaultComboBoxModel endTime() {
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.HOUR_OF_DAY, 0);
		endCalendar.set(Calendar.MINUTE, 0);

		Calendar endEnd = Calendar.getInstance();
		endEnd.set(Calendar.HOUR_OF_DAY, 23);
		endEnd.set(Calendar.MINUTE, 59);
		DefaultComboBoxModel<String> endTime = new DefaultComboBoxModel<>();
		do {
			SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
			String formatted = format1.format(endCalendar.getTime());
			endTime.addElement(formatted);
			endCalendar.add(Calendar.MINUTE, 15);
		} while (endCalendar.getTime().before(endEnd.getTime()));
		return endTime;
	}

	public class ForcedListSelectionModel extends DefaultListSelectionModel {

		public ForcedListSelectionModel() {
			setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}

		@Override
		public void clearSelection() {
		}

		@Override
		public void removeSelectionInterval(int index0, int index1) {
		}

	}

	private void createTable() {
		if (model != null)
			model.setRowCount(0);
		String[] header = { "Device Name" };
		DataLogger.systemLog("Loading data");
		String[][] data = us.Devices();
		model = new DefaultTableModel(data, header);
		table = new JTable(model) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.getTableHeader().setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		table.setRowHeight(35);
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		table.setSelectionModel(new ForcedListSelectionModel());
		;
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(186);
		table.changeSelection(0, 0, false, false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = 0;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, column).toString();
				lblNewLabel.setText(value);
				int typeID = us.getDeviceTypeID(value);
				DataLogger.systemLog("DeviceEnabled Status : " + us.getDeviceEnabledStatus(value));
				us.getOnOffTimer(comboBoxTimeOn, comboBoxTimeOff, value);
				if (us.getDeviceEnabledStatus(value) == 1) {
					us.enableDisableDevice(1, value);
					deviceEnableButton.setSelected(true);
					deviceEnableButton.setText("On");
					deviceTimerStatus.setEnabled(true);
					comboBoxTimeOn.setEnabled(true);
					comboBoxTimeOff.setEnabled(true);
				} else {
					us.enableDisableDevice(0, value);
					deviceEnableButton.setSelected(false);
					deviceEnableButton.setText("Off");
					deviceTimerStatus.setSelected(false);
					deviceTimerStatus.setEnabled(false);
					comboBoxTimeOn.setEnabled(false);
					comboBoxTimeOff.setEnabled(false);
				}
				setStuffVisible(typeID);
			}
		});
		scrollPane.setViewportView(table);
	}

	public void setStuffVisible(int typeID) {
		DataLogger.systemLog("Value: " + typeID);
		switch (typeID) {
		case 1:
			lblTimer.setVisible(true);
			lblTimerOn.setVisible(true);
			lblTimerOff.setVisible(true);
			deviceTimerStatus.setVisible(true);
			comboBoxTimeOn.setVisible(true);
			comboBoxTimeOff.setVisible(true);
			DataLogger.systemLog("Labels Visible");
			break;
		case 2:
			lblTimer.setVisible(false);
			lblTimerOn.setVisible(false);
			lblTimerOff.setVisible(false);
			deviceTimerStatus.setVisible(false);
			comboBoxTimeOn.setVisible(false);
			comboBoxTimeOff.setVisible(false);
			DataLogger.systemLog("Labels Not Visible");
			break;
		case 3:
			lblTimer.setVisible(false);
			lblTimerOn.setVisible(false);
			lblTimerOff.setVisible(false);
			deviceTimerStatus.setVisible(false);
			comboBoxTimeOn.setVisible(false);
			comboBoxTimeOff.setVisible(false);
			DataLogger.systemLog("Labels Not Visible");
			break;
		}
	}
}

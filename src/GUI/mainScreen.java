package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import data_tier.DataLogger;
import logic_tier.Device;
import logic_tier.User;

public class mainScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User us;
	private Device dev;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	private DateFormat df = new SimpleDateFormat("HH:mm");
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
	private DateFormat sdf = new SimpleDateFormat("hh:mm");
	private JPanel panel;
	private JPanel panel_1;
	
	// Users
	private JLabel lblUser = new JLabel("admin");
	private JTextField textField;
	private JTextField textField_1;
	
	// new users
	private JTextField txtUsername;
	private JTextField txtFullName;
	private JTextField txtEmailaddress;
	private JComboBox cbUserRole = new JComboBox();

	// New Device
	private JTextField txtDeviceName;
	private JComboBox cbDeviceType = new JComboBox();


	/**
	 * Create the frame.
	 */
	public mainScreen(User _us) {
		this.us = _us;

		setTitle("Home Automation System - HAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
		contentPane = null;
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		devices();
	}
	
	public void resetContentPane() {
		
		contentPane.removeAll();
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void devices() {
		setTitle("Device Menu");
		resetContentPane();
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 350, 720);
		contentPane.add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
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
				users();
			}
		});
		lblUsers.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblUsers.setBounds(6, 267, 190, 55);
		panel.add(lblUsers);

		JLabel lblSystemSettings = new JLabel("System Settings");
		lblSystemSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				system();
			}
		});
		lblSystemSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSystemSettings.setBounds(6, 327, 300, 55);
		panel.add(lblSystemSettings);

		JButton btnNewDevice = new JButton("New Device");
		btnNewDevice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				newDevices();
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

	public void newDevices() {
		us.getDeviceTypes(cbDeviceType);

		resetContentPane();
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 350, 720);
		contentPane.add(panel);
		panel.setLayout(null);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 193, 349, 12);
		panel.add(separator_5);

		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(77, 622, 190, 70);
		panel.add(btnNewButton);

		JLabel lblDevices = new JLabel("Devices");
		lblDevices.setForeground(Color.LIGHT_GRAY);
		lblDevices.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevices.setBounds(6, 207, 190, 55);
		panel.add(lblDevices);

		JLabel lblUsers = new JLabel("Users");
		lblUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				users();
			}
		});
		lblUsers.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblUsers.setBounds(6, 267, 190, 55);
		panel.add(lblUsers);

		JLabel lblSystemSettings = new JLabel("System Settings");
		lblSystemSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				system();
			}
		});
		lblSystemSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSystemSettings.setBounds(6, 327, 300, 55);
		panel.add(lblSystemSettings);

		JButton btnAddDevice = new JButton("Create Device");
		btnAddDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtDeviceName.getText().equals("")) {
					JOptionPane.showMessageDialog(txtDeviceName, "Please enter a valid Device Name");
				} else {
					newDevice();
					devices();
				}
			}
		});
		btnAddDevice.setBounds(993, 622, 190, 70);
		contentPane.add(btnAddDevice);

		JSeparator separator = new JSeparator();
		separator.setBounds(458, 139, 725, 12);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("New Device");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblNewLabel.setBounds(738, 47, 277, 55);
		contentPane.add(lblNewLabel);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(652, 101, 363, 12);
		contentPane.add(separator_1);

		JLabel label = new JLabel("Information");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		label.setBounds(759, 153, 209, 55);
		contentPane.add(label);

		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblType.setBounds(510, 210, 277, 55);
		contentPane.add(lblType);

		JLabel lblMacAddress = new JLabel("MAC ADDRESS");
		lblMacAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblMacAddress.setBounds(510, 311, 277, 55);
		contentPane.add(lblMacAddress);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblName.setBounds(510, 426, 277, 55);
		contentPane.add(lblName);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(510, 252, 310, 12);
		contentPane.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(510, 361, 310, 12);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(510, 469, 310, 12);
		contentPane.add(separator_4);

		txtDeviceName = new JTextField();
		txtDeviceName.setColumns(10);
		txtDeviceName.setBounds(510, 480, 602, 48);
		contentPane.add(txtDeviceName);
		cbDeviceType.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		cbDeviceType.setBounds(510, 252, 602, 66);
		contentPane.add(cbDeviceType);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(652, 196, 363, 12);
		contentPane.add(separator_6);

		JLabel lblNewLabel_1 = new JLabel("WORDT GEGENEREERD DOOR CODE");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(510, 366, 602, 48);
		contentPane.add(lblNewLabel_1);

		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				devices();
			}
		});
		btnReturn.setBounds(510, 622, 190, 70);
		contentPane.add(btnReturn);
	}
	
	public void users() {
		resetContentPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 350, 720);
		contentPane.add(panel);
		panel.setLayout(null);

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
		lblDevices.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				devices();
			}
		});
		lblDevices.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevices.setBounds(6, 207, 190, 55);
		panel.add(lblDevices);

		JLabel lblUsers = new JLabel("Users");
		lblUsers.setForeground(Color.LIGHT_GRAY);
		lblUsers.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblUsers.setBounds(6, 267, 190, 55);
		panel.add(lblUsers);

		JLabel lblSystemSettings = new JLabel("System Settings");
		lblSystemSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				system();
			}
		});
		lblSystemSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSystemSettings.setBounds(6, 327, 300, 55);
		panel.add(lblSystemSettings);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(350, 0, 200, 720);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewDecicw = new JButton("New User");
		btnNewDecicw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newUsers();
			}
		});
		btnNewDecicw.setBounds(6, 622, 190, 70);
		panel_1.add(btnNewDecicw);

		JButton btnRemoveDevice = new JButton("Remove User");
		btnRemoveDevice.setBounds(1084, 622, 190, 70);
		contentPane.add(btnRemoveDevice);

		JSeparator separator = new JSeparator();
		separator.setBounds(549, 139, 725, 12);
		contentPane.add(separator);

		lblUser.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblUser.setBounds(865, 39, 277, 55);
		contentPane.add(lblUser);

		JLabel lblSettings = new JLabel("Information");
		lblSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSettings.setBounds(850, 152, 209, 55);
		contentPane.add(lblSettings);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(743, 94, 363, 12);
		contentPane.add(separator_1);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(743, 195, 363, 12);
		contentPane.add(separator_6);

		JLabel label = new JLabel("Name");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		label.setBounds(601, 209, 277, 55);
		contentPane.add(label);

		JLabel label_1 = new JLabel("E-mail");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		label_1.setBounds(601, 324, 277, 55);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Rights");
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		label_2.setBounds(601, 425, 277, 55);
		contentPane.add(label_2);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(601, 251, 310, 12);
		contentPane.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(601, 367, 310, 12);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(601, 4720, 310, 12);
		contentPane.add(separator_4);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(601, 377, 602, 48);
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(601, 264, 602, 48);
		contentPane.add(textField_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(601, 479, 291, 48);
		contentPane.add(comboBox);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 10, 190, 600);
		panel_1.add(scrollPane);

		createTableUser();

	}
	
	public void newUsers() {
		resetContentPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 350, 720);
		contentPane.add(panel);
		panel.setLayout(null);

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
		lblDevices.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				devices();
			}
		});
		lblDevices.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevices.setBounds(6, 207, 190, 55);
		panel.add(lblDevices);

		JLabel lblUsers = new JLabel("Users");
		lblUsers.setForeground(Color.LIGHT_GRAY);
		lblUsers.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblUsers.setBounds(6, 267, 190, 55);
		panel.add(lblUsers);

		JLabel lblSystemSettings = new JLabel("System Settings");
		lblSystemSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				system();
			}
		});
		lblSystemSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSystemSettings.setBounds(6, 327, 300, 55);
		panel.add(lblSystemSettings);

		JSeparator separator = new JSeparator();
		separator.setBounds(457, 139, 725, 12);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("New user ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblNewLabel.setBounds(651, 39, 399, 55);
		contentPane.add(lblNewLabel);

		JLabel lblSettings = new JLabel("User details");
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSettings.setBounds(651, 152, 363, 55);
		contentPane.add(lblSettings);

		JLabel lblTimer = new JLabel("Full name");
		lblTimer.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblTimer.setBounds(509, 203, 277, 55);
		contentPane.add(lblTimer);

		JLabel lblTimerOn = new JLabel("Username");
		lblTimerOn.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblTimerOn.setBounds(509, 318, 277, 55);
		contentPane.add(lblTimerOn);

		JLabel lblTimerOff = new JLabel("User role");
		lblTimerOff.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblTimerOff.setBounds(509, 528, 277, 55);
		contentPane.add(lblTimerOff);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(651, 94, 363, 12);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(509, 245, 310, 12);
		contentPane.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(509, 361, 310, 12);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(509, 571, 310, 12);
		contentPane.add(separator_4);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtUsername.setColumns(10);
		txtUsername.setBounds(509, 371, 602, 48);
		contentPane.add(txtUsername);

		txtFullName = new JTextField();
		txtFullName.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtFullName.setColumns(10);
		txtFullName.setBounds(509, 270, 602, 48);
		contentPane.add(txtFullName);

		cbUserRole.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbUserRole.setModel(new DefaultComboBoxModel(new String[] { "Admin", "User" }));
		cbUserRole.setBounds(509, 582, 291, 48);
		contentPane.add(cbUserRole);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(651, 195, 363, 12);
		contentPane.add(separator_6);

		txtEmailaddress = new JTextField();
		txtEmailaddress.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtEmailaddress.setColumns(10);
		txtEmailaddress.setBounds(509, 483, 602, 48);
		contentPane.add(txtEmailaddress);

		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(509, 473, 310, 12);
		contentPane.add(separator_7);

		JLabel lblEmailaddress = new JLabel("Emailaddress");
		lblEmailaddress.setFont(new Font("Dialog", Font.PLAIN, 35));
		lblEmailaddress.setBounds(509, 436, 277, 55);
		contentPane.add(lblEmailaddress);

		JButton btnAddUser = new JButton("Save & Close");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtUsername.getText().equals("")) {
					JOptionPane.showMessageDialog(txtUsername, "Please enter a valid Username Name");
				} else {
					newUser();
					users();
				}
			}
		});
		btnAddUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				newUser();
				users();
			}
		});
		btnAddUser.setFont(new Font("Calibri", Font.BOLD, 18));
		btnAddUser.setBounds(992, 622, 190, 70);
		contentPane.add(btnAddUser);

		JButton button = new JButton("Return");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				users();
			}
		});
		button.setBounds(395, 622, 190, 70);
		contentPane.add(button);
	}
	
	public void system() {
		resetContentPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 350, 720);
		contentPane.add(panel);
		panel.setLayout(null);

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
		lblDevices.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				devices();
			}
		});
		lblDevices.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevices.setBounds(6, 207, 190, 55);
		panel.add(lblDevices);

		JLabel lblUsers = new JLabel("Users");
		lblUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				users();
			}
		});
		lblUsers.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblUsers.setBounds(6, 267, 190, 55);
		panel.add(lblUsers);

		JLabel lblSystemSettings = new JLabel("System Settings");
		lblSystemSettings.setForeground(Color.LIGHT_GRAY);
		lblSystemSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSystemSettings.setBounds(6, 327, 300, 55);
		panel.add(lblSystemSettings);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(350, 0, 200, 720);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblUser = new JLabel("System");
		lblUser.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblUser.setBounds(6, 30, 190, 55);
		panel_1.add(lblUser);

		JSeparator separator = new JSeparator();
		separator.setBounds(549, 139, 725, 12);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("System");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblNewLabel.setBounds(865, 39, 277, 55);
		contentPane.add(lblNewLabel);

		JLabel lblSettings = new JLabel("System Information");
		lblSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSettings.setBounds(762, 152, 329, 55);
		contentPane.add(lblSettings);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(743, 94, 363, 12);
		contentPane.add(separator_1);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(743, 195, 363, 12);
		contentPane.add(separator_6);

		JLabel lblSystemName = new JLabel("System name");
		lblSystemName.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSystemName.setBounds(601, 209, 277, 55);
		contentPane.add(lblSystemName);

		JLabel lblSystemVersion = new JLabel("System Version");
		lblSystemVersion.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSystemVersion.setBounds(601, 313, 277, 55);
		contentPane.add(lblSystemVersion);

		JLabel lblIpAdres = new JLabel("IP Adres");
		lblIpAdres.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblIpAdres.setBounds(601, 413, 277, 55);
		contentPane.add(lblIpAdres);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(601, 251, 310, 12);
		contentPane.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(601, 359, 310, 12);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(601, 457, 310, 12);
		contentPane.add(separator_4);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(601, 264, 602, 48);
		contentPane.add(textField_1);

		JLabel lblV = new JLabel("V1.1.1");
		lblV.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblV.setBounds(601, 365, 602, 48);
		contentPane.add(lblV);

		JLabel label_1 = new JLabel("192.168.1.1");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		label_1.setBounds(601, 466, 602, 48);
		contentPane.add(label_1);

		JLabel lblMacAdres = new JLabel("MAC ADDRESS");
		lblMacAdres.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblMacAdres.setBounds(601, 508, 277, 55);
		contentPane.add(lblMacAdres);

		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(601, 553, 310, 12);
		contentPane.add(separator_7);

		JLabel lblaetup = new JLabel("00:18:AE:48:TU:P4");
		lblaetup.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblaetup.setBounds(601, 562, 602, 48);
		contentPane.add(lblaetup);

		JButton btnCheckForUpdates = new JButton("Check for Updates");
		btnCheckForUpdates.setBounds(601, 622, 190, 70);
		contentPane.add(btnCheckForUpdates);
	}
	
	private void createTableUser() {
		if (model != null)
			model.setRowCount(0);
		String[] header = { "User Name" };

		DataLogger.systemLog("Loading data");
		String[][] data = us.Users();
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
		table.setSelectionModel(new ForcedListSelectionModelUser());
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(186);
		table.setRowSelectionInterval(0, 0);
		table.changeSelection(0, 0, false, false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = 0;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, column).toString();
				lblUser.setText(value);
			}
		});
		scrollPane.setViewportView(table);
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

	public void newDevice() {
		String deviceType = cbDeviceType.getSelectedItem().toString();
		int typeID = us.getTypeID(deviceType);
		DataLogger.systemLog(deviceType);
		String deviceName = txtDeviceName.getText();
		String lastIP = us.getTopIP();
		String newIP = "192.168.10." + (Integer.parseInt(lastIP.substring(11, lastIP.length())) + 1);
		DataLogger.systemLog(newIP);
		us.createNewDevice(deviceName, newIP, typeID);
	}

	public void newUser() {
		String ufn = txtFullName.getText();
		String uun = txtUsername.getText();
		String uea = txtEmailaddress.getText();
		String uro = String.valueOf(cbUserRole.getSelectedItem());
		DataLogger.systemLog(
				"\nFull Name: " + ufn + "\nUsername: " + uun + "\nEmailaddress: " + uea + "\nRole: " + uro + "\n");
		us.createNewUser(ufn, uun, uea, uro);
	}
	
	public class ForcedListSelectionModelUser extends DefaultListSelectionModel {

		public ForcedListSelectionModelUser() {
			setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}

		@Override
		public void clearSelection() {
		}

		@Override
		public void removeSelectionInterval(int index0, int index1) {
		}

	}

}

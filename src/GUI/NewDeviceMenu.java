package GUI;

import data_tier.DataLogger;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

import logic_tier.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewDeviceMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JComboBox combobox = new JComboBox();
	private User us;
	/**
	 * Create the frame.
	 */
	public NewDeviceMenu(User _us) {
		us = _us;
		us.getDeviceTypes(combobox);
		setTitle("Device Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,720);
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
		lblDevices.setForeground(Color.LIGHT_GRAY);
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

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(350, 0, 200, 720);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnRemoveDevice = new JButton("Create Device");
		btnRemoveDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(textField_1, "Please enter a valid Device Name");
				} else {
					newDevice();
					DeviceMenu dm = new DeviceMenu(us);
					dm.setVisible(true);
					dispose();
				}
			}
		});
		btnRemoveDevice.setBounds(1084, 622, 190, 70);
		contentPane.add(btnRemoveDevice);

		JSeparator separator = new JSeparator();
		separator.setBounds(549, 139, 725, 12);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("New Device");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblNewLabel.setBounds(829, 47, 277, 55);
		contentPane.add(lblNewLabel);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(743, 101, 363, 12);
		contentPane.add(separator_1);

		JLabel label = new JLabel("Information");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		label.setBounds(850, 153, 209, 55);
		contentPane.add(label);

		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblType.setBounds(601, 210, 277, 55);
		contentPane.add(lblType);

		JLabel lblMacAddress = new JLabel("MAC ADDRESS");
		lblMacAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblMacAddress.setBounds(601, 311, 277, 55);
		contentPane.add(lblMacAddress);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblName.setBounds(601, 426, 277, 55);
		contentPane.add(lblName);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(601, 252, 310, 12);
		contentPane.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(601, 361, 310, 12);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(601, 469, 310, 12);
		contentPane.add(separator_4);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(601, 480, 602, 48);
		contentPane.add(textField_1);
		combobox.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		combobox.setBounds(601, 252, 602, 66);
		contentPane.add(combobox);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(743, 196, 363, 12);
		contentPane.add(separator_6);

		JLabel lblNewLabel_1 = new JLabel("WORDT GEGENEREERD DOOR CODE");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(601, 366, 602, 48);
		contentPane.add(lblNewLabel_1);

		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeviceMenu dm = new DeviceMenu(us);
				dm.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(601, 622, 190, 70);
		contentPane.add(btnReturn);
	}

	public void newDevice() {
		String deviceType = combobox.getSelectedItem().toString();
		int typeID = us.getTypeID(deviceType);
		DataLogger.log(deviceType+"\n");
		String deviceName = textField_1.getText();
		String lastIP= us.getTopIP();
		String newIP = "192.168.10." + (Integer.parseInt(lastIP.substring(11,lastIP.length()))+1);
		DataLogger.log(newIP+"\n");
	    us.addNewDevice(deviceName, newIP, typeID);
	}
}

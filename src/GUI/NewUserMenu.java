package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

import logic_tier.User;

public class NewUserMenu extends JFrame {

	private User us;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;

	/**
	 * Create the frame.
	 * @param _us 
	 */
	public NewUserMenu(User _us) {
		us = _us;
		setTitle("New User");
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
		lblDevices.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DeviceMenu dm = new DeviceMenu(us);
				dm.setVisible(true);
				dispose();
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
		
		JLabel lblUser = new JLabel("User 1");
		lblUser.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblUser.setBounds(6, 30, 190, 55);
		panel_1.add(lblUser);
		
		JLabel lblDevice = new JLabel("User 2");
		lblDevice.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevice.setBounds(6, 90, 190, 55);
		panel_1.add(lblDevice);
		
		JLabel lblDevice_1 = new JLabel("User 3");
		lblDevice_1.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevice_1.setBounds(6, 150, 190, 55);
		panel_1.add(lblDevice_1);
		
		JLabel lblDevice_4 = new JLabel("User 6");
		lblDevice_4.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevice_4.setBounds(6, 325, 190, 55);
		panel_1.add(lblDevice_4);
		
		JLabel lblDevice_2 = new JLabel("User 4");
		lblDevice_2.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevice_2.setBounds(6, 205, 190, 55);
		panel_1.add(lblDevice_2);
		
		JLabel lblDevice_3 = new JLabel("User 5");
		lblDevice_3.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevice_3.setBounds(6, 265, 190, 55);
		panel_1.add(lblDevice_3);
		
		JButton btnRemoveDevice = new JButton("Create User");
		btnRemoveDevice.setBounds(1084, 622, 190, 70);
		contentPane.add(btnRemoveDevice);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(549, 139, 725, 12);
		contentPane.add(separator);
		
		JLabel lblNewLabel = new JLabel("New User");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblNewLabel.setBounds(865, 39, 277, 55);
		contentPane.add(lblNewLabel);
		
		JLabel lblSettings = new JLabel("Information");
		lblSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblSettings.setBounds(850, 152, 209, 55);
		contentPane.add(lblSettings);
		
		JLabel lblTimer = new JLabel("Name");
		lblTimer.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblTimer.setBounds(601, 209, 277, 55);
		contentPane.add(lblTimer);
		
		JLabel lblTimerOn = new JLabel("E-mail");
		lblTimerOn.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblTimerOn.setBounds(601, 324, 277, 55);
		contentPane.add(lblTimerOn);
		
		JLabel lblTimerOff = new JLabel("Rights");
		lblTimerOff.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblTimerOff.setBounds(601, 425, 277, 55);
		contentPane.add(lblTimerOff);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(743, 94, 363, 12);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(601, 251, 310, 12);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(601, 367, 310, 12);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(601, 468, 310, 12);
		contentPane.add(separator_4);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(601, 377, 602, 48);
		contentPane.add(textField);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(601, 264, 602, 48);
		contentPane.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "User"}));
		comboBox.setBounds(601, 479, 291, 48);
		contentPane.add(comboBox);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(743, 195, 363, 12);
		contentPane.add(separator_6);
	}
}

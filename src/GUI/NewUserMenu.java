package GUI;

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
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

import data_tier.DataLogger;
import logic_tier.User;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewUserMenu extends JFrame {

	private User us;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtFullName;
	private JTextField txtEmailaddress;
	private JComboBox cbUserRole = new JComboBox();

	/**
	 * Create the frame.
	 * 
	 * @param _us
	 */
	public NewUserMenu(User _us) {
		us = _us;
		setTitle("New User");
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
					UserMenu um = new UserMenu(us);
					um.setVisible(true);
					dispose();
				}
			}
		});
		btnAddUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				newUser();
				UserMenu um = new UserMenu(us);
				um.setVisible(true);
				dispose();
			}
		});
		btnAddUser.setFont(new Font("Calibri", Font.BOLD, 18));
		btnAddUser.setBounds(992, 622, 190, 70);
		contentPane.add(btnAddUser);

		JButton button = new JButton("Return");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMenu um = new UserMenu(us);
				um.setVisible(true);
				dispose();
			}
		});
		button.setBounds(395, 622, 190, 70);
		contentPane.add(button);
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
}

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
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class NewDeviceMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public NewDeviceMenu() {
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
				UserMenu um = new UserMenu();
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
				SystemSettings ss = new SystemSettings();
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
		
		JLabel label_3 = new JLabel("Device 1");
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		label_3.setBounds(6, 30, 190, 55);
		panel_1.add(label_3);
		
		JLabel lblDevice = new JLabel("Device 2");
		lblDevice.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevice.setBounds(6, 90, 190, 55);
		panel_1.add(lblDevice);
		
		JLabel lblDevice_1 = new JLabel("Device 3");
		lblDevice_1.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevice_1.setBounds(6, 150, 190, 55);
		panel_1.add(lblDevice_1);
		
		JLabel lblDevice_4 = new JLabel("Device 6");
		lblDevice_4.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevice_4.setBounds(6, 325, 190, 55);
		panel_1.add(lblDevice_4);
		
		JLabel lblDevice_2 = new JLabel("Device 4");
		lblDevice_2.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevice_2.setBounds(6, 205, 190, 55);
		panel_1.add(lblDevice_2);
		
		JLabel lblDevice_3 = new JLabel("Device 5");
		lblDevice_3.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblDevice_3.setBounds(6, 265, 190, 55);
		panel_1.add(lblDevice_3);
		
		JButton btnRemoveDevice = new JButton("Create Device");
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(601, 263, 602, 48);
		contentPane.add(comboBox);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(743, 196, 363, 12);
		contentPane.add(separator_6);
		
		JLabel lblNewLabel_1 = new JLabel("00:18:AE:48:TU:P4");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(601, 366, 602, 48);
		contentPane.add(lblNewLabel_1);
	}
}

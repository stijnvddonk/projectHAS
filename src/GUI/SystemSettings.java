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

public class SystemSettings extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public SystemSettings() {
		setTitle("User Menu");
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
				DeviceMenu dm = new DeviceMenu();
				dm.setVisible(true);
				dispose();
			}
		});
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
}

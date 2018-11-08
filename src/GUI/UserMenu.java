package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import data_tier.DataLogger;
import logic_tier.User;

public class UserMenu extends JFrame {

	User us;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblUser = new JLabel("admin");
	private JTextField textField;
	private JTextField textField_1;
	
	/**
	 * Create the frame.
	 */
	public UserMenu(User _us) {
		// Refresh van de Tabel na new User werkt niet
		us = _us;
		
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
		
		JButton btnNewDecicw = new JButton("New User");
		btnNewDecicw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NewUserMenu num = new NewUserMenu(us);
				num.setVisible(true);
				dispose();
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

		createTable();

	}
	
	public class ForcedListSelectionModel extends DefaultListSelectionModel {

	    public ForcedListSelectionModel () {
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
		table.setSelectionModel(new ForcedListSelectionModel());;
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

}

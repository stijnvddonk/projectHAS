package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logic_tier.User;

public class Test extends JFrame {

	User us;
	private DefaultTableModel model;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JPanel contentPane;
	private final JTable table_1 = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		us = new User();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 266);
		contentPane.add(panel);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(90, 51, 266, 159);
		panel.add(scrollPane_1);
		
		createTable();
	}
	
	private void createTable() {
	if (model != null)
		model.setRowCount(0);
	String[] header = { "Device Name" };
	System.out.println("Loading data");
	String[][] data = us.Devices();
	model = new DefaultTableModel(data, header);
	table = new JTable(model) {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
	table.getColumnModel().getColumn(0).setResizable(false);
	table.getColumnModel().getColumn(0).setPreferredWidth(190);
	scrollPane_1.setViewportView(table);
}
}

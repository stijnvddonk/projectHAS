package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import DataPackage.DatabaseManagement;
import backEnd.Company;
import backEnd.Engineer;
import backEnd.ProjectManager;
import backEnd.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class loginScreen {

	Color DWPTColor = new Color(90, 142, 200);

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	static Company comp;

	/**
	 * Launch the application.
	 * 
	 * ik voer een test uit
	 * gr. Remy
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginScreen window = new loginScreen();
					window.frame.setVisible(true);
					comp = new Company(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		// frame.setBounds(500, 250, 650, 500);
		// verrekte koekwaus
		frame.setSize(720, 480);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 338, 480);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(90, 142, 200));
		panel_1.setBounds(337, 0, 383, 480);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblSignIn = new JLabel("Sign in");
		lblSignIn.setBounds(107, 124, 78, 25);
		lblSignIn.setForeground(Color.WHITE);
		lblSignIn.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel_1.add(lblSignIn);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblUsername.setBounds(24, 183, 103, 25);
		panel_1.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPassword.setBounds(24, 252, 103, 25);
		panel_1.add(lblPassword);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(24, 234, 256, 12);
		panel_1.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(24, 296, 256, 12);
		panel_1.add(separator_1);

		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		txtPassword.setColumns(10);
		txtPassword.setToolTipText("");
		txtPassword.setText("Patrijsweg1");
		txtPassword.setForeground(Color.WHITE);
		txtPassword.setBackground(new Color(90, 142, 200));
		txtPassword.setBounds(24, 276, 256, 25);
		txtPassword.setBorder(null);
		panel_1.add(txtPassword);

		txtUsername = new JTextField();
		txtUsername.setForeground(Color.WHITE);
		txtUsername.setText("stijn.vandedonk");
		txtUsername.setBackground(new Color(90, 142, 200));
		txtUsername.setBounds(24, 214, 256, 26);
		txtUsername.setBorder(null);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblNewLabel = new JLabel("HAS");
		lblNewLabel.setBounds(107, 65, 109, 47);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setForeground(Color.WHITE);

		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(177, 336, 103, 36);
		panel_1.add(panel_2);

		JLabel label_1 = new JLabel("Close");
		label_1.setForeground(new Color(90, 142, 200));
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_1.setBounds(25, 5, 58, 25);
		panel_2.add(label_1);

		JPanel panel_3 = new JPanel();
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login();
			}
		});
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(24, 336, 103, 36);
		panel_1.add(panel_3);

		JLabel lblSubmit = new JLabel("Submit");
		lblSubmit.setForeground(new Color(90, 142, 200));
		lblSubmit.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSubmit.setBounds(15, 5, 72, 25);
		panel_3.add(lblSubmit);
		
		JLabel lblWachtwoordVergeten = new JLabel("Wachtwoord Vergeten?");
		lblWachtwoordVergeten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ResetPassword rp = new ResetPassword();
				rp.setVisible(true);
			}
		});
		lblWachtwoordVergeten.setForeground(Color.WHITE);
		lblWachtwoordVergeten.setBounds(79, 308, 150, 16);
		panel_1.add(lblWachtwoordVergeten);
	}

	public void login() {
		String pwd = new String(txtPassword.getPassword());
		String usr = txtUsername.getText();
		comp.loginDatabase(usr, pwd);
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(JTextField txtUsername) {
		this.txtUsername = txtUsername;
	}

	public void projectManagerGUI(ProjectManager bepm) {
		MainProjectManager mpm = new MainProjectManager(bepm);
		mpm.setVisible(true);
		frame.dispose();
	}

	public void engineerGUI(Engineer beeng) {
		MainEngineer me = new MainEngineer(beeng);
		me.setVisible(true);
		frame.dispose();
	}
	
}

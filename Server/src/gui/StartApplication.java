package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Font;

public class StartApplication {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartApplication window = new StartApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Home Automation System - HAS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(480, 360);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnWelcomeTo = new JTextPane();
		txtpnWelcomeTo.setFont(new Font("Tahoma", Font.BOLD, 30));
		txtpnWelcomeTo.setEnabled(false);
		txtpnWelcomeTo.setEditable(false);
		txtpnWelcomeTo.setText("Welcome to the server of\r\nHome Automation System\r\n");
		txtpnWelcomeTo.setBounds(10, 11, 460, 338);
		frame.getContentPane().add(txtpnWelcomeTo);
		
		MainScreen ms = new MainScreen();
		ms.setVisible(true);
		
		frame.removeAll();
		frame.dispose();
		frame.setVisible(false);
		
	}
}

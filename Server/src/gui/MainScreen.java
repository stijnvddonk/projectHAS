package gui;

import com.socket.server.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Button;
import java.awt.ScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTextPane serverFeedback;
	protected String feedback = null;
	protected SocketServer ss = new SocketServer(this);

	/**
	 * Create the frame.
	 */
	public MainScreen() {		
		ss.start();
		
		setTitle("Home Automation System - HAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel menu = new Panel();
		menu.setBounds(10, 52, 212, 658);
		contentPane.add(menu);
		menu.setLayout(null);
		
		Panel content = new Panel();
		content.setBounds(222, 52, 1048, 658);
		contentPane.add(content);
		content.setLayout(null);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(0, 0, 1048, 658);
		content.add(scrollPane);
		
		serverFeedback = new JTextPane();
		serverFeedback.setBounds(0, 0, 1048, 658);
		scrollPane.add(serverFeedback);
		
		Button button = new Button("Exit");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeAll();
			}
		});
		button.setBounds(1200, 10, 70, 22);
		contentPane.add(button);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("On");
		tglbtnNewToggleButton.setBounds(1071, 10, 121, 23);
		contentPane.add(tglbtnNewToggleButton);
	}
	
	public void addFeedback(String _fb) {
		if (feedback != null) {
			this.feedback += _fb + "\n";
		} else {
			this.feedback = _fb + "\n";
		}
//		System.out.print(feedback);
		updateTxt();
	}
	
	public void updateTxt()
	{
		this.serverFeedback.setText(this.feedback);
	}

	public void closeAll()
	{
		this.ss.closeConnection();
		System.exit(0);
	}
}

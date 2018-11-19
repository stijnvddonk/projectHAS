package data_tier;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class GIFLoader extends JFrame {
	JPanel contentPane;
    
	public GIFLoader() {
		
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 266);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		
		lblNewLabel.setIcon(new ImageIcon(GIFLoader.class.getResource("/data_tier/loader.GIF")));
		lblNewLabel.setBounds(0, 0, 426, 266);
		contentPane.add(lblNewLabel);		
		contentPane.setVisible(true);
	}

}
package project_supermario_2;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class mario {

	private JFrame frame;

	ImageIcon imageIcon = new ImageIcon("images/mario_left.png");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mario window = new mario();
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
	public mario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(imageIcon);
		lblNewLabel.setBounds(184, 118, 57, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(12, 200, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);
	}
}

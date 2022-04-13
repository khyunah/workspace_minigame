package project1_supermario_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class SuperMarioFrame{

	private JFrame frame;
	JPanel panel;

	Image backgroundImage = new ImageIcon("images/backgroundMap.gif").getImage();

	int x = 0;

	public SuperMarioFrame() {
		initData();
		setInitLayout();
	}

	private void initData() {
		frame = new JFrame();
		frame.setSize(1500, 1100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new CustomPanel(); // 다형성
	}

	public void setInitLayout() {
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
//		add(panel);

	}

	// 내부 클래스
	private class CustomPanel extends JPanel {
		public CustomPanel() {
			this.setFocusable(true); // 컴포넌트 여러개일때 우선권 부여
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						x--;
						repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, x, 0, 4000, 800, this);
		}

	} // end of inner class

	public static void main(String[] args) {
		// ㅁㄹ;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SuperMarioFrame().frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}

package project1_supermario_1;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class SuperMarioFrame{

	private JFrame frame;
	JPanel panel;
	Player player;

	
	// 파일 없어도 오류 안남
	Image backgroundImage = new ImageIcon("images/backgroundMap.gif").getImage();
	Image playerR = new ImageIcon("images/mario_right.png").getImage();
	

	private int x = 0;

	public SuperMarioFrame() {
		player = new Player();
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		frame = new JFrame();
		frame.setSize(1500, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new CustomPanel(); // 다형성
	}

	public void setInitLayout() {
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		panel.setLayout(null);
		
	}
	
	public void addEventListener() {
		panel.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				player.up();
				break;
			case KeyEvent.VK_RIGHT:
				player.right();
				break;
			case KeyEvent.VK_LEFT:
				player.left();
				break;
			case KeyEvent.VK_DOWN:
				player.down();
				break;
			}
			

		}
		
		
		});
	}
	// 내부 클래스
	private class CustomPanel extends JPanel {
		public CustomPanel() {
			this.setFocusable(true); // 컴포넌트 여러개일때 우선권 부여
			new Thread(new Runnable() {

				@Override
				public void run() {
						x = x - player.getPlayerX();
						repaint();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			}).start();

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, x, 0, 6000, 800, this);
			g.drawImage(playerR, 100, 640, 80, 80, null);
		}

	} // end of inner class

	public static void main(String[] args) {
		new SuperMarioFrame();
	}

}

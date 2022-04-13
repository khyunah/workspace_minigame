package project_supermario_2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SuperMarioFrame extends JFrame {

	private JLabel bgMap;
	private Player player;

	int pointX = 0;
	int pointY = 0;

	public SuperMarioFrame() {
		initData();
		setInitLayout();
		initListener();
	}

	private void initData() {
		player = new Player();
		bgMap = new JLabel(new ImageIcon("images/marioBackgroundMap.gif"));

		setSize(800, 600);
		setVisible(true);
		setResizable(true);
		setLocationRelativeTo(this);

	}

	private void setInitLayout() {
		setContentPane(bgMap);
		this.add(player);
	}

	private void initListener() {
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					System.out.println("왼쪽 방향키 눌림");
					new Thread(new Runnable() {
						@Override
						public void run() {

							for (int i = 0; i < 50; i++) {
								bgMap.setLocation(pointX, pointY);
								pointX++;
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}).start();
					player.left();
					break;

				case KeyEvent.VK_RIGHT:
					System.out.println("오른쪽 방향키 눌림");
					new Thread(new Runnable() {
						@Override
						public void run() {
							for (int i = 0; i < 50; i++) {
								bgMap.setLocation(pointX, pointY);
								pointX--;
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						}
					}).start();
					player.right();
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				}
			}
		});

	}

	public static void main(String[] args) {
		new SuperMarioFrame();
	}

}

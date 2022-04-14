package project_supermario_2;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SuperMarioFrame extends JFrame {

	Image image = new ImageIcon("images/marioBackgroundMap.gif").getImage();
    Image changImg = image.getScaledInstance(7000, 500, Image.SCALE_SMOOTH);
    ImageIcon changIcon = new ImageIcon(changImg);
	
    private JPanel panel;
    
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
		panel = new JPanel();
		player = new Player();
		bgMap = new JLabel(changIcon);

		setSize(1500, 600);
		setLocation(0,0);
		setVisible(true);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setInitLayout() {
		panel.setLocation(0, 0);
		bgMap.setLocation(0,0);
		bgMap.setHorizontalAlignment(JLabel.LEFT);
		panel.add(bgMap);
		panel.setLocation(0, 0);
		setContentPane(panel);
		bgMap.add(player);
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
							
							if(!player.isLeft() && !player.isLeftWallCrash()) {
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
					if(!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
					}
					break;

				case KeyEvent.VK_RIGHT:
					System.out.println("오른쪽 방향키 눌림");
					new Thread(new Runnable() {
						@Override
						public void run() {
							for (int i = 0; i < 8; i++) {
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
					if(!player.isRight()) {
						player.right();
					}
					break;
					
				case KeyEvent.VK_UP:
					System.out.println("위 쪽 방향키 눌림");
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							for (int i = 0; i < 7; i++) {
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							player.up();
						}
					}).start();
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

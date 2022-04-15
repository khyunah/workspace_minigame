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
	private JLabel label;

	private JLabel bgMap;
	private Player player;
	private Monster monster1;
	private Monster monster2;
	private Monster monster3;

	private Item itemBox1;
	private Item itemBox2;

	int pointX = 0;
	int pointY = 0;

	public SuperMarioFrame() {
		initData();
		setInitLayout();
		initListener();
	}

	private void initData() {
		panel = new JPanel();
		player = new Player(this);
		monster1 = new Monster(200, 410);
		monster2 = new Monster(700, 410);
		monster3 = new Monster(1100, 410);
		bgMap = new JLabel(changIcon);
		itemBox1 = new Item(player);

		itemBox2 = new Item(player);
		label = new JLabel(new ImageIcon("images/gameover.jpg"));
		setSize(1500, 540);
		setLocation(0, 0);
		setVisible(true);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setInitLayout() {
		bgMap.setLocation(0, 0);
		panel.add(bgMap);
		setContentPane(panel);
		
		bgMap.add(player);
		bgMap.add(itemBox1);

		bgMap.add(monster1);
		bgMap.add(monster2);
		bgMap.add(monster3);
		
	}
	
	public void showGameoverImage() {
		bgMap.add(label);
		label.setBounds(-bgMap.getX(),0, 1500, 540);
//		System.exit(0);
	}


	private void initListener() {
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (!player.getService().checkLeftWall()) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								for (int i = 0; i < 8; i++) {
									if (pointX <= 0) {
										pointX = pointX + 3;
										bgMap.setLocation(pointX, pointY);
										try {
											Thread.sleep(10);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
								}
							}
						}).start();
					}
					if (!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
					}
					break;

				case KeyEvent.VK_RIGHT:
					if (!player.getService().checkRightWall()) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								for (int i = 0; i < 8; i++) {
									if (pointX + 7000 >= 1500) {
										pointX = pointX - 3;
										bgMap.setLocation(pointX, pointY);
										try {
											Thread.sleep(10);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}
								}
							}
						}).start();
					}
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right();
					}
					break;

				case KeyEvent.VK_UP:
					new Thread(new Runnable() {

						@Override
						public void run() {
							for (int i = 0; i < 7; i++) {
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							if (!player.isUp() && !player.isDown()) {
								player.up();
							}
							if (player.isCrashOk()) {
								itemBox1.crashGetMoney();
							}
						}
					}).start();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					player.setRightWallCrash(false);
					break;
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					player.setLeftWallCrash(false);
					break;
				}
			}
			
		});
	}

	public static void main(String[] args) {
		new SuperMarioFrame();
	}
}
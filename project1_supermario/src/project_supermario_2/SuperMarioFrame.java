package project_supermario_2;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lombok.Getter;

@Getter
public class SuperMarioFrame extends JFrame {

	Image image = new ImageIcon("images/marioBackgroundMap.png").getImage();
	Image changImg = image.getScaledInstance(7000, 500, Image.SCALE_SMOOTH);
	ImageIcon changIcon = new ImageIcon(changImg);

	private JPanel panel;
	private JLabel label;
	private JLabel winImage;

	private JLabel bgMap;
	private Player player;
	Monster[] monsters = new Monster[3];
	private Mushroom mushroom;
	private boolean gameOver;

	private int pointX;
	private int pointY;

	public SuperMarioFrame() {
		initData();
		setInitLayout();
		initListener();
	}

	private void initData() {
		panel = new JPanel();
		player = Player.getInstance(this);
		monsters[0] = new Monster(700, 410, this);
		monsters[1] = new Monster(3900, 410, this);
		monsters[2] = new Monster(5600, 410, this);
		bgMap = new JLabel(changIcon);
		mushroom = new Mushroom(this);
		label = new JLabel(new ImageIcon("images/gameover.jpg"));
		winImage = new JLabel(new ImageIcon("images/winImg.png"));

		gameOver = false;
		pointX = 0;
		pointY = 0;

		setSize(1500, 540);
		setLocation(0, 0);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setInitLayout() {
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		bgMap.setLocation(pointX, pointY);
		panel.add(bgMap);
		setContentPane(panel);
		bgMap.add(player);
		for (int i = 0; i < monsters.length; i++) {
			bgMap.add(monsters[i]);
		}
		bgMap.add(mushroom);
	}

	public void showGameoverImage() {
		gameOver = true;
		bgMap.add(label);
		label.setBounds(-bgMap.getX(), 0, 1500, 540);
		bgMap.remove(player);
		for (int i = 0; i < monsters.length; i++) {
			bgMap.remove(monsters[i]);
		}
		bgMap.remove(mushroom);
		this.repaint();

		threadSleep(2000);
		System.exit(0);
	}

	public void showWinImage() {
		gameOver = true;
		bgMap.add(winImage);
		winImage.setBounds(-bgMap.getX(), 0, 1500, 540);
		bgMap.remove(player);
		bgMap.remove(player);
		for (int i = 0; i < monsters.length; i++) {
			bgMap.remove(monsters[i]);
		}
		bgMap.remove(mushroom);
		this.repaint();
	}

	private void initListener() {
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (!gameOver) {

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
											threadSleep(10);
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
											threadSleep(10);
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
									threadSleep(10);
								}
								if (!player.isUp() && !player.isDown()) {
									player.up();
								}
							}
						}).start();
						break;
					case KeyEvent.VK_DOWN:
						player.enterChimney();
						if (player.isEnter()) {
							pointX = -5139;
							bgMap.setLocation(pointX, pointY);
						}
						player.setEnter(false);
						break;
					case KeyEvent.VK_Z:
						bgMap.setLocation(pointX, pointY);
						break;
					}
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

	private void threadSleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SuperMarioFrame();
	}
}
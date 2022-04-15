package project_supermario_2;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Player extends JLabel implements Moveable {

	// 위치 상태
	private int x;
	private int y;

	// 플레이어의 방향

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 플레이어 속도 상태
	private final int SPEED = 15;
	private final int JUMPSPEED = 3;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	private ImageIcon player;
	private ImageIcon playerR;
	private ImageIcon playerL;
	private ImageIcon playerR2;
	private ImageIcon playerL2;
	private ImageIcon jumpR;
	private ImageIcon jumpL;

	private BackgroundMapService service;

	Item item;

	boolean crashOk;

	boolean isDie;

	public Player() {
		initObject();
		initSetting();
	}

	private void initObject() {
		playerR = new ImageIcon("images/mario_right.png");
		playerL = new ImageIcon("images/mario_left.png");
		playerR2 = new ImageIcon("images/mario1_right.png");
		playerL2 = new ImageIcon("images/mario1_left.png");
		jumpR = new ImageIcon("images/jump_right.png");
		jumpL = new ImageIcon("images/jump_left.png");
		player = playerR;
		service = new BackgroundMapService(this);
		item = new Item(this);
	}

	private void initSetting() {
		x = 80;
		y = 390;

		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;

		crashOk = false;
		isDie = false;

		setIcon(player);
		setSize(50, 60);
		setLocation(x, y);

	}

	// 이벤트 핸들러
	@Override
	public void left() {
		left = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {
					if (!service.checkLeftWall()) {
						service.checkLeftWall();
						service.checkBottomColor();
						player = playerL;
						setIcon(player);
						x = x - SPEED;
						setLocation(x, y);
						initSleep(10);
						player = playerL2;
						setIcon(player);
						x = x - SPEED;
						setLocation(x, y);
						initSleep(40);
					}
				}

			}
		}).start();

	}

	@Override
	public void right() {
		right = true;
		new Thread(new Runnable() {

			@Override
			public void run() {

				while (right) {
					if (!service.checkRightWall()) {
						service.checkRightWall();
						service.checkBottomColor();
						player = playerR;
						setIcon(player);
						x = x + SPEED;
						setLocation(x, y);
						initSleep(10);
						player = playerR2;
						setIcon(player);
						x = x + SPEED;
						setLocation(x, y);
						initSleep(40);
					}
				}
			}
		}).start();

	}

	// left + up , right + up
	@Override
	public void up() {
		up = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < (130 / JUMPSPEED); i++) {
					service.checkTopColor(item);
					service.checkBottomColor();
					y = y - JUMPSPEED;
					setLocation(x, y);
					initSleep(10);
					if (player.getDescription() == ("images/mario_left.png")
							|| player.getDescription() == ("images/mario1_left.png")) {
						player = jumpL;
						setIcon(player);
					} else if (player.getDescription() == ("images/mario_right.png")
							|| player.getDescription() == ("images/mario1_right.png")) {
						player = jumpR;
						setIcon(player);

					} else {
					}

				}
				if (player.getDescription() == "images/jump_left.png") {
					player = playerL;
					setIcon(player);
				} else if (player.getDescription() == "images/jump_right.png") {
					player = playerR;
					setIcon(player);
				}

				up = false;
				down();
			}
		}).start();
	}

	@Override
	public void down() {
		down = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (down) {
					if (y < 600) {

						if (y > 550) {
							isDie = true;
						}
						service.checkBottomColor();
						System.out.println(y);
						y = y + JUMPSPEED;
						setLocation(x, y);
						initSleep(3);
					}
				}
				down = false;
			}
		}).start();
	}

	public void initSleep(int sleepNum) {
		try {
			Thread.sleep(sleepNum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
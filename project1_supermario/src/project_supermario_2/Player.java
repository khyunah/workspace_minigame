package project_supermario_2;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Player extends JLabel implements Moveable {
		
	// 위치 상태
	private int x;
	private int y;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 플레이어 속도 상태
	private final int SPEED = 12;
	private final int JUMPSPEED = 5;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	private ImageIcon superMario;
	private ImageIcon player;
	private ImageIcon playerR;
	private ImageIcon playerL;
	private ImageIcon playerR2;
	private ImageIcon playerL2;
	private ImageIcon jumpR;
	private ImageIcon jumpL;
	private SuperMarioFrame mContext;
	private int playerW;
	private int playerH;

	private BackgroundMapService service;

	private boolean crashOk;

	private boolean isDie;

	private Player(SuperMarioFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
	}

	private static Player instance;

	public static Player getInstance(SuperMarioFrame mContext) {
		if (instance == null) {
			instance = new Player(mContext);
		}
		return instance;
	}

	private void initObject() {
		superMario = new ImageIcon("images/Sjump_right.png");
		playerR = new ImageIcon("images/mario_right.png");
		playerL = new ImageIcon("images/mario_left.png");
		playerR2 = new ImageIcon("images/mario1_right.png");
		playerL2 = new ImageIcon("images/mario1_left.png");
		jumpR = new ImageIcon("images/jump_right.png");
		jumpL = new ImageIcon("images/jump_left.png");
		player = playerR;
		service = new BackgroundMapService(this);
	}

	private void initSetting() {
		x = 80;
		y = 390;
		playerW = 50;
		playerH = 60;

		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;

		crashOk = false;
		isDie = false;

		setIcon(player);
		setSize(playerW, playerH);
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
						initSleep(1);
						player = playerL2;
						setIcon(player);
						x = x - SPEED;
						setLocation(x, y);
						initSleep(1);
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
					if(service.isWin()) {
						mContext.showWinImage();
					}
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
				for (int i = 0; i < (250/ JUMPSPEED); i++) {
					service.checkTopColor();
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
					}
				}
				if (player.getDescription() == "images/jump_left.png") {
					player = playerL;
					setIcon(player);
				} else if (player.getDescription() == "images/jump_right.png") {
					player = playerR;
					setIcon(player);
				}
				if(crashOk) {
					getMoney();
				}
				crashOk = false;

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
//							mContext.image = new ImageIcon("images/gameover.jpg").getImage();
//							mContext.repaint();
							mContext.showGameoverImage();
						}
						service.checkBottomColor();
						y = y + JUMPSPEED;
						setLocation(x, y);
						initSleep(3);
					}
				}
				down = false;
			}
		}).start();
	}
	
	private void getMoney() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Item item = new Item(mContext);
				mContext.getBgMap().add(item);
				item.crashGetMoney();
			}
		}).start();
	}

	public void giant() {
		this.setSize(playerW + 50, playerH + 60);
		ImageIcon supermario = new ImageIcon("images/Sjump_right.png");
		setIcon(supermario);
	}

	public void initSleep(int sleepNum) {
		try {
			Thread.sleep(sleepNum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
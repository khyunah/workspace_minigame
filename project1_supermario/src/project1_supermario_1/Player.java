package project1_supermario_1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {
	// 위치
	private int playerX;
	private int playerY;
	
	// 방향
//	private PlayerWay playerWay;
	
	// 움직임
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	// 속도
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;
	
	//벽에 충돌
	private boolean leftwallCrash;
	private boolean rightwallCrash;
	
	//이미지
	private ImageIcon playerR;
	private ImageIcon playerL;
	
	// getter/setter
	public int getPlayerX() {
		return playerX;
	}
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}
	public int getPlayerY() {
		return playerY;
	}
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isLeftwallCrash() {
		return leftwallCrash;
	}
	public void setLeftwallCrash(boolean leftwallCrash) {
		this.leftwallCrash = leftwallCrash;
	}
	public boolean isRightwallCrash() {
		return rightwallCrash;
	}
	public void setRightwallCrash(boolean rightwallCrash) {
		this.rightwallCrash = rightwallCrash;
	}
//	public PlayerWay getPlayerWay() {
//		return playerWay;
//	}
//	public void setPlayerWay(PlayerWay playerWay) {
//		this.playerWay = playerWay;
//	}
	public Player() {
		initObject();
		initSetting();
	}
	
	private void initObject() {
		playerR = new ImageIcon("images/mario_right.png");
		playerL = new ImageIcon("images/mario_left.png");
	}
	private void initSetting() {
		// 초기화
		playerX = 100;
		playerY = 800;
		left = false;
		right = false;
		up = false;
		down = false;
		leftwallCrash = false;
		rightwallCrash = false;
//		playerWay = PlayerWay.RIGHT;
		// 크기, 위치 지정
		setIcon(playerR);
		setSize(100, 100);
		setLocation(playerX, playerX);
	}
	
	public void initThread() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
//				if (playerWay == PlayerWay.LEFT) {
//					left();
//				} else {
//					right();
//				}
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			}
		}).start();
	}
	
	public void left() {
//		playerWay = PlayerWay.LEFT;
		left = true;
		while(left) {
			setIcon(playerL);
			playerX -= SPEED;
			setLocation(playerX, playerX);;
			
		}
	}
	public void right() {
		
	}
	public void up() {
		
	}
	public void down() {
		
	}

	
	
	
	
	
}

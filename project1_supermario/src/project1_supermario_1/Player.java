package project1_supermario_1;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Player extends SuperMarioFrame implements Moveable {
	
	SuperMarioFrame marioFrame;
	
	// 위치
	private int playerX;
	private int playerY;

	// 방향

	// 움직임
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 속도
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;

	// 벽에 충돌
	private boolean leftwallCrash;
	private boolean rightwallCrash;

	// 이미지
	private Image player;
	private Image playerR;
	private Image playerR2;
	private Image playerL;
	private Image playerL2;
	private Image jumpR;
	private Image jumpL;
	
	
	
	
	

	public SuperMarioFrame getMarioFrame() {
		return marioFrame;
	}

	public void setMarioFrame(SuperMarioFrame marioFrame) {
		this.marioFrame = marioFrame;
	}

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

	public void setPlayer(Image player) {
		this.player = player;
	}

	public Image getPlayerR() {
		return playerR;
	}

	public void setPlayerR(Image playerR) {
		this.playerR = playerR;
	}

	public Image getPlayerR2() {
		return playerR2;
	}

	public void setPlayerR2(Image playerR2) {
		this.playerR2 = playerR2;
	}

	public Image getPlayerL() {
		return playerL;
	}

	public void setPlayerL(Image playerL) {
		this.playerL = playerL;
	}

	public Image getPlayerL2() {
		return playerL2;
	}

	public void setPlayerL2(Image playerL2) {
		this.playerL2 = playerL2;
	}

	public Image getJumpR() {
		return jumpR;
	}

	public void setJumpR(Image jumpR) {
		this.jumpR = jumpR;
	}

	public Image getJumpL() {
		return jumpL;
	}

	public void setJumpL(Image jumpL) {
		this.jumpL = jumpL;
	}

	public int getSPEED() {
		return SPEED;
	}

	public int getJUMPSPEED() {
		return JUMPSPEED;
	}

	public Player() {
		initObject();
		initSetting();
	}

	private void initObject() {
		playerR = new ImageIcon("images/mario_right.png").getImage();
		playerR2 = new ImageIcon("images/mario1_right.png").getImage();
		playerL = new ImageIcon("images/mario_left.png").getImage();
		playerL2 = new ImageIcon("images/mario1_left.png").getImage();
		jumpL = new ImageIcon("images/jump_left.png").getImage();
		jumpR = new ImageIcon("images/jump_right.png").getImage();
		player = playerR;
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
		// 크기, 위치 지정
	}

	@Override
	public void left() {
		left = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (left) {
					player = playerL;
					playerX -= playerX;
					player = playerL2;
					playerX -= playerX;
//					setLocation(playerX, playerY)
					getPanel().repaint();
					threadSleep(10);
				}
				left = false;
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
//					setIcon(playerR);
					player = playerR;
					playerX -= playerX;
					player = playerR2;
					playerX -= playerX;
					getPanel().repaint();
//					setLocation(playerX, playerY);
					threadSleep(10);
				}
				right = false;
			}
		}).start();

	}

	@Override
	public void up() {
		up = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < (130 / JUMPSPEED); i++) {
					
					player = jumpL;
					playerY -= JUMPSPEED;
//					setLocation(playerX, playerY);
					getPanel().repaint();
					threadSleep(5);
				}
				up = false;

			}
		}).start();

	}

	@Override
	public void down() {
		down = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < (130 / JUMPSPEED); i++) {
					playerY += JUMPSPEED;
//					setLocation(playerX, playerY);
					getPanel().repaint();
					threadSleep(3);
				}
				down = false;

			}
		}).start();

	}

	public void threadSleep(int sleepNum) {
		try {
			Thread.sleep(sleepNum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

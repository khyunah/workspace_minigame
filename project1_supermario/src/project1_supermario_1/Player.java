package project1_supermario_1;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
					panel.repaint();
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
					panel.repaint();
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
					panel.repaint();
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
					panel.repaint();
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

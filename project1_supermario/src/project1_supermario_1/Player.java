package project1_supermario_1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends JLabel implements Moveable {
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
	private ImageIcon playerR;
	private ImageIcon playerL;

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
		// 크기, 위치 지정
		setIcon(playerR);
		setSize(100, 100);
		setLocation(playerX, playerX);
	}

	@Override
	public void left() {
		left = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (left) {
					setIcon(playerL);
					playerX -= playerX;
					setLocation(playerX, playerY);
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
					setIcon(playerR);
					playerX -= playerX;
					setLocation(playerX, playerY);
					setIcon(playerL);
					playerX -= playerX;
					setLocation(playerX, playerY);
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
					playerY -= JUMPSPEED;
					setLocation(playerX, playerY);
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
					setLocation(playerX, playerY);
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

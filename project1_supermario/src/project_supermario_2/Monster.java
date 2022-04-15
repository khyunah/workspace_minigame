package project_supermario_2;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Monster extends JLabel {

	private int monsterX;
	private int monsterY;
	private boolean left;
	private boolean right;

	private boolean leftCrash;
	private boolean rightCrash;

	boolean direction;

	private ImageIcon enemyL;
	private ImageIcon enemyR;
	Player player;
	private BackgroundMapService mapStervice;

	public Monster(int monsterX, int monsterY) {
		this.monsterX = monsterX;
		this.monsterY = monsterY;
		initObject();
		initSetting();
		addEventListener();
		initBackgroundMonsterService();
	}

	private void initBackgroundMonsterService() {
		new Thread(new BackgroundMonsterService(this)).start();
	}

	private void initObject() {
		enemyL = new ImageIcon("images/enemy_left.png");
		enemyR = new ImageIcon("images/enemy_right.png");

	}

	private void initSetting() {
//		monsterX = 0;
//		monsterY = 0;

		leftCrash = false;
		rightCrash = false;

		direction = true;

		setIcon(enemyL);
		setSize(30, 30);
		setLocation(monsterX, monsterY);

	}

	private void addEventListener() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
//					setIcon(enemyL);
					if (leftCrash) {
						direction = true;
					}
					if (rightCrash) {
						direction = false;
					}
					if (direction) {
						monsterX = monsterX + 10;
						setLocation(monsterX, monsterY);
//						System.out.println("몬스터 좌표값" + monsterX +"-" + monsterY);
						setIcon(enemyR);
//						playerCrash();
					} else {
						monsterX = monsterX - 10;
						setLocation(monsterX, monsterY);
						setIcon(enemyL);
//						playerCrash();

					}
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}
		}).start();
	}

//	private void playerCrash() {
//		if(player.getX() - monsterX < +-30 && player.getY() - monsterY < +- 30) {
//			System.out.println("게임 종료");
//		}
//	}

}

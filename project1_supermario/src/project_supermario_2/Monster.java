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
					setIcon(enemyL);
					if (direction) {
						monsterX = monsterX + 10;
						setLocation(monsterX, monsterY);
					} else {
						monsterX = monsterX - 10;
						setLocation(monsterX, monsterY);
					}
					if (leftCrash) {
						direction = true;
						setIcon(enemyR);
					}
					if (rightCrash) {
						direction = false;
						setIcon(enemyL);
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
	}

}

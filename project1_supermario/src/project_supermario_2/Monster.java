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

	private ImageIcon enemyL;
	private ImageIcon enemyR;
	private ImageIcon enemy;
	Player player;
	private BackgroundMapService mapStervice;
	private SuperMarioFrame mContext;

	public Monster(int monsterX, int monsterY) {
		this.monsterX = monsterX;
		this.monsterY = monsterY;
		initBackgroundMonsterService();
		initObject();
		initSetting();
		addEventListener();
	}

	private void initBackgroundMonsterService() {
		new Thread(new BackgroundMonsterService(this)).start();
	}

	private void initObject() {
		enemyL = new ImageIcon("images/enemy_left.png");
		enemyR = new ImageIcon("images/enemy_right.png");
		enemy = enemyR;
	}

	private void initSetting() {

		setIcon(enemyR);
		setSize(30, 30);
		setLocation(monsterX, monsterY);

		leftCrash = false;
		rightCrash = false;

	}

	private void addEventListener() {
		setIcon(enemy);
		new Thread(new Runnable() {
			boolean direction;

			@Override
			public void run() {
				setIcon(enemy);
				while (true) {
					if (leftCrash) {
						direction = true;
					}
					if (rightCrash) {
						direction = false;
					}
					if (direction) {
						setIcon(enemyR);
						monsterX = monsterX + 10;
						setLocation(monsterX, monsterY);
//						playerCrash();
					} else {
						setIcon(enemyL);
						monsterX = monsterX - 10;
						setLocation(monsterX, monsterY);
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

	public boolean playerCrash() {
		if (player.getX() - monsterX < 30 && monsterX - player.getX() < 30 && player.getY() - monsterY < 50) {
			System.out.println("게임 종료");
			
			return true;
		}
		return false;
	}

}

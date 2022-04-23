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
	private Player player;
	private SuperMarioFrame mContext;

	public Monster(int monsterX, int monsterY, SuperMarioFrame mContext) {
		this.mContext = mContext;
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
		player = Player.getInstance(mContext);
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
						playerCrash();
					} else {
						setIcon(enemyL);
						monsterX = monsterX - 10;
						setLocation(monsterX, monsterY);
						playerCrash();

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

	public void playerCrash() {
		if ( (Math.abs(player.getX()-monsterX) < 45) && (Math.abs(player.getY() -monsterY) < 55)) {
			System.out.println("게임 종료");
			mContext.showGameoverImage();
		}
	}
}
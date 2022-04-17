package project_supermario_2;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Mushroom extends JLabel implements Moveable {

	private ImageIcon mushroom;
	private Player player;
	

	private MushroomObserver mushroomObserver;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private int mushroomX = 762;
	private int mushroomY = 278;

	public Mushroom() {
		initObject();
		initSetting();
		addEventListener();

	}

	private void initObject() {
		mushroom = new ImageIcon("images/superMushroom.png");
		mushroomObserver = new MushroomObserver(this);

	}

	private void initSetting() {
		left = false;
		up = false;
		down = false;

		setIcon(mushroom);
		setSize(30, 30);
		setLocation(mushroomX, mushroomY);

	}

	private void addEventListener() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				
				while (true) {

					if (mushroomObserver.checkbottom()) {
						down();
					} else {
						down = false;
					}

					if (mushroomObserver.checkRightWall()) {
						left();
					} else {
						right();
					}

					threadSleep(25);

					setLocation(mushroomX, mushroomY);

				}

			}

		}).start();

	}

	@Override
	public void left() {

		while (true) {
			mushroomX--;
			setLocation(mushroomX, mushroomY);
			threadSleep(25);

		}

	}

	@Override
	public void right() {
		mushroomX++;
	}

	@Override
	public void down() {
		down = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (down) {
					mushroomY++;
					setLocation(mushroomX, mushroomY);
					threadSleep(200);
				}
				down = false;
			}
		}).start();
	}

	private void threadSleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void up() {
	}

	public void crashMushroom(Player player, Mushroom mushroom) {

		boolean crashCheck = false;
		if (Math.abs((player.getX() - mushroom.getX())
				- (mushroomX + mushroom.getWidth() / 2)) < (mushroom.getWidth() / 2 + player.getWidth() / 2)
				&& Math.abs((player.getY() + player.getHeight() / 2)
						- (mushroomY + mushroom.getHeight() / 2)) < (mushroom.getHeight() / 2
								+ player.getHeight() / 2)) {

		}
		System.out.println("충돌");
		crashCheck = true;

	}
}

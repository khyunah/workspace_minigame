package project_supermario_2;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Mushroom extends JLabel implements Moveable {
	private Mushroom mush;
	private ImageIcon mushroom;
	private Player player;
	private MushroomObserver mushroomObserver;
	private SuperMarioFrame mContext;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private int mushroomX = 762;
	private int mushroomY = 278;

	public Mushroom(SuperMarioFrame mContext) {
		this.mush = this;
		this.mContext = mContext;
		this.player = Player.getInstance(this.mContext);
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
			try {
				crashMushroom();
			} catch (AWTException e) {
				e.printStackTrace();
			}
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
			e.printStackTrace();
		}
	}

	
	public void crashMushroom() throws AWTException {
		if ((Math.abs(mushroomX - player.getX()) < 10)) {
			
			mush.setVisible(false);
			setIcon(null);
			
			Robot r;
			try {
				r = new Robot();
				r.keyPress(KeyEvent.VK_Z);
				r.keyRelease(KeyEvent.VK_Z);
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
	}
}
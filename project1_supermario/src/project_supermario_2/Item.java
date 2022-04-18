package project_supermario_2;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Item extends JLabel {

	private SuperMarioFrame mContext;

	private Item itemContext = this;

	private ImageIcon itemBox;
	private ImageIcon box;
	private ImageIcon itemMoney;
	private ImageIcon superMushroom;

	private Player player;
	private BackgroundMapService service;

	private int itemX;
	private int itemY;

	private boolean crashOk;

	public Item(SuperMarioFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.service = player.getService();
		initObject();
		initSetting();
//		crashGetMoney();
	}

	private void initObject() {
		itemBox = new ImageIcon("images/itemBox.png");
		box = new ImageIcon("images/box.png");
		itemMoney = new ImageIcon("images/itemMoney.png");
	}

	private void initSetting() {
		crashOk = false;
		itemX = service.getCrashX() + 10;
		itemY = service.getCrashY() - 50;

		setIcon(itemBox);
		setSize(32, 32);
		setLocation(itemX, itemY);
	}

	public void crashGetMoney() {
		threadSleep(200);
		for (int i = 0; i < 10; i++) {
			setIcon(itemMoney);
			setLocation(itemX, itemY - (i * 5));
			Robot r;
			try {
				r = new Robot();
				r.keyPress(KeyEvent.VK_DOWN);
				r.keyRelease(KeyEvent.VK_DOWN);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			threadSleep(100);
		}
		removeBox();
	}

	private void removeBox() {
		threadSleep(1000);
		setIcon(null);
		itemContext = null;
		Robot r;
		try {
			r = new Robot();
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	private void threadSleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

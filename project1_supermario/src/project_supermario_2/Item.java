package project_supermario_2;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

public class Item extends JLabel {

	private SuperMarioFrame mContext;

	private Item itemContext = this;

	private ImageIcon itemMoney;

	private Player player;
	private BackgroundMapService service;

	private int itemX;
	private int itemY;

	private boolean crashOk;
	
	public SuperMarioFrame getmContext() {
		return mContext;
	}

	public void setmContext(SuperMarioFrame mContext) {
		this.mContext = mContext;
	}

	public Item getItemContext() {
		return itemContext;
	}

	public void setItemContext(Item itemContext) {
		this.itemContext = itemContext;
	}

	public ImageIcon getItemMoney() {
		return itemMoney;
	}

	public void setItemMoney(ImageIcon itemMoney) {
		this.itemMoney = itemMoney;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BackgroundMapService getService() {
		return service;
	}

	public void setService(BackgroundMapService service) {
		this.service = service;
	}

	public int getItemX() {
		return itemX;
	}

	public void setItemX(int itemX) {
		this.itemX = itemX;
	}

	public int getItemY() {
		return itemY;
	}

	public void setItemY(int itemY) {
		this.itemY = itemY;
	}

	public boolean isCrashOk() {
		return crashOk;
	}

	public void setCrashOk(boolean crashOk) {
		this.crashOk = crashOk;
	}

	public Item(SuperMarioFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.service = player.getService();
		initObject();
		initSetting();
		crashOk = true;
	}

	private void initObject() {
		itemMoney = new ImageIcon("images/itemMoney.png");
	}

	private void initSetting() {
		itemX = service.getCrashX() + 10;
		itemY = service.getCrashY() - 50;

		setIcon(itemMoney);
		setSize(32, 32);
		setLocation(itemX, itemY);
	}

	public void crashGetMoney() {
		threadSleep(200);
		for (int i = 0; i < 10; i++) {
			setIcon(itemMoney);
			setLocation(itemX, itemY - (i * 5));
			robotKeyEvent();
			threadSleep(100);
		}
		removeBox();
	}

	private void removeBox() {
		threadSleep(1000);
		setIcon(null);
		itemContext = null;
		mContext.repaint();
		robotKeyEvent();
	}
	
	private void robotKeyEvent() {
		Robot r;
		try {
			r = new Robot();
			r.keyPress(KeyEvent.VK_Z);
			r.keyRelease(KeyEvent.VK_Z);
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

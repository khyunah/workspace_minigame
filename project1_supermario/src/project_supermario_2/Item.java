package project_supermario_2;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Item extends JLabel {

	private ImageIcon itemBox;
	private ImageIcon itemMoney;
	private ImageIcon superMushroom;

	private Player player;

	private int crashCount;

	public int getCrashCount() {
		return crashCount;
	}

	public void setCrashCount(int crashCount) {
		this.crashCount = crashCount;
	}
	
	public ImageIcon getItemBox() {
		return itemBox;
	}

	public void setItemBox(ImageIcon itemBox) {
		this.itemBox = itemBox;
	}

	public ImageIcon getItemMoney() {
		return itemMoney;
	}

	public void setItemMoney(ImageIcon itemMoney) {
		this.itemMoney = itemMoney;
	}

	public ImageIcon getSuperMushroom() {
		return superMushroom;
	}

	public void setSuperMushroom(ImageIcon superMushroom) {
		this.superMushroom = superMushroom;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Item() {
		initObject();
		initSetting();
		crashGetMoney();
	}

	private void initObject() {
		itemBox = new ImageIcon("images/itemBox.png");
		itemMoney = new ImageIcon("images/itemMoney.png");
		superMushroom = new ImageIcon("images/superMushroom.png");
	}

	private void initSetting() {
		crashCount = 0;
		setIcon(itemBox);
		setSize(30, 30);
		setLocation(695,305);
	}

	public void crashGetMoney() {
		if(crashCount == 1) {
			setIcon(itemMoney);
			setLocation(695,270);
		}
	}
}

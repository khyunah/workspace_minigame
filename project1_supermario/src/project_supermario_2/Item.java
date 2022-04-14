package project_supermario_2;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Item extends JLabel {

	private ImageIcon itemBox;
	private ImageIcon itemMoney;
	private ImageIcon superMushroom;

	private Player player;

	private int chrashCount;

	public int getChrashCount() {
		return chrashCount;
	}

	public void setChrashCount(int chrashCount) {
		this.chrashCount = chrashCount;
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
		chrashCount = 0;
		setIcon(itemBox);
		setSize(30, 30);
		setLocation(695,305);
	}

	public void crashGetMoney() {
		if(chrashCount == 1) {
			setIcon(itemMoney);
			setLocation(695,270);
		}
	}
}

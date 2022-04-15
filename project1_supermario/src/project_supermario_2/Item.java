package project_supermario_2;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Item extends JLabel {

	private Item itemContext = this;
	
	private ImageIcon itemBox;
	private ImageIcon box;
	private ImageIcon itemMoney;
	private ImageIcon superMushroom;

	private Player player;

	private boolean crashOk;

	public Item(Player player) {
		this.player = player;
		initObject();
		initSetting();
	}

	private void initObject() {
		itemBox = new ImageIcon("images/itemBox.png");
		box = new ImageIcon("images/box.png");
		itemMoney = new ImageIcon("images/itemMoney.png");
		superMushroom = new ImageIcon("images/superMushroom.png");
	}

	private void initSetting() {
		crashOk = false;

		setIcon(itemBox);
		setSize(32, 32);
		setLocation(695, 305);
	}

	public void crashGetMoney() {
		if (player.isCrashOk()) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			for (int i = 0; i < 10; i++) {
				setIcon(itemMoney);
//				setLocation(695, 305 - (i * 5));
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			removeBox();
		}
		player.setCrashOk(false);
	}
	
//	public void removeBox() {
//		try {
//			Thread.sleep(1000);
//			setIcon(box);
//			itemContext = null;
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
}

package project_supermario_2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundMonsterService implements Runnable {

	private BufferedImage bgImage;
	private Monster monster;

	private int monsterLeftX;
	private int monsterRightX;

	public BackgroundMonsterService(Monster monster) {
		this.monster = monster;
		try {
			bgImage = ImageIO.read(new File("images/MapService2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initSetting();
	}

	public void initSetting() {
		monsterLeftX = 0;
		monsterRightX = 30;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Color monsterLC = new Color(bgImage.getRGB(monster.getMonsterX(), monster.getMonsterY()));
				Color monsterRC = new Color(bgImage.getRGB(monster.getMonsterX() + 30, monster.getMonsterY()));

				if ((monsterRC.getRed() == 255 && monsterRC.getGreen() == 0 && monsterRC.getBlue() == 0)) {
					monster.setRightCrash(true);
				} else if (monsterLC.getRed() == 255 && monsterLC.getGreen() == 0 && monsterLC.getBlue() == 0) {
					monster.setLeftCrash(true);
				} 
				else {
					monster.setLeftCrash(false);
					monster.setRightCrash(false);
				}
				Thread.sleep(10);

			} catch (Exception e) {
				System.out.println("오류");
			}
		}
	}
}

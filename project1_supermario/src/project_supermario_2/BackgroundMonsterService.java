package project_supermario_2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundMonsterService implements Runnable {

	BufferedImage bgImage;
	Monster monster;

	int monsterLeftX;
	int monsterRightX;

	public BackgroundMonsterService(Monster monster) {
		this.monster = monster;
		try {
			bgImage = ImageIO.read(new File("images/backgroundMapService_sizeup2.jpg"));
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
//				System.out.println("몬스터 좌표 색상: " + monsterLC);
				Color monsterRC = new Color(bgImage.getRGB(monster.getMonsterX() + 30, monster.getMonsterY()));
//				System.out.println("몬스터 좌표 색상: " + monsterRC);

				if ((monsterLC.getRed() == 255 && monsterLC.getGreen() == 0 && monsterLC.getBlue() == 0)
						|| (monsterLC.getRed() == 0 && monsterLC.getGreen() == 255 && monsterLC.getBlue() == 0)) {
					monster.setLeftCrash(true);
				} else if ((monsterRC.getRed() == 255 && monsterRC.getGreen() == 0 && monsterRC.getBlue() == 0)
						|| (monsterRC.getRed() == 0 && monsterRC.getGreen() == 255 && monsterRC.getBlue() == 0)) {
					monster.setRightCrash(true);
				}

			} catch (Exception e) {
				
			}

		}

	}

}

package project1_supermario_1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundMapService {

	private BufferedImage bgServiceImage;
	private Player player;

	private final int LEFT_X = 0;
	private final int RIGHT_X = 100;
	private final int BOTTOM_X = 50;
	private final int BOTTOM_Y = 100;

	private boolean isWin;

	public BackgroundMapService() {
		initObject();
	}

	private void initObject() {
		isWin = false;
		try {
			bgServiceImage = ImageIO.read(new File("images/backgroundMapService.png"));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}
	}

	public boolean checkLeftWall() {
//		player.setLeft(false);
		return isWallCrashColor(LEFT_X);
	}

	public boolean checkRightWall() {
//		player.setRight(false);
		return isWallCrashColor(RIGHT_X);
	}

	public boolean checkBottomColor() {
		return true;
	}

	/**
	 * player가 위치한 색상을 구하여 player 측면의 색상이 빨강색(==벽) 이거나 초록색(==굴뚝) 이면 움직임에 제한을 두기 위한
	 * 메소드
	 * 
	 * @param correctionPoint
	 * @return
	 */
	private boolean isWallCrashColor(int correctionXPoint) {
		Color color = new Color(bgServiceImage.getRGB(player.getPlayerX() + correctionXPoint, player.getPlayerY()));

		// 부딪히는 색상이 빨강색이거나 초록색일때 true
		if (color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0
				|| color.getRed() == 0 && color.getGreen() == 255 && color.getBlue() == 0) {
			return true;

			// 부딪히는 색상이 검정색일때
		} else if (color.getRed() == 0 && color.getGreen() == 0 && color.getBlue() == 0) {
			isWin = true;
			return false;
		}
		return false;
	}

	private boolean isBottomCrashColor() {
		Color color = new Color(bgServiceImage.getRGB(player.getPlayerX() + BOTTOM_X, player.getPlayerY() + BOTTOM_Y));

		if (color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0) {
			// 부딪히는 색상이 빨강색 일때
			return true;
		} else if (color.getRed() == 0 && color.getGreen() == 255 && color.getBlue() == 0) {
			// 부딪히는 색상이 초록색 일때
			return true;
		} else if (color.getRed() == 0 && color.getGreen() == 0 && color.getBlue() == 255) {
			// 부딪히는 색상이 파랑색 일때
			return true;
		}
		return false;
	}
}

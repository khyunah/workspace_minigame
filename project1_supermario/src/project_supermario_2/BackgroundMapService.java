package project_supermario_2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundMapService {

	private BufferedImage bgServiceImage;

	private final int LEFT_X = 0;
	private final int LEFTANDRIGHT_Y = 50;
	private final int RIGHT_X = 50;
	private final int BOTTOM_X = 25;
	private final int BOTTOM_Y = 65;
	private final int TOP_X = 25;

	private int crashX;
	private int crashY;

	private Player player;

	// 성에 도착했을때 true
	private boolean isWin;

	public boolean isWin() {
		return isWin;
	}

	public int getCrashX() {
		return crashX;
	}

	public int getCrashY() {
		return crashY;
	}

	public BackgroundMapService(Player player) {
		this.player = player;
		crashX = 0;
		crashY = 0;
		initObject();
	}

	private void initObject() {
		isWin = false;
		try {
			bgServiceImage = ImageIO.read(new File("images/MapService.png"));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}
	}

	public boolean checkLeftWall() {
		player.setLeftWallCrash(isWallCrashColor(LEFT_X));
		player.setLeft(false);
		return isWallCrashColor(LEFT_X);
	}

	public boolean checkRightWall() {
		player.setRightWallCrash(isWallCrashColor(RIGHT_X));
		player.setRight(false);
		return isWallCrashColor(RIGHT_X);
	}

	public void checkBottomColor() {
		isBottomCrashColor();
	}

	public void checkTopColor() {
		isTopCrashColor();
	}

	private boolean isWallCrashColor(int correctionXPoint) {
		try {
			Color color = new Color(
					bgServiceImage.getRGB(player.getX() + correctionXPoint, player.getY() + LEFTANDRIGHT_Y));

			int red = color.getRed();
			int green = color.getGreen();
			int blue = color.getBlue();

			System.out.println(color);
			// 부딪히는 색상이 빨강색일때 true
			if (red == 255 && green == 0 && blue == 0) {
				return true;
				// 부딪히는 색상이 파랑색일때 ( == 게임 마지막 성에 도착했을때 )
			} else if (red == 0 && green == 0 && blue == 255 || red == 172 && green == 172 && blue == 255) {
				// 92 92 255 
				isWin = true;
				return false;
			}

		} catch (Exception e) {
		}
		return false;
	}

	private void isBottomCrashColor() {
		try {
			Color color = new Color(bgServiceImage.getRGB(player.getX() + BOTTOM_X, player.getY() + BOTTOM_Y));
			int red = color.getRed();
			int green = color.getGreen();
			int blue = color.getBlue();

			// 바닥이 흰색 아닐때
			if (!(red == 255 && green == 255 && blue == 255)) {
				player.setDown(false);
			} else {
				if (!player.isUp() && !player.isDown()) {
					player.down();
				}
			}
		} catch (Exception e) {
		}
	}

	private void isTopCrashColor() {
		try {
			Color color = new Color(bgServiceImage.getRGB(player.getX() + TOP_X, player.getY()));
			int red = color.getRed();
			int green = color.getGreen();
			int blue = color.getBlue();

			if (red == 0 && green == 0 && blue == 0) {
				// 246 246 246
				player.setCrashOk(true);
				crashX = player.getX();
				crashY = player.getY();
			}
			if (!(red == 255 && green == 255 && blue == 255)) {
				player.setUp(false);
			}
		} catch (Exception e) {
		}
	}
}

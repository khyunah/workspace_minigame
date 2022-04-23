package project_supermario_2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MushroomObserver {

	private static int RIGHT_XPOINT = 30;
	private static int BOTTOM_XPOINT = 15;

	private BufferedImage image;
	private Mushroom mushroom;

	public MushroomObserver(Mushroom mushroom) {
		this.mushroom = mushroom;

		try {
			image = ImageIO.read(new File("images/MapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean checkRightWall() {
		return isRightCrashColor();
	}

	public boolean checkbottom() {
		return isBottomColor();
	}

	private boolean isRightCrashColor() {
		int rightColor = image.getRGB(mushroom.getX() + RIGHT_XPOINT, mushroom.getY() + BOTTOM_XPOINT);

		if (rightColor == -65536) {
			return true;
		}
		return false;
	}

	private boolean isBottomColor() {
		int bottomColor = image.getRGB(mushroom.getX() + RIGHT_XPOINT, mushroom.getY() + RIGHT_XPOINT)
				+ image.getRGB(mushroom.getX() - 1, mushroom.getY() + RIGHT_XPOINT);

		// 흰색이면 -2
		if (bottomColor == -2) {
			return true;
			// 바닥 하얀색
		}
		return false;
	}
}
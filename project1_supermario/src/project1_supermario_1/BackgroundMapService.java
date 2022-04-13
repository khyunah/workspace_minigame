package project1_supermario_1;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundMapService extends Player {

	private BufferedImage bgServiceImage;

	private final int LEFT_X = 0;
	private final int RIGHT_X = 100;
	private final int BOTTOM_X = 50;
	private final int BOTTOM_Y = 100;

	// 성에 도착했을때 true
	// true이면 이기는 화면 나오게 구현하기
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

	/**
	 * todo check메소드 player에서 각각의 방향 if문으로 false처리 해주기
	 * 
	 * @return
	 */
	public boolean checkLeftWall() {
		return isWallCrashColor(LEFT_X);
	}

	public boolean checkRightWall() {
		return isWallCrashColor(RIGHT_X);
	}

	public void checkBottomColor(SuperMarioFrame marioFrame) {
		isBottomCrashColor(marioFrame);
	}

	/**
	 * player 측면의 색상이 빨강색(==벽) 이거나 초록색(==굴뚝) 이면 움직임에 제한을 두기 위한 메소드
	 * 
	 * @param correctionPoint
	 * @return
	 */
	private boolean isWallCrashColor(int correctionXPoint) {
		Color color = new Color(bgServiceImage.getRGB(getPlayerX() + correctionXPoint, getPlayerY()));
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();

		// 부딪히는 색상이 빨강색이거나 초록색일때 true
		if (red == 255 && green == 0 && blue == 0 || red == 0 && green == 255 && blue == 0) {
			return true;
			// 부딪히는 색상이 검정색일때 ( == 게임 마지막 성에 도착했을때 )
		} else if (red == 0 && green == 0 && blue == 0) {
			isWin = true;
			return false;
		}
		return false;
	}

	private void isBottomCrashColor(SuperMarioFrame marioFrame) {
		Color color = new Color(bgServiceImage.getRGB(getPlayerX() + BOTTOM_X, getPlayerY() + BOTTOM_Y));
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();

		// 바닥이 흰색 일때
		if (red == 255 && green == 255 && blue == 255) {
			setDown(true);
		} else if (red == 0 && green == 255 && blue == 0) {
			// player의 바닥 색상이 초록색 일때
			setDown(false);
//			marioFrame.addKeyListener(new KeyAdapter() {
//				@Override
//				public void keyPressed(KeyEvent e) {
//					switch (e.getKeyCode()) {
//					case KeyEvent.VK_DOWN:
//						setDown(true);
//						break;
//					}
//				}
//			});
		} else {
			setDown(false);
		}
	}
}

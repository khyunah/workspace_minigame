package project_supermario_2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundPlayerService implements Runnable {

	private BufferedImage bgImage;
	private Player player;
	
	private Item item;

	private int playerRightX;
	private int playerBottomX;
	private int playerBottomY;
	private final int TOP_X = 25;

	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			bgImage = ImageIO.read(new File("images/backgroundMapService_sizeup.png"));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}
		initSetting();
	}

	private void initSetting() {
		playerRightX = 50;
		playerBottomX = 25;
		playerBottomY = 60;
	}
	
//	public void isTopCrashColor() {
//		Color topColor = new Color(bgImage.getRGB(player.getX() + TOP_X, player.getY()));
//		int red = color.getRed();
//		int green = color.getGreen();
//		int blue = color.getBlue();
//
//		// 머리가 파랑색 일때
//		if (red == 0 && green == 0 && blue == 255) {
//			player.setUp(false);
//			item.setChrashCount(1);
//		}
//	}

	@Override
	public void run() {
		while(true) {
			try {
				System.out.println("-----------------백그라운드 맵 서비스 시작");
				Color leftColor = new Color(bgImage.getRGB(player.getX(), player.getY()));
				System.out.println(leftColor);
				Color rightColor = new Color(bgImage.getRGB(player.getX() + playerRightX, player.getY()));
				System.out.println(rightColor);
				Color bottomColor = new Color(bgImage.getRGB(player.getX() + playerBottomX, player.getY() + playerBottomY));
				System.out.println(bottomColor);
//				Color topColor = new Color(bgImage.getRGB(player.getX() + TOP_X, player.getY()));
				
				System.out.println("======================================");
				
				// 바닥 색상 확인
				if(!(bottomColor.getRed() == 255 && bottomColor.getGreen() == 255 && bottomColor.getBlue() == 255)) {
					player.setDown(false);
				} else {
					if (!player.isUp() && !player.isDown()) {
						player.down();
					}
				}
				
				// 벽 넘지 못하게 확인
				if((leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0)
						|| (leftColor.getRed() == 0 && leftColor.getGreen() == 255 && leftColor.getBlue() == 0)) {
					player.setLeftWallCrash(true);
					player.setLeft(false);
				} else if ((rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0)
						|| (rightColor.getRed() == 0 && rightColor.getGreen() == 255 && rightColor.getBlue() == 0)) {
					player.setRightWallCrash(true);
					player.setRight(false);
				}
				
//				// 머리위가 파랑색일때 
//				if(!(bottomColor.getRed() == 0 && bottomColor.getGreen() == 0 && bottomColor.getBlue() == 255)) {
//					player.setUp(false);
//					item.setChrashCount(1);
//				}
				
				

			} catch (Exception e) {
				System.out.println("문제발생 ");
			}
		}
		
	}

}
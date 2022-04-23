package project_supermario_2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lombok.Data;

@Data
public class BackgroundPlayerService implements Runnable {

	private BufferedImage bgImage;
	private Player player;

	// 플레이어 방향에 따른 x, y 좌표값 설정
	private int playerLeftRightY;
	private int playerRightX;
	private int playerBottomX;
	private int playerBottomY;
	private int playerTopX;
	
	// 아이템 생성을 위한 좌표 담을 변수 
	private int crashX;
	private int crashY;
	
	// 파란색에 닿으면 이기는 상태 변수
	private boolean isWin;

	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			bgImage = ImageIO.read(new File("images/MapService.png"));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}
		initSetting();
	}

	private void initSetting() {
		playerLeftRightY = 53;
		playerRightX = 50;
		playerBottomX = 25;
		playerBottomY = 60;
		playerTopX = 25;
		
		crashX = 0;
		crashY = 0;
		
		isWin = false;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Color leftColor = new Color(bgImage.getRGB(player.getX(), player.getY() + playerLeftRightY));
				Color rightColor = new Color(bgImage.getRGB(player.getX() + playerRightX, player.getY() + playerLeftRightY));
				Color bottomColor = new Color(bgImage.getRGB(player.getX() + playerBottomX, player.getY() + playerBottomY));
				Color topColor = new Color(bgImage.getRGB(player.getX() + playerTopX, player.getY()));
				
				// 바닥 색상 확인 ( 다운 )
				if(!(bottomColor.getRed() == 255 && bottomColor.getGreen() == 255 && bottomColor.getBlue() == 255)) {
					player.setDown(false);
				} else {
					if (!player.isUp() && !player.isDown()) {
						player.down();
					}
				}

				// 벽 넘지 못하게 확인
				if((leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0)) {
					player.setLeftWallCrash(true);
					player.setLeft(false);
				} else if ((rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0)) {
					player.setRightWallCrash(true);
					player.setRight(false);
				} 
				
				// 파랑색에 닿으면 Win
				if((rightColor.getRed() == 172 && rightColor.getGreen() == 172 && rightColor.getBlue() == 255) ||
						(rightColor.getRed() == 0 && rightColor.getGreen() == 0 && rightColor.getBlue() == 255)) {
					isWin = true;
				}
				
				// 검은색에 닿으면 아이템 얻음
				if (topColor.getRed() == 0 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
					// 246 246 246
					player.setCrashOk(true);
					crashX = player.getX();
					crashY = player.getY();
				}
				
				// 일반벽돌이 머리에 닿으면 점프 못하게
				if (!(topColor.getRed() == 255 && topColor.getGreen() == 255 && topColor.getBlue() == 255)) {
					player.setUp(false);
				}
				
			} catch (Exception e) {
				System.out.println("컬러오류");
			}
		}
	}
}
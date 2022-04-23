package project_supermario_2;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lombok.Getter;

@Getter
public class SuperMarioFrame extends JFrame {

	private Image image = new ImageIcon("images/marioBackgroundMap.png").getImage();
	private Image changImg = image.getScaledInstance(7000, 500, Image.SCALE_SMOOTH);
	private ImageIcon changIcon = new ImageIcon(changImg);

	private JPanel panel;
	private JLabel label;
	private JLabel winImage;

	private JLabel bgMap;
	private Player player;
	private Monster[] monsters = new Monster[3];
	private Mushroom mushroom;
	private boolean gameOver;

	private int pointX;
	private int pointY;

	public SuperMarioFrame() {
		initData();
		setInitLayout();
		initListener();
	}

	private void initData() {
		panel = new JPanel();
		bgMap = new JLabel(changIcon);
		player = Player.getInstance(this);
		monsters[0] = new Monster(700, 410, this);
		monsters[1] = new Monster(3900, 410, this);
		monsters[2] = new Monster(5600, 410, this);
		mushroom = new Mushroom(this);

		label = new JLabel(new ImageIcon("images/gameover.jpg"));
		winImage = new JLabel(new ImageIcon("images/winImg.png"));

		gameOver = false;
		pointX = 0;
		pointY = 0;

		setSize(1500, 540);
		setLocation(0, 0);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setInitLayout() {
		setContentPane(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.add(bgMap);

		bgMap.setLocation(pointX, pointY);
		bgMap.add(player);
		bgMap.add(mushroom);
		for (int i = 0; i < monsters.length; i++) {
			bgMap.add(monsters[i]);
		}
	}

	public void showGameoverImage() {
		endGame(label);
		threadSleep(2000);
		System.exit(0);
	}

	public void showWinImage() {
		endGame(winImage);
	}

	private void initListener() {
		
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				
				if (!gameOver) {

					switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						if (!player.isLeftWallCrash()) {
							moveMap();
						}
						if (!player.isLeft() && !player.isLeftWallCrash()) {
							player.left();
						}
						break;

					case KeyEvent.VK_RIGHT:
						if (!player.isRightWallCrash()) {
							moveMap();
						}
						if (!player.isRight() && !player.isRightWallCrash()) {
							player.right();
						}
						break;

					case KeyEvent.VK_UP:
						if (!player.isLeftWallCrash()) {
							moveMap();
						} else {
							moveMap();
						}

						if (!player.isUp() && !player.isDown()) {
							player.up();
						}
						break;

					case KeyEvent.VK_DOWN:
						player.enterChimney();
						if (player.isEnter()) {
							pointX = -5139;
							bgMap.setLocation(pointX, pointY);
						}
						player.setEnter(false);
						break;
						
					case KeyEvent.VK_Z:
						bgMap.setLocation(-player.getX() + 100, pointY);
						break;
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					player.setRightWallCrash(false);
					break;
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					player.setLeftWallCrash(false);
					break;
				}
			}
		});
	}

	/**
	 * 원본 백그라운드 이미지가 길기 때문에 플레이어가 이동함에 따라 백그라운드 맵도 같이 이동하기 위한 메소드 <br>
	 * for문 : 플레이어가 움직이는 SPEED만큼 ( 한번 걸을때 SPEED가 두번 작동하기 때문에 * 2 해줌 )<br>
	 * if문 1 : 백그라운드맵의 x좌표가 이동할때 왼쪽 오른쪽 여백이 생기지 않게 범위 설정<br>
	 * if문 2 :플레이어의 좌표의 최소값, 최대값을 설정
	 * 
	 */
	private void moveMap() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 0; i < (player.getSPEED() * 2); i++) {

					if (pointX <= 0 && (pointX >= (getSize().getWidth() - bgMap.getWidth()))) {

						if (player.getX() >= 100 && (player.getX() <= (bgMap.getWidth() - getSize().getWidth()))) {

							bgMap.setLocation(-player.getX() + 100, pointY);
							threadSleep(10);
						}
					}
				}
			}
		}).start();
	}

	/**
	 * 게임을 지거나 이길때 라벨을 바꿔주기 위한 메소드. <br>
	 * 라벨을 올려주고 백그라운드 맵에 add()했던 player, monster, mushroom을 제거해준다. <br>
	 * gameOver의 상태변수를 true로 변경하므로써 키 이벤트가 동작하지 않게 된다. ( 이미지가 바뀐후에 움직이는 버그 잡기 위해 )
	 * 
	 * @param endLabel : 이미지가 담긴 JLabel타입을 넣어주면 된다.
	 */
	private void endGame(JLabel endLabel) {
		gameOver = true;

		bgMap.add(endLabel);
		endLabel.setBounds(-bgMap.getX(), 0, (int) getSize().getWidth(), (int) getSize().getHeight());

		bgMap.remove(player);
		for (int i = 0; i < monsters.length; i++) {
			bgMap.remove(monsters[i]);
		}
		bgMap.remove(mushroom);

		repaint();
	}

	private void threadSleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SuperMarioFrame();
	}
}
package bubble.test.ex10;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/*
 * 현재 메인 쓰레드는 너~무 바쁨
 * 백그라운드에서 계속 Player 에 움직임을 관찰
 */
public class BackgroundEnemyService implements Runnable {

	private BufferedImage image;
	private Enemy enemy;
	

	// 생성자 의존 주입 DI
	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;

		try {
			image = ImageIO.read(new File("Img/backgroundMapService.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			// 색상 확인 todo (보정값 필요)
			Color leftColor = new Color(image.getRGB(enemy.getX(), enemy.getY() + 25));
			Color rightColor = new Color(image.getRGB(enemy.getX() + 60, enemy.getY() + 25));
			// Color bottomColor = new Color(image.getRGB(player.getX() + 25, player.getY()
			// + 50));

			// 흰색이면 바닥 RGB 255 255 255
			// 바닥 인경우 --> 255 0 0 (바닥이라고 판단 가능)
			// 바닥 인경우 --> 0 0 255 (바닥이라고 판단 가능)
			int bottomColorLeft = image.getRGB(enemy.getX() + 20, enemy.getY() + 50 + 5);
			int bottomColorRight = image.getRGB(enemy.getX() + 50 - 10, enemy.getY() + 50 + 5);

			Color bubbleColorLeft = new Color(image.getRGB(enemy.getX(), enemy.getY() + 25));
			Color bubbleColorRight = new Color(image.getRGB(enemy.getX() + 60, enemy.getY() + 25));

			// 하얀색 ---> int 값이 - 1
			if (bottomColorLeft + bottomColorRight != -2) {
				// 여기는 멈추어야 한다. (빨간 바닥 또는 파란색 바닥)
				enemy.setDown(false);
			} else if (bottomColorLeft + bottomColorRight == -2) {
				if (!enemy.isUp() && !enemy.isDown()) {
					enemy.down();

				}
			}

			// 왼쪽에 충돌함
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				// System.out.println("왼쪽벽에 충돌 함.");
				enemy.setLeftWallCrash(true);
				enemy.setLeft(false);
				// 오른쪽에 충돌함
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				// System.out.println("오른쪽벽에 충돌 함");
				enemy.setRightWallCrash(true);
				enemy.setRight(false);
			} else {
				enemy.setLeftWallCrash(false);
				enemy.setRightWallCrash(false);
			}
			// 위 두 조건이 아니면 player 마음대로 움직일 수 있다.

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}

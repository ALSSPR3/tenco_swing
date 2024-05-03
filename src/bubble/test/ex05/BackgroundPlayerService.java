package bubble.test.ex05;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * 현재 메인 쓰레드는 너~무 바쁨
 * 백그라운드에서 계속 Player 에 움직임을 관찰
 */
public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;

	// 생성자 의존 주입 DI
	public BackgroundPlayerService(Player player) {
		this.player = player;

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
			Color leftColor = new Color(image.getRGB(player.getX(), player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 60, player.getY() + 25));

			// 왼쪽에 충돌함
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				System.out.println("왼쪽벽에 충돌 함.");
				player.setLeftWallCrash(true);
				player.setLeft(false);
				// 오른쪽에 충돌함
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				System.out.println("오른쪽벽에 충돌 함");
				player.setRightWallCrash(true);
				player.setRight(false);
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
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

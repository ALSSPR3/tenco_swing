package bubble.test.ex07;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundBubbleService implements Runnable {

	private BufferedImage image;
	private Bubble bubble;

	public BackgroundBubbleService(Bubble bubble) {
		this.bubble = bubble;

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
			Color bubbleColorLeft = new Color(image.getRGB(bubble.getX() + 10, bubble.getY() + 25));
			Color bubbleColorRight = new Color(image.getRGB(bubble.getX() + 50 + 10, bubble.getY() + 25));
			System.out.println(bubbleColorRight);

			// 왼쪽에 충돌함
			if (bubbleColorLeft.getRed() == 255 && bubbleColorLeft.getGreen() == 0 && bubbleColorLeft.getBlue() == 0) {
				// System.out.println("왼쪽벽에 충돌 함.");
				bubble.setBubbleLeftWallCrash(true);
				bubble.setLeft(false);
				// 오른쪽에 충돌함
			} else if (bubbleColorRight.getRed() == 255 && bubbleColorRight.getGreen() == 0
					&& bubbleColorRight.getBlue() == 0) {
				// System.out.println("오른쪽벽에 충돌 함");
				bubble.setBubbleRightWallCrash(true);
				bubble.setRight(false);
			} else {
				bubble.setBubbleLeftWallCrash(false);
				bubble.setBubbleRightWallCrash(false);
			}
			// 위 두 조건이 아니면 player 마음대로 움직일 수 있다.

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

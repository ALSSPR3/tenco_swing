package ch08;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.EventListener;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

	private BufferedImage backgroundImage;
	private BufferedImage player1;
	private BufferedImage enemy1;
	private ImagePanel imagePanel;
	private int PLAYER_X_DISTENCE = 150;
	private int PLAYER_Y_DISTENCE = 300;
	private int ENEMY_X_DISTENCE = 250;
	private int ENEMY_Y_DISTENCE = 420;
	private boolean flag = true;

	public GameFrame() {

		initData();
		setInitLayout();
		addEventListener();

	}

	// 클래스 안에 클래스 -> 중첩 클래스 -> 외부 클래스, 내부클래스
	private class ImagePanel extends JPanel implements Runnable {

		// paintComponents --> x
		// paintComponent --> o
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, 600, 600, null);
			g.drawImage(player1, PLAYER_X_DISTENCE, PLAYER_Y_DISTENCE, 50, 50, null);
			g.drawImage(enemy1, ENEMY_X_DISTENCE, ENEMY_Y_DISTENCE, 50, 50, null);

			// Todo 플레이어, 적군 그림 그려야 함
			// 쓰레드를 활용할 예정

		}

		@Override
		public void run() {

			// true : 왼쪽으로 가는 상황
			// false : 오른쪽으로 가는 상황
			boolean direction = true;

			while (true) {

				while (flag) {

					if (direction) {
						ENEMY_X_DISTENCE -= 10;
					} else {
						ENEMY_X_DISTENCE += 10;
					}
					// 방향 바꾸는 개념은 적군 x 좌표값이
					if (ENEMY_X_DISTENCE <= 50) {
						direction = false;
					}
					if (ENEMY_X_DISTENCE >= 500) {
						direction = true;
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();
				}
			}
		}
	}

	private void initData() {
		setTitle("Thread 활용한 미니 예제");
		setSize(600, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			backgroundImage = ImageIO.read(new File("Img/backgroundMap.png"));
			player1 = ImageIO.read(new File("Img/playerL.png"));
			enemy1 = ImageIO.read(new File("Img/enemyL.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		imagePanel = new ImagePanel();
		// 다른 작업자에게 일을 위임 시킨다.
		Thread thread = new Thread(imagePanel);
		thread.start();
	}

	private void setInitLayout() {
		// setLayout(null);
		// setResizable(false); // 프레임 크기 조절 불가 설정
		setVisible(true);

		add(imagePanel);

	}

	private void addEventListener() {
		// 이벤트 리스너 등록 방법 2가지 중
		Thread thread = new Thread(imagePanel);
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();

				switch (code) {
				case KeyEvent.VK_UP:
					PLAYER_Y_DISTENCE -= 120;
					break;
				case KeyEvent.VK_DOWN:
					PLAYER_Y_DISTENCE += 120;
					break;
				case KeyEvent.VK_LEFT:
					PLAYER_X_DISTENCE -= 10;
					break;
				case KeyEvent.VK_RIGHT:
					PLAYER_X_DISTENCE += 10;
					break;
				case KeyEvent.VK_SPACE:
					// 1. 스페이스를 눌렀을 때 적군을 멈출 수 있도록 코드 수정
					if (flag) {
						flag = false;
						System.out.println(flag);
					} else {
						flag = true;
						thread.interrupt();
						Thread thread = new Thread(imagePanel);
				        thread.start();
						System.out.println(flag);
					}
				default:
					break;
				}

				// 2.player 가 적군과 만났다면 player 그림을 없애 주세요.
				if (checkHitenemy()) {
					player1 = null;
				}
				repaint();
			} // end of keypressed
		});
		 
	}
	
	private boolean checkHitenemy() {
		int playerW = PLAYER_X_DISTENCE + 50;
		int playerH = PLAYER_Y_DISTENCE + 50;
		int enemyW = ENEMY_X_DISTENCE + 50;
		int enemyH = ENEMY_Y_DISTENCE + 50;
		
		if(PLAYER_X_DISTENCE < enemyW && playerW > ENEMY_X_DISTENCE &&
				PLAYER_Y_DISTENCE < enemyH && playerH > ENEMY_Y_DISTENCE) {
			return true;
		}
		
		return false;
	}
}

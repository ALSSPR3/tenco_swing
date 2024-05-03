package bubble.test.ex07;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bubble extends JLabel implements Moveable {

	private Player player;

	private int x;
	private int y;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;

	// 적군을 맞춘 상태
	private int state; // 0. 기본물방울, 1. 적을 가둔 상태 물방울

	private ImageIcon bubble; // 기본 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울 터짐

	private boolean bubbleLeftWallCrash;
	private boolean bubbleRightWallCrash;

	// 연관관계, 의존성 컴포지션 관계, 생성자 의존 (DI)
	public Bubble(Player player) {
		this.player = player;
		initData();
		setInitLayout();

		// 객체 생성시 무조건 스레드 시작
		initThread();
	}

	// get, set
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public ImageIcon getBubble() {
		return bubble;
	}

	public void setBubble(ImageIcon bubble) {
		this.bubble = bubble;
	}

	public ImageIcon getBubbled() {
		return bubbled;
	}

	public void setBubbled(ImageIcon bubbled) {
		this.bubbled = bubbled;
	}

	public ImageIcon getBomb() {
		return bomb;
	}

	public void setBomb(ImageIcon bomb) {
		this.bomb = bomb;
	}

	public boolean isBubbleLeftWallCrash() {
		return bubbleLeftWallCrash;
	}

	public void setBubbleLeftWallCrash(boolean bubbleLeftWallCrash) {
		this.bubbleLeftWallCrash = bubbleLeftWallCrash;
	}

	public boolean isBubbleRightWallCrash() {
		return bubbleRightWallCrash;
	}

	public void setBubbleRightWallCrash(boolean bubbleRightWallCrash) {
		this.bubbleRightWallCrash = bubbleRightWallCrash;
	}

	private void initData() {
		bubble = new ImageIcon("Img/bubble.png");
		bubbled = new ImageIcon("Img/bubbled.png");
		bomb = new ImageIcon("Img/bomb.png");

		left = false;
		right = false;
		up = false;
		state = 0;

		bubbleLeftWallCrash = false;
		bubbleRightWallCrash = false;
	}

	private void setInitLayout() {

		x = player.getX();
		y = player.getY();

		setIcon(bubble);
		setSize(50, 50);
		setLocation(x, y);
		setVisible(true);
	}

	// 공통으로 사용하는 부분을 메서드로 만들어 보자
	// 이 메서드는 내부에서만 사용할 예정
	private void initThread() {
		// 버블은 스레드가 하나면 된다
		// 익명 클래스, 익명 구현 클래스
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (player.playerWay == PlayerWay.LEFT) {
					left();
				} else {
					right();
				}
			}
		}).start();
	}

	@Override
	public synchronized void left() {
		left = true;
		if (left) {
			for (int i = 0; i < 400; i++) {
				x--;
				setLocation(x, y);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		up();
	}

	@Override
	public synchronized void right() {
		right = true;
		if (right) {
			for (int i = 0; i < 400; i++) {
				x++;
				setLocation(x, y);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while (up) {
			y--;
			setLocation(x, y);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (y == 12) {
				up = false;
				setIcon(bomb);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		setIcon(null);
		up = false;
	}

}

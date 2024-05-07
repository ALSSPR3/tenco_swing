package bubble.test.ex10;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bubble extends JLabel implements Moveable {

	private BubbleFrame mContext;

	// 의존성 컴포지션 관계
	private Player player;
	private BackgroundBubbleService backgroundBubbleService;

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

	// 연관관계, 의존성 컴포지션 관계, 생성자 의존 (DI)
	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		initData();
		setInitLayout();
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

	private void initData() {
		bubble = new ImageIcon("Img/bubble.png");
		bubbled = new ImageIcon("Img/bubbled.png");
		bomb = new ImageIcon("Img/bomb.png");
		backgroundBubbleService = new BackgroundBubbleService(this);

		left = false;
		right = false;
		up = false;
		state = 0;

	}

	private void setInitLayout() {

		x = player.getX();
		y = player.getY();

		setIcon(bubble);
		setSize(50, 50);
		setLocation(x, y);
		setVisible(true);
	}

	@Override
	public synchronized void left() {
		left = true;
		if (left) {
			for (int i = 0; i < 400; i++) {
				x--;
				setLocation(x, y);
				// 만약 왼쪽 벽 --> up()
				if (backgroundBubbleService.leftWall()) {
					break;
				}

				// x 절대값만 확인해 보자.
				bubbleAbs();

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
				if (backgroundBubbleService.rightWall()) {
					break;
				}
				bubbleAbs();
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
			if (backgroundBubbleService.topWall()) {
				clearBubble();
				break;
			}
		}
	}

	private void clearBubble() {
		try {
			Thread.sleep(400);
			setIcon(bomb);

			Thread.sleep(300);
			// todo 테스트 필요
			setIcon(null);
			Thread.sleep(10);
			mContext.remove(this);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void bubbleAbs() {
		int absX = Math.abs(x - mContext.getEnemy1().getX());
		int absY = Math.abs(y - mContext.getEnemy1().getY());
//		System.out.println(absX);
//		System.out.println(absY);
		if (absX < 10 && absY < 50) {
			// 단, 적군이 살아 있을 때 crash() 메서드 호출이 되어야 함.
			if (mContext.getEnemy1().getState() == 0) {
				crash();
			}
		}
	}

	public void crash() {
		// 적군이 살아 있는 상태에서
		// 버블에 갇힌 상태로 변경
		// 버블의 이미지를 변경 처리
		mContext.getEnemy1().setState(1);
		state = 1;
		setIcon(bubbled);
		// mContext.getEnemy1().setIcon(null);
		mContext.remove(mContext.getEnemy1());
		mContext.repaint();
	}

}

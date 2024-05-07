package bubble.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.BubbleFrame;
import bubble.interfaces.Moveable;
import bubble.service.BackgroundEnemyService;
import bubble.state.EnemyWay;

public class Enemy extends JLabel implements Moveable {

	BubbleFrame mContext;
	// 살아있는 상태 0, 물방울에 갇힌 상태 1
	private int state;

	// 적군의 좌표값 위치 상태
	private int x;
	private int y;
	private ImageIcon EnemyR, EnemyL;

	// 움직임의 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	// 적군 속도 상태
	private final int SPEED = 3;
	private final int JUMPSPEED = 1;

	// enum 타입의 활용
	private EnemyWay enemyWay;

	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		new Thread(new BackgroundEnemyService(this)).start();
		
		left();
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

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	private void initData() {
		EnemyR = new ImageIcon("Img/enemyR.png");
		EnemyL = new ImageIcon("Img/enemyL.png");

		// 초기화
		state = 0;

		// 처음 실행 시 적군의 위치 초기 값 셋팅
		x = 720;
		y = 175;

		// 적군의 방향 상태
		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;

		enemyWay = EnemyWay.LEFT;
	}

	private void setInitLayout() {
		setIcon(EnemyL);
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	public void left() {
		enemyWay = EnemyWay.LEFT;
		left = true;
		setIcon(EnemyL);

		// <-<-<-
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {
					x -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void right() {
		enemyWay = EnemyWay.RIGHT;
		right = true;
		setIcon(EnemyR);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (right) {
					x += SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	} // end of right

	@Override
	public void up() {
		// System.out.println("점프");
		up = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 150 / JUMPSPEED; i++) {
					y -= JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 객체의 상태값을 잘 조절해야 한다.
				up = false;
				down();
			}
		}).start();
	}

	@Override
	public void down() {
		// System.out.println("다운");
		down = true;

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (down) {
					y += JUMPSPEED;
					setLocation(x, y);

					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}

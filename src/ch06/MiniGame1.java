package ch06;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MiniGame1 extends JFrame {

	private JLabel jPlayer;
	private static int jPlayerx = 100;
	private static int jPlayery = 100;
	private final static int MOVE_DISTENCE = 50;
	private final int FRAME_WIDTH = 500;
	private final int FRAME_HEIGHT = 500;
	private final String PLAYER_NAME = "야스오";
	private final int PLAYER_WIDTH = 100;
	private final int PLAYER_HEIGHT = 100;

	public MiniGame1() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jPlayer = new JLabel(PLAYER_NAME);
		jPlayer.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
	}

	private void setInitLayout() {
		// 좌표기반으로 배치관리자 변경
		setLayout(null);
		add(jPlayer);
		jPlayer.setLocation(100, 100);

		setVisible(true);
	}

	private void addEventListener() {
		// jPlayer 객체에게서만 KeyListener 동작을 시키고자 한다면
		// 익명 구현클래스로 KeyListener 인터페이스를 재 정의할 수 있다.
		// jPlayer.addKeyListener(this);
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					jPlayery -= MOVE_DISTENCE;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					jPlayerx -= MOVE_DISTENCE;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					jPlayerx += MOVE_DISTENCE;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					jPlayery += MOVE_DISTENCE;
				}
				jPlayer.setLocation(jPlayerx, jPlayery);
			}
		});
	}

	// 코드 테스트
	public static void main(String[] args) {
		new MiniGame1();
	}

} // end of class

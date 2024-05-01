package ch06;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MiniGame2 extends JFrame {

	private JLabel jPlayer;
	private JLabel jPlayer_Left;
	private JLabel jPlayer_Right;
	private JLabel background;
	private static int jPlayerx = 500;
	private static int jPlayery = 530;
	private final static int MOVE_DISTENCE = 50;
	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 900;
	private final int BACKGROUND_WIDTH = 1000;
	private final int BACKGROUND_HEIGHT = 600;
	private final int PLAYER_WIDTH = 50;
	private final int PLAYER_HEIGHT = 50;
	private final String BACKGROUND_IMAGE = "Images/backgroundMap.png";
	private final String PLAYER_LEFT_IMAGE = "Images/playerL.png";
	private final String PLAYER_RIGHT_IMAGE = "Images/playerR.png";

	public MiniGame2() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		background = new JLabel(new ImageIcon(BACKGROUND_IMAGE));
		background.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		jPlayer = new JLabel(new ImageIcon(PLAYER_LEFT_IMAGE));
		jPlayer_Left = new JLabel(new ImageIcon(PLAYER_LEFT_IMAGE));
		jPlayer_Right = new JLabel(new ImageIcon(PLAYER_RIGHT_IMAGE));
		jPlayer.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);

	}

	private void setInitLayout() {
		setLayout(null);
		add(background);
		background.add(jPlayer);
		jPlayer.setLocation(jPlayerx, jPlayery);

		setVisible(true);
	}

	private void addEventListener() {
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

	public static void main(String[] args) {
		new MiniGame2();
	}
}

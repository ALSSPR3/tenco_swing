package ch01;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BorderLayoutEx2 extends JFrame {

	final int WIDTH = 600;
	final int HEIGHT = 600;
	JButton[] buttons;
	String[] directions = { BorderLayout.EAST, BorderLayout.WEST, BorderLayout.SOUTH, BorderLayout.NORTH,
			BorderLayout.CENTER };

	public BorderLayoutEx2() {

		initData();
		setInitLayout();
	}

	public void initData() {
		setTitle("broderLayout 연습");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttons = new JButton[directions.length];

	}

	public void setInitLayout() {
		// 배치 관리자 선정(컨테이너)
		// BorderLayout -- 컴포넌트들을 동서남북가운데로 배치 시켜주는 레이아웃이다.
		setLayout(new BorderLayout());
		String[] dis = { "동", "서", "남", "북", "중앙" };
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(dis[i]);
		}

		for (int i = 0; i < buttons.length; i++) {
			add(buttons[i], directions[i]);
		}
//		add(new JButton("동"), BorderLayout.EAST);
//		add(new JButton("서"), BorderLayout.WEST);
//		add(new JButton("남"), BorderLayout.SOUTH);
//		add(new JButton("북"), BorderLayout.NORTH);
//		add(new JButton("중앙"), BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		new BorderLayoutEx2();

	} // end of main

} // end of class

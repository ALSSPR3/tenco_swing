package ch05;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorChangerFrame3 extends JFrame implements ActionListener {

	private JButton[] button;
	private JPanel panel1;
	private JPanel panel2;

	public ColorChangerFrame3() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new FlowLayout());

		button = new JButton[5];

		button[0] = new JButton("빨강색");
		button[1] = new JButton("초록색");
		button[2] = new JButton("파랑색");
		button[3] = new JButton("검정색");
		button[4] = new JButton("하양색");

	}

	private void setInitLayout() {
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
		for (int i = 0; i < button.length; i++) {
			panel2.add(button[i]);
		}
		setVisible(true);
	}

	private void addEventListener() {
		for (int i = 0; i < button.length; i++) {
			button[i].addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectButton = (JButton) e.getSource();
		if (selectButton == button[0]) {
			panel1.setBackground(Color.red);
		} else if (selectButton == button[1]) {
			panel1.setBackground(Color.green);
		} else if (selectButton == button[2]) {
			panel1.setBackground(Color.blue);
		} else if (selectButton == button[3]) {
			panel1.setBackground(Color.black);
		} else {
			panel1.setBackground(Color.white);
		}

	}

	public static void main(String[] args) {
		new ColorChangerFrame3();
	}

}

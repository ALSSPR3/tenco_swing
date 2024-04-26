package ch02;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
// 내부 클래스를 활용해서 코드를 완성해주세요
public class PaintFrame extends JFrame{

	DrawPanel drawPanel;

	public PaintFrame() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("내부클래스를 활용한 그림 그리는 연습");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel();
	}

	private void setInitLayout() {

		add(drawPanel);
		setVisible(true);

	}
	class DrawPanel extends JPanel{
		@Override
		public void paint(Graphics g) {
			int[] x = {70,250,430};
			int[] y = {250,60,250};
			super.paint(g);
			g.drawRect(100, 250, 300, 300);
			g.drawPolygon(x,y,3);
			g.drawRect(150, 350, 100, 200);
			g.drawOval(230, 450, 10, 10);
			g.drawRect(300, 300, 90, 90);
			g.drawLine(345, 300, 345, 390);
			g.drawOval(450, 5, 100, 100);		}
	}
}

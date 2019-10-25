import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CircularMenu extends JPanel {

	private ArrayList<MenuItem> list;
	private int radius;
	private Point center;
	private Color color;
	
	private void init() {
		list = new ArrayList<MenuItem>();
		radius = 100;
		center = new Point(0,0);
		color = new Color(255, 255, 255);
		setSize(radius*3, radius*3);
	}
		
	public CircularMenu(ArrayList<MenuItem> l) {
		init();
		list = l;
		itemsPlacement();
	}

	public void addItem(MenuItem item) {
		init();
		list.add(item);
		itemsPlacement();
	}
	
	private void itemsPlacement() {
		if (list != null && list.size() > 0) {
			setLayout(null);
			double angle = 2 * Math.PI / (8.);
			int xCentre = getWidth() / 2;
			int yCentre = getHeight() / 2;
			int taille = 0;
			if (list.size() < 9) {
				for (int i = list.size() - 1; i >= 0; i--) {
					taille = list.get(i).getLength();
					list.get(i).setBounds((int) (xCentre - taille / 2 + radius * Math.cos(i * angle - Math.PI / 2)),
							(int) (yCentre - taille / 2 + radius * Math.sin(i * angle - Math.PI / 2)), taille + 10,
							taille + 10);
					add(list.get(i));
				}
			} else {
				for (int i = 7; i >= 0; i--) {
					taille = list.get(i).getLength();
					list.get(i).setBounds((int) (xCentre - taille / 2 + radius * Math.cos(i * angle - Math.PI / 2)),
							(int) (yCentre - taille / 2 + radius * Math.sin(i * angle - Math.PI / 2)), taille + 10,
							taille + 10);
					add(list.get(i));
				}
				int shift = 1;
				for (int i = list.size() -1; i > 7; i--,shift+=2) {
					taille = list.get(i).getLength();
					list.get(i).setBounds((int) (xCentre - taille / 2 + radius * Math.cos(4 * angle - Math.PI / 2)),
							(int) (yCentre - taille / 2 + radius * Math.sin(4 * angle - Math.PI / 2)) + taille + (taille*shift) , taille + 10,
							taille + 10);
					add(list.get(i));
				}
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gradient = new GradientPaint(new Point(0, 0), color, new Point(getWidth(), getHeight()),
				color);
		g2d.setPaint(gradient);
		g2d.fillOval(0, 0, getWidth(), getHeight());
//		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
	
}

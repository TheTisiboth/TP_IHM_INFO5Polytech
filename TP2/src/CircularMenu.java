import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CircularMenu extends JPanel {

	private ArrayList<MenuItem> list;
	private int radius_text;
	private int radius_border;
	private int diameter_border;
	private Point center;
	private Color color;

	private void init() {
		list = new ArrayList<MenuItem>();
		radius_text = 100;
		radius_border = (int) (radius_text * 1.5);
		diameter_border = radius_border*2;
		
		center = new Point(0, 0);
		color = new Color(150, 150, 150);
		setSize(diameter_border, diameter_border);
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
					list.get(i).setBounds((int) (xCentre - taille / 2 + radius_text * Math.cos(i * angle - Math.PI / 2)),
							(int) (yCentre - 40 / 2 + radius_text * Math.sin(i * angle - Math.PI / 2)), taille + 10,
							40);
					add(list.get(i));
				}
			} else {
				for (int i = 7; i >= 0; i--) {
					taille = list.get(i).getLength();
					list.get(i).setBounds((int) (xCentre - taille / 2 + radius_text * Math.cos(i * angle - Math.PI / 2)),
							(int) (yCentre - 40 / 2 + radius_text * Math.sin(i * angle - Math.PI / 2)), taille + 10,
							40);
					add(list.get(i));
				}
				int shift = 1;
				for (int i = list.size() -1; i > 7; i--,shift+=2) {
					taille = list.get(i).getLength();
					list.get(i).setBounds((int) (xCentre - taille / 2 + radius_text * Math.cos(4 * angle - Math.PI / 2)),
							(int) (yCentre - 40 / 2 + radius_text * Math.sin(4 * angle - Math.PI / 2)) + taille + (taille*shift) , taille + 10,
							40);
					add(list.get(i));
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gradient = new GradientPaint(new Point(0, 0), color, new Point(getWidth(), getHeight()), color);
		g2d.setPaint(gradient);
//		g2d.fillOval(0, 0, getWidth(), getHeight());
		
//		int arcAngle = (int) (360/list.size());
		int arcAngle = (int) (360/8);
		Point s1,s2;
		int startAngle = (int)(arcAngle/2);
		
//		g2d.drawLine(radius_text, radius_text, s1.x, s1.y);
		for (int i = 0; i < list.size(); i++) {
			s1 = new Point((int)(radius_border+radius_border*Math.cos(Math.PI*(startAngle+arcAngle*i)/180)),(int)(300 - (radius_border+radius_border*Math.sin(Math.PI*(startAngle+arcAngle*i)/180))));
			//s2 = new Point((int)(radius_border+radius_border*Math.cos(startAngle+arcAngle*(i+1))),(int)(radius_border+radius_border*Math.sin(startAngle+arcAngle*(i+1))));
			g2d.setColor(Color.RED);
			g2d.fillArc(0,0, diameter_border, diameter_border, (int)((startAngle+arcAngle*i)%360), (int)arcAngle);
			g2d.setColor(Color.BLACK);
			g2d.drawLine(radius_border, radius_border, s1.x+i, s1.y+i);
//			g2d.drawLine(radius_border, radius_border, s2.x, s2.y);
		}
//		g2d.fillRect(0, 0, getWidth(), getHeight());
	}

}

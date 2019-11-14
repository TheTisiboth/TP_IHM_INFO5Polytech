import java.awt.Color;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import javafx.event.ActionEvent;

public class CircularMenu extends JPanel {

	private ArrayList<MenuItem> list;
	private int radius;
	private Color color;

	private void init(ArrayList<MenuItem> l, int r) {
		list = l;
		radius = r;
		color = new Color(150, 150, 150);
		setSize(r*3, r*3);
	}

	public CircularMenu(ArrayList<MenuItem> l) {
		init(l, 100);
		itemsPlacement();
		addListener();
	}
	
	public CircularMenu(ArrayList<MenuItem> l, int radius) {
		init(l, radius);
		itemsPlacement();
		addListener();
	}

	public void addItem(MenuItem item) {
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
							(int) (yCentre - 40 / 2 + radius * Math.sin(i * angle - Math.PI / 2)), taille + 10,
							40);
					add(list.get(i));
				}
			} else {
				for (int i = 7; i >= 0; i--) {
					taille = list.get(i).getLength();
					list.get(i).setBounds((int) (xCentre - taille / 2 + radius * Math.cos(i * angle - Math.PI / 2)),
							(int) (yCentre - 40 / 2 + radius * Math.sin(i * angle - Math.PI / 2)), taille + 10,
							40);
					add(list.get(i));
				}
				int shift = 1;
				for (int i = list.size() -1; i > 7; i--,shift+=2) {
					taille = list.get(i).getLength();
					list.get(i).setBounds((int) (xCentre - taille / 2 + radius * Math.cos(4 * angle - Math.PI / 2)),
							(int) (yCentre - 40 / 2 + radius * Math.sin(4 * angle - Math.PI / 2)) + taille + (taille*shift) , taille + 10,
							40);
					add(list.get(i));
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;	
		int arcAngle = (int) (360/8);
		Point s1,s2;
		int startAngle = (int)(arcAngle/2);
		
//		for (int i = 0; i < list.size(); i++) {
//			s1 = new Point((int)(radius*1.5+radius*1.5*Math.cos(startAngle+arcAngle*i)),(int)(300 - (radius*1.5+radius*1.5*Math.sin(startAngle+arcAngle*i))));
//			s2 = new Point((int)(radius*1.5+radius*1.5*Math.cos(startAngle+arcAngle*(i+1))),(int)(radius*1.5+radius*1.5*Math.sin(startAngle+arcAngle*(i+1))));
//			g2d.setColor(Color.RED);
//			g2d.fillArc(0,0, radius*3, radius*3, (int)((startAngle+arcAngle*i)%360), (int)arcAngle);
//			g2d.setColor(Color.BLACK);
//			g2d.drawLine((int)(radius*1.5), (int)(radius*1.5), s1.x, s1.y);
////			g2d.drawLine((int)(radius*1.5), (int)(radius*1.5), s2.x, s2.y);
//		}
		
		g2d.setColor(Color.GREEN);
		g2d.fillOval(0, 0, getWidth(), getHeight());
	}
	
	private void addListener() {
		addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {
				if (Math.sqrt(Math.pow(e.getX(), 2) + Math.pow(e.getY(), 2)) < Math.pow(radius, 2)) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				} else {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (Math.sqrt(Math.pow(e.getX(), 2) + Math.pow(e.getY(), 2)) < Math.pow(radius, 2)) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				} else {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}

		});
	}

}

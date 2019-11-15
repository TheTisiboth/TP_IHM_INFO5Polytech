import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CircularMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<MenuItem> list;
	private Color color;
	private int radius_text;
	private int radius_border;
	private int diameter_border;
	private Point center;
	private int distMin;

	private void init(ArrayList<MenuItem> l, int rT) {
		list = l;
		radius_text = rT;
		radius_border = (int) (radius_text * 1.5);
		diameter_border = radius_border * 2;
		distMin = 25;
		int extraItems;
		if (list.size() <= 8) {
			extraItems = 0;
		} else {
			extraItems = list.size() - 8;
		}

		color = new Color(150, 150, 150);
		setSize(diameter_border, diameter_border + extraItems * 45 + 5);
		center = new Point(radius_border, radius_border);
	}

	public CircularMenu(ArrayList<MenuItem> l) {
		init(l, 100);
		itemsPlacement();
		addListener();
	}

	public CircularMenu(ArrayList<MenuItem> l, int radiusT) {
		init(l, radiusT);
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
			int xCentre = (int) (center.getX());
			int yCentre = (int) (center.getY());
			int taille = 0;

			if (list.size() <= 8) {
				angle = (double) (2 * Math.PI / list.size());
				for (int i = 0; i < list.size(); i++) {
					taille = list.get(i).getLength() + 10;
					list.get(i).setBounds((int) (xCentre + radius_text * Math.cos(i * angle) - taille / 2),
							(int) (diameter_border - (yCentre + radius_text * Math.sin(i * angle)) - 40 / 2), taille,
							40);
					add(list.get(i));
				}
			} else {
				for (int i = 0; i < 8; i++) {
					taille = list.get(i).getLength() + 10;
					list.get(i).setBounds((int) (xCentre + radius_text * Math.cos(i * angle) - taille / 2),
							(int) (diameter_border - (yCentre + radius_text * Math.sin(i * angle)) - 40 / 2), taille,
							40);
					add(list.get(i));
				}
				for (int i = 8; i < list.size(); i++) {
					taille = list.get(i).getLength() + 10;
					list.get(i).setBounds((int) (xCentre - taille / 2),
							(int) (yCentre + radius_border + 5 + 45 * (i - 8)), taille, 40);
					add(list.get(i));
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Point s1;
		int sectionsNbr;
		int taille = 0;
		if (list.size() <= 8) {
			sectionsNbr = list.size();
		} else {
			sectionsNbr = 8;
			for (int i = 8; i < list.size(); i++) {
				taille = list.get(i).getLength() + 10;
				g2d.setColor(color);
				g2d.fillRect((int) (center.getX() - taille / 2),
						(int) (center.getY() + radius_border + 5 + 45 * (i - 8)), taille, 40);
			}
		}
		double arcAngle = 360. / (double) (sectionsNbr);
		double startAngle = arcAngle / 2.;
		for (int i = 0; i < sectionsNbr; i++) {
			s1 = new Point(
					(int) (center.getX() + radius_border * Math.cos(Math.PI * (startAngle + arcAngle * i) / 180)),
					(int) (diameter_border
							- (center.getY() + radius_border * Math.sin(Math.PI * (startAngle + arcAngle * i) / 180))));
			g2d.setColor(color);
			g2d.fillArc(0, 0, diameter_border, diameter_border, (int) ((startAngle + arcAngle * i) % 360),
					(int) arcAngle);
			g2d.setColor(Color.BLACK);
			g2d.drawLine((int) (center.getX()), (int) (center.getY()), s1.x, s1.y);
		}
		s1 = new Point(
				(int) (center.getX() + radius_border * Math.cos(Math.PI * (startAngle + arcAngle * sectionsNbr) / 180)),
				(int) (diameter_border - (center.getY()
						+ radius_border * Math.sin(Math.PI * (startAngle + arcAngle * sectionsNbr) / 180))));
		g2d.setColor(Color.BLACK);
		g2d.drawLine((int) (center.getX()), (int) (center.getY()), s1.x, s1.y);
		g2d.setColor(Color.WHITE);
		g2d.fillOval((int) (center.getX()-distMin),(int) (center.getY()-distMin), distMin*2, distMin*2);
		g2d.setColor(Color.BLACK);
		g2d.drawOval(0, 0, diameter_border - 1, diameter_border - 1);
		g2d.drawOval((int) (center.getX()-distMin),(int) (center.getY()-distMin), distMin*2, distMin*2);
	}

	private void addListener() {
		addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					identifySection(e.getPoint()).doClick(getX() + e.getX(), getY() + e.getY());
				}
				setVisible(false);
			}

			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseEntered(MouseEvent e) {
				if (Math.sqrt(Math.pow(e.getX(), 2) + Math.pow(e.getY(), 2)) < Math.pow(radius_border, 2)) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				} else {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}

		});
	}

	public MenuItem identifySection(Point clickPoint) {
		double angle;
		double dist = clickPoint.distance(center);
		int sectionsNbr;
		int index = -1;
		int taille;
		if (dist < distMin) {
			return null;
		}
		if (list.size() <= 8) {
			sectionsNbr = list.size();
			if (dist > radius_border) {
				return null;
			}
		} else {
			sectionsNbr = 8;
			if (dist > radius_border) {
				double px = clickPoint.getX();
				double cx = center.getX();
				double py = clickPoint.getY();
				double cy = center.getY();
				for (int i = 8; i < list.size(); i++) {
					taille = list.get(i).getLength() + 10;
					if ((cx - taille / 2) <= px && px <= (cx + taille / 2)
							&& (cy + radius_border + 5 + 45 * (i - 8)) <= py
							&& py <= (cy + radius_border + 5 + 45 * (i - 7))) {
						index = i;
					}
				}
				if (index == -1) {
					return null;
				} else {
					System.out.println(index);
					return list.get(index);
				}
			}
		}
		if ((clickPoint.getY() - center.getY()) < 0) {
			angle = Math.acos((clickPoint.getX() - center.getX()) / dist);
		} else {
			angle = 2 * Math.PI - Math.acos((clickPoint.getX() - center.getX()) / dist);
		}
		double arcAngle = 2 * Math.PI / sectionsNbr;
		angle = (angle + arcAngle / 2) % (2 * Math.PI);
		index = (int) (Math.floor(angle / arcAngle));
		return list.get(index);
	}

	public void handlePlacement(int width, int height, int eX, int eY) {
		int w = getWidth();
		int h = getHeight();
		int x = eX - w / 2;
		int y = eY - 3 * h / 4;
		if (x < 0) {
			x = 0;
		} else if (x + w > width) {
			x = width - w;
		}

		if (y < 0) {
			y = 0;
		} else if (y + 5*h/4 > height) {
			y = height - 5*h/4;
		}
		setBounds(x, y, w, h);
		setVisible(true);
	}

}

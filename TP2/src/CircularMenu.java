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

	private ArrayList<MenuItem> list;
	private Color color;
	private int radius_text;
	private int radius_border;
	private int diameter_border;
	private Point center;

	private void init(ArrayList<MenuItem> l, int rT) {
		list = l;
		radius_text = rT;
		radius_border = (int) (radius_text * 1.5);
		diameter_border = radius_border * 2;

		color = new Color(150, 150, 150);
		setSize(diameter_border, diameter_border);
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
			int xCentre = getWidth() / 2;
			int yCentre = getHeight() / 2;
			int taille = 0;

			if (list.size() <= 8) {
				angle = (double) (2 * Math.PI / list.size());
				for (int i = 0; i < list.size(); i++) {
					taille = list.get(i).getLength();
					list.get(i).setBounds((int) (xCentre + radius_text * Math.cos(i * angle) - taille / 2),
							(int) (getHeight() - (yCentre + radius_text * Math.sin(i * angle)) - 40 / 2), taille + 10,
							40);
					add(list.get(i));
				}
			} else {
				for (int i = 7; i >= 0; i--) {
					taille = list.get(i).getLength();
					list.get(i).setBounds(
							(int) (xCentre - taille / 2 + radius_text * Math.cos(i * angle - Math.PI / 2)),
							(int) (yCentre - 40 / 2 + radius_text * Math.sin(i * angle - Math.PI / 2)), taille + 10,
							40);
					add(list.get(i));
				}
				int shift = 1;
				for (int i = list.size() - 1; i > 7; i--, shift += 2) {
					taille = list.get(i).getLength();
					list.get(i).setBounds(
							(int) (xCentre - taille / 2 + radius_text * Math.cos(4 * angle - Math.PI / 2)),
							(int) (yCentre - 40 / 2 + radius_text * Math.sin(4 * angle - Math.PI / 2)) + taille
									+ (taille * shift),
							taille + 10, 40);
					add(list.get(i));
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Point s1;
		double arcAngle = 360 / list.size();
		double startAngle = arcAngle / 2;
		for (int i = 0; i < list.size(); i++) {
			s1 = new Point(
					(int) (center.getX() + radius_border * Math.cos(Math.PI * (startAngle + arcAngle * i) / 180)),
					(int) (diameter_border
							- (center.getY() + radius_border * Math.sin(Math.PI * (startAngle + arcAngle * i) / 180))));
			g2d.setColor(Color.RED);
			g2d.fillArc(0, 0, diameter_border, diameter_border, (int) ((startAngle + arcAngle * i) % 360),
					(int) arcAngle);
			g2d.setColor(Color.BLACK);
			g2d.drawLine((int) (center.getX()), (int) (center.getY()), s1.x, s1.y);
		}
		s1 = new Point(
				(int) (center.getX() + radius_border * Math.cos(Math.PI * (startAngle + arcAngle * list.size()) / 180)),
				(int) (diameter_border - (center.getY()
						+ radius_border * Math.sin(Math.PI * (startAngle + arcAngle * list.size()) / 180))));
		g2d.setColor(Color.BLACK);
		g2d.drawLine((int) (center.getX()), (int) (center.getY()), s1.x, s1.y);
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
		if (list.size() <= 8) {
			sectionsNbr = list.size();
		}
		else {
			sectionsNbr = 8;
		}
		if ((clickPoint.getY() - center.getY()) < 0) {
			angle = Math.acos((clickPoint.getX() - center.getX())/dist);
		}
		else {
			angle = 2*Math.PI - Math.acos((clickPoint.getX() - center.getX())/dist);
		}
		double arcAngle  = 2*Math.PI / sectionsNbr;
		angle = (angle + arcAngle/2)%(2*Math.PI);
		int index = (int) (Math.floor(angle/arcAngle));
		return list.get(index);
	}
	

}

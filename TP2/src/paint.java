//////////////////////////////////////////////////////////////////////////////
// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////

/* imports *****************************************************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/* paint *******************************************************************/

class paint extends JFrame {

	private static final long serialVersionUID = 1L;
	Vector<Shape> shapes = new Vector<Shape>();

	class Tool extends AbstractAction implements MouseInputListener {
		Point o;
		Shape shape;

		public Tool(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("using tool " + this);
			panel.removeMouseListener(tool);
			panel.removeMouseMotionListener(tool);
			tool = this;
			panel.addMouseListener(tool);
			panel.addMouseMotionListener(tool);
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			o = e.getPoint();
			menu.setVisible(false);

			if (e.getButton() == MouseEvent.BUTTON3) {
				int w = menu.getWidth();
				int h = menu.getHeight();
				int x = e.getX() - w / 2;
				int y = e.getY() - h / 2;
				if (x < 0) {
					x = 0;
				} else if (x + w > getWidth()) {
					x = getWidth() - w;
				}

				if (y < 0) {
					y = 0;
				} else if (y + h > getHeight()) {
					y = getHeight() - y;
				}
				menu.setBounds(x, y, w, h);
				menu.setVisible(true);
			}
		}

		public void mouseReleased(MouseEvent e) {
			shape = null;
		}

		public void mouseDragged(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}
	}

	Tool tools[] = { new Tool("pen") {
		public void mouseDragged(MouseEvent e) {
			if (!menu.isVisible()) {
				Path2D.Double path = (Path2D.Double) shape;
				if (path == null) {
					path = new Path2D.Double();
					path.moveTo(o.getX(), o.getY());
					shapes.add(shape = path);
					colors.add(current);
				}
				path.lineTo(e.getX(), e.getY());
				panel.repaint();
			}
		}
	}, new Tool("rect") {
		public void mouseDragged(MouseEvent e) {
			if (!menu.isVisible()) {
				Rectangle2D.Double rect = (Rectangle2D.Double) shape;
				if (rect == null) {
					rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
					shapes.add(shape = rect);
					colors.add(current);
				}
				rect.setRect(Math.min(e.getX(), o.getX()), Math.min(e.getY(), o.getY()), Math.abs(e.getX() - o.getX()),
						Math.abs(e.getY() - o.getY()));
				panel.repaint();
			}
		}
	}, new Tool("ellipse") {
		public void mouseDragged(MouseEvent e) {
			if (!menu.isVisible()) {
				Ellipse2D.Double ell = (Ellipse2D.Double) shape;
				if (ell == null) {
					ell = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
					shapes.add(shape = ell);
					colors.add(current);
				}
				ell.setFrame(Math.min(e.getX(), o.getX()), Math.min(e.getY(), o.getY()), Math.abs(e.getX() - o.getX()),
						Math.abs(e.getY() - o.getY()));
				panel.repaint();
			}
		}
	} };
	Tool tool;

	class ColorTool extends JButton implements MouseListener {
		Color color;

		public ColorTool(Color c) {
			super("    ");
			color = c;
			setBackground(c);
			addMouseListener(this);
		}

		public void mouseClicked(MouseEvent e) {
			System.out.println("using color " + color);
			current = color;
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

	ColorTool[] buttons = { new ColorTool(Color.BLACK), new ColorTool(Color.DARK_GRAY), new ColorTool(Color.GRAY),
			new ColorTool(Color.LIGHT_GRAY), new ColorTool(Color.YELLOW), new ColorTool(Color.ORANGE),
			new ColorTool(Color.RED), new ColorTool(Color.PINK), new ColorTool(Color.MAGENTA),
			new ColorTool(Color.BLUE), new ColorTool(Color.CYAN), new ColorTool(Color.GREEN) };

	ArrayList<Color> colors = new ArrayList<Color>();
	Color current = Color.BLACK;

	JPanel panel;
	CircularMenu menu;

	public paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));

		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		items.add(new MenuItem("Pen", tools[0]));
		items.add(new MenuItem("Black", buttons[0]));
		items.add(new MenuItem("Rect", tools[2]));
		items.add(new MenuItem("Ellipse", tools[1]));
		items.add(new MenuItem("Red", buttons[6]));
		items.add(new MenuItem("Green", buttons[11]));
		menu = new CircularMenu(items);
		menu.setVisible(false);
		add(menu, BorderLayout.CENTER);

		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				menu.setVisible(false);
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!menu.isVisible())
					if (e.getButton() == MouseEvent.BUTTON3) {
						int w = menu.getWidth();
						int h = menu.getHeight();
						int x = e.getX() - w / 2;
						int y = e.getY() - 3 * h / 4;
						if (x < 0) {
							x = 0;
						} else if (x + w > getWidth()) {
							x = getWidth() - w;
						}

						if (y < 0) {
							y = 0;
						} else if (y + h > getHeight()) {
							y = getHeight() - y;
						}
						menu.setBounds(x, y, w, h);
						menu.setVisible(true);
					}
			}
		});

		add(new JToolBar() {
			{
				for (AbstractAction tool : tools) {
					add(tool);
				}
			}
		}, BorderLayout.NORTH);
		add(new JToolBar() {
			{
				for (JButton b : buttons) {
					add(b);
				}
			}
		}, BorderLayout.SOUTH);
		add(panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(), getHeight());

				for (int i = 0; i < shapes.size(); i++) {
					g2.setColor(colors.get(i));
					g2.draw(shapes.get(i));
				}
			}
		});

		pack();
		setVisible(true);
	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				paint paint = new paint("paint");

			}
		});
	}
}

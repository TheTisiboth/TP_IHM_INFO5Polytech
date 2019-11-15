package circular;
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
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

/* paint *******************************************************************/

public class paintCircular extends JFrame {

	private static final long serialVersionUID = 1L;

	// ---------------------------------- //
	// | INITIALISATION FOR SHAPE TOOLS | //
	// ---------------------------------- //
	static Vector<Shape> shapes = new Vector<Shape>();

	ShapeTool tools[] = { new ShapeTool("pen"), new ShapeTool("rect"), new ShapeTool("ellipse") };
	static ShapeTool tool;

	public static void setTool(ShapeTool t) {
		tool = t;
	}

	// ---------------------------------- //
	// | INITIALISATION FOR COLOR TOOLS | //
	// ---------------------------------- //
	ColorTool[] buttons = { new ColorTool(Color.BLACK), new ColorTool(Color.DARK_GRAY), new ColorTool(Color.GRAY),
			new ColorTool(Color.LIGHT_GRAY), new ColorTool(Color.YELLOW), new ColorTool(Color.ORANGE),
			new ColorTool(Color.RED), new ColorTool(Color.PINK), new ColorTool(Color.MAGENTA),
			new ColorTool(Color.BLUE), new ColorTool(Color.CYAN), new ColorTool(Color.GREEN) };

	static ArrayList<Color> colors = new ArrayList<Color>();
	static Color current = Color.BLACK;

	public static void setCurrentColor(Color c) {
		current = c;
	}

	// ------------------------------------- //
	// | INITIALISATION FOR CIRCULAR MENUS | //
	// ------------------------------------- //
	JPanel panel;
	CircularMenu mainMenu, toolsMenu, colorsMenu;

	/**
	 * Create the whole menus, add them to the JFrame, and hide them
	 */
	private void initMenus() {
		MenuTool bTools = new MenuTool("Tools");
		MenuTool bColors = new MenuTool("Colors");

		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		items.add(new MenuItem("Tools", bTools));
		items.add(new MenuItem("Colors", bColors));
		mainMenu = new CircularMenu(items);
		mainMenu.setVisible(false);
		add(mainMenu, BorderLayout.CENTER);

		items = new ArrayList<MenuItem>();
		items.add(new MenuItem("Pen", tools[0]));
		items.add(new MenuItem("Rect", tools[1]));
		items.add(new MenuItem("Ellipse", tools[2]));
		toolsMenu = new CircularMenu(items);
		toolsMenu.setVisible(false);
		add(toolsMenu, BorderLayout.CENTER);

		items = new ArrayList<MenuItem>();
		items.add(new MenuItem("Black", buttons[0]));
		items.add(new MenuItem("Dark Gray", buttons[1]));
		items.add(new MenuItem("Gray", buttons[2]));
		items.add(new MenuItem("Light Gray", buttons[3]));
		items.add(new MenuItem("Yellow", buttons[4]));
		items.add(new MenuItem("Orange", buttons[5]));
		items.add(new MenuItem("Red", buttons[6]));
		items.add(new MenuItem("Pink", buttons[7]));
		items.add(new MenuItem("Magenta", buttons[8]));
		items.add(new MenuItem("Blue", buttons[9]));
		items.add(new MenuItem("Cyan", buttons[10]));
		items.add(new MenuItem("Green", buttons[11]));
		colorsMenu = new CircularMenu(items);
		colorsMenu.setVisible(false);
		add(colorsMenu, BorderLayout.CENTER);

		bTools.init(toolsMenu, getWidth(), getHeight());
		bColors.init(colorsMenu, getWidth(), getHeight());
	}

	// ---------------------------------- //
	// | MAIN LOGIC FOR OUR APPLICATION | //
	// ---------------------------------- //
	@SuppressWarnings("serial")
	public paintCircular(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1400, 1400));

		// Initialisation of the menus
		initMenus();

		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (tool != null) {
					// Create a mouse event to the shapeTool mouseListener
					MouseEvent me = new MouseEvent(tool, // which
							MouseEvent.MOUSE_RELEASED, // what
							System.currentTimeMillis(), // when
							0, // no modifiers
							e.getX(), e.getY(), // where: at (10, 10}
							1, // only 1 click
							false); // not a popup trigger
					// Send the mouse event
					tool.dispatchEvent(me);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// Hide the menus, no matter when we click
				mainMenu.setVisible(false);
				toolsMenu.setVisible(false);
				colorsMenu.setVisible(false);
				if (e.getButton() == MouseEvent.BUTTON3) {
					// If we right click, we have to show the menu, centered on our click
					mainMenu.handlePlacement(getWidth(), getHeight(), e.getX(), e.getY());
				}

				if (tool != null) {
					MouseEvent me = new MouseEvent(tool, // which
							MouseEvent.MOUSE_PRESSED, // what
							System.currentTimeMillis(), // when
							0, // no modifiers
							e.getX(), e.getY(), // where: at (10, 10}
							1, // only 1 click
							false); // not a popup trigger
					tool.dispatchEvent(me);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (tool != null) {
					// Draw of different shapes, depending on the tools
					switch (tool.type) {
					case "pen":
						Path2D.Double path = (Path2D.Double) tool.shape;
						if (path == null) {
							path = new Path2D.Double();
							path.moveTo(tool.o.getX(), tool.o.getY());
							shapes.add(tool.shape = path);
							colors.add(current);
						}
						path.lineTo(e.getX(), e.getY());
						repaint();
						break;
					case "rect":
						Rectangle2D.Double rect = (Rectangle2D.Double) tool.shape;
						;
						if (rect == null) {
							rect = new Rectangle2D.Double(tool.o.getX(), tool.o.getY(), 0, 0);
							shapes.add(tool.shape = rect);
							colors.add(current);
						}
						rect.setRect(Math.min(e.getX(), tool.o.getX()), Math.min(e.getY(), tool.o.getY()),
								Math.abs(e.getX() - tool.o.getX()), Math.abs(e.getY() - tool.o.getY()));
						repaint();

						break;
					case "ellipse":
						Ellipse2D.Double ell = (Ellipse2D.Double) tool.shape;
						;
						if (ell == null) {
							ell = new Ellipse2D.Double(tool.o.getX(), tool.o.getY(), 0, 0);
							shapes.add(tool.shape = ell);
							colors.add(current);
						}
						ell.setFrame(Math.min(e.getX(), tool.o.getX()), Math.min(e.getY(), tool.o.getY()),
								Math.abs(e.getX() - tool.o.getX()), Math.abs(e.getY() - tool.o.getY()));
						repaint();
						break;
					default:
						System.out.println("Error");
					}
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}
		});

//		add(new JToolBar() {
//			{
//				for (JButton tool : tools) {
//					add(tool);
//				}
//			}
//		}, BorderLayout.NORTH);
//		add(new JToolBar() {
//			{
//				for (JButton b : buttons) {
//					add(b);
//				}
//			}
//		}, BorderLayout.SOUTH);
//		add(panel = new JPanel() {
//			public void paintComponent(Graphics g) {
//				super.paintComponent(g);
//				Graphics2D g2 = (Graphics2D) g;
//				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//				g2.setColor(Color.WHITE);
//				g2.fillRect(0, 0, getWidth(), getHeight());
//
//				for (int i = 0; i < shapes.size(); i++) {
//					g2.setColor(colors.get(i));
//					g2.draw(shapes.get(i));
//				}
//			}
//		});

		pack();
		setVisible(true);
	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new paintCircular("paint");
			}
		});
	}
}

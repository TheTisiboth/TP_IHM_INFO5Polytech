import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

class ShapeTool extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;
	Point o;
	Shape shape;
	String type;

	public ShapeTool(String name) {
		super(name);
		type = name;
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("using tool " + type);
		paint.setTool(this);
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		o = e.getPoint();
	}

	public void mouseReleased(MouseEvent e) {
		shape = null;
	}
}
package marking;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import marking.paintMarking;

public class ShapeTool extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;
	public Point o;
	public Shape shape;
	public String type;

	public ShapeTool(String name) {
		super(name);
		type = name;
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("using tool " + type);
		paintMarking.setTool(this);
		paintMarking.setSelected("");
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
package marking;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class MenuTool extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;
	Color color;
	MarkingMenu subM;
	int width, height;
	String name;

	public MenuTool(String n) {
		super(n);
		name = n;
	}

	public void init(MarkingMenu s, int w, int h) {
		subM = s;
		width = w;
		height = h;
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		paintMarking.setSelected(name);
		subM.handlePlacement(width, height, e.getX(), e.getY());
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

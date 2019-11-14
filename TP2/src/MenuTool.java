import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

class MenuTool extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;
	Color color;
	CircularMenu sub;
	int width, height;

	public MenuTool(String name) {
		super(name);
	}

	public void init(CircularMenu s, int w, int h) {
		sub = s;
		width = w;
		height = h;
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		sub.handlePlacement(width, height, e.getX(), e.getY());
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

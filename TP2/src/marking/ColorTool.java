package marking;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import marking.paintMarking;

public class ColorTool extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;
	Color color;

	public ColorTool(Color c) {
		super("    ");
		color = c;
		setBackground(c);
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("using color " + color);
		paintMarking.setCurrentColor(color);
		paintMarking.setSelected("");
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
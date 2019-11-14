import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

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
			paint.setCurrentColor(color);
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
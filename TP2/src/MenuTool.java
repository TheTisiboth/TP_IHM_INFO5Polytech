import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

class MenuTool extends JButton implements MouseListener {
		Color color;
		CircularMenu main, sub;
		int width, height;

		public MenuTool(String name) {
			super(name);
		}

		public void init(CircularMenu m, CircularMenu s, int w, int h) {
			main = m;
			sub = s;
			width = w;
			height = h;
			addMouseListener(this);
		}
		
		public void mouseClicked(MouseEvent e) {
			System.out.println("ola");
			main.setVisible(false);
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

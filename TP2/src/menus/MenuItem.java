package menus;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuItem extends JPanel {

	private static final long serialVersionUID = 1L;
	private String text;
	private int length;
	private JButton tool = null;

	public MenuItem(String t, JButton tl) {
		tool = tl;
		init(t);
	}

	public void init(String t) {
		text = t;
		length = t.length() * 12;
		this.setSize(new Dimension(length + 10, length + 10));
		this.setOpaque(false);
	}

	public void doClick(int x, int y) {
		MouseEvent me = new MouseEvent(tool, // which
				MouseEvent.MOUSE_CLICKED, // what
				System.currentTimeMillis(), // when
				0, // no modifiers
				x, y, // where: at (10, 10}
				1, // only 1 click
				false); // not a popup trigger
		tool.dispatchEvent(me);
	}

	public void paint(Graphics arg0) {
		super.paint(arg0);

		Graphics2D g2d = (Graphics2D) arg0;

		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Calibri", Font.BOLD, 14));

		FontMetrics fm = g2d.getFontMetrics();

		int x = (this.getWidth() - fm.stringWidth(text)) / 2;
		int y = (fm.getAscent() + (this.getHeight() - (fm.getAscent() + fm.getDescent())) / 2);

		g2d.drawString(text, x, y);
	}

	public int getLength() {
		return length;
	}
}

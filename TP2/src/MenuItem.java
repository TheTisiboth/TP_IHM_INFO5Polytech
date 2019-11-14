import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuItem extends JPanel {

	private static final long serialVersionUID = 1L;
	private Color couleur;
	private String text;
	private int length;
	private AbstractAction tool = null;
	private JButton colorTool = null;
	private String type;
	
	public MenuItem(String t, AbstractAction tl) {
		tool = tl;
		init(t);
	}
	
	public MenuItem(String t, JButton ctl) {
		colorTool = ctl;
		init(t);
	}
	
	public void init(String t) {
		couleur = new Color(255, 255, 255);
		text = t;
		length = t.length() * 12;
		this.setSize(new Dimension(length + 10, length + 10));
		this.setOpaque(false);
	}

	public void doClick() {
		if (tool == null) {
			colorTool.doClick();
		} else {
			tool.actionPerformed(null);;
		}
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
		// TODO Auto-generated method stub
		return length;
	}
}

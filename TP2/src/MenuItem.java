import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MenuItem extends JPanel {
	
	private Color couleur;
	private String text;
	private int length;
	
	public MenuItem(String t) {
		couleur = new Color(255, 255, 255);
		text = t;
		length = t.length()*12;
		this.setSize(new Dimension(length + 10, length + 10));
		this.setOpaque(false);
		addListener();
	}	
	
	private void addListener() {
		addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
//				couleur = couleurNormal;
//				repaint();
			}

			public void mouseEntered(MouseEvent e) {
//				couleur = couleurActif;
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				repaint();

			}

		});
	}
	
	public void paint(Graphics arg0) {
		super.paint(arg0);
		
		Graphics2D g2d = (Graphics2D) arg0;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(couleur);
		g2d.setStroke(new BasicStroke(3));
		
//		GradientPaint gradient = new GradientPaint(length / 2, 4, couleur, (length) / 2, 4 + length,
//				new Color(255, 255, 255, 200));
//		
//		g2d.setPaint(gradient);
		g2d.fillRect(4, 4, length, length);
		
//		gradient = new GradientPaint(length / 2, 4, Color.white, length / 2, 4 + length / 2,
//				new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 0));
//		
//		g2d.setPaint(gradient);
		g2d.fillRect(4 + length / 5, 4, 5 * length / 8, length / 3);
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

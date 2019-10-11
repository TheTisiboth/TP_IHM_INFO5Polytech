package mvc.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicSliderUI;

import mvc.Model.RangeSlider;

public class RangeSliderUI extends BasicSliderUI {
	private Rectangle secondThumbRect;

	public RangeSliderUI(JSlider slider) {
		super(slider);
	}

	@Override
	public void installUI(JComponent c) {
		secondThumbRect = new Rectangle();

		super.installUI(c);
	}

	@Override
	public void uninstallUI(JComponent c) {
		secondThumbRect = null;

		super.uninstallUI(c);
	}

	protected RangeTrackListener createRangeTrackListener() {
		return new RangeTrackListener();
	}

	@Override
	protected void calculateThumbSize() {
		super.calculateThumbSize();

		secondThumbRect.setSize(getThumbSize());
	}

	@Override
	protected void calculateThumbLocation() {
		super.calculateThumbLocation();

		int pos = xPositionForValue(((RangeSlider) slider).getUpperValue());
		secondThumbRect.x = pos - secondThumbRect.width / 2;
		secondThumbRect.y = trackRect.y;
	}

	@Override
	public void paintThumb(Graphics g) {
		super.paintThumb(g);

		Rectangle knobBounds = secondThumbRect;
		int w = knobBounds.width;
		int h = knobBounds.height;

		g.translate(knobBounds.x, knobBounds.y);

		if (slider.isEnabled())
			g.setColor(slider.getBackground());
		else
			g.setColor(slider.getBackground().darker());

		g.fillRect(0, 0, w, h);

		g.setColor(Color.black);
		g.drawLine(0, h - 1, w - 1, h - 1);
		g.drawLine(w - 1, 0, w - 1, h - 1);

		g.setColor(getHighlightColor());
		g.drawLine(0, 0, 0, h - 2);
		g.drawLine(1, 0, w - 2, 0);

		g.setColor(getShadowColor());
		g.drawLine(1, h - 2, w - 2, h - 2);
		g.drawLine(w - 2, 1, w - 2, h - 3);

		g.translate(-knobBounds.x, -knobBounds.y);
	}
	
	private static Rectangle unionRect = new Rectangle();

	public void setSecondThumbLocation(int x, int y) {
		unionRect.setBounds(secondThumbRect);

		secondThumbRect.setLocation(x, y);

		SwingUtilities.computeUnion(secondThumbRect.x, secondThumbRect.y, secondThumbRect.width, secondThumbRect.height, unionRect);
		slider.repaint(unionRect.x, unionRect.y, unionRect.width, unionRect.height);
	}
	
	public class RangeTrackListener extends TrackListener {

	}

}

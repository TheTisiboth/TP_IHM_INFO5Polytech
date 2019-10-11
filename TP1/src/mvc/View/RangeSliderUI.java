package mvc.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSliderUI;

import mvc.Model.RangeSlider;

public class RangeSliderUI extends BasicSliderUI {
	private Rectangle secondThumbRect;
	private Rectangle unionRect;

	public RangeSliderUI(JSlider slider) {
		super(slider);
	}

	public static ComponentUI createUI(JComponent b) {
		return new RangeSliderUI((JSlider) b);
	}

	@Override
	public void installUI(JComponent c) {
		secondThumbRect = new Rectangle();
		unionRect = new Rectangle();
		super.installUI(c);
	}

	@Override
	public void uninstallUI(JComponent c) {
		secondThumbRect = null;
		unionRect = null;

		super.uninstallUI(c);
	}

	protected RangeTrackListener createRangeTrackListener() {
		return new RangeTrackListener();
	}

	@Override
	protected void calculateThumbSize() {
		super.calculateThumbSize();
		secondThumbRect.setSize(getThumbSize());
		thumbRect.setSize(getThumbSize());
	}

	@Override
	protected void calculateThumbLocation() {
		super.calculateThumbLocation();

		int pos = xPositionForValue(((RangeSlider) slider).getUpperValue());
		secondThumbRect.x = pos - secondThumbRect.width / 2;
		secondThumbRect.y = trackRect.y;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);

		Rectangle clip = g.getClipBounds();

		if (clip.intersects(secondThumbRect))
			paintThumb(g);
	}

	@Override
	public void paintThumb(Graphics g) {
		super.paintThumb(g);

		Rectangle knobBounds = thumbRect;
		subPaintThumb(g, knobBounds);

		knobBounds = secondThumbRect;
		subPaintThumb(g, knobBounds);
	}

	private void subPaintThumb(Graphics g, Rectangle knobBounds) {
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

	public void setSecondThumbLocation(int x, int y) {
		unionRect.setBounds(secondThumbRect);

		secondThumbRect.setLocation(x, y);

		SwingUtilities.computeUnion(secondThumbRect.x, secondThumbRect.y, secondThumbRect.width, secondThumbRect.height,
				unionRect);
		slider.repaint(unionRect.x, unionRect.y, unionRect.width, unionRect.height);
	}

	public class RangeTrackListener extends TrackListener {
		private boolean lowerDrag, upperDrag;

		public RangeTrackListener() {
			super();

			lowerDrag = false;
			upperDrag = false;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (!slider.isEnabled())
				return;

			offset = 0;
			scrollTimer.stop();

			lowerDrag = false;
			upperDrag = false;

			slider.setValueIsAdjusting(false);
			slider.repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (!slider.isEnabled())
				return;

			calculateGeometry();

			currentMouseX = e.getX();
			currentMouseY = e.getY();

			if (slider.isRequestFocusEnabled()) {
				slider.requestFocus();
			}

			if (thumbRect.contains(currentMouseX, currentMouseY)) {
				if (UIManager.getBoolean("Slider.onlyLeftMouseButtonDrag") && !SwingUtilities.isLeftMouseButton(e))
					return;

				offset = currentMouseX - thumbRect.x;
				lowerDrag = true;
				upperDrag = false;

				return;
			}

			if (secondThumbRect.contains(currentMouseX, currentMouseY)) {
				if (UIManager.getBoolean("Slider.onlyLeftMouseButtonDrag") && !SwingUtilities.isLeftMouseButton(e))
					return;

				offset = currentMouseX - secondThumbRect.x;
				lowerDrag = false;
				upperDrag = true;

				return;
			}

			if (!SwingUtilities.isLeftMouseButton(e))
				return;

			lowerDrag = false;
			upperDrag = false;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int thumbsMid;

			if (!slider.isEnabled())
				return;

			currentMouseX = e.getX();
			currentMouseY = e.getY();

			if (lowerDrag) {
				slider.setValueIsAdjusting(true);

				int halfThumbWidth = thumbRect.width / 2;
				int thumbLeft = e.getX() - offset;
				int trackLeft = trackRect.x;
				int trackRight = trackRect.x + trackRect.width - 1;
				int hMax = xPositionForValue(slider.getMaximum() - slider.getExtent());

				if (drawInverted())
					trackLeft = hMax;
				else
					trackRight = hMax;
				thumbLeft = Math.max(thumbLeft, trackLeft - halfThumbWidth);
				thumbLeft = Math.min(thumbLeft, trackRight - halfThumbWidth); // ca n'a pas de sens

				thumbsMid = thumbLeft + halfThumbWidth;
				int thumbRight = thumbLeft + thumbRect.width;

				if (valueForXPosition(thumbRight) < ((RangeSlider) slider).getUpperValue()) {
					setThumbLocation(thumbLeft, thumbRect.y);
					slider.setValue(valueForXPosition(thumbsMid));
				}
			}

			if (upperDrag) {
				slider.setValueIsAdjusting(true);

				int halfThumbWidth = secondThumbRect.width / 2;
				int thumbLeft = e.getX() - offset;
				int trackLeft = trackRect.x;
				int trackRight = trackRect.x + trackRect.width - 1;
				int hMax = xPositionForValue(slider.getMaximum() - ((RangeSlider) slider).getUpperExtent());

				if (drawInverted())
					trackLeft = hMax;
				else
					trackRight = hMax;
				thumbLeft = Math.max(thumbLeft, trackLeft - halfThumbWidth);
				thumbLeft = Math.min(thumbLeft, trackRight - halfThumbWidth); // ca n'a pas de sens

				thumbsMid = thumbLeft + halfThumbWidth;

				if (valueForXPosition(thumbLeft) > slider.getValue()) {
					setSecondThumbLocation(thumbLeft, secondThumbRect.y);
					((RangeSlider) slider).setUpperValue(valueForXPosition(thumbsMid));
				}
			}
		}
	}

}

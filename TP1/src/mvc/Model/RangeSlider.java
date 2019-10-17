package mvc.Model;

import javax.swing.JSlider;
import javax.swing.SwingConstants;

import mvc.View.RangeSliderUI;

@SuppressWarnings("serial")
/**
 * This is the widget that implements the Jslider, with a second slider
 * 
 * @author leo
 *
 */
public class RangeSlider extends JSlider {

	public static String ACCESSIBLE_UPPER_VALUE_PROPERTY = "AccessibleUpperValue";

	/**
	 * Creates a slider with the specified minimum, maximum, and initial values.
	 * 
	 * @param min
	 *            the minimum value of the slider
	 * @param max
	 *            the maximum value of the slider
	 * @param lowVal
	 *            the initial lower value of the slider
	 * @param upVal
	 *            the initial upper value of the slider
	 */
	public RangeSlider(int min, int max, int lowVal, int upVal) {
		this.orientation = SwingConstants.HORIZONTAL;

		setModel(new DefaultBoundedRangeSliderModel(lowVal, 0, upVal, 0, min, max));
		updateUI();
	}

	/**
	 * Creates a slider with the specified minimum, maximum, and sets the lowerValue
	 * to min, and the upperValue to max.
	 * 
	 * @param min
	 *            the minimum value of the slider
	 * @param max
	 *            the maximum value of the slider
	 */
	public RangeSlider(int min, int max) {
		this.orientation = SwingConstants.HORIZONTAL;

		setModel(new DefaultBoundedRangeSliderModel(min, 0, max, 0, min, max));
		updateUI();
	}

	/**
	 * Creates a slider with default values:
	 * 
	 * min = 0 max = 100 lowerValue = 0 upperValue = 100
	 */
	public RangeSlider() {
		this.orientation = SwingConstants.HORIZONTAL;

		setModel(new DefaultBoundedRangeSliderModel());
		updateUI();
	}

	@Override
	public void updateUI() {
		setUI(new RangeSliderUI(this));
		updateLabelUIs();
	}

	public int getUpperValue() {
		if (getModel().getClass() != DefaultBoundedRangeSliderModel.class)
			return 0;
		return (((BoundedRangeSliderModel) getModel()).getUpperValue());
	}

	public int getUpperExtent() {
		return (((BoundedRangeSliderModel) getModel()).getUpperExtent());
	}

	/**
	 * Sets the slider's current upper value to n. This method forwards the new
	 * value to the model.
	 * 
	 * The data model (an instance of BoundedRangeSliderModel) handles any
	 * mathematical issues arising from assigning faulty values. See the
	 * BoundedRangeSliderModel documentation for details.
	 * 
	 * If the new value is different from the previous value, all change listeners
	 * are notified.
	 *
	 * @param n
	 *            the new value
	 */
	public void setUpperValue(int newVal) {
		BoundedRangeSliderModel m = (BoundedRangeSliderModel) getModel();
		int oldVal = m.getUpperValue();

		if (oldVal == newVal) {
			return;
		}
		m.setUpperValue(newVal);

		if (accessibleContext != null) {
			accessibleContext.firePropertyChange(RangeSlider.ACCESSIBLE_UPPER_VALUE_PROPERTY, Integer.valueOf(oldVal),
					Integer.valueOf(m.getUpperValue()));
		}
	}
}

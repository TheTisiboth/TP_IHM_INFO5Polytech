package mvc.Model;

import java.io.Serializable;
import java.util.EventListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

@SuppressWarnings("serial") // Same-version serialization only
/**
 * Implementation of the BoundedRangeSliderModel, that is the Model of the Range Slider
 * 
 * @author Eva Leo Xavier
 *
 */
public class DefaultBoundedRangeSliderModel implements BoundedRangeSliderModel, Serializable {

	protected transient ChangeEvent changeEvent = null;

	protected EventListenerList listenerList = new EventListenerList();

	private int lowerValue; // Value of the first cursor
	private int upperValue; // Value of the second cursor, and so one
	private int lowerExtent;
	private int upperExtent;
	private int min;
	private int max;
	private boolean isAdjusting = false;

	/**
	 * Initializes all of the properties with default values.
	 */
	public DefaultBoundedRangeSliderModel() {
		lowerValue = 0;
		min = 0;
		lowerExtent = 0;
		upperValue = 100;
		upperExtent = 0;
		max = 100;
	}

	/**
	 * Verify that the inputs are correct, and initialize the variables.
	 * 
	 * @param lowVal
	 *            an int giving the current lower value
	 * @param lowExt
	 *            the length of the inner range that begins at the model's lower
	 *            value
	 * @param upVal
	 *            an int giving the current upper value
	 * @param upExt
	 *            the length of the inner range that begins at the model's upper
	 *            value
	 * @param min
	 *            an int giving the minimum value
	 * @param max
	 *            an int giving the maximum value
	 */
	public DefaultBoundedRangeSliderModel(int lowVal, int lowExt, int upVal, int upExt, int min, int max) {

		if (max >= min && lowVal >= min && lowVal + lowExt >= lowVal && lowVal + lowExt <= upVal
				&& upVal + lowExt >= upVal && upVal + upExt <= max) {
			this.lowerValue = lowVal;
			this.upperValue = upVal;
			this.lowerExtent = lowExt;
			this.upperExtent = upExt;
			this.min = min;
			this.max = max;
		} else {
			throw new IllegalArgumentException("invalid range properties");
		}
	}

	public int getLowerValue() {
		return lowerValue;
	}

	public int getUpperValue() {
		return upperValue;
	}

	public int getLowerExtent() {
		return lowerExtent;
	}

	public int getUpperExtent() {
		return upperExtent;
	}

	public int getMinimum() {
		return min;
	}

	public int getMaximum() {
		return max;
	}

	/**
	 * Sets the current lower value of the model. For a slider, that determines
	 * where the knob appears. Ensures that the new value, lowVal falls within the
	 * model's constraints:
	 * 
	 * minimum <= lowVal <= lowVal + lowerExtent <= upperValue
	 */
	public void setLowerValue(int lowVal) {
		lowVal = Math.min(lowVal, Integer.MAX_VALUE - lowerExtent);

		int newValue = Math.max(lowVal, min);
		if (newValue + lowerExtent > upperValue) {
			newValue = upperValue - lowerExtent;
		}
		setRangeProperties(newValue, lowerExtent, upperValue, upperExtent, min, max, isAdjusting);
	}

	/**
	 * Sets the current upper value of the model. For a slider, that determines
	 * where the knob appears. Ensures that the new value, upVal falls within the
	 * model's constraints:
	 * 
	 * lowVal <= upVal <= upVal + upperExtent <= maximum
	 */
	public void setUpperValue(int upVal) {
		upVal = Math.min(upVal, Integer.MAX_VALUE - upperExtent);

		int newValue = Math.max(upVal, min);
		if (newValue + upperExtent > max) {
			newValue = max - upperExtent;
		}
		setRangeProperties(lowerValue, lowerExtent, newValue, upperExtent, min, max, isAdjusting);
	}

	/**
	 * Sets the extent to lowExt after ensuring that lowExt is greater than or equal
	 * to zero and falls within the model's constraints:
	 * 
	 * minimum <= lowerValue <= lowerValue + lowExt <= maximum
	 */
	public void setLowerExtent(int lowExt) {
		int newExtent = Math.max(0, lowExt);

		if (lowerValue + newExtent > upperValue) {
			newExtent = upperValue - lowerValue;
		}
		setRangeProperties(lowerValue, newExtent, upperValue, upperExtent, min, max, isAdjusting);
	}

	/**
	 * Sets the extent to upExt after ensuring that upExt is greater than or equal
	 * to zero and falls within the model's constraints:
	 * 
	 * lowVal <= upVal <= upperValue + upExt <= maximum
	 */
	public void setUpperExtent(int upExt) {
		int newExtent = Math.max(0, upExt);

		if (upperValue + newExtent > max) {
			newExtent = max - upperValue;
		}
		setRangeProperties(lowerValue, lowerExtent, upperValue, newExtent, min, max, isAdjusting);
	}

	/**
	 * Sets the minimum to n after ensuring that n that the other three properties
	 * obey the model's constraints:
	 * 
	 * minimum <= value <= value + extent <= maximum
	 * 
	 */
	public void setMinimum(int n) {
		int newMax = Math.max(n, max);
		int newLowerValue = Math.max(n, lowerValue);
		int newUpperValue = Math.max(n, upperValue);
		int newLowerExtent = Math.min(newUpperValue - newLowerValue, lowerExtent);
		int newUpperExtent = Math.min(newMax - newUpperValue, upperExtent);

		setRangeProperties(newLowerValue, newLowerExtent, newUpperValue, newUpperExtent, n, newMax, isAdjusting);
	}

	/**
	 * Sets the maximum to n after ensuring that n that the other three properties
	 * obey the model's constraints:
	 * 
	 * minimum &lt;= value &lt;= value + extent &lt;= maximum
	 * 
	 */
	public void setMaximum(int n) {
		int newMin = Math.min(n, min);
		int newLowerExtent = Math.min(n - newMin, lowerExtent);
		int newLowerValue = Math.min(n - newLowerExtent, lowerValue);
		int newUpperExtent = Math.min(n - newLowerValue, upperExtent);
		int newUpperValue = Math.min(n - newUpperExtent, upperValue);

		setRangeProperties(newLowerValue, newLowerExtent, newUpperValue, newUpperExtent, newMin, n, isAdjusting);
	}

	public void setValueIsAdjusting(boolean b) {
		setRangeProperties(lowerValue, lowerExtent, upperValue, upperExtent, min, max, b);
	}

	public boolean getValueIsAdjusting() {
		return isAdjusting;
	}

	/**
	 * Sets all of the BoundedRangeSliderModel properties after forcing the
	 * arguments to obey the usual constraints:
	 *
	 * minimum &lt;= value &lt;= value+extent &lt;= maximum
	 *
	 * At most, one ChangeEvent is generated.
	 */
	@Override
	public void setRangeProperties(int newLowerValue, int newLowerExtent, int newUpperValue, int newUpperExtent,
			int newMin, int newMax, boolean adjusting) {

		if (newMin > newMax)
			newMin = newMax;
		if (newLowerValue > newUpperValue)
			newUpperValue = newLowerValue;
		if (newUpperValue > newMax)
			newMax = newUpperValue;
		if (newLowerValue < newMin)
			newMin = newLowerValue;

		if ((long) newLowerExtent + (long) newLowerValue > newUpperValue)
			newLowerExtent = newUpperValue - newLowerValue;
		if ((long) newUpperExtent + (long) newUpperValue > newMax)
			newUpperExtent = newMax - newUpperValue;

		if (newLowerExtent < 0)
			newLowerExtent = 0;
		if (newUpperExtent < 0)
			newUpperExtent = 0;

		boolean isChange = newLowerValue != lowerValue || newLowerExtent != lowerExtent || newUpperValue != upperValue
				|| newUpperExtent != upperExtent || newMin != min || newMax != max || adjusting != isAdjusting;

		if (isChange) {
			lowerValue = newLowerValue;
			lowerExtent = newLowerExtent;
			upperValue = newUpperValue;
			upperExtent = newUpperExtent;
			min = newMin;
			max = newMax;
			isAdjusting = adjusting;

			fireStateChanged();
		}
	}

	public void addChangeListener(ChangeListener l) {
		listenerList.add(ChangeListener.class, l);
	}

	public void removeChangeListener(ChangeListener l) {
		listenerList.remove(ChangeListener.class, l);
	}

	public ChangeListener[] getChangeListeners() {
		return listenerList.getListeners(ChangeListener.class);
	}

	protected void fireStateChanged() {
		Object[] listeners = listenerList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ChangeListener.class) {
				if (changeEvent == null) {
					changeEvent = new ChangeEvent(this);
				}
				((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
			}
		}
	}

	/**
	 * Returns a string that displays all of the
	 * <code>BoundedRangeSliderModel</code> properties.
	 */
	public String toString() {
		String modelString = "lowerValue=" + getLowerValue() + ", " + "lowerExtent=" + getLowerExtent() + ", "
				+ "upperValue=" + getUpperValue() + ", " + "upperExtent=" + getUpperExtent() + ", " + "min="
				+ getMinimum() + ", " + "max=" + getMaximum() + ", " + "adj=" + getValueIsAdjusting();

		return getClass().getName() + "[" + modelString + "]";
	}

	public <T extends EventListener> T[] getListeners(Class<T> listenerType) {
		return listenerList.getListeners(listenerType);
	}

	@Override
	public int getValue() {
		return getLowerValue();
	}

	@Override
	public void setValue(int newValue) {
		setLowerValue(newValue);

	}

	@Override
	public int getExtent() {
		return getLowerExtent();
	}

	@Override
	public void setExtent(int newExtent) {
		setLowerExtent(newExtent);
	}

	@Override
	public void setRangeProperties(int value, int extent, int min, int max, boolean adjusting) {
		// TODO Auto-generated method stub

	}

}

package mvc.Model;

import java.io.Serializable;
import java.util.EventListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

@SuppressWarnings("serial") // Same-version serialization only
public class DefaultBoundedRangeSliderModel implements BoundedRangeSliderModel, Serializable {

	protected transient ChangeEvent changeEvent = null;

	protected EventListenerList listenerList = new EventListenerList();

	private int lowerValue;
	private int upperValue;
	private int lowerExtent;
	private int upperExtent;
	private int min;
	private int max;
	private boolean isAdjusting = false;

	public DefaultBoundedRangeSliderModel() {
		lowerValue = 0;
		min = 0;
		lowerExtent = 0;
		upperValue = 100;
		upperExtent = 0;
		max = 100;
	}

	public DefaultBoundedRangeSliderModel(int lowVal, int lowExt, int upVal, int upExt, int min, int max) {

		if (max >= min && lowerValue >= min && lowerValue + lowerExtent >= lowerValue
				&& lowerValue + lowerExtent <= upperValue && upperValue + lowerExtent >= upperValue
				&& upperValue + upperExtent <= max) {
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

	public void setLowerValue(int lowVal) {
		lowVal = Math.min(lowVal, Integer.MAX_VALUE - lowerExtent);

		int newValue = Math.max(lowVal, min);
		if (newValue + lowerExtent > upperValue) {
			newValue = upperValue - lowerExtent;
		}
		setRangeProperties(newValue, lowerExtent, upperValue, upperExtent, min, max, isAdjusting);
	}

	public void setUpperValue(int upVal) {
		upVal = Math.min(upVal, Integer.MAX_VALUE - upperExtent);

		int newValue = Math.max(upVal, min);
		if (newValue + upperExtent > max) {
			newValue = max - upperExtent;
		}
		setRangeProperties(lowerValue, lowerExtent, newValue, upperExtent, min, max, isAdjusting);
	}

	public void setLowerExtent(int lowExt) {
		int newExtent = Math.max(0, lowExt);

		if (lowerValue + newExtent > upperValue) {
			newExtent = upperValue - lowerValue;
		}
		setRangeProperties(lowerValue, newExtent, upperValue, upperExtent, min, max, isAdjusting);
	}

	public void setUpperExtent(int upExt) {
		int newExtent = Math.max(0, upExt);

		if (upperValue + newExtent > max) {
			newExtent = max - upperValue;
		}
		setRangeProperties(lowerValue, lowerExtent, upperValue, newExtent, min, max, isAdjusting);
	}

	public void setMinimum(int n) {
		int newMax = Math.max(n, max);
		int newLowerValue = Math.max(n, lowerValue);
		int newUpperValue = Math.max(n, upperValue);
		int newLowerExtent = Math.min(newUpperValue - newLowerValue, lowerExtent);
		int newUpperExtent = Math.min(newMax - newUpperValue, upperExtent);

		setRangeProperties(newLowerValue, newLowerExtent, newUpperValue, newUpperExtent, n, newMax, isAdjusting);
	}

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

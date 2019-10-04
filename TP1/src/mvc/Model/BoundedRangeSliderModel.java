package mvc.Model;

import java.util.EventListener;

import javax.swing.event.ChangeListener;

public interface BoundedRangeSliderModel {
	public int getLowerValue();

	public int getUpperValue();

	public int getLowerExtent();

	public int getUpperExtent();

	public int getMinimum();
	
	public int getMaximum();

	public void setLowerValue(int lowVal);

	public void setUpperValue(int upVal);

	public void setLowerExtent(int lowExt);

	public void setUpperExtent(int upExt);

	public void setMinimum(int n);

	public void setMaximum(int n);

	public void setValueIsAdjusting(boolean b);

	public boolean getValueIsAdjusting();

	public void setRangeProperties(int newLowerValue, int newLowerExtent, int newUpperValue, int newUpperExtent,
			int newMin, int newMax, boolean adjusting);

	public void addChangeListener(ChangeListener l);

	public void removeChangeListener(ChangeListener l);
	
	public ChangeListener[] getChangeListeners();

	public <T extends EventListener> T[] getListeners(Class<T> listenerType);
}

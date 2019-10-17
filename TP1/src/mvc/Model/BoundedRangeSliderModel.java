package mvc.Model;

import java.util.EventListener;

import javax.swing.BoundedRangeModel;
import javax.swing.event.ChangeListener;

public interface BoundedRangeSliderModel extends BoundedRangeModel {
	
	public int getLowerValue();

	public int getUpperValue();

	public int getLowerExtent();

	public int getUpperExtent();

	@Override
	public int getMinimum();
	
	@Override
	public int getMaximum();

	public void setLowerValue(int lowVal);

	public void setUpperValue(int upVal);

	public void setLowerExtent(int lowExt);

	public void setUpperExtent(int upExt);

	@Override
	public void setMinimum(int n);
	
	@Override
	public void setMaximum(int n);

	@Override
	public void setValueIsAdjusting(boolean b);

	@Override
	public boolean getValueIsAdjusting();

	public void setRangeProperties(int newLowerValue, int newLowerExtent, int newUpperValue, int newUpperExtent,
			int newMin, int newMax, boolean adjusting);

	@Override
	public void addChangeListener(ChangeListener l);

	@Override
	public void removeChangeListener(ChangeListener l);
	
	public ChangeListener[] getChangeListeners();

	public <T extends EventListener> T[] getListeners(Class<T> listenerType);
}

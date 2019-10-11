package mvc.Model;

import javax.swing.BoundedRangeModel;
import javax.swing.JSlider;

import mvc.View.RangeSliderUI;

@SuppressWarnings("serial")
public class RangeSlider extends JSlider {
	
    public static String ACCESSIBLE_UPPER_VALUE_PROPERTY = "AccessibleUpperValue";
	
	public RangeSlider(int min, int max, int lowVal, int lowExt, int upVal, int upExt) {
		setModel((BoundedRangeModel) new DefaultBoundedRangeSliderModel(lowVal, 0, upVal, 0, min, max));
		updateUI();
	}
	
	@Override
	public void updateUI() {
		setUI(new RangeSliderUI(this));
		updateLabelUIs();
	}
	
	public int getUpperValue() {
		return (((BoundedRangeSliderModel) getModel()).getUpperValue());
	}
	
	public int getUpperExtent() {
		return (((BoundedRangeSliderModel) getModel()).getUpperExtent());
	}
	
	public void setUpperValue(int newVal) {
        BoundedRangeSliderModel m = (BoundedRangeSliderModel) getModel();
        int oldVal = m.getUpperValue();
        
        if (oldVal == newVal) {
        	return;
        }
        m.setUpperValue(newVal);
        
        if (accessibleContext != null) {
        	accessibleContext.firePropertyChange(RangeSlider.ACCESSIBLE_UPPER_VALUE_PROPERTY, oldVal, m.getUpperValue());
        }
	}
}

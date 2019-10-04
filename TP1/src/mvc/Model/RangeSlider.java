package mvc.Model;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JSlider;

public class RangeSlider extends JSlider {
	public RangeSlider(int min, int max, int value1, int value2) {

        setModel(new DefaultBoundedRangeSliderModel(value1, value2, 0, min, max));
        updateUI();
	}
}

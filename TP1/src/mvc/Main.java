package mvc;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {

	public static void main(String[] args) {
		JSlider slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {

         public void stateChanged(ChangeEvent e) {

            // the source will be 

            // the slider this time..

            JSlider s = (JSlider)e.getSource();

            System.out.println("value changed: " +

               s.getValue());

         }
        });

	}

}

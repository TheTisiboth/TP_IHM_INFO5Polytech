package mvc;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import mvc.Model.RangeSlider;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame("TP1 - Range Slider");
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500,500));
		window.add(panel);
		
		RangeSlider slider = new RangeSlider(0, 300, 100, 250);
		panel.add(slider);
		
		window.pack();
		window.setVisible(true);
	}

}

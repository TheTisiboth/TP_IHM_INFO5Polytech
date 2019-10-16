package HomeFinder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mvc.Model.RangeSlider;

public class Main {

	public static void main(String[] args) {
		SliderTestFrame frame = new SliderTestFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}

class SliderTestFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChangeListener listenerRoom;
	private ChangeListener listenerPrice;
	private JTextField tfRooms;
	private JTextField tfPrice;
	JPanel mainPanel;
	
	public SliderTestFrame() {
		setTitle("TP1 - Home Finder");

		// common listener for all sliders
		listenerRoom = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// update text field when the slider value changes
				RangeSlider source = (RangeSlider) event.getSource();
				tfRooms.setText("" + source.getValue() + " <->" + source.getUpperValue());
			}
		};
		
		listenerPrice= new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// update text field when the slider value changes
				RangeSlider source = (RangeSlider) event.getSource();
				tfPrice.setText("" + source.getValue()+ " <->" + source.getUpperValue());
			}
		};

		mainPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(2,2));
		mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		add(mainPanel);

		tfRooms = new JTextField();
		tfPrice = new JTextField();
		RangeSlider roomSlider = new RangeSlider();
		addSliderPrice(roomSlider,"Room Slider");
		RangeSlider priceSlider = new RangeSlider();
		addSliderRoom(priceSlider,"Price Slider");
		rightPanel.add(roomSlider, BorderLayout.NORTH);
		
		rightPanel.add(tfRooms);
		
		rightPanel.add(priceSlider, BorderLayout.CENTER);
		rightPanel.add(tfPrice);
		mainPanel.add(rightPanel, BorderLayout.EAST);

		pack();
		setVisible(true);
	}

	public void addSliderPrice(JSlider s, String description) {
		s.addChangeListener(listenerPrice);
		JPanel panel = new JPanel();
		panel.add(s);
		panel.add(new JLabel(description));
		mainPanel.add(panel);
	}
	
	public void addSliderRoom(JSlider s, String description) {
		s.addChangeListener(listenerRoom);
		JPanel panel = new JPanel();
		panel.add(s);
		panel.add(new JLabel(description));
		mainPanel.add(panel);
	}
}

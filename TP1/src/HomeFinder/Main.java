package HomeFinder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mvc.Model.RangeSlider;

public class Main {

	public static void main(String[] args) {
		SliderTestFrame frame = new SliderTestFrame();

	}

}

class SliderTestFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChangeListener listenerRoom;
	private ChangeListener listenerPrice;
	private JLabel lowPrice = new JLabel();
	private JLabel upPrice = new JLabel();
	private JLabel lowRoom = new JLabel();
	private JLabel upRoom = new JLabel();
	JPanel centerPanel;
	JPanel rightPanel;
	RangeSlider roomSlider;
	RangeSlider priceSlider;
	
	public SliderTestFrame() {
		setTitle("TP1 - Home Finder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		// common listener for all sliders
		listenerRoom = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// update text field when the slider value changes
				RangeSlider source = (RangeSlider) event.getSource();
				lowRoom.setText("Low: " + source.getValue());
				upRoom.setText("Up: " + source.getUpperValue());
			}
		};

		listenerPrice = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// update text field when the slider value changes
				RangeSlider source = (RangeSlider) event.getSource();
				lowPrice.setText("Low: " + source.getValue());
				upPrice.setText("Up: " + source.getUpperValue());
			}
		};

		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(400, 400));
		
		// PRICE SLIDER PART
		priceSlider = new RangeSlider(0, 1000, 0, 1000);
		priceSlider.addChangeListener(listenerPrice);
					
		JPanel priceMinMaxPane = new JPanel(new BorderLayout());		
		priceMinMaxPane.add(new JLabel(""+priceSlider.getMinimum()), BorderLayout.WEST);
		priceMinMaxPane.add(new JLabel(""+priceSlider.getMaximum()), BorderLayout.EAST);
		priceMinMaxPane.add(new JLabel("Price", SwingConstants.CENTER), BorderLayout.CENTER);
		
		JPanel priceLowUpPane = new JPanel(new BorderLayout());	
		lowPrice.setText("Low: " + priceSlider.getValue());
		priceLowUpPane.add(lowPrice, BorderLayout.WEST);
		upPrice.setText("Up: " + priceSlider.getUpperValue());
		priceLowUpPane.add(upPrice, BorderLayout.EAST);
		
		JPanel pricePane = new JPanel(new BorderLayout());
		pricePane.add(priceMinMaxPane, BorderLayout.NORTH);
		pricePane.add(priceSlider, BorderLayout.CENTER);
		pricePane.add(priceLowUpPane, BorderLayout.SOUTH);
		
		// ROOM SLIDER PART
		roomSlider = new RangeSlider(0, 15, 0, 15);
		roomSlider.addChangeListener(listenerRoom);
		
		JPanel roomMinMaxPane = new JPanel(new BorderLayout());		
		roomMinMaxPane.add(new JLabel(""+roomSlider.getMinimum()), BorderLayout.WEST);
		roomMinMaxPane.add(new JLabel(""+roomSlider.getMaximum()), BorderLayout.EAST);
		roomMinMaxPane.add(new JLabel("Room", SwingConstants.CENTER), BorderLayout.CENTER);
		
		JPanel roomLowUpPane = new JPanel(new BorderLayout());	
		lowRoom.setText("Low: " + roomSlider.getValue());
		roomLowUpPane.add(lowRoom, BorderLayout.WEST);
		upRoom.setText("Up: " + roomSlider.getUpperValue());
		roomLowUpPane.add(upRoom, BorderLayout.EAST);
		
		JPanel roomPane = new JPanel(new BorderLayout());
		roomPane.add(roomMinMaxPane, BorderLayout.NORTH);
		roomPane.add(roomSlider, BorderLayout.CENTER);
		roomPane.add(roomLowUpPane, BorderLayout.SOUTH);
		
		// APP PART
		JPanel sliders = new JPanel(new BorderLayout());
		sliders.add(pricePane, BorderLayout.NORTH);
		sliders.add(roomPane, BorderLayout.SOUTH);
		
		getContentPane().add(sliders, BorderLayout.EAST);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		pack();
		setVisible(true);

	}
}

package HomeFinder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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
	JPanel centerPanel;
	JPanel rightPanel;

	public SliderTestFrame() {
		setTitle("TP1 - Home Finder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		// common listener for all sliders
		listenerRoom = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// update text field when the slider value changes
				RangeSlider source = (RangeSlider) event.getSource();
				tfRooms.setText("" + source.getValue() + " <->" + source.getUpperValue());
			}
		};

		listenerPrice = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// update text field when the slider value changes
				RangeSlider source = (RangeSlider) event.getSource();
				tfPrice.setText("" + source.getValue() + " <->" + source.getUpperValue());
			}
		};

		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(400, 400));

		tfRooms = new JTextField();
		tfRooms.setColumns(2);
		tfRooms.setPreferredSize(new Dimension(20, 20));
		tfPrice = new JTextField();
		RangeSlider roomSlider = new RangeSlider();
		addSliderPrice(roomSlider, "Room Slider");
		RangeSlider priceSlider = new RangeSlider();
		addSliderRoom(priceSlider, "Price Slider");

		rightPanel = new JPanel();
		rightPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 0, 0, 0);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		rightPanel.add(roomSlider, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 0, 0, 0); // Top padding
		c.weightx = 0;
		c.weighty = 0;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 1;
		rightPanel.add(tfPrice, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(30, 0, 0, 0); // Top padding
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 2;
		rightPanel.add(priceSlider, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 0, 0, 0); // Top padding
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 10;
		rightPanel.add(tfRooms, c);

		getContentPane().add(rightPanel, BorderLayout.EAST);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		pack();
		setVisible(true);

	}

	public void addSliderPrice(JSlider s, String description) {
		s.addChangeListener(listenerPrice);
		JPanel panel = new JPanel();
		panel.add(s);
		panel.add(new JLabel(description));
		getContentPane().add(panel);
	}

	public void addSliderRoom(JSlider s, String description) {
		s.addChangeListener(listenerRoom);
		JPanel panel = new JPanel();
		panel.add(s);
		panel.add(new JLabel(description));
		getContentPane().add(panel);
	}
}

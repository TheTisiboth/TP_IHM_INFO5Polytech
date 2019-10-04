package mvc;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class test {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				SliderTestFrame frame = new SliderTestFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

/**
 * A frame with many sliders and a text field to show slider values.
 */
class SliderTestFrame extends JFrame {
	public SliderTestFrame() {
		setTitle("SliderTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		sliderPanel = new JPanel();
		sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// common listener for all sliders
		listener = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// update text field when the slider value changes
				JSlider source = (JSlider) event.getSource();
				textField.setText("" + source.getValue());
			}
		};

		// add a plain slider

		JSlider slider = new JSlider();
		addSlider(slider, "Plain");



		// add the text field that displays the slider value

		textField = new JTextField();
		add(sliderPanel, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
	}

	/**
	 * Adds a slider to the slider panel and hooks up the listener
	 * 
	 * @param s           the slider
	 * @param description the slider description
	 */
	public void addSlider(JSlider s, String description) {
		s.addChangeListener(listener);
		JPanel panel = new JPanel();
		panel.add(s);
		panel.add(new JLabel(description));
		sliderPanel.add(panel);
	}

	public static final int DEFAULT_WIDTH = 350;
	public static final int DEFAULT_HEIGHT = 450;

	private JPanel sliderPanel;
	private JTextField textField;
	private ChangeListener listener;
}

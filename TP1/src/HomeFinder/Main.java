package HomeFinder;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	final int MIN_PRICE = 10;
	final int MAX_PRICE = 1000;
	final int MIN_ROOM = 1;
	final int MAX_ROOM = 15;

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
	ArrayList<Home> homes = new ArrayList<>();
	Map map;

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
				repaint();
			}
		};

		listenerPrice = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// update text field when the slider value changes
				RangeSlider source = (RangeSlider) event.getSource();
				lowPrice.setText("Low: " + source.getValue());
				upPrice.setText("Up: " + source.getUpperValue());
				repaint();
			}
		};

		// PRICE SLIDER PART
		priceSlider = new RangeSlider(MIN_PRICE, MAX_PRICE);
		priceSlider.addChangeListener(listenerPrice);

		JPanel priceMinMaxPane = new JPanel(new BorderLayout());
		priceMinMaxPane.add(new JLabel("" + priceSlider.getMinimum()), BorderLayout.WEST);
		priceMinMaxPane.add(new JLabel("" + priceSlider.getMaximum()), BorderLayout.EAST);
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
		roomSlider = new RangeSlider(MIN_ROOM, MAX_ROOM);
		roomSlider.addChangeListener(listenerRoom);

		JPanel roomMinMaxPane = new JPanel(new BorderLayout());
		roomMinMaxPane.add(new JLabel("" + roomSlider.getMinimum()), BorderLayout.WEST);
		roomMinMaxPane.add(new JLabel("" + roomSlider.getMaximum()), BorderLayout.EAST);
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

		// HOMES CREATION
		map = new Map(priceSlider, roomSlider, MIN_PRICE, MAX_PRICE, MIN_ROOM, MAX_ROOM);

		// APP PART
		JPanel sliders = new JPanel(new BorderLayout());
		sliders.add(pricePane, BorderLayout.NORTH);
		sliders.add(roomPane, BorderLayout.SOUTH);

		getContentPane().add(sliders, BorderLayout.EAST);
		getContentPane().add(map, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	public void clearCanvas(Canvas c) {
		c.getGraphics().clearRect(0, 0, 500, 500);
	}
}

class Map extends JComponent {
	ArrayList<Home> homes = new ArrayList<>();
	RangeSlider priceSlider, roomSlider;
	int minPrice, maxPrice, minRoom, maxRoom;

	public Map(RangeSlider price, RangeSlider room, int minP, int maxP, int minR, int maxR) {
		priceSlider = price;
		roomSlider = room;
		minPrice = minP;
		maxPrice = maxP;
		minRoom = minR;
		maxRoom = maxR;
		homes = generateHomes();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 500, 500);

		for (Home home : homes) {
			int price = home.getPrice();
			int rooms = home.getNbRooms();
			if (price >= priceSlider.getValue() && price <= priceSlider.getUpperValue()
					&& rooms >= roomSlider.getValue() && rooms <= roomSlider.getUpperValue()) {
				int x = home.getX() - (2 / 2);
				int y = home.getY() - (2 / 2);
				g.setColor(Color.RED);
				System.out.println("test");
				g.fillOval(x, y, 2, 2);
			}
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	public ArrayList<Home> generateHomes() {
		ArrayList<Home> homes = new ArrayList<>();
		Home tmp;
		int x, y, price, rooms;
		Random random = new Random();

		for (int i = 0; i < 40; i++) {
			x = random.nextInt(500);
			y = random.nextInt(500);
			price = random.nextInt(maxPrice) + minPrice;
			rooms = random.nextInt(maxRoom) + minRoom;

			tmp = new Home(x, y, rooms, price);
			homes.add(tmp);
		}

		return homes;
	}

	// Retrieved from stackOverflow
	public static int getRandomIntegerBetweenRange(int min, int max) {
		int x = (int) (Math.random() * ((max - min) + 1)) + min;
		return x;
	}

	public ArrayList<Home> getHomes() {
		return homes;
	}
}

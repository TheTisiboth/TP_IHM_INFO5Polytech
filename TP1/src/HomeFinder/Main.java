package HomeFinder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mvc.Model.RangeSlider;

public class Main {
	public static void main(String[] args) {
		SliderTestFrame frame = new SliderTestFrame();
		frame.pack();
		frame.setVisible(true);
	}

}

class SliderTestFrame extends JFrame {
	final int MIN_PRICE = 10;
	final int MAX_PRICE = 1000;
	final int MIN_ROOM = 1;
	final int MAX_ROOM = 15;

	private static final long serialVersionUID = 1L;
	private ChangeListener listenerRoom, listenerPrice;
	private JLabel lowPrice = new JLabel();
	private JLabel upPrice = new JLabel();
	private JLabel lowRoom = new JLabel();
	private JLabel upRoom = new JLabel();
	RangeSlider roomSlider, priceSlider;
	ArrayList<Home> homes = new ArrayList<>();
	Map map;

	public SliderTestFrame() {
		setTitle("TP1 - Range Slider and Home Finder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// will listen on PriceSlider changes to update our view
		listenerPrice = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// update text field when the slider value changes
				RangeSlider source = (RangeSlider) event.getSource();
				lowPrice.setText("Lower value: " + source.getValue());
				upPrice.setText("Upper value: " + source.getUpperValue());
				repaint();
			}
		};

		// will listen on RoomSlider changes to update our view
		listenerRoom = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// update text field when the slider value changes
				RangeSlider source = (RangeSlider) event.getSource();
				lowRoom.setText("Lower value: " + source.getValue());
				upRoom.setText("Upper value: " + source.getUpperValue());
				repaint();
			}
		};

		// PRICE SLIDER PART
		priceSlider = new RangeSlider(MIN_PRICE, MAX_PRICE);
		priceSlider.addChangeListener(listenerPrice);

		JPanel priceMinMaxPane = new JPanel(new BorderLayout());
		priceMinMaxPane.add(new JLabel("Min: " + priceSlider.getMinimum()), BorderLayout.WEST);
		priceMinMaxPane.add(new JLabel("Max: " + priceSlider.getMaximum()), BorderLayout.EAST);
		JLabel titlePrice = new JLabel("House's price", SwingConstants.CENTER);
		titlePrice.setFont(new Font("Monospaced", Font.BOLD, 20));
		priceMinMaxPane.add(titlePrice, BorderLayout.CENTER);

		JPanel priceLowUpPane = new JPanel(new BorderLayout());
		lowPrice.setText("Lower value: " + priceSlider.getValue());
		priceLowUpPane.add(lowPrice, BorderLayout.WEST);
		upPrice.setText("Upper value: " + priceSlider.getUpperValue());
		priceLowUpPane.add(upPrice, BorderLayout.EAST);

		JPanel pricePane = new JPanel(new BorderLayout());
		pricePane.add(priceMinMaxPane, BorderLayout.NORTH);
		pricePane.add(priceSlider, BorderLayout.CENTER);
		pricePane.add(priceLowUpPane, BorderLayout.SOUTH);

		// ROOM SLIDER PART
		roomSlider = new RangeSlider(MIN_ROOM, MAX_ROOM);
		roomSlider.addChangeListener(listenerRoom);

		JPanel roomMinMaxPane = new JPanel(new BorderLayout());
		roomMinMaxPane.add(new JLabel("Min: " + roomSlider.getMinimum()), BorderLayout.WEST);
		roomMinMaxPane.add(new JLabel("Max: " + roomSlider.getMaximum()), BorderLayout.EAST);
		JLabel titleRoom = new JLabel("Number of rooms", SwingConstants.CENTER);
		titleRoom.setFont(new Font("Monospaced", Font.BOLD, 20));
		roomMinMaxPane.add(titleRoom, BorderLayout.CENTER);

		JPanel roomLowUpPane = new JPanel(new BorderLayout());
		lowRoom.setText("Lower value: " + roomSlider.getValue());
		roomLowUpPane.add(lowRoom, BorderLayout.WEST);
		upRoom.setText("Upper value: " + roomSlider.getUpperValue());
		roomLowUpPane.add(upRoom, BorderLayout.EAST);

		JPanel roomPane = new JPanel(new BorderLayout());
		roomPane.add(roomMinMaxPane, BorderLayout.NORTH);
		roomPane.add(roomSlider, BorderLayout.CENTER);
		roomPane.add(roomLowUpPane, BorderLayout.SOUTH);

		// HOMES CREATION
		map = new Map(priceSlider, roomSlider, MIN_PRICE, MAX_PRICE, MIN_ROOM, MAX_ROOM);

		// APP PART
		JPanel sliders = new JPanel(new GridLayout(1, 2, 40, 0));
		sliders.add(pricePane);
		sliders.add(roomPane);
		JLabel info = new JLabel("Homes matching your search right below:", SwingConstants.CENTER);
		Border border = info.getBorder();
		Border margin = new EmptyBorder(20, 20, 20, 20);
		info.setBorder(new CompoundBorder(border, margin));
		info.setFont(new Font("Monospaced", Font.BOLD, 26));

		getContentPane().add(sliders, BorderLayout.NORTH);
		getContentPane().add(info, BorderLayout.CENTER);
		getContentPane().add(map, BorderLayout.SOUTH);
	}
}

class Map extends Component {
	private static final long serialVersionUID = 1L;
	ArrayList<Home> homes = new ArrayList<>();
	RangeSlider priceSlider, roomSlider;
	int minPrice, maxPrice, minRoom, maxRoom;
	private static Font monoFont = new Font("Monospaced", Font.BOLD, 15);
	final int WIDTH = 1000;
	final int HEIGHT = 1000;

	public Map(RangeSlider price, RangeSlider room, int minP, int maxP, int minR, int maxR) {
		priceSlider = price;
		roomSlider = room;
		minPrice = minP;
		maxPrice = maxP;
		minRoom = minR;
		maxRoom = maxR;
		homes = generateHomes();
	}

	@Override
	public void paint(Graphics gc) {
		Graphics2D g = (Graphics2D) gc;

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		int price, rooms, x, y, w, h;

		for (Home home : homes) {
			price = home.getPrice();
			rooms = home.getNbRooms();
			if (price >= priceSlider.getValue() && price <= priceSlider.getUpperValue()
					&& rooms >= roomSlider.getValue() && rooms <= roomSlider.getUpperValue()) {

				// Setting color for the drawing
				g.setColor(home.getColor());

				// Home drawing
				x = home.getX() - (2 / 2);
				y = home.getY() - (2 / 2);
				g.fill3DRect(x, y, 8, 8, true);

				// Info text
				g.setFont(monoFont);
				FontMetrics fm = g.getFontMetrics();
				w = fm.stringWidth("P:" + price + "€");
				h = fm.getAscent();
				g.drawString("P:" + price + "€", x - (w / 2), y - h - 3);
				w = fm.stringWidth("R:" + rooms);
				h = fm.getAscent();
				g.drawString("R:" + rooms, x - (w / 2), y - h / 4);
			}
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	public ArrayList<Home> generateHomes() {
		ArrayList<Home> homes = new ArrayList<>();
		Home tmp;
		int x, y, price, rooms;
		int r, g, b;
		Color color;
		Random random = new Random();

		for (int i = 0; i < 40; i++) {
			x = random.nextInt(WIDTH);
			y = random.nextInt(HEIGHT);
			price = random.nextInt(maxPrice) + minPrice;
			rooms = random.nextInt(maxRoom) + minRoom;
			// Used to set a random color for each house
			r = random.nextInt(255);
			g = random.nextInt(255);
			b = random.nextInt(255);
			color = new Color(r, g, b);

			tmp = new Home(x, y, rooms, price, color);
			homes.add(tmp);
		}

		return homes;
	}
}

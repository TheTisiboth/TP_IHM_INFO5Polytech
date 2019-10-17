package HomeFinder;

import java.awt.Color;

/**
 * Representation of a Home, with its coordinates, price, number of rooms and color
 * @author Eva Leo Xavier
 *
 */
public class Home {
	private int x;
	private int y;
	private int nbRooms;
	private int price;
	private Color color;

	public Home(int x, int y, int nbRooms, int price, Color color) {
		this.x = x;
		this.y = y;
		this.nbRooms = nbRooms;
		this.price = price;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getNbRooms() {
		return nbRooms;
	}

	public int getPrice() {
		return price;
	}
	
	public Color getColor() {
		return color;
	}
}

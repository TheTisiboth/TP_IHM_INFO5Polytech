package HomeFinder;

import java.awt.Color;

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

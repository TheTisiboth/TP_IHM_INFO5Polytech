package HomeFinder;

public class Home {
	private int x;
	private int y;
	private int nbRooms;
	private int price;

	public Home(int x, int y, int nbRooms, int price) {
		this.x = x;
		this.y = y;
		this.nbRooms = nbRooms;
		this.price = price;
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
}

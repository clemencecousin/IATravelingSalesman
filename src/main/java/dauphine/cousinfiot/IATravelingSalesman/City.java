package dauphine.cousinfiot.IATravelingSalesman;

public class City {
	private int x;
	private int y;

	public City(int a) {
		this.x = (int) (Math.random() * a);
		this.y = (int) (Math.random() * a);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public double distance(City c) {
		return Math.sqrt(Math.pow(this.x - c.getX(), 2) + Math.pow(this.y - c.getY(), 2));
	}

	public boolean equals(City c) {
		return (this.x == c.getX() && this.y == c.getY());
	}

	@Override
	public String toString() {
		return "(" + getX() + ";" + getY() + ")";
	}
}

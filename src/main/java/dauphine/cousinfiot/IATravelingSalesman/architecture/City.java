package dauphine.cousinfiot.IATravelingSalesman.architecture;

/**
 * This class represents a city in order to solve the traveling salesman
 * problem. Each city is characterized by its position (x and y coordinates).
 *
 */
public class City {
	private int x;
	private int y;

	/**
	 * Conctructor of the class. The coordinates of the city are generated between 0
	 * and a.
	 * 
	 * @param a integer, max value for the coordinates
	 */
	public City(int a) {
		this.x = (int) (Math.random() * a);
		this.y = (int) (Math.random() * a);
	}
	
	public City(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	/**
	 * Calculate the Euclidean distance between two cities.
	 * 
	 * @param c the second city to calculate distance with
	 * @return distance between this city and the param city c
	 */
	public double distance(City c) {
		return Math.sqrt(Math.pow(this.x - c.getX(), 2) + Math.pow(this.y - c.getY(), 2));
	}

	/**
	 * If two cities have the same x and y coordinates, they are equals.
	 * 
	 * @param c the second city to compare with
	 * @return true if x and y coordinates are equals
	 */
	public boolean equals(City c) {
		return (this.x == c.getX() && this.y == c.getY());
	}

	@Override
	public String toString() {
		return "(" + getX() + ";" + getY() + ")";
	}
}

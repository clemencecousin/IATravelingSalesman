package dauphine.cousinfiot.IATravelingSalesman.architecture;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class generates a define number of cities. These cities will be used to
 * solve the traveling salesman problem.
 *
 */
public class CityMap {
	private ArrayList<City> myCities = new ArrayList<>();
	private HashMap<Pair<City, City>, Double> myGraph = new HashMap<>();

	public CityMap(int nbCities, int distanceMax) {
		for (int i = 0; i < nbCities; i++) {
			City c = new City(distanceMax);
			while (myCities.contains(c)) {
				c = new City(distanceMax);
			}
			myCities.add(c);
		}
		this.myGraph = constructGraph(this.myCities);
		
	}
	
	public static HashMap<Pair<City,City>, Double> constructGraph(ArrayList<City> c) {
		HashMap<Pair<City,City>, Double> h = new HashMap<>();
		for (int i = 0; i < c.size() - 1; i++) {
			for (int j = i + 1; j < c.size(); j++) {
				Pair<City, City> p = new Pair<>(c.get(i), c.get(j));
				h.put(p, c.get(i).distance(c.get(j)));
			}
		}
		return h;
	}
	
	/**
	 * Calculates the total distance of the salesman with this travel.
	 * 
	 * @return the sum of the distance between each pair of cities.
	 */
	public double totalDistance() {
		double d = 0;
		for (int i = 0; i < myCities.size() - 1; i++) {
			d = d + myCities.get(i).distance(myCities.get(i + 1));
		}
		d = d + myCities.get(myCities.size() - 1).distance(myCities.get(0));
		return d;
	}
	
	public CityMap(ArrayList<City> myCities, HashMap<Pair<City, City>, Double> myGraph) {
		this.myCities = myCities;
		this.myGraph = myGraph;
	}

	public ArrayList<City> getMyCities() {
		return myCities;
	}

	public HashMap<Pair<City, City>, Double> getMyGraph() {
		return myGraph;
	}
	
	public int nbCities() {
		return this.myCities.size();
	}
}

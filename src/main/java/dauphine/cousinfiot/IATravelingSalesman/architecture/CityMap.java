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
		for (int i = 0; i < this.myCities.size() - 1; i++) {
			for (int j = i + 1; j < this.myCities.size(); j++) {
				Pair<City, City> p = new Pair<>(this.myCities.get(i), this.myCities.get(j));
				this.myGraph.put(p, this.myCities.get(i).distance(this.myCities.get(j)));
			}
		}
	}

	public ArrayList<City> getMyCities() {
		return myCities;
	}

	public HashMap<Pair<City, City>, Double> getMyGraph() {
		return myGraph;
	}
}

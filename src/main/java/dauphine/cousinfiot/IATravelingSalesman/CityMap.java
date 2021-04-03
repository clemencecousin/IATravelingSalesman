package dauphine.cousinfiot.IATravelingSalesman;

import java.util.ArrayList;
import java.util.HashMap;

public class CityMap {
	private ArrayList<City> myCities = new ArrayList<City>();
	private HashMap<Pair<City,City>, Double> myGraph = new HashMap<Pair<City,City>, Double>();
	
	public CityMap(int nbCities, int distanceMax) {
		for(int i = 0; i < nbCities ; i++) {
			City c = new City(distanceMax);
			while(myCities.contains(c)) {
				c = new City(distanceMax);
			}
			myCities.add(c);			
		}
		for(int i = 0; i< this.myCities.size() - 1; i ++) {
			for(int j = i+1 ; j < this.myCities.size(); j ++) {
				Pair<City, City> p = new Pair<City,City>(this.myCities.get(i), this.myCities.get(j));
				this.myGraph.put(p, this.myCities.get(i).distance(this.myCities.get(j)));
			}
		}
	}

}

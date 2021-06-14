package dauphine.cousinfiot.IATravelingSalesman.architecture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class represents a possible travel based on the random generation of
 * cities form CityMap.The order of the cities is the order in which the
 * salesman visits cities.
 *
 */
public class Travel {
	private CityMap cities;
	private ArrayList<City> citiesList;

	public Travel(CityMap cities) {
		this.cities = cities;
		generateTravel();
	}

	public Travel() {
		this.citiesList = new ArrayList<>();
	}

	/**
	 * Add a new city to the travel.
	 * 
	 * @param c the city to add
	 */
	public void add(City c) {
		citiesList.add(c);
	}

	/**
	 * Generate a random travel from the original list of cities in CityMap.
	 */
	private void generateTravel() {
		ArrayList<City> tempCities = new ArrayList<>();
		while (tempCities.size() < cities.getMyCities().size()) {
			int random = new Random().nextInt(cities.getMyCities().size());
			City c = cities.getMyCities().get(random);
			if (!tempCities.contains(c)) {
				tempCities.add(c);
			}
		}
		this.citiesList = tempCities;
	}

	public ArrayList<City> getCitiesList() {
		return citiesList;
	}

	/**
	 * Search if the city is present in the travel.
	 * 
	 * @param c the city to search
	 * @return true if the city is in this travel
	 */
	public boolean contains(City c) {
		if (citiesList.contains(c)) {
			return true;
		}
		return false;
	}

	/**
	 * Mutation of the travel. Generate randomly a new order of cities from CityMap.
	 */
	public void mutate() {
		generateTravel();
	}

	public void setCities(CityMap cities) {
		this.cities = cities;
	}
	
	public CityMap getCities() {
		return this.cities;
	}
	
	/**
	 * Calculates the total distance of the salesman with this travel.
	 * 
	 * @return the sum of the distance between each pair of cities.
	 */
	public double totalDistance() {
		double d = 0;
		for (int i = 0; i < citiesList.size() - 1; i++) {
			d = d + citiesList.get(i).distance(citiesList.get(i + 1));
		}
		d = d + citiesList.get(citiesList.size() - 1).distance(citiesList.get(0));
		return d;
	}
}

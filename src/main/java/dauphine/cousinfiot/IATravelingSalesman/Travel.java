package dauphine.cousinfiot.IATravelingSalesman;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

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
	
	public void add(City c) {
		citiesList.add(c);
	}

	private void generateTravel() {
		ArrayList<City> tempCities = new ArrayList<>();
		while (tempCities.size() < cities.getMyCities().size()) {
			int random = new Random().nextInt(cities.getMyCities().size());
			City c = cities.getMyCities().get(random);
			if (!tempCities.contains(c)) {
				tempCities.add(c);
			}
		}
		this.citiesList =  tempCities;
	}
	
	public double totalDistance() {
		double d = 0;
		for (int i = 0; i < citiesList.size()-1; i++) {
			d = d + citiesList.get(i).distance(citiesList.get(i+1));
		}
		d = d + citiesList.get(citiesList.size()-1).distance(citiesList.get(0));
		return d;
	}

	public ArrayList<City> getCitiesList() {
		return citiesList;
	}
	
	public boolean contains(City c) {
		if (citiesList.contains(c)) {
			return true;
		}
		return false;
	}
	
	public void mutate() {
		generateTravel();
	}
	
	public void setCities(CityMap cities) {
		this.cities = cities;
	}
}

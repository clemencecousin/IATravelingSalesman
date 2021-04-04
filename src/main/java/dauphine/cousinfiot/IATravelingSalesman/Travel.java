package dauphine.cousinfiot.IATravelingSalesman;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Travel {
	Map<Pair<City, City>, Double> travel;
	CityMap cities;
	ArrayList<Pair<City, City>> pairCities;

	public Travel(CityMap cities) {
		this.cities = cities;
		generateTravel();
	}
	
	public Travel() {
		this.travel = new LinkedHashMap<>();
	}
	
	public void add(Pair<City, City> p, double d) {
		travel.put(p, d);
	}

	private void generateTravel() {
		ArrayList<Pair<City, City>> pairList = new ArrayList<>(cities.getMyGraph().keySet());
		int mapSize = cities.getMyCities().size();

		Map<Pair<City, City>, Double> tempMap = new LinkedHashMap<>();
		while (tempMap.size() < mapSize) {
			int random = new Random().nextInt(cities.getMyGraph().size());
			Pair<City, City> pairTemp = pairList.get(random);
			if (!tempMap.containsKey(pairTemp)) {
				tempMap.put(pairTemp, cities.getMyGraph().get(pairTemp));
			}
		}
		this.travel = tempMap;
	}

	public Map<Pair<City, City>, Double> getTravel() {
		return travel;
	}

	public int numberIssues() {
		ArrayList<Pair<City, City>> pairList = new ArrayList<>(travel.keySet());
		Map<City, Integer> citiesList = new HashMap<>();
		for (Pair<City, City> pair : pairList) {
			if (citiesList.containsKey(pair.getLeft())) {
				citiesList.put(pair.getLeft(), citiesList.get(pair.getLeft()) + 1);
			} else {
				citiesList.put(pair.getLeft(), 1);
			}

			if (citiesList.containsKey(pair.getRight())) {
				citiesList.put(pair.getRight(), citiesList.get(pair.getRight()) + 1);
			} else {
				citiesList.put(pair.getRight(), 1);
			}
		}

		int counter = 0;
		Collection<Integer> listOccurence = citiesList.values();
		for (Integer integer : listOccurence) {
			if (integer != 2)
				counter++;
		}

		return counter;
	}
	
	public double totalDistance() {
		Collection<Double> listOccurence = travel.values();
		double d = 0;
		for (Double dist : listOccurence) {
			d = d + dist;
		}

		return d;
	}
	
	private void computePairCities() {
		pairCities = new ArrayList<>(travel.keySet());
	}
	
	public ArrayList<Pair<City, City>> getPairCities() {
		computePairCities();
		return pairCities;
	}
}

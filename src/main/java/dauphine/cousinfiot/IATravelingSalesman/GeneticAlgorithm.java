package dauphine.cousinfiot.IATravelingSalesman;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GeneticAlgorithm implements TravelingSalesmanSolve {
	CityMap cities;
//	ArrayList<ArrayList<City>> population = new ArrayList<>();
	ArrayList<Map<Pair<City, City>, Double>> population = new ArrayList<>();
	int populationSize;
//	ArrayList<Double> fitness;
	ArrayList<Pair<HashMap<City, City>, HashMap<City, City>>> couple;

	private void generatePopulation(int populationSize) {
		this.populationSize = populationSize;
		ArrayList<Pair<City, City>> pairList = new ArrayList<>(cities.getMyGraph().keySet());
		int mapSize = cities.getMyCities().size();

		for (int i = 0; i < populationSize; i++) {
//			ArrayList<City> cityTemp = (ArrayList<City>) cities.getMyCities().clone();
//			Collections.shuffle(cityTemp);
//			population.add(cityTemp);

			Map<Pair<City, City>, Double> tempMap = new LinkedHashMap<>();
			while (tempMap.size() < mapSize - 1) {
				int random = new Random().nextInt(cities.getMyGraph().size());
				Pair<City, City> pairTemp = pairList.get(random);
				if (!tempMap.containsKey(pairTemp)) {
					tempMap.put(pairTemp, cities.getMyGraph().get(pairTemp));
				}
			}
			population.add(tempMap);
		}
	}

	private static int numberIssues(Map<Pair<City, City>, Double> map) {
		ArrayList<Pair<City, City>> pairList = new ArrayList<>(map.keySet());
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
			if (integer != 2) counter++;
		}
		
		return counter;
	}
	
	private static double totalDistance(Map<Pair<City, City>, Double> map) {
		Collection<Double> listOccurence = map.values();
		double d = 0;
		for (Double dist : listOccurence) {
			d = d + dist;
		}
		
		return d;
	}

	private void makeCouple() {
		couple = new ArrayList<>();
		RandomSelector random = new RandomSelector();
		ArrayList<HashMap<Pair<City, City>, Double>> explored;
		
		for (Map<Pair<City, City>, Double> pop : population) {
			random.add(numberIssues(pop));
		}
		
//		while (exploredBoards.size() < populationSize) {
//			mother = population.get(random.randomChoice());
//			father = population.get(random.randomChoice());
//			if ((!mother.equals(father)) & (exploredBoards.indexOf(mother) == -1)
//					& (exploredBoards.indexOf(father) == -1)) {
//				exploredBoards.add(mother);
//				exploredBoards.add(father);
//				coupleList.add(new Couple(mother, father));
//			}
//		}
		
	}
	
	private void crossover() {
		
	}
	
	@Override
	public ArrayList<City> solve() {
		while (true) {
			
			if (numberIssues(null) == 0) break;
		}
		return null;
	}

	public void setCities(CityMap cities) {
		this.cities = cities;
	}
	
//	private void fitness() {
//		ArrayList<Double> fit = new ArrayList<>();
//		for (Map<Pair<City, City>, Double> pop : population) {
//			fit.add((double) (1-(1/numberIssues(pop))));
//		}
//		fitness = fit;
//	}

	public static void main(String[] args) {
		GeneticAlgorithm g = new GeneticAlgorithm();
		g.setCities(new CityMap(10, 500));
		g.generatePopulation(5);
		for (Map<Pair<City, City>, Double> map : g.population) {
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());
			}
			System.out.println("------------------------------------------");
			System.out.println(numberIssues(map));
			System.out.println(totalDistance(map));
			System.out.println("------------------------------------------");
		}
	}
}

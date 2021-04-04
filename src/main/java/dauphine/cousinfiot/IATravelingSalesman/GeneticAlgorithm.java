package dauphine.cousinfiot.IATravelingSalesman;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GeneticAlgorithm implements TravelingSalesmanSolve {
	CityMap cities;
	ArrayList<Travel> population = new ArrayList<>();
	int populationSize;
	ArrayList<Pair<Travel, Travel>> couple;

	private void generatePopulation(int popSize) {
		this.populationSize = popSize;

		for (int i = 0; i < popSize; i++) {
			population.add(new Travel(cities));
		}
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

		for (Travel pop : population) {
			random.add(pop.numberIssues());
		}
	}

	private void crossover() {

	}

	@Override
	public ArrayList<City> solve() {
//		while (true) {
//			
////			if (numberIssues(null) == 0) break;
//		}
		return null;
	}

	public void setCities(CityMap cities) {
		this.cities = cities;
	}

	public static void main(String[] args) {
		GeneticAlgorithm g = new GeneticAlgorithm();
		g.setCities(new CityMap(10, 500));
		g.generatePopulation(5);
		for (Travel t : g.population) {
			Iterator it = t.getTravel().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());
			}
			System.out.println("------------------------------------------");
			System.out.println(t.numberIssues());
			System.out.println(totalDistance(t.getTravel()));
			System.out.println("------------------------------------------");
		}
	}
}

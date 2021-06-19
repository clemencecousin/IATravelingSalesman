package dauphine.cousinfiot.IATravelingSalesman.algorithm;

import java.util.ArrayList;
import java.util.List;

import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Population;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Travel;

public class LocalBeamSearch implements TravelingSalesmanSolve {
	private CityMap cities;
	private int width;
	private Population kBest;
	private Population newGeneration;
	private int iteration;
	private Travel solution;

	public LocalBeamSearch(CityMap cities, int width) {
		this.cities = cities;
		this.width = width;
		init();
	}

	private void init() {
		kBest = new Population();
		while (kBest.getPopulationSize() < width) {
			kBest.add(new Travel(cities));
		}
	}

	private void generateNewGeneration() {
		newGeneration = new Population();
		List<Travel> keyList = kBest.getPopulation();
		for (int i = 0; i < width; i++) {
			Travel t = keyList.get(i).copy();
			for (int j = 0; j < cities.getMyCities().size() - 1; j++) {
				Travel newTravel = t.copy();
				newTravel.swap(j, j + 1);
				newGeneration.add(newTravel);
			}
			Travel newTravel = t.copy();
			newTravel.swap(cities.getMyCities().size() - 1, 0);
			newGeneration.add(newTravel);
		}
	}

	@Override
	public ArrayList<City> solve() {
		iteration = 0;
		double minDist = Double.POSITIVE_INFINITY;
		Boolean loop = true;
		int counter = 0;

		while (loop) {
			iteration++;
			counter++;
			generateNewGeneration();
			List<Travel> sortGeneration = newGeneration.getSortPop();
			kBest = new Population();

			for (int i = 0; i < width; i++) {
				kBest.add(sortGeneration.get(i));
			}

			for (Travel pop : kBest.getPopulation()) {
				if (pop.totalDistance() < minDist) {
					minDist = pop.totalDistance();
					solution = pop;
					counter = 0;
				}

				if (counter == 1000)
					loop = false;
			}
		}

		return solution.getCitiesList();
	}

	@Override
	public Travel getSolution() {
		return solution;
	}

	@Override
	public int getIteration() {
		return iteration;
	}

	public static void main(String[] args) {
		CityMap c = new CityMap(6, 500);
		LocalBeamSearch lbs = new LocalBeamSearch(c, 2);
		System.out.println(lbs.solve());
	}
}

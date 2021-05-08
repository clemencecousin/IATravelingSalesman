package dauphine.cousinfiot.IATravelingSalesman.algorithm;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Travel;

/**
 * https://www.fourmilab.ch/documents/travelling/anneal/
 *
 */
public class SimulatedAnnealing implements TravelingSalesmanSolve {
	private Travel solution;
	private double ditanceOfSolution;
	private CityMap cities;

	public SimulatedAnnealing(CityMap cities) {
		this.cities = cities;
		this.solution = new Travel(cities);
		this.ditanceOfSolution = solution.totalDistance();
	}

	@Override
	public ArrayList<City> solve() {
		Travel tempSolution = solution.copy();
		double minDist = solution.totalDistance();
		Boolean loop = true;
		int counter = 0;

		while (loop) {
			counter++;
			int method = new Random().nextInt(2);

			int p1 = new Random().nextInt(tempSolution.size());
			int p2 = (p1 + 1) % tempSolution.size();

			if (method == 0) {
				tempSolution.swap(p1, p2);
			} else {
				int newPosition = new Random().nextInt(tempSolution.size());
				while (p1 == newPosition) {
					newPosition = new Random().nextInt(tempSolution.size());
				}
				
				if (p1 < newPosition) {
					tempSolution.getCitiesList().add(newPosition, tempSolution.getCitiesList().get(p1));
					tempSolution.getCitiesList().remove(p1);
				} else {
					tempSolution.getCitiesList().add(newPosition, tempSolution.getCitiesList().get(p1));
					tempSolution.getCitiesList().remove(p1 + 1);
				}
				
				if (p2 < (newPosition + 1) % tempSolution.size()) {
					tempSolution.getCitiesList().add((newPosition + 1) % tempSolution.size(),
							tempSolution.getCitiesList().get(p2));
					tempSolution.getCitiesList().remove(p2);
				} else {
					tempSolution.getCitiesList().add((newPosition + 1) % tempSolution.size(),
							tempSolution.getCitiesList().get(p2));
					tempSolution.getCitiesList().remove(p2 + 1);
				}
			}

			if (tempSolution.totalDistance() < minDist) {
				minDist = tempSolution.totalDistance();
				solution = tempSolution;
				counter = 0;
			}

			if (counter == 100)
				loop = false;
		}
		return solution.getCitiesList();
	}

	public static void main(String[] args) {
		CityMap cities = new CityMap(6, 400);
		SimulatedAnnealing sa = new SimulatedAnnealing(cities);
		GeneticAlgorithm ga = new GeneticAlgorithm(cities);

		System.out.println(ga.solve());
		System.out.println(sa.solve());
	}
}

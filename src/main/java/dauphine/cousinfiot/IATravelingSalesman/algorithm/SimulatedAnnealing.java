package dauphine.cousinfiot.IATravelingSalesman.algorithm;

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
	private int iteration = 0;

	public SimulatedAnnealing(CityMap cities) {
		this.solution = new Travel(cities);
	}

	@Override
	public ArrayList<City> solve() {
		iteration = 0;
		double minDist = solution.totalDistance();
		Boolean loop = true;
		int counter = 0;

		while (loop) {
			iteration++;
			Travel tempSolution = solution.copy();
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

			double diff = tempSolution.totalDistance() - minDist;
			if ((tempSolution.totalDistance() < minDist) || (Math.exp(-diff)/minDist > new Random().nextDouble())) {
				System.out.println(counter);
				minDist = tempSolution.totalDistance();
				solution = tempSolution;
				counter = 0;
			}

			if (counter == 1000)
				loop = false;
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
		CityMap cities = new CityMap(30, 400);
		SimulatedAnnealing sa = new SimulatedAnnealing(cities);
//		GeneticAlgorithm ga = new GeneticAlgorithm(cities, 20);

//		System.out.println("---" + ga.solve());
		System.out.println("---" + sa.solve());
	}
}

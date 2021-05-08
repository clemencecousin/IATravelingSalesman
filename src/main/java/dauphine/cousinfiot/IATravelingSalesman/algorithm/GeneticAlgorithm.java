package dauphine.cousinfiot.IATravelingSalesman.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;

import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Pair;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Population;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Travel;

/**
 * Using the genetic algorithm to solve the traveling salesman problem.
 *
 */
public class GeneticAlgorithm implements TravelingSalesmanSolve {
	CityMap cities;
	Population population = new Population();
	int populationSize;
	ArrayList<Pair<Travel, Travel>> couple;
	
	public GeneticAlgorithm(CityMap cities) {
		setCities(cities);
		generatePopulation(cities.getMyCities().size());
	}

	/**
	 * Constructor which generates a population of random individuals.
	 * 
	 * @param popSize number of individuals in the population, must be an even
	 *                integer
	 */
	private void generatePopulation(int popSize) {
		this.populationSize = popSize;

		for (int i = 0; i < popSize; i++) {
			Travel t = new Travel(cities);
			population.add(t);
		}
	}

	/**
	 * Generates couple in the population randomly but where the probability of
	 * picking an element is proportional to its value (here its the distance of the
	 * travel).
	 */
	private void makeCouple() {
		couple = new ArrayList<>();
		RandomSelector random = new RandomSelector();
		ArrayList<Travel> explored = new ArrayList<>();

		for (Travel pop : population.getPopulation()) {
			random.add(pop.totalDistance());
		}

		Travel mother = null;
		Travel father = null;

		while (explored.size() < populationSize) {
			mother = population.getPopulation().get(random.randomChoice());
			father = population.getPopulation().get(random.randomChoice());
			if ((!mother.equals(father)) & (explored.indexOf(mother) == -1) & (explored.indexOf(father) == -1)) {
				explored.add(mother);
				explored.add(father);
				couple.add(new Pair(mother, father));
			}
		}
	}

	/**
	 * Mix the order of the city of the parents to make two new children. First half
	 * of the child1 is composed of the first half of the mother, the second half is
	 * composed of the missing cities in the same order they appear in the father.
	 * Same principal for the second child but with opposite place for mother and
	 * father. (Order Crossover Operator)
	 * 
	 * @return a new population
	 */
	private Population crossover() {
		System.out.println("size = " + couple.size());
		Population newPopulation = new Population();

		for (Pair<Travel, Travel> c : couple) {
			Travel child1 = new Travel();
			Travel child2 = new Travel();

			ArrayList<City> mother = c.getLeft().getCitiesList();
			ArrayList<City> father = c.getRight().getCitiesList();

			for (int i = 0; i < mother.size(); i++) {
				if (i < mother.size() / 2) {
					child1.add(mother.get(i));
					child2.add(father.get(i));
				} else {
					for (City city : mother) {
						if (!child2.contains(city))
							child2.add(city);
					}
					for (City city : father) {
						if (!child1.contains(city))
							child1.add(city);
					}
				}
			}
			newPopulation.add(child1);
			newPopulation.add(child2);
		}
		return newPopulation;
	}

	/**
	 * Solve the traveling salesman problem with genetic algorithm method.
	 * 
	 * @param mutationRate percentage of random mutation in the population
	 * @param elitistRate  percentage of the best individual which are kept from a
	 *                     generation to another
	 * @return the travel with the minimum totalDistance(), must be the optimal
	 *         journey to solve the problem.
	 */
	public Travel solveTravel(double mutationRate, double elitistRate) {
		Travel solution = null;
		double minDist = Double.POSITIVE_INFINITY;
		Boolean loop = true;
		int counter = 0;
		while (loop) {
			counter++;
			System.out.println(counter + "---------------------------------------");

			makeCouple();
			Population crossover = crossover();

			for (int j = 0; j < crossover.getPopulationSize(); j++) {
				if (Math.random() < mutationRate) {
					crossover.get(j).setCities(cities);
					crossover.get(j).mutate();
				}
			}

			for (int j = 0; j < elitistRate * populationSize; j++) {
				System.out.println(j);
				System.out.println(population.getSortPop());
				crossover.replace(population.getSortPop().get(j));
			}
			population = crossover;

			for (Travel pop : population.getPopulation()) {
				System.out.println(pop.totalDistance());
				if (pop.totalDistance() < minDist) {
					minDist = pop.totalDistance();
					solution = pop;
					counter = 0;
				}

				if (counter == 100)
					loop = false;
			}
		}

		System.out.println("***" + solution.getCitiesList());
		System.out.println("***" + solution.totalDistance());
		return solution;
	}

	@Override
	public ArrayList<City> solve() {
		Travel t = solveTravel(0.1, 0.4);
		return t.getCitiesList();
	}

	public void setCities(CityMap cities) {
		this.cities = cities;
	}

	public static void main(String[] args) {
		GeneticAlgorithm g = new GeneticAlgorithm(new CityMap(6, 500));

		ArrayList<City> sol = g.solve();
		System.out.println(g.cities.getMyGraph());
	}
}

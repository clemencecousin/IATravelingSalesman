package dauphine.cousinfiot.IATravelingSalesman;

import java.util.ArrayList;
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

	private void makeCouple() {
		couple = new ArrayList<>();
		RandomSelector random = new RandomSelector();
		ArrayList<Travel> explored = new ArrayList<>();

		for (Travel pop : population) {
			random.add(pop.numberIssues());
		}

		Travel mother = null;
		Travel father = null;

		while (explored.size() < populationSize) {
			mother = population.get(random.randomChoice());
			father = population.get(random.randomChoice());
			if ((!mother.equals(father)) & (explored.indexOf(mother) == -1) & (explored.indexOf(father) == -1)) {
				explored.add(mother);
				explored.add(father);
				couple.add(new Pair(mother, father));
			}
		}
	}

	private void crossover() {
		System.out.println("size = " + couple.size());
		ArrayList<Travel> newPopulation = new ArrayList<>();
		RandomSelector random = new RandomSelector();
		random.add(1);
		random.add(1);

		for (Pair<Travel, Travel> c : couple) {
			Travel child1 = new Travel();
			Travel child2 = new Travel();
			
			ArrayList<Pair<City, City>> citiesLeft = c.getLeft().getPairCities();
			ArrayList<Pair<City, City>> citiesRight = c.getRight().getPairCities();
			System.out.println("Pair = " + c.getLeft().getTravel() + " ; " + c.getRight().getTravel());
			
//			System.out.println(citiesLeft);
//			System.out.println(citiesRight);

			for (int i = 0; i < 10; i++) {
				int newRandom = random.randomChoice();

				if (newRandom == 0) {
					child1.add(citiesLeft.get(i), c.getLeft().getTravel().get(citiesLeft.get(i)));
					child2.add(citiesRight.get(i), c.getRight().getTravel().get(citiesRight.get(i)));
				} else {
					child1.add(citiesRight.get(i), c.getRight().getTravel().get(citiesRight.get(i)));
					child2.add(citiesLeft.get(i), c.getLeft().getTravel().get(citiesLeft.get(i)));
				}
			}
			
			System.out.println(child1.getTravel());
			System.out.println(child2.getTravel());
			newPopulation.add(child1);
			newPopulation.add(child2);
		}

		population = newPopulation;
	}

	public Travel solve1() {
		Travel solution = null;
		Boolean loop = true;
		int counter = 0;
		while (loop) {
			counter++;
			System.out.println(counter + "---------------------------------------");
			
			makeCouple();
			crossover();

			for (Travel pop : population) {
				solution = pop;
				
				System.out.println(pop.numberIssues());
				
				if (pop.numberIssues() == 0)
					loop = false;
			}
		}
		return solution;
	}

	public void setCities(CityMap cities) {
		this.cities = cities;
	}

	public static void main(String[] args) {
		GeneticAlgorithm g = new GeneticAlgorithm();
		g.setCities(new CityMap(10, 500));
		g.generatePopulation(6);
		System.out.println(g.solve1());
//		g.makeCouple();
//		System.out.println("end");
	}

	@Override
	public ArrayList<City> solve() {
		return null;
	}
}

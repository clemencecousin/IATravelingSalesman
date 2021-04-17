package dauphine.cousinfiot.IATravelingSalesman.architecture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains a list of all individuals. An individual is a travel, a
 * specific order of cities to visit.
 *
 */
public class Population {
	List<Travel> population;
	List<Travel> sortPop;

	public Population() {
		this.population = new ArrayList<>();
	}

	/**
	 * Sort the list of travel by their min of totalDistance.
	 */
	private void sort() {
		sortPop = new ArrayList<>(population);
		Collections.sort(sortPop, new Comparator<Travel>() {

			@Override
			public int compare(Travel t1, Travel t2) {
				if (t1.totalDistance() < t2.totalDistance())
					return -1;
				if (t1.totalDistance() > t2.totalDistance())
					return 1;
				return 0;
			}
		});
	}

	/**
	 * Replace the travel with the biggest totalDistance().
	 * @param t the new travel which replaces the worst individual
	 */
	public void replace(Travel t) {
		sort();
		population.set(population.size() - 1, t);
	}

	/**
	 * Add a new travel to the population.
	 * @param t the travel to add
	 */
	public void add(Travel t) {
		population.add(t);
	}

	public List<Travel> getSortPop() {
		sort();
		return sortPop;
	}

	public List<Travel> getPopulation() {
		return population;
	}

	public int getPopulationSize() {
		return population.size();
	}

	/**
	 * Get a travel at a specific position in the population.
	 * @param index
	 * @return
	 */
	public Travel get(int index) {
		return population.get(index);
	}
}

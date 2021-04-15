package dauphine.cousinfiot.IATravelingSalesman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Population {
	List<Travel> population;
	List<Travel> sortPop;

	public Population() {
		this.population = new ArrayList<>();
	}
	
	private void sort() {
		sortPop = new ArrayList<>(population);
		Collections.sort(sortPop, new Comparator<Travel>(){

			@Override
			public int compare(Travel t1, Travel t2) {
				if (t1.totalDistance() < t2.totalDistance()) return -1;
				if (t1.totalDistance() > t2.totalDistance()) return 1;
				return 0;
			}});
	}
	
	public void replace(Travel t) {
		sort();
		population.set(population.size()-1, t);
	}
	
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
	
	public Travel get(int index) {
		return population.get(index);
	}
}

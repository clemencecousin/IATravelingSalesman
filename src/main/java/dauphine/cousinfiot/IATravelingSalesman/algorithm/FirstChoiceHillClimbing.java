package dauphine.cousinfiot.IATravelingSalesman.algorithm;

import java.util.ArrayList;

import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Travel;

public class FirstChoiceHillClimbing extends HillClimbingAlgorithm implements TravelingSalesmanSolve {

	private CityMap solution;

	private int ite;

	/**
	 * This function allows us to find graph that are neighbors to our solution.
	 * This means that we exchange two edges of our current graph to build a new path for the traveling salesman
	 *
	 * @return a list of all possible graph from our initial graph
	 */
	private ArrayList<CityMap> getNeighbors() {
		ArrayList<CityMap> neighbors = new ArrayList<>();
		for(int i = 0; i < this.solution.nbCities() ; i++) {
			for(int j = i+1; j < this.solution.nbCities() ; j++) {
				ArrayList<City> neighbor = new ArrayList<>();
				for(int k = 0; k < this.solution.nbCities() ; k ++) {
					if(k != i && k != j) {
						neighbor.add(this.solution.getMyCities().get(k));
					}
					else if(k == i) {
						neighbor.add(this.solution.getMyCities().get(j));
					}
					else {
						neighbor.add(this.solution.getMyCities().get(i));
					}
				}
				neighbors.add(new CityMap(neighbor, CityMap.constructGraph(neighbor)));
			}
		}
		return neighbors;		
	}

	/**
	 * Gives all the graph identified as neighbors that have a total distance smaller than our current graph
	 *
	 * @param neighbors the graph that are identified as "neighbors" to our current solution
	 * @return the graphs that have a path smaller than our current graph. It can be empty
	 */
	private ArrayList<CityMap> betterThanCurrentState(ArrayList<CityMap> neighbors){
		ArrayList<CityMap> betterState = new ArrayList<>();
		for(CityMap c : neighbors) {
			if(c.totalDistance() < this.solution.totalDistance()) {
				betterState.add(c);
			}
		}
		return betterState;
	}

	/**
	 * Solve the traveling salesman problem
	 *
	 * @return a list of cities sorted to allow the salesman to take the shorter path according to the first choice 
	 * hill-climbing search
	 */
	@Override
	public ArrayList<City> solve(){
		Travel init = new Travel(this.solution);
		this.solution = new CityMap(init.getCitiesList(), CityMap.constructGraph(init.getCitiesList()));
		double currentRoute = this.solution.totalDistance();
		ArrayList<CityMap> n = this.betterThanCurrentState(this.getNeighbors());
		if(n.isEmpty()) {
			return this.solution.getMyCities();
		}
		CityMap neighbor = n.get(0);
		ite = 0;
		while(neighbor.totalDistance() < currentRoute) {
			ite++;
			this.solution = neighbor;
			currentRoute = this.solution.totalDistance();
			n.clear();
			n = this.getNeighbors();
			if(n.isEmpty()) {
				return this.solution.getMyCities();
			}
			neighbor = n.get(0);
		}
		return this.solution.getMyCities();		
	}

	/**
	 * Sets the initial graph.
	 *
	 * @param the graph we want to set
	 */
	@Override
	public void setCities(CityMap cities) {
		this.solution = cities;
	}

	/**
	 * Gets the solution found.
	 *
	 * @return the solution
	 */
	@Override
	public Travel getSolution() {
		Travel t = new Travel();
		t.setCities(solution);
		t.setCitiesList(solution.getMyCities());
		return t;
	}

	/**
	 * Gets the numbers of iterations needed to solve the problem.
	 *
	 * @return the iteration
	 */
	@Override
	public int getIteration() {
		return ite;
	}

	public static void main(String[] args) {
		FirstChoiceHillClimbing g = new FirstChoiceHillClimbing();
		g.setCities(new CityMap(6, 500));
		g.solve();
		System.out.println(g.solution.totalDistance());
		System.out.println(g.solution.getMyGraph());
	}
}

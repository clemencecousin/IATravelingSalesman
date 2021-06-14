package dauphine.cousinfiot.IATravelingSalesman.algorithm;

import java.util.ArrayList;

import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Travel;

/**
 * This class allows us to solve the problem using the hill-climbing search algorithm.
 * This code was inspired by the Python code we can found here :
 * https://towardsdatascience.com/how-to-implement-the-hill-climbing-algorithm-in-python-1c65c29469de
 */
public class HillClimbingAlgorithm implements TravelingSalesmanSolve {

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
	 * This function allow us to know which neighbors graph has the shortest path to solve the traveling salesman problem.
	 * This path can be longer than the one of the current graph (will be check on function solve())
	 *
	 * @param neighbors the neighbors' graph of our current graph
	 * @return the "best" neighbor, the one with the shortest path
	 */
	private static CityMap getBestNeighbors(ArrayList<CityMap> neighbors) {
		double bestRoute = neighbors.get(0).totalDistance();
		CityMap bestNeighbor = neighbors.get(0);
		for(CityMap c : neighbors) {
			double currentRoute = c.totalDistance();
			if(currentRoute < bestRoute) {
				bestRoute = currentRoute;
				bestNeighbor = c;
			}
		}
		return bestNeighbor;
	}

	/**
	 * Solve the traveling salesman problem
	 *
	 * @return a list of cities sorted to allow the salesman to take the shorter path according to the
	 * hill-climbing search algorithm
	 */
	@Override
	public ArrayList<City> solve() {
		Travel init = new Travel(this.solution);
		this.solution = new CityMap(init.getCitiesList(), CityMap.constructGraph(init.getCitiesList()));
		double currentRoute = this.solution.totalDistance();
		CityMap bestNeighbor = getBestNeighbors(this.getNeighbors());
		ite = 0;
		while(bestNeighbor.totalDistance() < currentRoute) {
			ite++;
			this.solution = bestNeighbor;
			currentRoute = this.solution.totalDistance();
			bestNeighbor = getBestNeighbors(this.getNeighbors());
		}
		return this.solution.getMyCities();		
	}
	
	/**
	 * Sets the initial graph.
	 *
	 * @param the graph we want to set
	 */
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
		HillClimbingAlgorithm g = new HillClimbingAlgorithm();
		g.setCities(new CityMap(6, 500));

		g.solve();
		System.out.println(g.solution.totalDistance());
		System.out.println(g.solution.getMyGraph());
	}

}

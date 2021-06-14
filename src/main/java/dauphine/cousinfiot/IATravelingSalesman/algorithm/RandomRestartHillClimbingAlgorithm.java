package dauphine.cousinfiot.IATravelingSalesman.algorithm;

import java.util.ArrayList;

import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Travel;

public class RandomRestartHillClimbingAlgorithm extends HillClimbingAlgorithm implements TravelingSalesmanSolve{

	private CityMap solution;

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
	 * This function allow us to know which neighbor graph has the shortest path to solve the traveling salesman problem.
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
	 * Restart the hill climbing algorithm x times in order to find a global maximum
	 *
	 * @param nbRestart is how many times we will restart the algorithm
	 * @param sol the solution found during the last restarting
	 * @return the solution of the problem
	 */
	private ArrayList<City> restart(int nbRestart, ArrayList<City> sol){
		if(nbRestart == 0) {
			return sol;
		}
		return restart(nbRestart - 1, this.solve());
	}

	/**
	 * Solve the traveling salesman problem
	 *
	 * @return a list of cities sorted to allow the salesman to take the shorter path according to the random restart
	 * hill-climbing search algorithm
	 */
	@Override
	public ArrayList<City> solve(){
		Travel init = new Travel(this.solution);
		CityMap sol = new CityMap(init.getCitiesList(), CityMap.constructGraph(init.getCitiesList()));
		double currentRoute = sol.totalDistance();
		CityMap neighbor = getBestNeighbors(this.getNeighbors());
		while(neighbor.totalDistance() < currentRoute) {
			sol = neighbor;
			currentRoute = sol.totalDistance();
			neighbor = getBestNeighbors(this.getNeighbors());
		}
		if(sol.totalDistance() < this.solution.totalDistance()) {
			this.solution = sol;
		}
		return sol.getMyCities();		
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

	public static void main(String[] args) {
		RandomRestartHillClimbingAlgorithm g = new RandomRestartHillClimbingAlgorithm();
		g.setCities(new CityMap(6, 500));

		g.restart(7, g.solution.getMyCities());
		System.out.println(g.solution.totalDistance());
		System.out.println(g.solution.getMyGraph());
	}

}

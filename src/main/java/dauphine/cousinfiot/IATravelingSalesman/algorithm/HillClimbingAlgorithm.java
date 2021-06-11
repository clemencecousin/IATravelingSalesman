package dauphine.cousinfiot.IATravelingSalesman.algorithm;

import java.util.ArrayList;

import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Travel;

public class HillClimbingAlgorithm implements TravelingSalesmanSolve {
	
	private CityMap solution;
	
	private ArrayList<CityMap> getNeighbors() {
		ArrayList<CityMap> neighbors = new ArrayList<CityMap>();
		for(int i = 0; i < this.solution.nbCities() ; i++) {
			for(int j = i+1; j < this.solution.nbCities() ; j++) {
				ArrayList<City> neighbor = (ArrayList<City>) solution.getMyCities().clone();
				neighbor.add(i, solution.getMyCities().get(j));
				neighbor.add(j, solution.getMyCities().get(i));
				neighbor.remove(i+1);
				neighbor.remove(j);
				neighbors.add(new CityMap(neighbor, CityMap.constructGraph(neighbor)));
			}
		}
		return neighbors;		
	}
	
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

	@Override
	public ArrayList<City> solve() {
		Travel init = new Travel(this.solution);
		this.solution = new CityMap(init.getCitiesList(), CityMap.constructGraph(init.getCitiesList()));
		double currentRoute = this.solution.totalDistance();
		CityMap bestNeighbor = getBestNeighbors(this.getNeighbors());
		while(bestNeighbor.totalDistance() < currentRoute) {
			this.solution = bestNeighbor;
			currentRoute = this.solution.totalDistance();
			bestNeighbor = getBestNeighbors(this.getNeighbors());
		}
		return this.solution.getMyCities();		
	}
	
	

}

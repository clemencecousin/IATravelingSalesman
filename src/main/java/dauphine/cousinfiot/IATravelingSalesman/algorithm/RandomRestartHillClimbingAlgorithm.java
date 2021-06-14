package dauphine.cousinfiot.IATravelingSalesman.algorithm;

import java.util.ArrayList;

import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Travel;

public class RandomRestartHillClimbingAlgorithm extends HillClimbingAlgorithm implements TravelingSalesmanSolve{

	private CityMap solution;
	private int ite;

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

	private ArrayList<City> restart(int nbRestart, ArrayList<City> sol){
		if(nbRestart == 0) {
			return sol;
		}
		return restart(nbRestart - 1, this.solve());
	}

	@Override
	public ArrayList<City> solve(){
		Travel init = new Travel(this.solution);
		CityMap sol = new CityMap(init.getCitiesList(), CityMap.constructGraph(init.getCitiesList()));
		double currentRoute = sol.totalDistance();
		CityMap neighbor = getBestNeighbors(this.getNeighbors());
		ite = 0;
		while(neighbor.totalDistance() < currentRoute) {
			ite++;
			sol = neighbor;
			currentRoute = sol.totalDistance();
			neighbor = getBestNeighbors(this.getNeighbors());
		}
		if(sol.totalDistance() < this.solution.totalDistance()) {
			this.solution = sol;
		}
		return this.solution.getMyCities();		
	}

	@Override
	public void setCities(CityMap cities) {
		this.solution = cities;
	}
	
	@Override
	public Travel getSolution() {
		Travel t = new Travel();
		t.setCities(solution);
		t.setCitiesList(solution.getMyCities());
		return t;
	}
	
	@Override
	public int getIteration() {
		return ite;
	}

	public static void main(String[] args) {
		RandomRestartHillClimbingAlgorithm g = new RandomRestartHillClimbingAlgorithm();
		g.setCities(new CityMap(6, 500));

		g.restart(7, g.solution.getMyCities());
		System.out.println(g.solution.totalDistance());
		System.out.println(g.solution.getMyGraph());
	}

}

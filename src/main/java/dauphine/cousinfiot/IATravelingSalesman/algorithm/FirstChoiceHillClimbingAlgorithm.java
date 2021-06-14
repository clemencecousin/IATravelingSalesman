package dauphine.cousinfiot.IATravelingSalesman.algorithm;

import java.util.ArrayList;

import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Travel;

public class FirstChoiceHillClimbingAlgorithm extends HillClimbingAlgorithm implements TravelingSalesmanSolve {
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

	private ArrayList<CityMap> betterThanCurrentState(ArrayList<CityMap> neighbors){
		ArrayList<CityMap> betterState = new ArrayList<>();
		for(CityMap c : neighbors) {
			if(c.totalDistance() < this.solution.totalDistance()) {
				betterState.add(c);
			}
		}
		return betterState;
	}

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
		FirstChoiceHillClimbingAlgorithm g = new FirstChoiceHillClimbingAlgorithm();
		g.setCities(new CityMap(6, 500));
		g.solve();
		System.out.println(g.solution.totalDistance());
		System.out.println(g.solution.getMyGraph());
	}
}

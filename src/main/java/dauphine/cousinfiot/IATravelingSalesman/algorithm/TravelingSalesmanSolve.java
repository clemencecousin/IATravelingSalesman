package dauphine.cousinfiot.IATravelingSalesman.algorithm;

import java.util.ArrayList;

import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Travel;

public interface TravelingSalesmanSolve {

	public ArrayList<City> solve();

	public Travel getSolution();

	public int getIteration();

}

/*
 * 
 */
package dauphine.cousinfiot.IATravelingSalesman.algorithm;

import java.util.ArrayList;

import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.Travel;

/**
 * The Interface TravelingSalesmanSolve implements the method needed to the algorithm.
 */
public interface TravelingSalesmanSolve {

	/**
	 * Solve the traveling salesman problem.
	 *
	 * @return the array list containing the solution.
	 */
	public ArrayList<City> solve();

	/**
	 * Gets the solution in Travel format.
	 *
	 * @return the travel
	 */
	public Travel getSolution();

	/**
	 * Gets the number of iterations.
	 *
	 * @return the number of iteration
	 */
	public int getIteration();

}

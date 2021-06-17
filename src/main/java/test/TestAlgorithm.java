package test;

import java.util.ArrayList;

import dauphine.cousinfiot.IATravelingSalesman.algorithm.FirstChoiceHillClimbing;
import dauphine.cousinfiot.IATravelingSalesman.algorithm.GeneticAlgorithm;
import dauphine.cousinfiot.IATravelingSalesman.algorithm.HillClimbingAlgorithm;
import dauphine.cousinfiot.IATravelingSalesman.algorithm.LocalBeamSearch;
import dauphine.cousinfiot.IATravelingSalesman.algorithm.RandomRestartHillClimbing;
import dauphine.cousinfiot.IATravelingSalesman.algorithm.SimulatedAnnealing;
import dauphine.cousinfiot.IATravelingSalesman.algorithm.StochasticHillClimbing;
import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;

public class TestAlgorithm {
	FirstChoiceHillClimbing firstChoice;
	HillClimbingAlgorithm hillClimbing;
	GeneticAlgorithm genetic;
	LocalBeamSearch localBeam;
	RandomRestartHillClimbing randomRestart;
	SimulatedAnnealing simulatedAnnealing;
	StochasticHillClimbing stochastic;
	
	public TestAlgorithm(CityMap c, int width) {
		this.firstChoice = new FirstChoiceHillClimbing();
		this.firstChoice.setCities(c);
		this.genetic = new GeneticAlgorithm(c);
		this.hillClimbing = new HillClimbingAlgorithm();
		this.hillClimbing.setCities(c);
		this.localBeam = new LocalBeamSearch(c, width);
		this.randomRestart = new RandomRestartHillClimbing();
		this.randomRestart.setCities(c);
		this.simulatedAnnealing = new SimulatedAnnealing(c);
		this.stochastic = new StochasticHillClimbing();	
		this.stochastic.setCities(c);
	}
	
	private ArrayList<Integer> testFirstChoice(double theoricalResult){
		ArrayList<Integer> results = new ArrayList<>();
		for(int i = 0; i < 100 ; i++) {
			ArrayList<City> firstChoiceCity = this.firstChoice.solve();
			CityMap firstChoiceResult = new CityMap(firstChoiceCity, CityMap.constructGraph(firstChoiceCity));
			if(firstChoiceResult.totalDistance()>= (theoricalResult - 5) && firstChoiceResult.totalDistance() <= (theoricalResult + 5)) {
				results.add(0);
			}
			else {
				results.add(1);
			}
		}
		return results;
	}
	
	private ArrayList<Integer> testHillClimbing(double theoricalResult){
		ArrayList<Integer> results = new ArrayList<>();
		for(int i = 0; i < 100 ; i++) {
			ArrayList<City> hillClimbingCity = this.hillClimbing.solve();
			CityMap hillClimbingResult = new CityMap(hillClimbingCity, CityMap.constructGraph(hillClimbingCity));
			if(hillClimbingResult.totalDistance()>= (theoricalResult - 5) && hillClimbingResult.totalDistance() <= (theoricalResult + 5)) {
				results.add(0);
			}
			else {
				results.add(1);
			}
		}
		return results;
	}
	
	private ArrayList<Integer> testGenetic(double theoricalResult){
		ArrayList<Integer> results = new ArrayList<>();
		for(int i = 0; i < 100 ; i++) {
			ArrayList<City> geneticCity = this.genetic.solve();
			CityMap geneticResult = new CityMap(geneticCity, CityMap.constructGraph(geneticCity));
			if(geneticResult.totalDistance()>= (theoricalResult - 5) && geneticResult.totalDistance() <= (theoricalResult + 5)) {
				results.add(0);
			}
			else {
				results.add(1);
			}
		}
		return results;
	}
	
	private ArrayList<Integer> testLocalBeam(double theoricalResult){
		ArrayList<Integer> results = new ArrayList<>();
		for(int i = 0; i < 100 ; i++) {
			ArrayList<City> localBeamCity = this.localBeam.solve();
			CityMap localBeamResult = new CityMap(localBeamCity, CityMap.constructGraph(localBeamCity));
			if(localBeamResult.totalDistance()>= (theoricalResult - 5) && localBeamResult.totalDistance() <= (theoricalResult + 5)) {
				results.add(0);
			}
			else {
				results.add(1);
			}
		}
		return results;
	}
	
	private ArrayList<Integer> testRandomRestart(double theoricalResult, int nbRestart){
		ArrayList<Integer> results = new ArrayList<>();
		for(int i = 0; i < 100 ; i++) {
			ArrayList<City> randomCity = this.randomRestart.restart(nbRestart);
			CityMap randomResult = new CityMap(randomCity, CityMap.constructGraph(randomCity));
			if(randomResult.totalDistance()>= (theoricalResult - 5) && randomResult.totalDistance() <= (theoricalResult + 5)) {
				results.add(0);
			}
			else {
				results.add(1);
			}
		}
		return results;
	}
	
	private ArrayList<Integer> testSimulatedAnnealing(double theoricalResult){
		ArrayList<Integer> results = new ArrayList<>();
		for(int i = 0; i < 100 ; i++) {
			ArrayList<City> simulatedCity = this.simulatedAnnealing.solve();
			CityMap simulatedResult = new CityMap(simulatedCity, CityMap.constructGraph(simulatedCity));
			if(simulatedResult.totalDistance()>= (theoricalResult - 5) && simulatedResult.totalDistance() <= (theoricalResult + 5)) {
				results.add(0);
			}
			else {
				results.add(1);
			}
		}
		return results;
	}
	
	private ArrayList<Integer> testStochasticHill(double theoricalResult){
		ArrayList<Integer> results = new ArrayList<>();
		for(int i = 0; i < 100 ; i++) {
			ArrayList<City> stochasticCity = this.stochastic.solve();
			CityMap stochasticResult = new CityMap(stochasticCity, CityMap.constructGraph(stochasticCity));
			if(stochasticResult.totalDistance()>= (theoricalResult - 5) && stochasticResult.totalDistance() <= (theoricalResult + 5)) {
				results.add(0);
			}
			else {
				results.add(1);
			}
		}
		return results;
	}
	
	public static int sum(ArrayList<Integer> t) {
		int error = 0;
		for(int i : t) {
			error = error + i;
		}
		return error;
	}
	
	public static void main(String[] args) {
		
		ArrayList<City> fourCities = new ArrayList<>();
		fourCities.add(new City(70,60));
		fourCities.add(new City(70,4));
		fourCities.add(new City(18,86));
		fourCities.add(new City(41,92));
		CityMap c4 = new CityMap(fourCities, CityMap.constructGraph(fourCities));
		
		ArrayList<City> sixCities = new ArrayList<>();
		sixCities.add(new City(62,65));
		sixCities.add(new City(70,68));
		sixCities.add(new City(87,91));
		sixCities.add(new City(99,74));
		sixCities.add(new City(1,2));
		sixCities.add(new City(9,20));
		CityMap c6 = new CityMap(sixCities, CityMap.constructGraph(sixCities));
		
		ArrayList<City> eightCities = new ArrayList<>();
		eightCities.add(new City(77,56));
		eightCities.add(new City(36,90));
		eightCities.add(new City(23,33));
		eightCities.add(new City(52,0));
		eightCities.add(new City(46,82));
		eightCities.add(new City(69,4));
		eightCities.add(new City(79,18));
		eightCities.add(new City(81,6));
		CityMap c8 = new CityMap(eightCities, CityMap.constructGraph(eightCities));
		
		ArrayList<City> tenCities = new ArrayList<>();
		tenCities.add(new City(50,96));
		tenCities.add(new City(56,3));
		tenCities.add(new City(93,76));
		tenCities.add(new City(67,5));
		tenCities.add(new City(42,40));
		tenCities.add(new City(86,37));
		tenCities.add(new City(88,13));
		tenCities.add(new City(80,68));
		tenCities.add(new City(73,52));
		tenCities.add(new City(98,70));
		CityMap c10 = new CityMap(tenCities, CityMap.constructGraph(tenCities));
		
		TestAlgorithm t1 = new TestAlgorithm(c4, 2);
		TestAlgorithm t2 = new TestAlgorithm(c6, 2);
		TestAlgorithm t3 = new TestAlgorithm(c8, 2);
		TestAlgorithm t4 = new TestAlgorithm(c10, 2);
		
		System.out.println("Pour 4 villes :");
		System.out.println("First choice hill climbing :" + sum(t1.testFirstChoice(218)) + "% d'erreur par rapport à la théorie");
		System.out.println("Genetic algorithm :" + sum(t1.testGenetic(218)) + "% d'erreur par rapport à la théorie");
		System.out.println("Hill climbing :" + sum(t1.testHillClimbing(218)) + "% d'erreur par rapport à la théorie");
		System.out.println("Local beam search :" + sum(t1.testLocalBeam(218)) + "% d'erreur par rapport à la théorie");
		System.out.println("Random restart hill climbing :" + sum(t1.testRandomRestart(218, 4)) + "% d'erreur par rapport à la théorie");
		System.out.println("Simulated annealing :" + sum(t1.testSimulatedAnnealing(218)) + "% d'erreur par rapport à la théorie");
		System.out.println("Stochastic hill climbing :" + sum(t1.testStochasticHill(218)) + "% d'erreur par rapport à la théorie");
		System.out.println();
		
		System.out.println("Pour 6 villes :");
		System.out.println("First choice hill climbing :" + sum(t2.testFirstChoice(269)) + "% d'erreur par rapport à la théorie");
		System.out.println("Genetic algorithm :" + sum(t2.testGenetic(269)) + "% d'erreur par rapport à la théorie");
		System.out.println("Hill climbing :" + sum(t2.testHillClimbing(269)) + "% d'erreur par rapport à la théorie");
		System.out.println("Local beam search :" + sum(t2.testLocalBeam(269)) + "% d'erreur par rapport à la théorie");
		System.out.println("Random restart hill climbing :" + sum(t2.testRandomRestart(269, 4)) + "% d'erreur par rapport à la théorie");
		System.out.println("Simulated annealing :" + sum(t2.testSimulatedAnnealing(269)) + "% d'erreur par rapport à la théorie");
		System.out.println("Stochastic hill climbing :" + sum(t2.testStochasticHill(269)) + "% d'erreur par rapport à la théorie");
		System.out.println();
		
		System.out.println("Pour 8 villes :");
		System.out.println("First choice hill climbing :" + sum(t3.testFirstChoice(235)) + "% d'erreur par rapport à la théorie");
		System.out.println("Genetic algorithm :" + sum(t3.testGenetic(235)) + "% d'erreur par rapport à la théorie");
		System.out.println("Hill climbing :" + sum(t3.testHillClimbing(235)) + "% d'erreur par rapport à la théorie");
		System.out.println("Local beam search :" + sum(t3.testLocalBeam(235)) + "% d'erreur par rapport à la théorie");
		System.out.println("Random restart hill climbing :" + sum(t3.testRandomRestart(235, 4)) + "% d'erreur par rapport à la théorie");
		System.out.println("Simulated annealing :" + sum(t3.testSimulatedAnnealing(235)) + "% d'erreur par rapport à la théorie");
		System.out.println("Stochastic hill climbing :" + sum(t3.testStochasticHill(235)) + "% d'erreur par rapport à la théorie");
		System.out.println();
		
		System.out.println("Pour 10 villes :");
		System.out.println("First choice hill climbing :" + sum(t4.testFirstChoice(266)) + "% d'erreur par rapport à la théorie");
		System.out.println("Genetic algorithm :" + sum(t4.testGenetic(266)) + "% d'erreur par rapport à la théorie");
		System.out.println("Hill climbing :" + sum(t4.testHillClimbing(266)) + "% d'erreur par rapport à la théorie");
		System.out.println("Local beam search :" + sum(t4.testLocalBeam(266)) + "% d'erreur par rapport à la théorie");
		System.out.println("Random restart hill climbing :" + sum(t4.testRandomRestart(266, 4)) + "% d'erreur par rapport à la théorie");
		System.out.println("Simulated annealing :" + sum(t4.testSimulatedAnnealing(266)) + "% d'erreur par rapport à la théorie");
		System.out.println("Stochastic hill climbing :" + sum(t4.testStochasticHill(266)) + "% d'erreur par rapport à la théorie");
		System.out.println();
	}

}

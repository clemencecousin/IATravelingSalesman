package dauphine.cousinfiot.IATravelingSalesman.graphInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import dauphine.cousinfiot.IATravelingSalesman.algorithm.SimulatedAnnealing;
import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;

/**
 * Controller class to implement action when the button to select game type is
 * clicked.
 *
 */
public class AnnealingButtonController implements ActionListener {
	private CityMap cities;
	private Graphics g;
	private Container container;

	/**
	 * Constructor for ButtonController class. Put an
	 * {@link java.awt.event.ActionListener ActionListener} on a button.
	 * 
	 * @param button a {@link javax.swing.JButton JButton} which perform actions
	 */
	public AnnealingButtonController(JButton button, CityMap cities, Graphics g, Container container) {
		button.addActionListener(this);
		this.cities = cities;
		this.g = g;
		this.container = container;
	}

	/**
	 * When button is clicked, the actual frame is closed and the grid is displayed
	 * with the right game type. It starts a new game.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		container.update(g);
		SimulatedAnnealing anneal = new SimulatedAnnealing(cities);

		ArrayList<City> sol = anneal.solve();
		System.out.println(cities.getMyCities());
		System.out.println(sol);
		for (int i = 0; i < cities.getMyCities().size() - 1; i++) {
			g.drawLine(sol.get(i).getX(), sol.get(i).getY(), sol.get(i + 1).getX(), sol.get(i + 1).getY());
		}

		g.drawLine(sol.get(0).getX(), sol.get(0).getY(), sol.get(cities.getMyCities().size() - 1).getX(),
				sol.get(cities.getMyCities().size() - 1).getY());

		g.setColor(Color.white);
		g.fillRect(0, 720, 300, 25);
		g.setColor(Color.black);
		g.drawString("Total distance = " + Math.round(anneal.getSolution().totalDistance())
				+ " --- Number of iterations = " + anneal.getIteration(), 5, 735);
	}
}

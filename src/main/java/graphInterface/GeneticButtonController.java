package graphInterface;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import dauphine.cousinfiot.IATravelingSalesman.algorithm.GeneticAlgorithm;
import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;

/**
 * Controller class to implement action when the button to select game type is
 * clicked.
 *
 */
public class GeneticButtonController implements ActionListener {
	private final JButton button;
	private CityMap cities;
	private Graphics g;

	/**
	 * Constructor for ButtonController class. Put an
	 * {@link java.awt.event.ActionListener ActionListener} on a button.
	 * 
	 * @param button a {@link javax.swing.JButton JButton} which perform actions
	 */
	public GeneticButtonController(JButton button, CityMap cities, Graphics g) {
		button.addActionListener(this);
		this.button = button;
		this.cities = cities;
		this.g = g;
	}

	/**
	 * When button is clicked, the actual frame is closed and the grid is displayed
	 * with the right game type. It starts a new game.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		GeneticAlgorithm gen = new GeneticAlgorithm(cities);

		ArrayList<City> sol = gen.solve();
		for (int i = 0; i < cities.getMyCities().size() - 1; i++) {
			g.drawLine(sol.get(i).getX(), sol.get(i).getY(), sol.get(i+1).getX(), sol.get(i+1).getY());
		}

		g.drawLine(sol.get(0).getX(), sol.get(0).getY(), sol.get(cities.getMyCities().size() - 1).getX(), sol.get(cities.getMyCities().size() - 1).getY());
		
		System.out.println(button.getParent().getParent().getParent());
	}
}

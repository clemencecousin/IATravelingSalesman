package graphInterface;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import dauphine.cousinfiot.IATravelingSalesman.architecture.City;
import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;

/**
 * User Interface which only display the grid. Is called by GameGUI.
 *
 */
public class GridDisplay extends JPanel {
	private CityMap cities;

	/**
	 * The grid depends on the gameType.
	 * 
	 * @param gameType a
	 *                 {@link fr.dauphine.ja.CartierFiot.MorpionSolitaire.Model.GameType
	 *                 GameType} which can be 5D or 5T.
	 */
	public GridDisplay(CityMap cities) {
		this.cities = cities;
	}

	/**
	 * Paint the grid, displayed : the grid, the already added pawn, the already
	 * added line and the invisible pawn which can be added.
	 */
	@Override
	public void paint(Graphics g) {
		for (City c : cities.getMyCities()) {
			g.drawOval(c.getX(), c.getY(), 5, 5);
			g.drawString(c.toString(), c.getX(), c.getY() + 15);
		}

		setLayout(null);
	}
}

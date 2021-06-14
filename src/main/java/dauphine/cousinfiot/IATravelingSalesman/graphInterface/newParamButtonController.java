package dauphine.cousinfiot.IATravelingSalesman.graphInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;

/**
 * Controller class to implement action when the button to select game type is
 * clicked.
 *
 */
public class newParamButtonController implements ActionListener {
	private final JButton button;

	/**
	 * Constructor for ButtonController class. Put an
	 * {@link java.awt.event.ActionListener ActionListener} on a button.
	 * 
	 * @param button a {@link javax.swing.JButton JButton} which perform actions
	 */
	public newParamButtonController(JButton button) {
		button.addActionListener(this);
		this.button = button;
	}

	/**
	 * When button is clicked, the actual frame is closed and the grid is displayed
	 * with the right game type. It starts a new game.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String numberCities = JOptionPane.showInputDialog("Number of cities: ", 10);
		new TravelGUI(new CityMap(Integer.parseInt(numberCities), 400));
	}
}

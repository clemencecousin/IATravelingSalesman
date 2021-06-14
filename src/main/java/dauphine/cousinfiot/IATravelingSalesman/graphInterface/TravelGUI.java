package dauphine.cousinfiot.IATravelingSalesman.graphInterface;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import dauphine.cousinfiot.IATravelingSalesman.architecture.CityMap;

/**
 * User interface which display the grid and a side panel containing other
 * information on the game. Contains a grid, the type of game, an help option to
 * display possible moves, an undo option to remove the last added pawn and an
 * option to finish randomly the game.
 */
public class TravelGUI {
	private CityMap cities;
//	private JFormattedTextField numberCities;

	public TravelGUI(CityMap cities) {
		this.cities = cities;

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		layout.columnWidths = new int[] { 800, 200 };
		layout.rowHeights = new int[] { 800 };
		layout.columnWeights = new double[] { 1, 1 };
		layout.rowWeights = new double[] { 1 };

		JFrame frame = new JFrame("Traveling Salesman");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		frame.setLayout(layout);

		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;

		JPanel myGrid = new GridDisplay(cities);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		layout.setConstraints(myGrid, constraints);

		JPanel settingsPanel = new JPanel(new GridLayout(4, 1));
		settingsPanel.setVisible(true);

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		JLabel title = new JLabel("Traveling Salesman");
		titlePanel.add(title);
		settingsPanel.add(titlePanel);
		JButton newParamButton = new JButton("Set new parameters");
		titlePanel.add(newParamButton);

		JPanel GeneticPanel = new JPanel();
		GeneticPanel.setBackground(Color.WHITE);
		JLabel geneticAlgoHelp = new JLabel("Run genetic algorithm : ");
		geneticAlgoHelp.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
		JButton geneticAlgoButton = new JButton("Genetic Algorithm");
		GeneticPanel.add(geneticAlgoHelp);
		GeneticPanel.add(geneticAlgoButton);
		settingsPanel.add(GeneticPanel);

		JPanel annealingPanel = new JPanel();
		annealingPanel.setBackground(Color.WHITE);
		JLabel printAnnealing = new JLabel("Run simulated annealing algorithm :");
		printAnnealing.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
		JButton annealingButton = new JButton("Simulated annealing");
		annealingPanel.add(printAnnealing);
		annealingPanel.add(annealingButton);
		settingsPanel.add(annealingPanel);

		JPanel beamSearchPanel = new JPanel();
		beamSearchPanel.setBackground(Color.WHITE);
		JLabel beamSearch = new JLabel("Run local beam search algorithm :");
		JButton beamSearchButton = new JButton("Local beam search");
		beamSearchPanel.add(beamSearch);
		beamSearchPanel.add(beamSearchButton);
		settingsPanel.add(beamSearchPanel);

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		layout.setConstraints(settingsPanel, constraints);

		frame.add(myGrid);
		frame.add(settingsPanel);

		System.out.println(frame);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		new newParamButtonController(newParamButton);
		new GeneticButtonController(geneticAlgoButton, cities, myGrid.getParent().getGraphics(), myGrid.getParent());
		new AnnealingButtonController(annealingButton, cities, myGrid.getParent().getGraphics(), myGrid.getParent());
		new BeamButtonController(beamSearchButton, cities, myGrid.getParent().getGraphics(), myGrid.getParent());
	}

	public static void main(String[] args) {
		new TravelGUI(new CityMap(6, 600));
	}
}

package com.mr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JPanel {

	private static final long serialVersionUID = 1L;

	private MapHolderDrawer mapHolderDrawer;
	private MapHolder mapHolder;
	private View view;
	private CountriesHolder countriesHolder;
	private Simulation simulation;

	private BufferedImage img; // image of world where borders are black and

	// each country has different color

	public Main() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		try {
			img = ImageIO.read(getClass().getResource("map2.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		countriesHolder = new CountriesHolder(img);
		mapHolder = new MapHolder();

		mapHolderDrawer = new MapHolderDrawer();
		view = new View(mapHolderDrawer, mapHolder, img);
		add(view);

		JButton sim1Button = new JButton("total random");
		sim1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				simulation = new TotalRandomSimulation(mapHolder, countriesHolder, img.getWidth(),
						img.getHeight());
			}
		});
		add(sim1Button);

		JButton artButton = new JButton("art");
		artButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				simulation = new ArticleSimulation(0.5, mapHolder, countriesHolder, img.getWidth(),
						img.getHeight(), new DistanceFunction() {

							@Override
							public double invoke(double value) {
								return value;
							}
						});
			}
		});
		add(artButton);

		JButton degreesButton = new JButton("print degrees");
		degreesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Degrees");
				TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
				for (City city : mapHolder.getCities()) {
					int deg = mapHolder.getDegree(city);
					if (map.get(deg) != null) {
						map.put(deg, map.get(deg) + 1);
					} else {
						map.put(deg, 1);
					}
				}
				for (Integer i : map.descendingKeySet()) {
					System.out.println(i + " " + map.get(i));
				}
			}
		});
		add(degreesButton);

		JButton stepButton = new JButton("Step");
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (simulation == null) {
					JOptionPane.showMessageDialog(Main.this, "Select simulation first!");
					return;
				}

				simulation.step();
				view.repaint();
				// System.out.println("---------------------------\n" +
				// mapHolder);
			}
		});
		add(stepButton);

	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Airport network");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main app = new Main();

		frame.getContentPane().add(app);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}

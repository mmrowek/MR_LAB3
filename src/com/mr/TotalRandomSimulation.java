package com.mr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TotalRandomSimulation implements Simulation {

	private double p = 0.5;
	private Random random = new Random();

	private MapHolder mapHolder;
	private CountriesHolder countriesHolder;
	private int xSize;
	private int ySize;

	public TotalRandomSimulation(MapHolder mapHolder, CountriesHolder countriesHolder, int xSize,
			int ySize) {

		this.mapHolder = mapHolder;
		this.countriesHolder = countriesHolder;
		this.xSize = xSize;
		this.ySize = ySize;
	}

	List<City> getRandomCities(int counter) {

		ArrayList<City> chosen = new ArrayList<City>();

		if (!mapHolder.getCities().isEmpty()) {
			ArrayList<City> cities = new ArrayList<City>(mapHolder.getCities());
			for (int i = 0; i < counter; ++i) {
				int n = random.nextInt(cities.size());
				chosen.add(cities.get(n));
				cities.remove(n);
			}
		}
		return chosen;
	}

	@Override
	public void step() {
		double r = random.nextDouble();

		if (r > p || mapHolder.getCities().size() < 2) {
			// add new city and connection
			int x = random.nextInt(xSize);
			int y = random.nextInt(ySize);
			City city = new City(x, y);
			mapHolder.addCity(city);

			int counter = random.nextInt(mapHolder.getCities().size());

			for (City city2 : getRandomCities(counter)) {
				mapHolder.addConnection(city, city2);
			}

		} else {
			// add connection between existing cities

			List<City> list = getRandomCities(2);
			mapHolder.addConnection(list.get(0), list.get(1));
		}

	}

}

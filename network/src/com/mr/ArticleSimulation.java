package com.mr;

import java.util.Random;

public class ArticleSimulation implements Simulation {

	private double p = 0.5;
	private int m = 1;
	private Random random = new Random();

	private MapHolder mapHolder;
	private CountriesHolder countriesHolder;
	private int xSize;
	private int ySize;
	private DistanceFunction distanceFunction;

	public ArticleSimulation(double p, MapHolder mapHolder, CountriesHolder countriesHolder,
			int xSize, int ySize, DistanceFunction distanceFunction) {
		super();
		this.p = p;
		this.mapHolder = mapHolder;
		this.countriesHolder = countriesHolder;
		this.xSize = xSize;
		this.ySize = ySize;
		this.distanceFunction = distanceFunction;
	}

	private double computeProp(City city1, City city2) {
		return mapHolder.getDegree(city2) / distanceFunction.invoke(city2.getDistance(city1));
	}

	private double computePropConn(City city1, City city2) {
		return mapHolder.getDegree(city1) * mapHolder.getDegree(city2)
				/ distanceFunction.invoke(city2.getDistance(city1));
	}

	private void addNewCity() {
		City city = null;

		do {
			int x = random.nextInt(xSize);
			int y = random.nextInt(ySize);
			city = new City(x, y);
		} while (!countriesHolder.addCity(city));
		mapHolder.addCity(city);

		double propSum = 0.0;
		for (City city2 : mapHolder.getCities()) {
			if (!city.equals(city2)) {
				propSum += computeProp(city, city2);
			}
		}
		double val = random.nextDouble() * propSum;
		System.out.println("propSUm = " + propSum);
		propSum = 0.0;

		// m
		for (City city2 : mapHolder.getCities()) {
			if (!city.equals(city2)) {
				double prop = computeProp(city, city2);
				propSum += prop;
				if (val <= propSum) {
					mapHolder.addConnection(city, city2);
					// po m razach break
					break;
				}
			}
		}
	}

	private void addConnections() {
		double propSum = 0.0;
		// to trzeba napisac efektywniej
		for (City city1 : mapHolder.getCities()) {
			for (City city2 : mapHolder.getCities()) {
				if (!city1.equals(city2) && !mapHolder.getConnections(city1).contains(city2)) {
					propSum += computePropConn(city1, city2);
				}
			}
		}
		System.out.println("propSUm = " + propSum);
		double val = random.nextDouble() * propSum;
		propSum = 0.0;

		
		for (City city1 : mapHolder.getCities()) {
			for (City city2 : mapHolder.getCities()) {
				if (!city1.equals(city2) && !mapHolder.getConnections(city1).contains(city2)) {
					propSum += computePropConn(city1, city2);
					if (val <= propSum) {
						mapHolder.addConnection(city1, city2);
						System.out.println("added " + propSum);
						return;
					}
				}
			}
		}
	}

	@Override
	public void step() {
		double r = random.nextDouble();

		if (r > p || mapHolder.getCities().size() < 2) {
			// add new city and connection
			addNewCity();
			System.out.println("adding new city and connection");
		} else {
			// add connection between existing cities
			addConnections();
			System.out.println("adding connection between existing cities");
		}

	}

}

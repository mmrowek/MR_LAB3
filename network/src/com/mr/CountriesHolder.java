package com.mr;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CountriesHolder {
	private BufferedImage img;
	private Map<Integer, ArrayList<City>> countries = new HashMap<Integer, ArrayList<City>>();

	public CountriesHolder(BufferedImage img) {
		this.img = img;
		for (int x = 0; x < img.getWidth(); ++x) {
			for (int y = 0; y < img.getHeight(); ++y) {
				int col = img.getRGB(x, y);

				if (col != Color.BLACK.getRGB()) {
					countries.put(col, new ArrayList<City>());
				}
			}
		}
		System.out.println("countries: " + countries.size());
	}

	public BufferedImage getImage() {
		return img;
	}

	public boolean inTheSameCountry(City c1, City c2) {
		return img.getRGB(c1.getX(), c1.getY()) == img.getRGB(c2.getX(), c2.getY());
	}

	public boolean addCity(City city) {
		int col = img.getRGB(city.getX(), city.getY());
		if (col != Color.BLACK.getRGB()) {
			countries.get(col).add(city);
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<City> getCitiesInTheSameCountry(City city) {
		int col = img.getRGB(city.getX(), city.getY());
		return countries.get(col);
	}
}

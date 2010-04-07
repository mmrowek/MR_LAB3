package com.mr;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class CountriesHolder {
	private BufferedImage img;
	private Set<Integer> countries = new HashSet<Integer>();

	public CountriesHolder(BufferedImage img) {
		this.img = img;
		for (int x = 0; x < img.getWidth(); ++x) {
			for (int y = 0; y < img.getHeight(); ++y) {
				int col = img.getRGB(x, y);

				if (col != Color.BLACK.getRGB()) {
					countries.add(col);
				}
			}
		}
		System.out.println("countries: " + countries.size());
		for (Integer i : countries) {
			System.out.println(i);
		}

	}

	public BufferedImage getImage() {
		return img;
	}

	public boolean inTheSameCountry(City c1, City c2) {
		return img.getRGB(c1.getX(), c1.getY()) == img.getRGB(c2.getX(), c2.getY());
	}
}

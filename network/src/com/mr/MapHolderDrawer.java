package com.mr;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MapHolderDrawer {

	private Color cityColor = Color.red;
	private Color connectionColor = Color.green;
	private int pointWidth = 10;
	private int pointHight = 10;

	public MapHolderDrawer(Color cityColor, Color connectionColor, int pointWidth, int pointHight) {
		this.cityColor = cityColor;
		this.connectionColor = connectionColor;
		this.pointWidth = pointWidth;
		this.pointHight = pointHight;
	}

	public MapHolderDrawer() {
	}

	public void draw(MapHolder mapHolder, Graphics g) {
		int ppW = pointWidth / 2;
		int ppH = pointHight / 2;

		Graphics2D g2 = (Graphics2D) g;
		g.setColor(connectionColor);
		for (City city1 : mapHolder.getCities()) {
			for (City city2 : mapHolder.getConnections(city1)) {
				g2.drawLine(city1.getX() + ppW, city1.getY() + ppH, city2.getX() + ppW, city2
						.getY()
						+ ppH);
			}
		}
		g.setColor(cityColor);
		for (City city1 : mapHolder.getCities()) {
			g2.fillOval(city1.getX(), city1.getY(), pointWidth, pointHight);
		}
	}

}

package com.mr;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class View extends Component {

	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private MapHolderDrawer mapHolderDrawer;
	private MapHolder mapHolder;
	BufferedImage img;

	public View(MapHolderDrawer mapHolderDrawer, MapHolder mapHolder, BufferedImage img) {
		super();
		this.mapHolderDrawer = mapHolderDrawer;
		this.mapHolder = mapHolder;
		this.img = img;

		y = img.getHeight();
		x = img.getWidth();
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
		mapHolderDrawer.draw(mapHolder, g);
	}

	public Dimension getPreferredSize() {
		return new Dimension(x, y);
	}
}

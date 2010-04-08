package com.mr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Holds cities and connections between them
 */

public class MapHolder {
	private Map<City, HashSet<City>> connections = new HashMap<City, HashSet<City>>();

	public void addCity(City city) {
		connections.put(city, new HashSet<City>());
	}

	public void removeCity(City city) {
		connections.remove(city);
	}

	public void addConnection(City c1, City c2) {
		if (connections.get(c1) != null && connections.get(c2) != null) {
			connections.get(c1).add(c2);
			connections.get(c2).add(c1);
		} else {
			throw new RuntimeException("Can't add connection: city pair doesn't exist");
		}
	}

	public void removeConnection(City c1, City c2) {
		if (connections.get(c1) != null && connections.get(c2) != null) {
			connections.get(c1).remove(c2);
			connections.get(c2).remove(c1);
		} else {
			throw new RuntimeException("Can't remove connection: city pair doesn't exist");
		}
	}

	public int getDegree(City city) {
		return connections.get(city).size();
	}

	public Set<City> getConnections(City city) {
		return connections.get(city);
	}

	public Set<City> getCities() {
		return connections.keySet();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (City city : getCities()) {
			sb.append(city);
			sb.append(':');
			for (City con : getConnections(city)) {
				sb.append(con);
				sb.append(", ");
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}

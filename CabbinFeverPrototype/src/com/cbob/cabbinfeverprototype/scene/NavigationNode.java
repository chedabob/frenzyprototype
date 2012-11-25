package com.cbob.cabbinfeverprototype.scene;

import android.graphics.Point;

public class NavigationNode {
	private NavigationNode north, east, south, west;
	private Point location;
	private int id;

	public NavigationNode (int id) {
		this.id = id;
	}
	
	public void setNorth(NavigationNode north) {
		this.north = north;
	}

	public void setEast(NavigationNode east) {
		this.east = east;
	}

	public void setSouth(NavigationNode south) {
		this.south = south;
	}

	public void setWest(NavigationNode west) {
		this.west = west;
	}
	public void setLocation (Point p){
		location = p;
	}
	public NavigationNode getNorth() {
		return north;
	}

	public NavigationNode getEast() {
		return east;
	}

	public NavigationNode getSouth() {
		return south;
	}

	public NavigationNode getWest() {
		return west;
	}

	public Point getLocation() {
		return location;
	}
}

package com.cbob.cabbinfeverprototype.scene;

import java.util.Vector;

import android.graphics.Point;

public class NavigationNode {
	private Point location;
	private int id;
	private Vector<NavigationNode> connections;

	public NavigationNode (int id) {
		this.id = id;
		connections = new Vector<NavigationNode>();
	}
	
	public void addConnection (NavigationNode n)
	{
		connections.add(n);
		sortConnections();
	}
	
	private void sortConnections ()
	{
		// Was supposed to sort them clockwise, not sure if necessary
	}
	
	@SuppressWarnings("unchecked")
	public Vector <NavigationNode> getConnections ()
	{
		return (Vector<NavigationNode>) connections.clone();
	}
	public void setLocation (Point p){
		location = p;
	}

	public Point getLocation() {
		return location;
	}
}

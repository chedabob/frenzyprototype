package com.cbob.cabbinfeverprototype.navigation;

import java.util.Vector;

import android.graphics.Point;

public class NavigationNode {
	private Point location;
	private int id;
	private Vector<NavigationNode> connections;
	private boolean disabled = false;
	
	public NavigationNode (int id) {
		this.id = id;
		connections = new Vector<NavigationNode>();
	}
	
	public int getID ()
	{
		return id;
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
	
	public boolean isDisabled ()
	{
		return disabled;
	}
	
	public void setDisabled (boolean dis)
	{
		disabled = dis;
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

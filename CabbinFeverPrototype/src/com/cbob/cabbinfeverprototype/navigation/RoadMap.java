package com.cbob.cabbinfeverprototype.navigation;

import java.util.Vector;

import com.cbob.cabbinfeverprototype.canvasrenderer.Helpers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class RoadMap {
	Vector<NavigationNode> nodes;
	Bitmap output;
	private final int ROAD_WIDTH = 20;

	@SuppressWarnings("unchecked")
	public Vector<NavigationNode> getNodes() {
		return (Vector<NavigationNode>) nodes.clone();
	}

	public Bitmap getSceneBitmap() {
		return output;
	}

	public void buildTestNodes() {

		nodes = new Vector<NavigationNode>();
		for (int i = 0; i < 9; i++) {
			nodes.add(new NavigationNode(i));
		}

		NavigationNode nav1 = nodeForID(0);
		nav1.setLocation(new Point(50, 50));
		NavigationNode nav2 = nodeForID(1);
		nav2.setLocation(new Point(150, 50));
		NavigationNode nav3 = nodeForID(2);
		nav3.setLocation(new Point(250, 50));
		NavigationNode nav4 = nodeForID(3);
		nav4.setLocation(new Point(50, 150));
		NavigationNode nav5 = nodeForID(4);
		nav5.setLocation(new Point(160, 175));
		NavigationNode nav6 = nodeForID(5);
		nav6.setLocation(new Point(250, 150));
		NavigationNode nav7 = nodeForID(6);
		nav7.setLocation(new Point(50, 250));
		NavigationNode nav8 = nodeForID(7);
		nav8.setLocation(new Point(150, 250));
		NavigationNode nav9 = nodeForID(8);
		nav9.setLocation(new Point(275, 290));

		nav1.addConnection(nodeForID(1));
		nav1.addConnection(nodeForID(3));

		nav2.addConnection(nodeForID(0));
		nav2.addConnection(nodeForID(4));
		nav2.addConnection(nodeForID(2));

		nav3.addConnection(nodeForID(1));
		nav3.addConnection(nodeForID(5));

		nav4.addConnection(nodeForID(0));
		nav4.addConnection(nodeForID(6));
		nav4.addConnection(nodeForID(4));

		nav5.addConnection(nodeForID(3));
		nav5.addConnection(nodeForID(5));
		nav5.addConnection(nodeForID(1));
		nav5.addConnection(nodeForID(7));

		nav6.addConnection(nodeForID(4));
		nav6.addConnection(nodeForID(2));
		nav6.addConnection(nodeForID(8));

		nav7.addConnection(nodeForID(3));
		nav7.addConnection(nodeForID(7));

		nav8.addConnection(nodeForID(6));
		nav8.addConnection(nodeForID(8));
		nav8.addConnection(nodeForID(4));

		nav9.addConnection(nodeForID(7));

		nav9.addConnection(nodeForID(5));

	}

	public NavigationNode nodeForID(int id) {
		for (NavigationNode n : nodes) {
			if (n.getID() == id)
				return n;
		}
		return null;
	}

	public Vector<NavigationNode> navigateToNodeFromNode (NavigationNode start, NavigationNode end)
	{
		Vector <NavigationNode> points = new Vector<NavigationNode>();
		points.add(start);
		
		Point endLoc = end.getLocation();
		
		NavigationNode current = start;
		
		while (current != end)
		{
			NavigationNode candidate = null;
			
			for (NavigationNode n: current.getConnections())
			{
				if (candidate == null) {
					candidate = n;
					continue;
				}
				if (points.contains(n))
					continue;
				int nDistance = Helpers.distanceBetweenTwoPoints(n.getLocation(), endLoc);
				int candidateDistance = Helpers.distanceBetweenTwoPoints(candidate.getLocation(), endLoc);
				if (nDistance < candidateDistance )
					candidate = n;
			}
			
			current = candidate;
			points.add(current);
		}
		return points;
	}

	public NavigationNode nodeForPoint(Point p, int threshold) {
		for (NavigationNode n : nodes) {
			Point nLocation = n.getLocation();
			int distance = Helpers.distanceBetweenTwoPoints(nLocation, p);

			if (distance < threshold)
				return n;

		}
		return null;
	}

	public void buildBitmap(int width, int height) {
		output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(output);
		Paint p = new Paint();
		p.setAntiAlias(true);

		p.setColor(Color.BLACK);
		c.drawRect(new Rect(0, 0, c.getWidth(), c.getHeight()), p);

		p.setColor(Color.LTGRAY);
		// Draw the junction's kerbs
		for (NavigationNode n : nodes) {
			Point nLoc = n.getLocation();
			c.drawCircle(nLoc.x, nLoc.y, (ROAD_WIDTH / 2), p);
		}

		p.setColor(Color.GRAY);
		// Draw the junctions
		for (NavigationNode n : nodes) {
			Point nLoc = n.getLocation();
			c.drawCircle(nLoc.x, nLoc.y, (ROAD_WIDTH / 2) - 2, p);
		}

		p.setColor(Color.LTGRAY);
		p.setStrokeWidth(ROAD_WIDTH);

		// Draw the road's kerbs
		for (NavigationNode n : nodes) {
			Point nLoc = n.getLocation();
			for (NavigationNode connection : n.getConnections()) {
				c.drawLine(nLoc.x, nLoc.y, connection.getLocation().x,
						connection.getLocation().y, p);
			}
		}

		p.setColor(Color.GRAY);
		p.setStrokeWidth(ROAD_WIDTH - 4);
		// Draw the roads
		for (NavigationNode n : nodes) {
			Point nLoc = n.getLocation();
			for (NavigationNode connection : n.getConnections()) {
				c.drawLine(nLoc.x, nLoc.y, connection.getLocation().x,
						connection.getLocation().y, p);
			}
		}

		p.setColor(Color.WHITE);
		// Draw roundabouts
		for (NavigationNode n : nodes) {
			if (n.getConnections().size() > 3) {
				Point nLoc = n.getLocation();
				c.drawCircle(nLoc.x, nLoc.y, (ROAD_WIDTH / 4), p);
			}

		}
	}

	public Vector<NavigationNode> navigateFromAToB(NavigationNode a,
			NavigationNode b) {
		Vector<NavigationNode> set = new Vector<NavigationNode>();
		if (a == b) {
			set.add(a);
			return set;
		}

		return set;
	}
}

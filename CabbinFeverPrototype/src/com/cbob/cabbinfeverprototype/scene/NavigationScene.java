package com.cbob.cabbinfeverprototype.scene;

import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class NavigationScene {
	Vector<NavigationNode> nodes;
	Bitmap output;
	private final int ROAD_WIDTH = 20;

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

		NavigationNode nav1 = nodes.get(0);
		nav1.setLocation(new Point(50, 50));
		NavigationNode nav2 = nodes.get(1);
		nav2.setLocation(new Point(150, 50));
		NavigationNode nav3 = nodes.get(2);
		nav3.setLocation(new Point(250, 50));
		NavigationNode nav4 = nodes.get(3);
		nav4.setLocation(new Point(50, 150));
		NavigationNode nav5 = nodes.get(4);
		nav5.setLocation(new Point(160, 175));
		NavigationNode nav6 = nodes.get(5);
		nav6.setLocation(new Point(250, 150));
		NavigationNode nav7 = nodes.get(6);
		nav7.setLocation(new Point(50, 250));
		NavigationNode nav8 = nodes.get(7);
		nav8.setLocation(new Point(150, 250));
		NavigationNode nav9 = nodes.get(8);
		nav9.setLocation(new Point(275, 290));

		nav1.addConnection(nodes.get(1));
		nav1.addConnection(nodes.get(3));

		// nav2.addConnection(nodes.get(0));
		nav2.addConnection(nodes.get(4));
		nav2.addConnection(nodes.get(2));

		nav3.addConnection(nodes.get(1));
		nav3.addConnection(nodes.get(5));

		nav4.addConnection(nodes.get(0));
		nav4.addConnection(nodes.get(6));
		nav4.addConnection(nodes.get(4));

		nav5.addConnection(nodes.get(3));
		nav5.addConnection(nodes.get(5));
		nav5.addConnection(nodes.get(1));
		nav5.addConnection(nodes.get(7));

		nav6.addConnection(nodes.get(4));
		nav6.addConnection(nodes.get(2));
		nav6.addConnection(nodes.get(8));

		nav7.addConnection(nodes.get(3));
		nav7.addConnection(nodes.get(7));

		nav8.addConnection(nodes.get(6));
		nav8.addConnection(nodes.get(8));
		nav8.addConnection(nodes.get(4));

		nav9.addConnection(nodes.get(7));

		nav9.addConnection(nodes.get(5));

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
			c.drawCircle(nLoc.x, nLoc.y, (ROAD_WIDTH / 2) , p);
		}
		p.setColor(Color.GRAY);
		// Draw the junctions 
		for (NavigationNode n : nodes) {
			Point nLoc = n.getLocation();
			c.drawCircle(nLoc.x, nLoc.y, (ROAD_WIDTH / 2) - 2, p);
		}
		
		p.setColor(Color.LTGRAY);
		p.setStrokeWidth(ROAD_WIDTH);

		// Draw the kerbs
		for (NavigationNode n : nodes) {
			Point nLoc = n.getLocation();
			for (NavigationNode connection: n.getConnections())
			{
				c.drawLine(nLoc.x, nLoc.y, connection.getLocation().x, connection.getLocation().y, p);
			}
		}
		
		p.setColor(Color.GRAY);
		p.setStrokeWidth(ROAD_WIDTH - 4);
		// Draw the roads
		for (NavigationNode n : nodes) {
			Point nLoc = n.getLocation();
			for (NavigationNode connection: n.getConnections())
			{
				c.drawLine(nLoc.x, nLoc.y, connection.getLocation().x, connection.getLocation().y, p);
			}
		}
		
		p.setColor(Color.WHITE);
		// Draw the roads
		for (NavigationNode n : nodes) {
			if (n.getConnections().size() > 3)
			{
				Point nLoc = n.getLocation();
				c.drawCircle(nLoc.x, nLoc.y, (ROAD_WIDTH / 4), p);
			}

		}
	}
}

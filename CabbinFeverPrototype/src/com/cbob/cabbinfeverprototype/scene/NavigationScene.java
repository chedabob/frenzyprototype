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
	
	public Vector<NavigationNode> getNodes ()
	{
		return (Vector<NavigationNode>) nodes.clone();
	}
	
	public Bitmap getSceneBitmap ()
	{
		return output;
	}
	public void buildTestNodes() {

		nodes = new Vector<NavigationNode>();
		for (int i = 0; i < 9; i++) {
			nodes.add(new NavigationNode(i));
		}
		
		NavigationNode nav1 = nodes.get(0);
		//nav1.setEast(nodes.get(1));
		nav1.setSouth(nodes.get(3));
		nav1.setLocation(new Point(50, 50));
		
		NavigationNode nav2 = nodes.get(1);
		//nav2.setWest(nodes.get(0));
		nav2.setSouth(nodes.get(4));
		nav2.setEast(nodes.get(2));
		nav2.setLocation(new Point(150, 50));
		
		NavigationNode nav3 = nodes.get(2);
		nav3.setWest(nodes.get(1));
		nav3.setSouth(nodes.get(5));
		nav3.setLocation(new Point(250, 50));
		
		NavigationNode nav4 = nodes.get(3);
		nav4.setNorth(nodes.get(0));
		nav4.setSouth(nodes.get(6));
		nav4.setEast(nodes.get(4));
		nav4.setLocation(new Point(50, 150));
		
		NavigationNode nav5 = nodes.get(4);
		nav5.setWest(nodes.get(3));
		nav5.setEast(nodes.get(5));
		nav5.setNorth(nodes.get(1));
		nav5.setSouth(nodes.get(7));
		nav5.setLocation(new Point(150,150));
		
		NavigationNode nav6 = nodes.get(5);
		nav6.setWest(nodes.get(4));
		nav6.setNorth(nodes.get(2));
		nav6.setSouth(nodes.get(8));
		nav6.setLocation(new Point(250,150));

		NavigationNode nav7 = nodes.get(6);
		nav7.setNorth(nodes.get(3));
		nav7.setEast(nodes.get(7));
		nav7.setLocation(new Point(50,250));

		NavigationNode nav8 = nodes.get(7);
		nav8.setWest(nodes.get(6));
		nav8.setEast(nodes.get(8));
		nav8.setNorth(nodes.get(4));
		nav8.setLocation(new Point(150,250));

		NavigationNode nav9 = nodes.get(8);
		nav9.setNorth(nodes.get(5));
		nav9.setWest(nodes.get(7));
		nav9.setLocation(new Point(250,250));

	
	}
	
	public void buildBitmap (int width, int height)
	{
		output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(output);
		Paint p = new Paint();
		
		p.setColor(Color.BLACK); 
		c.drawRect(new Rect(0,0,c.getWidth(),c.getHeight()), p);
		
		p.setColor(Color.GRAY);
		// Draw the junctions
		for (NavigationNode n : nodes) {
			Point nLoc = n.getLocation();
			c.drawRect(nLoc.x - (ROAD_WIDTH / 2), nLoc.y - (ROAD_WIDTH / 2), nLoc.x + (ROAD_WIDTH / 2), nLoc.y + (ROAD_WIDTH / 2), p);
		}
		
		// Draw the roads
		for (NavigationNode n : nodes) {
			Point nLoc = n.getLocation();
			
			if (n.getWest() != null)
			{
				Point westLoc = n.getWest().getLocation();
				c.drawRect(westLoc.x + (ROAD_WIDTH / 2), westLoc.y - (ROAD_WIDTH / 2), nLoc.x - (ROAD_WIDTH / 2), nLoc.y + (ROAD_WIDTH / 2), p);
			}
			
			if (n.getNorth() != null)
			{
				Point northLoc = n.getNorth().getLocation();
				c.drawRect(nLoc.x + (ROAD_WIDTH / 2), nLoc.y - (ROAD_WIDTH / 2), northLoc.x - (ROAD_WIDTH / 2), northLoc.y + (ROAD_WIDTH / 2), p);
			}
			
			if (n.getEast() != null)
			{
				Point eastLoc = n.getEast().getLocation();
				c.drawRect(nLoc.x + (ROAD_WIDTH / 2), nLoc.y - (ROAD_WIDTH / 2), eastLoc.x - (ROAD_WIDTH / 2), eastLoc.y + (ROAD_WIDTH / 2), p);
			}
			
			if (n.getSouth() != null)
			{
				Point southLoc = n.getSouth().getLocation();
				c.drawRect(nLoc.x - (ROAD_WIDTH / 2), nLoc.y + (ROAD_WIDTH / 2), southLoc.x + (ROAD_WIDTH / 2), southLoc.y - (ROAD_WIDTH / 2), p);
			}
		}
		
		// Draw the kerbs
		Paint kerbPaint = new Paint();
		//kerbPaint.setStyle(Paint.Style.STROKE);
		kerbPaint.setStrokeWidth(2.0f);
		//kerbPaint.setPathEffect(new DashPathEffect(new float [] { 5,2 } , 1));
		kerbPaint.setColor(Color.LTGRAY);
		
		
		for (NavigationNode n : nodes) {
			Point nLoc = n.getLocation();
			
			if (n.getWest() != null)
			{
				Point westLoc = n.getWest().getLocation();
				c.drawLine(nLoc.x - (ROAD_WIDTH / 2), nLoc.y - (ROAD_WIDTH / 2), westLoc.x + (ROAD_WIDTH / 2), westLoc.y - (ROAD_WIDTH / 2), kerbPaint);
				c.drawLine(nLoc.x - (ROAD_WIDTH / 2), nLoc.y + (ROAD_WIDTH / 2), westLoc.x + (ROAD_WIDTH / 2), westLoc.y + (ROAD_WIDTH / 2), kerbPaint);
			}
			else 
			{
				c.drawLine(nLoc.x - (ROAD_WIDTH / 2), nLoc.y - (ROAD_WIDTH / 2), nLoc.x - (ROAD_WIDTH / 2), nLoc.y + (ROAD_WIDTH / 2), kerbPaint);
			}
			
			if (n.getNorth() != null)
			{
				Point northLoc = n.getNorth().getLocation();
				c.drawLine(nLoc.x - (ROAD_WIDTH / 2), nLoc.y - (ROAD_WIDTH / 2), northLoc.x - (ROAD_WIDTH / 2), northLoc.y + (ROAD_WIDTH / 2), kerbPaint);
				c.drawLine(nLoc.x + (ROAD_WIDTH / 2), nLoc.y - (ROAD_WIDTH / 2), northLoc.x + (ROAD_WIDTH / 2), northLoc.y + (ROAD_WIDTH / 2), kerbPaint);
			}
			else
			{
				c.drawLine(nLoc.x - (ROAD_WIDTH / 2), nLoc.y - (ROAD_WIDTH / 2), nLoc.x + (ROAD_WIDTH / 2), nLoc.y - (ROAD_WIDTH / 2), kerbPaint);
			}
			
			if (n.getEast() != null)
			{
				Point eastLoc = n.getEast().getLocation();
				c.drawLine(nLoc.x + (ROAD_WIDTH / 2), nLoc.y - (ROAD_WIDTH / 2), eastLoc.x - (ROAD_WIDTH / 2), eastLoc.y - (ROAD_WIDTH / 2), kerbPaint);
				c.drawLine(nLoc.x + (ROAD_WIDTH / 2), nLoc.y + (ROAD_WIDTH / 2), eastLoc.x - (ROAD_WIDTH / 2), eastLoc.y + (ROAD_WIDTH / 2), kerbPaint);

			}
			else
			{
				c.drawLine(nLoc.x + (ROAD_WIDTH / 2), nLoc.y - (ROAD_WIDTH / 2), nLoc.x + (ROAD_WIDTH / 2), nLoc.y + (ROAD_WIDTH / 2), kerbPaint);
			}
			
			if (n.getSouth() != null)
			{
				Point southLoc = n.getSouth().getLocation();
				c.drawLine(nLoc.x - (ROAD_WIDTH / 2), nLoc.y + (ROAD_WIDTH / 2), southLoc.x - (ROAD_WIDTH / 2), southLoc.y - (ROAD_WIDTH / 2), kerbPaint);
				c.drawLine(nLoc.x + (ROAD_WIDTH / 2), nLoc.y + (ROAD_WIDTH / 2), southLoc.x + (ROAD_WIDTH / 2), southLoc.y - (ROAD_WIDTH / 2), kerbPaint);
			}
			else 
			{
				c.drawLine(nLoc.x - (ROAD_WIDTH / 2), nLoc.y + (ROAD_WIDTH / 2), nLoc.x + (ROAD_WIDTH / 2), nLoc.y + (ROAD_WIDTH / 2), kerbPaint);
			}
		}
	}
}

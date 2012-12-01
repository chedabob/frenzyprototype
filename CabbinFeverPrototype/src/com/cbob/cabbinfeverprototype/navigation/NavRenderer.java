package com.cbob.cabbinfeverprototype.navigation;

import android.graphics.Canvas;

import com.cbob.cabbinfeverprototype.canvasrenderer.Renderable;

public class NavRenderer extends Renderable {
	RoadMap scene;
	
	public RoadMap getScene ()
	{
		return scene;
	}
	public NavRenderer ()
	{
		scene = new RoadMap();
		scene.buildTestNodes();
	}
	
	@Override
	public void Render(Canvas c) {
		if (scene.getSceneBitmap() == null)
			scene.buildBitmap(c.getWidth(), c.getHeight());
		c.drawBitmap(scene.getSceneBitmap(), 0, 0, null);
	}
}

package com.cbob.cabbinfeverprototype.canvasrenderer;

import java.util.Vector;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class CanvasRenderer {
	private Vector<Scene> scenes;

	private static CanvasRenderer _INSTANCE = new CanvasRenderer();
	public static CanvasRenderer GetInstance ()
	{
		return _INSTANCE;
	}
	public CanvasRenderer() {
		scenes = new Vector<Scene>();
	}

	public void addScene(Scene r) {
		scenes.add(r);
		scenes = Scene.sortScenes(scenes);
	}

	public void RemoveRenderable(Scene r) {
		scenes.remove(r);
	}

	public void Render(Canvas c) {
		for (Scene s : scenes) {
			s.Render(c);
		}
	}
	public void Update (long delta)
	{
		for (Scene s : scenes) {
			s.Update(delta);
		}
	}
	public Boolean HandleMotionEvent (MotionEvent e)
	{
		for (Scene s : scenes)
		{
			if (s.handleMotionEvent(e))
				return true;
		}
		return false;
	}
}

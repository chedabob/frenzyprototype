package com.cbob.cabbinfeverprototype.canvasrenderer;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class Scene extends CanvasObject {
	private Vector<CanvasObject> children;
	
	@SuppressWarnings("unchecked")
	public Vector<CanvasObject> getChildren ()
	{
		return (Vector<CanvasObject>) children.clone();
	}
	public Scene ()
	{
		children = new Vector<CanvasObject>();
	}
	protected void addChild (CanvasObject child)
	{
		child.parent = this;
		children.add(child);
		children = CanvasObject.sortCanvasObjects(children);
	}
	
	protected void removeChild (CanvasObject child)
	{
		children.remove(child);
		children = CanvasObject.sortCanvasObjects(children);

	}
	@Override
	public void Render(Canvas c) {
		for (CanvasObject r: children)
		{
			r.Render(c);
		}
	}
	
	public void Update(long delta) {
		for (CanvasObject r: children)
		{
			r.Update(delta);
		}
	}
	
	// Pah
	public static Vector<Scene> sortScenes(Vector<Scene> r) {
		Collections.sort(r, new Comparator<Scene>() {
			@Override
			public int compare(Scene lhs, Scene rhs) {
				if (lhs == null && rhs == null)
					return 0;

				if (lhs == null)
					return -1;
				if (rhs == null)
					return 1;

				if (rhs.Z == lhs.Z)
					return 0;

				return (rhs.Z > lhs.Z) ? -1 : 1;
			}
		});
		
		return r;
	}
	
	@Override
	public Boolean handleMotionEvent(MotionEvent e) {
		for (CanvasObject o: children)
		{
			if (o.handleMotionEvent(e))
				return true;
		}
		return false;
	}
}

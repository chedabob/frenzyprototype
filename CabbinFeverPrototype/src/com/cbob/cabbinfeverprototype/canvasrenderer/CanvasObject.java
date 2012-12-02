package com.cbob.cabbinfeverprototype.canvasrenderer;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class CanvasObject {
	public int Z = 0;
	public Scene parent;
	
	public CanvasObject ()
	{
		this.Z = 0;
	}
	
	public CanvasObject (int Z)
	{
		this.Z = Z;
	}
	public void Render (Canvas c)
	{
		throw new RuntimeException("Override me");
	}
	
	public void Update (long delta)
	{
		
	}
	
	public static Vector<CanvasObject> sortCanvasObjects(Vector<CanvasObject> r) {
		Collections.sort(r, new Comparator<CanvasObject>() {
			@Override
			public int compare(CanvasObject lhs, CanvasObject rhs) {
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
	
	public Boolean handleMotionEvent (MotionEvent e)
	{
		return false;
	}
}

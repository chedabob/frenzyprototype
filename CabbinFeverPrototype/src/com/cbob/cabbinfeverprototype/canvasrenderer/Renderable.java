package com.cbob.cabbinfeverprototype.canvasrenderer;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import android.graphics.Canvas;

public abstract class Renderable {
	public int Z = 0;
	public Renderable parent;
	
	public Renderable ()
	{
		this.Z = 0;
	}
	
	public Renderable (int Z)
	{
		this.Z = Z;
	}
	public void Render (Canvas c)
	{
		throw new RuntimeException("Override me");
	}
	
	public static Vector<Renderable> sortRenderables(Vector<Renderable> r) {
		Collections.sort(r, new Comparator<Renderable>() {
			@Override
			public int compare(Renderable lhs, Renderable rhs) {
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
}

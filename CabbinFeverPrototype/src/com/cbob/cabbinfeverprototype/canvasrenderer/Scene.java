package com.cbob.cabbinfeverprototype.canvasrenderer;

import java.util.Vector;

import android.graphics.Canvas;

public abstract class Scene extends Renderable {
	private Vector<Renderable> children;
	
	@SuppressWarnings("unchecked")
	public Vector<Renderable> getChildren ()
	{
		return (Vector<Renderable>) children.clone();
	}
	public Scene ()
	{
		children = new Vector<Renderable>();
	}
	protected void addChild (Renderable child)
	{
		child.parent = this;
		children.add(child);
		children = Renderable.sortRenderables(children);
	}
	
	protected void removeChild (Renderable child)
	{
		children.remove(child);
		children = Renderable.sortRenderables(children);

	}
	@Override
	public void Render(Canvas c) {
		for (Renderable r: children)
		{
			r.Render(c);
		}
	}
}

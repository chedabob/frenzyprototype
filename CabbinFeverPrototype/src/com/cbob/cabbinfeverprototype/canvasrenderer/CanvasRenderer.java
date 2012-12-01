package com.cbob.cabbinfeverprototype.canvasrenderer;

import java.util.Vector;

import android.graphics.Canvas;

public class CanvasRenderer {
	private Vector<Renderable> renderables;

	private static CanvasRenderer _INSTANCE = new CanvasRenderer();
	public static CanvasRenderer GetInstance ()
	{
		return _INSTANCE;
	}
	public CanvasRenderer() {
		renderables = new Vector<Renderable>();
	}

	public void AddRenderable(Renderable r) {
		renderables.add(r);
		renderables = Renderable.sortRenderables(renderables);
	}

	public void RemoveRenderable(Renderable r) {
		renderables.remove(r);
	}



	public void Render(Canvas c) {
		for (Renderable r : renderables) {
			r.Render(c);
		}
	}
}

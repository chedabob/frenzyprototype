package com.cbob.cabbinfeverprototype.scene;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SceneView extends ImageView {
	private final int FRAME_RATE = 16;
	Handler h;
	NavigationScene scene;
	
	public SceneView(Context context, AttributeSet attrs) {
		super(context, attrs);
		h = new Handler();
		scene = new NavigationScene();
		scene.buildTestNodes();
		
	}


	
	

	private Runnable r = new Runnable() {
		@Override
		public void run() {
			invalidate();
		}
	};

	@Override
	protected void onDraw(Canvas canvas) {

		if (scene.getSceneBitmap() == null)
			scene.buildBitmap(this.getWidth(), this.getHeight());
		canvas.drawBitmap(scene.getSceneBitmap(), 0, 0, null);
		
		h.postDelayed(r, FRAME_RATE);
	}
}

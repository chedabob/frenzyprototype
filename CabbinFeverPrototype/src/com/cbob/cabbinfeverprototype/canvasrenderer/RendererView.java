package com.cbob.cabbinfeverprototype.canvasrenderer;

import java.util.Date;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RendererView extends ImageView {
	private final int FRAME_RATE = 16; // 60 FPS
	Handler h;
	
	CanvasRenderer renderer;
	com.cbob.cabbinfeverprototype.animations.Animator animator;
	private Date lastTick;
	public RendererView(Context context, AttributeSet attrs) {
		super(context, attrs);
		h = new Handler();
		
		renderer = CanvasRenderer.GetInstance();
		animator = com.cbob.cabbinfeverprototype.animations.Animator.GetInstance();
		lastTick = new Date();
	}
	
	private Runnable r = new Runnable() {
		@Override
		public void run() {
			Date newTick = new Date();
			long delta = newTick.getTime() - lastTick.getTime();
			animator.Step(delta);
			lastTick = newTick;
			invalidate();
		}
	};

	@Override
	protected void onDraw(Canvas canvas) {
		
		renderer.Render(canvas);
		h.postDelayed(r, FRAME_RATE);
	}
}

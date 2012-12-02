package com.cbob.cabbinfeverprototype.animations;


import android.graphics.Point;

import com.cbob.cabbinfeverprototype.canvasrenderer.Helpers;
import com.cbob.cabbinfeverprototype.canvasrenderer.Sprite;

public class TranslateSpriteAnim extends Animation {
	private Sprite _sprite;
	private Point _destination, _source, _diff;
	private long _duration, _elapsed;
	public TranslateSpriteAnim (Sprite s, Point destination, long duration)
	{
		_sprite = s;
		_destination = destination;
		_source = s.getLocation();
		_duration = duration;
		_elapsed = 0;
		
		_diff = new Point (_source.x - _destination.x, _source.y - _destination.y);
		
		_sprite.AddAnimation(this);
	}
	@Override
	public void Step(long delta) {
		_elapsed += delta;
		
		float diff = (float)_elapsed / (float)_duration;
		diff = Helpers.ClampValue(0.0f, 1.0f, diff);

		int x, y;
		int deltaX, deltaY;
		
		deltaX = (int) (_diff.x * diff);
		deltaY = (int) (_diff.y * diff);
		

		x = _source.x - deltaX;

		y = _source.y - deltaY;
		
		_sprite.setLocation(new Point(x,y));
		
		
		if (_elapsed > _duration)
			this.Stop();
	}
	
	public void Stop ()
	{
		_sprite.RemoveAnimation(this);
		animator.RemoveAnimation(this);
	}
}

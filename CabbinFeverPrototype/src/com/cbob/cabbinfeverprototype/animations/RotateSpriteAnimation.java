package com.cbob.cabbinfeverprototype.animations;

import com.cbob.cabbinfeverprototype.canvasrenderer.Helpers;
import com.cbob.cabbinfeverprototype.canvasrenderer.Sprite;

public class RotateSpriteAnimation extends Animation {
	
	private Sprite _sprite;
	private float _destination, _source, _diff;
	private long _duration, _elapsed;
	public RotateSpriteAnimation(Sprite s, float rotation, long duration)
	{
		_sprite = s;
		_destination = rotation;
		_source = s.getRotation();
		_duration = duration;
		_elapsed = 0;
		
		_diff = _destination - _source;
	}
	
	@Override
	public void Step(long delta) {
		_elapsed += delta;
		
		float diff = (float)_elapsed / (float)_duration;
		
		diff = Helpers.ClampValue(0.0f, 1.0f, diff);
		
		float newDelta = diff * _diff;
		
		_sprite.setRotation(_source + newDelta);
		
		if (_elapsed > _duration)
			animator.RemoveAnimation(this);
		
	}
}

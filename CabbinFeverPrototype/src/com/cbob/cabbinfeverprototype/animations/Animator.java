package com.cbob.cabbinfeverprototype.animations;

import java.util.Vector;

public class Animator {

	private static Animator	_INSTANCE;
	
	public synchronized static Animator GetInstance ()
	{
		if (_INSTANCE == null)
			_INSTANCE = new Animator();
		
		return _INSTANCE;
	}
	
	private Vector<Animation> animations;
 	private Animator ()
	{
		animations = new Vector<Animation>();
	}
	public void AddAnimation (Animation a)
	{
		a.animator = this;
		animations.add(a);
	}
	
	public void RemoveAnimation (Animation a)
	{
		animations.remove(a);
	}
	public void Step (long delta)
	{
		for (Animation a: animations)
		{
			a.Step(delta);
		}
	}
	
}

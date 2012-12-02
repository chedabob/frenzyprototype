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
	public synchronized void AddAnimation (Animation a)
	{
		a.animator = this;
		animations.add(a);
	}
	
	public synchronized void RemoveAnimation (Animation a)
	{
		animations.remove(a);
	}
	
	public synchronized void Step (long delta)
	{
		Vector<Animation> temp = (Vector<Animation>)animations.clone();
		for (Animation a: temp)
		{
			a.Step(delta);
		}
	}
	
}

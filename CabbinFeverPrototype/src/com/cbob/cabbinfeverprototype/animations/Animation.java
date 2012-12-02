package com.cbob.cabbinfeverprototype.animations;

public abstract class Animation {
	public Animator animator;
	
	public void Step (long delta)
	{
		throw new RuntimeException("Override me");
	}
	
	public void Stop ()
	{
		throw new RuntimeException("Override me");
	}
}

package com.cbob.cabbinfeverprototype.canvasrenderer;

public class Helpers {
	public static float ClampValue (float start, float end, float currentValue)
	{
		if (currentValue > end)
			return end;
		
		if (currentValue < start)
			return start;
		
		return currentValue;
	}
	
	public static int ClampValue (int start, int end, int currentValue)
	{
		if (currentValue > end)
			return end;
		
		if (currentValue < start)
			return start;
		
		return currentValue;
	}
	
	public static double ClampValue (double start, double end, double currentValue)
	{
		if (currentValue > end)
			return end;
		
		if (currentValue < start)
			return start;
		
		return currentValue;
	}
}

package com.cbob.cabbinfeverprototype.canvasrenderer;

import android.graphics.Point;

public class Helpers {
	public static float ClampValue(float start, float end, float currentValue) {
		if (currentValue > end)
			return end;

		if (currentValue < start)
			return start;

		return currentValue;
	}

	public static int ClampValue(int start, int end, int currentValue) {
		if (currentValue > end)
			return end;

		if (currentValue < start)
			return start;

		return currentValue;
	}

	public static double ClampValue(double start, double end,
			double currentValue) {
		if (currentValue > end)
			return end;

		if (currentValue < start)
			return start;

		return currentValue;
	}

	public static int distanceBetweenTwoPoints(Point a, Point b) {
		int deltaX = a.x - b.x;
		int deltaY = a.y - b.y;

		int distance = (int) Math.sqrt(((deltaX * deltaX) + (deltaY * deltaY)));
		return distance;
	}

	public static double angleBetweenTwoPoints(Point a, Point b) {
		float angle = (float) Math.toDegrees(Math.atan2(a.y - b.y, a.x - b.x));

		//if (angle < 0) {
		//	angle += 360;
		//}

		return angle;
	}
}

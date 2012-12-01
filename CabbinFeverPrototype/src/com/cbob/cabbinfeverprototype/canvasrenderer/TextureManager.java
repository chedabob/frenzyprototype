package com.cbob.cabbinfeverprototype.canvasrenderer;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TextureManager {
	private static WeakReference<Activity> act;
	private HashMap<String, Bitmap> bitmaps;
	
	private static TextureManager _instance;
	public static synchronized TextureManager Singleton ()
	{
		if (_instance == null)
			_instance = new TextureManager();
		
		return _instance;
	}
	
	public static void setActivity (Activity a)
	{
		act = new WeakReference<Activity>(a);
	}
	private TextureManager () 
	{
		bitmaps = new HashMap<String, Bitmap>();
	}
	
	public Bitmap getImage (int resNumber)
	{
		String resString = Integer.toString(resNumber);
		if (bitmaps.containsKey(resString))
			return bitmaps.get(resString);
		
		Activity a = act.get();
		assert(a != null);
		
		Bitmap b = BitmapFactory.decodeResource(a.getResources(), resNumber);
		if (b == null)
			throw new RuntimeException("Failed to load bitmap for Res ID:" + resString);
		
		bitmaps.put(resString, b);
		return b;
	}
}

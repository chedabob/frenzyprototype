package com.cbob.cabbinfeverprototype.canvasrenderer;


import java.util.Vector;

import com.cbob.cabbinfeverprototype.animations.Animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import android.graphics.Point;

public class Sprite extends CanvasObject {
	private Point location;
	private Point scale;
	private float rotation;
	private Bitmap image;
	private Boolean dirty; // Recalculates the matrix
	private Matrix m;
	
	protected Vector<Animation> animations;
	
	public Sprite (int  resID)
	{
		image = TextureManager.Singleton().getImage(resID);
		scale = new Point(1,1);
		location = new Point(0, 0);
		rotation = 0.0f;
		dirty = true;
		m = new Matrix();
		animations = new Vector<Animation>();
	}
	
	public void Render(Canvas c) {
		// http://stackoverflow.com/a/10766968/78496
		
		if (dirty) {

			m.reset();
			m.postScale(scale.x, scale.y);
			m.postTranslate(-image.getWidth() / 2, -image.getHeight() / 2); 
			m.postRotate(rotation);
			m.postTranslate(location.x, location.y);
			
			dirty = false;
		}
		c.drawBitmap(image, m, new Paint());
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
		dirty = true;
	}

	public Point getScale() {
		return scale;
	}

	public void setScale(Point scale) {
		this.scale = scale;
		dirty = true;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
		dirty = true;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
		dirty = true;
	}	
	
	public synchronized void AddAnimation (Animation a)
	{
		animations.add(a);
	}
	
	public synchronized void RemoveAnimation (Animation a)
	{
		animations.remove(a);
	}
}

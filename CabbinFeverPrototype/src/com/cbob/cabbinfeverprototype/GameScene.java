package com.cbob.cabbinfeverprototype;


import android.graphics.Point;
import android.view.MotionEvent;

import com.cbob.cabbinfeverprototype.animations.Animator;
import com.cbob.cabbinfeverprototype.animations.RotateSpriteAnimation;
import com.cbob.cabbinfeverprototype.animations.TranslateSpriteAnim;
import com.cbob.cabbinfeverprototype.canvasrenderer.Helpers;
import com.cbob.cabbinfeverprototype.canvasrenderer.Scene;
import com.cbob.cabbinfeverprototype.navigation.NavRenderer;
import com.cbob.cabbinfeverprototype.navigation.NavigationNode;

public class GameScene extends Scene {
	private NavRenderer nav;
	private Taxi taxi ;
	public GameScene()
	{
		nav = new NavRenderer();
		addChild(nav);
		
		taxi= new Taxi(nav.getScene(), nav.getScene().nodeForID(0));
		addChild(taxi);
		
		//TranslateSpriteAnim anim = new TranslateSpriteAnim(taxi, nav.getScene().nodeForID(0).getLocation(), 3000);
		//Animator.GetInstance().AddAnimation(anim);
		
		//RotateSpriteAnimation anim = new RotateSpriteAnimation(taxi, 480.0f, 10000);
		//Animator.GetInstance().AddAnimation(anim);
	}
	
	@Override
	public Boolean handleMotionEvent(MotionEvent e) {
		if (e.getAction() == MotionEvent.ACTION_DOWN)
		{
			Point touchLoc = new Point((int)e.getX(), (int)e.getY());
			
			NavigationNode n = nav.getScene().nodeForPoint(touchLoc, 30);
			
			if (n != null)
			{
				taxi.NavigateToNode(n);	
			}
			
		}
		return super.handleMotionEvent(e);
	}
}

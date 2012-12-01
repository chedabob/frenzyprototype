package com.cbob.cabbinfeverprototype;

import com.cbob.cabbinfeverprototype.animations.Animator;
import com.cbob.cabbinfeverprototype.animations.TranslateSpriteAnim;
import com.cbob.cabbinfeverprototype.canvasrenderer.Scene;
import com.cbob.cabbinfeverprototype.navigation.NavRenderer;

public class GameScene extends Scene {
	private NavRenderer nav;
	public GameScene()
	{
		nav = new NavRenderer();
		addChild(nav);
		
		Taxi taxi = new Taxi();
		taxi.setLocation(nav.getScene().nodeForID(4).getLocation());
		addChild(taxi);
		
		TranslateSpriteAnim anim = new TranslateSpriteAnim(taxi, nav.getScene().nodeForID(0).getLocation(), 3000);
		Animator.GetInstance().AddAnimation(anim);
	}
}

package com.cbob.cabbinfeverprototype;

import java.util.Vector;


import com.cbob.cabbinfeverprototype.animations.Animation;
import com.cbob.cabbinfeverprototype.animations.Animator;
import com.cbob.cabbinfeverprototype.animations.RotateSpriteAnimation;
import com.cbob.cabbinfeverprototype.animations.TranslateSpriteAnim;
import com.cbob.cabbinfeverprototype.canvasrenderer.Helpers;
import com.cbob.cabbinfeverprototype.canvasrenderer.Sprite;
import com.cbob.cabbinfeverprototype.navigation.NavigationNode;
import com.cbob.cabbinfeverprototype.navigation.RoadMap;

public class Taxi extends Sprite {

	GameScene gScene = (GameScene) this.parent;
	RoadMap nav;
	NavigationNode currentNode;
	Vector<NavigationNode> currentRoute;
	boolean navigating = false;
	private final int NODE_THRESHOLD = 5;
	
	public Taxi(RoadMap navigation, NavigationNode initial) {
		super(R.raw.taxi);
		nav = navigation;
		currentNode = initial;
		setLocation(currentNode.getLocation());
	}
	
	public void NavigateToNode (NavigationNode n)
	{
		if (navigating)
			return;
		
		if (n == currentNode)
			return;
		currentRoute = nav.navigateToNodeFromNode(currentNode, n);
		if (currentRoute.size() == 1)
			return;
		
		StopAllAnims();

		
		currentNode = currentRoute.elementAt(1);
		navigating = true;
		
		TranslateSpriteAnim anim1 = new TranslateSpriteAnim(this, currentNode.getLocation(), Helpers.distanceBetweenTwoPoints(getLocation(), currentNode.getLocation())  * 10);
		Animator.GetInstance().AddAnimation(anim1);
		
		double angle = Helpers.angleBetweenTwoPoints(currentNode.getLocation(),getLocation());
		//angle = -angle + 90;
		//if (angle > 180)
		//	angle = -(angle - 180);
		RotateSpriteAnimation anim2 = new RotateSpriteAnimation(this, (float)angle, 200);
		Animator.GetInstance().AddAnimation(anim2);
	}
	
	@Override
	public void Update (long delta)
	{
		if (!navigating)
			return;
		
		if (Helpers.distanceBetweenTwoPoints(getLocation(), currentNode.getLocation()) < NODE_THRESHOLD)
		{
			StopAllAnims();
			
			int index = currentRoute.indexOf(currentNode);
			
			if (index + 1 >= currentRoute.size())
			{
				navigating = false;
				return;
			}
			currentNode = currentRoute.elementAt(index + 1);
			
			TranslateSpriteAnim anim1 = new TranslateSpriteAnim(this, currentNode.getLocation(), Helpers.distanceBetweenTwoPoints(getLocation(), currentNode.getLocation())  * 10);
			Animator.GetInstance().AddAnimation(anim1);
			
			double angle = Helpers.angleBetweenTwoPoints(getLocation(), currentNode.getLocation());
			//angle = -angle + 90;
			//if (angle > 180)
			//	angle = -(angle - 180);
			RotateSpriteAnimation anim2 = new RotateSpriteAnimation(this, (float)angle, 200);
			Animator.GetInstance().AddAnimation(anim2);

		}
	}
	public void StopAllAnims ()
	{
		Vector<Animation> temp = (Vector<Animation>)animations.clone();
		for (Animation a : temp)
		{
			a.Stop();
		}
	}
}

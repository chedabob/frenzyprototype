package com.cbob.cabbinfeverprototype;

import com.cbob.cabbinfeverprototype.canvasrenderer.CanvasRenderer;
import com.cbob.cabbinfeverprototype.canvasrenderer.TextureManager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GameScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen);
		TextureManager.setActivity(this);
		CanvasRenderer.GetInstance().addScene(new GameScene());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game_screen, menu);
		return true;
	}

}

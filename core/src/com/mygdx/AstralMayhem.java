package com.mygdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.databaseConnection.Result;
import com.mygdx.gameStates.MenuScreen;
import com.mygdx.utils.MyAssetManager;

import java.util.List;

public class AstralMayhem extends Game {
	public SpriteBatch batch;
	public BitmapFont textPrinter;
	public MyAssetManager am;

	public String username = "";
	public  List<Result> results = null;

	public AstralMayhem() {
		super();
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		textPrinter = new BitmapFont();
		am = new MyAssetManager();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		textPrinter.dispose();
		am.dispose();
	}
}

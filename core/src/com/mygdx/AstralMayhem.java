package com.mygdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.databaseConnection.ConcreteResultDAO;
import com.mygdx.databaseConnection.Result;
import com.mygdx.databaseConnection.ResultDAO;
import com.mygdx.gameStates.GameScreen;
import com.mygdx.gameStates.MenuScreen;
import com.mygdx.utils.MyAssetManager;

import java.sql.SQLException;
import java.util.List;

public class AstralMayhem extends Game {
	public SpriteBatch batch;
	public BitmapFont textPrinter;
	public final MyAssetManager am = new MyAssetManager();

	public  List<Result> results = null;

	public AstralMayhem() {
		super();
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		textPrinter = new BitmapFont();

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

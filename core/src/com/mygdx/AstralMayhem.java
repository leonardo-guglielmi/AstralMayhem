package com.mygdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.distributedGameScore.Client;
import com.mygdx.gameStates.GameScreen;

import java.io.IOException;

public class AstralMayhem extends Game {
	public SpriteBatch batch;
	public BitmapFont textPrinter;
	public final AssetManager am = new AssetManager();
	public final Client client = null;

	public AstralMayhem() throws IOException {
		super();
		// todo: fix when server works
		//client = new Client();
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		textPrinter = new BitmapFont();
		this.setScreen(new GameScreen(this));
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
		try {
			client.closeConnection();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}

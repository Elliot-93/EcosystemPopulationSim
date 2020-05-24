package com.ecopopsim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EcosystemPopulationSimScreen extends ScreenAdapter {

	EcosystemPopulationSimGame game;

	SpriteBatch batch;
	Texture img;

	public EcosystemPopulationSimScreen(EcosystemPopulationSimGame game) {
		this.game = game;
	}

	@Override
	public void show(){
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown(int keyCode) {
				if (keyCode == Input.Keys.ESCAPE) {
					game.setScreen(new ParametersScreen(game));
				}
				return true;
			}
		});
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
//		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void hide () {
		Gdx.input.setInputProcessor(null);
	}
}

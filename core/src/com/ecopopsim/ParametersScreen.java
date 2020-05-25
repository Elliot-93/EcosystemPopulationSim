package com.ecopopsim;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ParametersScreen extends ScreenAdapter {
	private EcosystemPopulationSimGame game;
	private Stage stage;

	public ParametersScreen(EcosystemPopulationSimGame game) {
		this.game = game;
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show(){

		int row_height = Gdx.graphics.getHeight() / 12;
		int col_width = Gdx.graphics.getWidth() / 12;

		Skin skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

		Label titleLabel = new Label("Ecosystem Population Simulator", skin, "title");
		titleLabel.setSize(col_width*4,row_height);
		titleLabel.setPosition(col_width * 2,row_height * 11);
		stage.addActor(titleLabel);

		Label enterParamsLabel = new Label("Enter your starting parameters below", skin);
		enterParamsLabel.setSize(col_width*4,row_height);
		enterParamsLabel.setPosition(col_width*1,Gdx.graphics.getHeight()-row_height*2);
		stage.addActor(enterParamsLabel);

		// TODO: Input for number of plants
		// TODO: Params for starting numbers etc. Could add tabs for each creature params


		Button playButton = new TextButton("Click here to begin", skin);
		playButton.setSize(col_width*4,row_height);
		playButton.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*11);
		playButton.addListener(new InputListener(){
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				((TextButton)event.getListenerActor()).setText("Loading...");
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new EcosystemPopulationSimScreen(game));
			}
		});
		stage.addActor(playButton);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, .25f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void hide(){
		Gdx.input.setInputProcessor(null);
	}
}

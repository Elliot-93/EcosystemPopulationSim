package com.ecopopsim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ecopopsim.models.Plant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EcosystemPopulationSimScreen extends ScreenAdapter {

	EcosystemPopulationSimGame game;
	SpriteBatch batch;

	List<Plant> plants = new ArrayList<>();
	Random rand = new Random();

	public EcosystemPopulationSimScreen(EcosystemPopulationSimGame game) {
		this.game = game;
		batch = new SpriteBatch();
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
		Gdx.gl.glClearColor(0.4f, 0.2f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		generatePlants();
		for (Plant plant: plants)
		{
			plant.draw(batch);
		}

		batch.end();
	}
	
	@Override
	public void hide () {
		Gdx.input.setInputProcessor(null);
	}

	private void generatePlants(){


		Boolean[] takenXCoords = new Boolean[Gdx.graphics.getHeight()];
		Boolean[] takenYCoords = new Boolean[Gdx.graphics.getWidth()];

		Arrays.fill(takenXCoords, false);
		Arrays.fill(takenYCoords, false);

		for(int plantNum = 0; plantNum < 10; plantNum++) {
			int plantSizeMultiplier = 2;
			int plantSize = rand.nextInt(10) * plantSizeMultiplier;

			List<Integer> possibleXCoords = new ArrayList<>(Gdx.graphics.getHeight());
			List<Integer> possibleYCoords = new ArrayList<>(Gdx.graphics.getWidth());

			for (int i = 0; i < takenXCoords.length; i++) {
				if (takenXCoords[i] || takenXCoords[i + plantSize]){
					continue;
				}
				possibleXCoords.add(i);
			}

			for (int i = 0; i < takenXCoords.length; i++) {
				if (takenYCoords[i] || takenYCoords[i + plantSize]){
					continue;
				}
				possibleYCoords.add(i);
			}

			int x = rand.nextInt(possibleXCoords.size());
			int y = rand.nextInt(possibleYCoords.size());

			Arrays.fill(takenXCoords, x, x + plantSize, false);
			Arrays.fill(takenYCoords, y, y + plantSize, false);

			plants.add(new Plant(x, y, plantSize, plantSize, 0));
		}
	}

//	private void setAll(List<Boolean> list, int from, int to, Boolean value){
//		for (int i = from; i <= to; i++) {
//			list.set(i, value);
//		}
//	}

}

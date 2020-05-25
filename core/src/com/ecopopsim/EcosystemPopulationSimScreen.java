package com.ecopopsim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.ecopopsim.models.Plant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EcosystemPopulationSimScreen extends ScreenAdapter {

	EcosystemPopulationSimGame game;

	Boolean[] mapXCoordsPopulatedWithPlant;
	Boolean[] mapYCoordsPopulatedWithPlant;

	List<Plant> plants = new ArrayList<>();
	Random rand = new Random();

	public EcosystemPopulationSimScreen(EcosystemPopulationSimGame game) {
		this.game = game;

		// All coords set to true if populated with plant already
		mapXCoordsPopulatedWithPlant = new Boolean[Gdx.graphics.getHeight()];
		mapYCoordsPopulatedWithPlant = new Boolean[Gdx.graphics.getWidth()];
		Arrays.fill(mapXCoordsPopulatedWithPlant, false);
		Arrays.fill(mapYCoordsPopulatedWithPlant, false);
	}

	@Override
	public void show(){
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown(int keyCode) {
				if (keyCode == Input.Keys.ESCAPE) {
					game.setScreen(new ParametersScreen(game));
				}
				if (keyCode == Input.Keys.SPACE) {
					generatePlants();
				}
				return true;
			}
		});

		generatePlants();
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0.4f, 0.2f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();

		for (Plant plant: plants)
		{
			plant.draw(game.batch);
		}

		game.batch.end();
	}
	
	@Override
	public void hide () {
		Gdx.input.setInputProcessor(null);
	}

	private void generatePlants(){
		for(int plantNum = 0; plantNum < 10 ; plantNum++) {
			int plantSizeMultiplier = 2;
			int plantSize = rand.nextInt(10) * plantSizeMultiplier;

			List<Integer> possibleXCoords = new ArrayList<>();
			List<Integer> possibleYCoords = new ArrayList<>();

			for (int i = 0; i < mapXCoordsPopulatedWithPlant.length - plantSize; i++) {
				if (mapXCoordsPopulatedWithPlant[i] || mapXCoordsPopulatedWithPlant[i + plantSize]){
					continue;
				}
				possibleXCoords.add(i);
			}

			for (int i = 0; i < mapYCoordsPopulatedWithPlant.length - plantSize; i++) {
				if (mapYCoordsPopulatedWithPlant[i] || mapYCoordsPopulatedWithPlant[i + plantSize ]){
					continue;
				}
				possibleYCoords.add(i);
			}

			if (possibleXCoords.isEmpty()|| possibleYCoords.isEmpty()) {
				continue;
			}

			//todo: getting exception here

			int x = possibleXCoords.get(rand.nextInt(possibleXCoords.size() - 1));
			int y = possibleYCoords.get(rand.nextInt(possibleYCoords.size() - 1));

			Arrays.fill(mapXCoordsPopulatedWithPlant, x,x + plantSize, true);
			Arrays.fill(mapYCoordsPopulatedWithPlant, y,y + plantSize, true);

			plants.add(new Plant(x, y, plantSize, plantSize, 0));
		}
	}
}

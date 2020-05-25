package com.ecopopsim.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Plant {
    public final Sprite sprite;

    public Plant(int x, int y, int width, int height, int rotation) {
        sprite = new Sprite(new Texture(Gdx.files.internal("plant.png")), 50, 50);
        sprite.setPosition(x, y);
        sprite.setSize(width, height);

        sprite.setRotation(rotation);
    }

    public void draw(Batch batch) {
        sprite.draw(batch);
    }

}



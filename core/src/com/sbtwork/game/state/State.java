package com.sbtwork.game.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by sbtrwt on 03-06-2016.
 */
public abstract class State {

    //State properties
    protected OrthographicCamera statecam;
    private Vector3 mouse;
    protected GameStateManager gsm;

    public  State(GameStateManager gsmFound){
        this.gsm = gsmFound;
        statecam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
    public abstract void render(ShapeRenderer sr);
}

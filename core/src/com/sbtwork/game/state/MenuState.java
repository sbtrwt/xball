package com.sbtwork.game.state;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.sbtwork.game.sprite.ball;
import com.sbtwork.game.xball;

/**
 * Created by sbtrwt on 03-06-2016.
 */
public class MenuState extends State {

private Texture background;
private Texture plyrBtn;

    public MenuState(GameStateManager gsmFound) {
        super(gsmFound);
        background = new Texture("bg.png");
        plyrBtn = new Texture("playbtn.png");
        statecam.setToOrtho(false, xball.WIDTH /2 , xball.HEIGHT /2);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(statecam.combined);
        sb.begin();
        //sb.draw(img, 0, 0);
        sb.draw(background, 0, 0, xball.WIDTH/2, xball.HEIGHT/2);
        sb.draw(plyrBtn, (xball.WIDTH/2)-(plyrBtn.getWidth() / 2), xball.HEIGHT / 2);
        sb.end();


    }

    @Override
    public void dispose() {
        background.dispose();
        plyrBtn.dispose();
    }

    @Override
    public void render(ShapeRenderer sr) {

    }
}

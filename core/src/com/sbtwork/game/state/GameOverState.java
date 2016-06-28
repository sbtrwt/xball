package com.sbtwork.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sbtwork.game.scene.Hud;
import com.sbtwork.game.xball;

/**
 * Created by sbtrwt on 10-06-2016.
 */
public class GameOverState extends State {

    public Stage stg;
    public Viewport vport;
    Table tblscore;
    private Label lblgmScore;


    public GameOverState(GameStateManager gsmFound) {
        super(gsmFound);
        statecam.setToOrtho(false, xball.WIDTH / 2, xball.HEIGHT / 2);
        vport = new FitViewport(xball.WIDTH,xball.HEIGHT,statecam);


        tblscore = new Table();
        tblscore.top();
        tblscore.setFillParent(true);
        //tblscore.row();
        lblgmScore = new Label(String.format("%03d Game Over", Hud.gamescore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        tblscore.add(lblgmScore).expandX().padTop(xball.HEIGHT/2 + lblgmScore.getWidth()).padBottom(20f);

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            dispose();
            gsm.set(new PlayState(gsm));

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix(statecam.combined);
        //sb.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //sb.end();
        stg = new Stage(vport,sb);
        stg.addActor(tblscore);
        stg.draw();

    }

    @Override
    public void dispose() {
        stg.dispose();

    }

    @Override
    public void render(ShapeRenderer sr) {

    }
}

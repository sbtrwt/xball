package com.sbtwork.game.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sbtwork.game.sprite.ball;
import com.sbtwork.game.xball;

/**
 * Created by sbtrwt on 08-06-2016.
 */
public class Hud {
    public Stage stg;
    public Viewport vport;
    public static int gamescore;
    private static Label lblgmScore;

    public  Hud(SpriteBatch sb){
        gamescore = 0;
        vport = new FitViewport(xball.WIDTH,xball.HEIGHT,new OrthographicCamera());
        stg = new Stage(vport,sb);

        Table tblscore = new Table();
        tblscore.top();
        tblscore.setFillParent(true);
        //tblscore.row();
        lblgmScore = new Label(String.format("%03d",gamescore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        tblscore.add(lblgmScore).expandX();
        stg.addActor(tblscore);
    }

    public static void addScore(boolean isInit, int nValue){
        if(isInit)
            gamescore = 0;
        else
        gamescore += nValue;
        //if( !ball.isDead)
        lblgmScore.setText(String.format("%03d",gamescore));
    }
}

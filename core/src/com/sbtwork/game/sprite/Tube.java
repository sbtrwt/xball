package com.sbtwork.game.sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Rectangle;
import java.util.Random;
/**
 * Created by sbtrwt on 04-06-2016.
 */
public class Tube {
    public static final int TUBE_WIDTH = 52;
    public static final int TUBE_PAD =300;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Rectangle boundsTop, boundsBot;
    private Random rand;


    public Tube(float y){
        topTube = new Texture("leftTube.png");
        bottomTube = new Texture("rightTube.png");
        rand = new Random();


        posTopTube = new Vector2(-rand.nextInt(FLUCTUATION)-TUBE_GAP - LOWEST_OPENING,TUBE_PAD + y);
        posBotTube = new Vector2(posTopTube.x + TUBE_GAP + bottomTube.getWidth(),TUBE_PAD + y);

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }


    public Texture getBottomTube() {
        return bottomTube;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void reposition(float y){
        posTopTube.set( -rand.nextInt(FLUCTUATION) - TUBE_GAP - LOWEST_OPENING,y);
        posBotTube.set( posTopTube.x + TUBE_GAP + bottomTube.getWidth(),y);
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}


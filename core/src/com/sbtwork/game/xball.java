package com.sbtwork.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sbtwork.game.scene.Hud;
import com.sbtwork.game.sprite.ball;
import com.sbtwork.game.state.GameStateManager;
import com.sbtwork.game.state.MenuState;

public class xball extends ApplicationAdapter {
	public  static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "x-ball";
	private GameStateManager gsm;
	private SpriteBatch batch;
	private ShapeRenderer srGame;
	private Music music;
	private Hud hdScore;
	@Override
	public void create () {
		batch = new SpriteBatch();
		srGame = new ShapeRenderer();
		music = Gdx.audio.newMusic(Gdx.files.internal("war.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
		hdScore = new Hud(batch);
		Gdx.gl.glClearColor(0, 0, 0, 5);
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();


		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		gsm.render(srGame);
		batch.setProjectionMatrix(hdScore.stg.getCamera().combined);
		if( !ball.isDead)
		hdScore.stg.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
}

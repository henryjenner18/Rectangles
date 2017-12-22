package main;

import com.badlogic.gdx.Game;

import screens.Rectangles;

public class Main extends Game {

	@Override
	public void create() {
		setScreen(new Rectangles());
	}

}

package managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import objects.Rectangle;

public class Renderer {
	
	private OrthographicCamera cam;
	private ShapeRenderer sr;
	private World world;

	public Renderer(World world) {
		this.world = world;
		cam = new OrthographicCamera(600, 600);
		cam.translate(300, 300);
		cam.update();
		sr = new ShapeRenderer();
		sr.setProjectionMatrix(cam.combined);		
	}

	public void render() {
		rectangles();
	}

	private void rectangles() {
		for(int i = 0; i < world.getNumRectangles(); i++) {
			
			Rectangle rect = world.getRectangle(i);
			float x = rect.getPos().x;
			float y = rect.getPos().y;		
			int width = rect.getWidth();
			int height = rect.getHeight();
			
			Gdx.gl.glLineWidth(2);
			sr.begin(ShapeType.Filled);
			sr.setColor(Color.GOLD);
			sr.rect(x, y, width, height);
			sr.end();
			
			sr.begin(ShapeType.Line);
			sr.setColor(Color.MAROON);
			sr.rect(x, y, width, height);
			sr.end();
		}
	}

}

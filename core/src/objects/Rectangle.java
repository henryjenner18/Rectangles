package objects;

import com.badlogic.gdx.math.Vector2;

public class Rectangle {
	
	private Vector2 pos, vel;
	private int width, height;
	
	public Rectangle(int x, int y, int xVel, int yVel, int width, int height) {
		this.width = width;
		this.height = height;
		pos = new Vector2(x, y);
		vel = new Vector2(xVel, yVel);
	}
	
	public void update() {
		move();
	}
	
	private void move() {
		pos.add(vel);
		checkEdges();
	}
	
	private void checkEdges() {
		if(pos.x <= 0) { // Hit left side
			pos.x = 0;
			vel.x *= -1;
			
		} else if(pos.x >= 600 - width) { // Hit right side
			pos.x = 600 - width;
			vel.x *= -1;
		}
		
		if(pos.y <= 0) { // Hit bottom
			pos.y = 0;
			vel.y *= -1;
			
		} else if(pos.y >= 600 - height) { // Hit top
			pos.y = 600 - height;
			vel.y *= -1;
		}
	}
	
	public void setXPos(float x) {
		pos.x = x;
	}
	
	public void setYPos(float y) {
		pos.y = y;
	}
	
	public void setVel(Vector2 vel) {
		this.vel = vel;
	}
	
	public void setXVel(float xVel) {
		vel.x = xVel;
	}
	
	public void setYVel(float yVel) {
		vel.y = yVel;
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public Vector2 getVel() {
		return vel;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}

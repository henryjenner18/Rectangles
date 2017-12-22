package managers;

import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;

import objects.Rectangle;

public class World {
	
	private ArrayList<Rectangle> rectangles;
	private ArrayList<int[]> pairsChecked;
	private int numRectangles;
	
	public World() {
		numRectangles = 5;
		rectangles = new ArrayList<Rectangle>();
		
		for(int i = 0; i < numRectangles; i++) {
			int width = randInt(80, 100);
			int height = width;
			
			int x = randInt(0, 600 - width);
			int y = randInt(0, 600 - height);
			
			int xVel = randInt(-6, 6);
			int yVel = randInt(-6, 6);
			
			createRect(x, y, xVel, yVel, width, height);
		}
	}
	
	private int randInt(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}

	public void update(float delta) {
		rectangles();
		checkCollisions();
	}
	
	private void checkCollisions() {
		pairsChecked = new ArrayList<int[]>();
		Rectangle rect1, rect2;
		
		for(int i = 0; i < getNumRectangles(); i++) {
			rect1 = getRectangle(i);
			
			for(int e = 0; e < getNumRectangles(); e++) {
				rect2 = getRectangle(e);
				
				if(i != e && checkValidPair(i, e) == true) { // Not the same rectangle and not an already tested pair
					checkForCollision(rect1, rect2);
					
					int[] pair = {i, e};
					pairsChecked.add(pair);
				}
			}
		}
	}
	
	private boolean checkValidPair(int i, int e) {
		boolean valid = true;
		
		for(int p = 0; p < pairsChecked.size(); p++) {
			int[] pair = pairsChecked.get(p);
			
			if((pair[0] == i && pair[1] == e) ||
					(pair[0] == e && pair[1] == i)) {
				valid = false;
			}
		}
		
		if(valid == true) {
			return true;
		} else {
			return false;
		}
	}

	private void checkForCollision(Rectangle rect1, Rectangle rect2) {
		Vector2 rect1Pos = rect1.getPos();
		Vector2 rect2Pos = rect2.getPos();
		int rect1Width = rect1.getWidth();
		int rect2Width = rect2.getWidth();
		int rect1Height = rect1.getHeight();
		int rect2Height = rect2.getHeight();
		
		float xDiff;
		boolean xInRange = false;
		
		if(rect1Pos.x < rect2Pos.x) { // 1 is to the left of 2
			xDiff = rect2Pos.x - rect1Pos.x; // Positive x difference
			if(xDiff <= rect1Width) {
				xInRange = true;
			}
			
		} else { // 2 is to the left of 1
			xDiff = rect1Pos.x - rect2Pos.x;
			if(xDiff <= rect2Width) {
				xInRange = true;
			}
		}
		
		float yDiff;
		boolean yInRange = false;
		
		if(rect1Pos.y < rect2Pos.y) {
			yDiff = rect2Pos.y - rect1Pos.y;
			if(yDiff <= rect1Height) {
				yInRange = true;
			}
			
		} else {
			yDiff = rect1Pos.y - rect2Pos.y;
			if(yDiff <= rect2Height) {
				yInRange = true;
			}
		}
		
		if(xInRange && yInRange) {
			if(xDiff > yDiff) { // x collision
				float temp = rect1.getVel().x;
				rect1.setXVel(rect2.getVel().x);
				rect2.setXVel(temp);
				
				
				if(rect1Pos.x < rect2Pos.x) { // 1 is to the left of 2
					float overlap = rect1Width - xDiff;
					rect1.setXPos(rect1Pos.x - overlap/2);
					rect2.setXPos(rect2Pos.x + overlap/2);

				} else { // 2 is to the left of 1
					float overlap = rect2Width - xDiff;
					rect1.setXPos(rect1Pos.x + overlap/2);
					rect2.setXPos(rect2Pos.x - overlap/2);
				}
				
			} else { // y collision
				float temp = rect1.getVel().y;
				rect1.setYVel(rect2.getVel().y);
				rect2.setYVel(temp);
				
				if(rect1Pos.y < rect2Pos.y) { // 1 is below 2
					float overlap = rect1Height - yDiff;
					rect1.setYPos(rect1Pos.y - overlap/2);
					rect2.setYPos(rect2Pos.y + overlap/2);

				} else { // 2 is below 1
					float overlap = rect2Height - yDiff;
					rect1.setYPos(rect1Pos.y + overlap/2);
					rect2.setYPos(rect2Pos.y - overlap/2);
				}
			}
		}		
	}

	private void rectangles() {
		for(int i = 0; i < getNumRectangles(); i++) {
			getRectangle(i).update();
		}
	}
	
	private void createRect(int x, int y, int xVel, int yVel, int width, int height) {
		Rectangle rect = new Rectangle(x, y, xVel, yVel, width, height);
		rectangles.add(rect);
	}
	
	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}
	
	public Rectangle getRectangle(int i) {
		return rectangles.get(i);
	}
	
	public int getNumRectangles() {
		return rectangles.size();
	}

}

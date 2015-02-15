package net.dobervich.aprecursion;

import processing.core.PApplet;

public class RandomTerrain extends PApplet {

	public void setup() {
		size(600, 600);
			
	}
	
	public void draw() {
		
	}
	
	public void mouseReleased() {
		background(255);
		drawTerrainLine(mouseX, mouseY, 550, 300, 6, 80);
	}
	
	public void drawTerrainLine(int x1, int y1, int x2, int y2, int n, double diffamount) {
		if (n <= 0) {
			line(x1, y1, x2, y2);
			return;
		}
		
		int midx = (x2 + x1)/2;
		int midy = (y1 + y2)/2;
		midy += (int)(Math.random()*2*diffamount - diffamount);
		
		drawTerrainLine(x1, y1, midx, midy, n-1, diffamount*0.5);
		drawTerrainLine(midx, midy, x2, y2, n-1, diffamount*0.5);
	}

}

package net.dobervich.aprecursion;

import java.util.ArrayList;
import processing.core.PApplet;

public class GoldenRod extends PApplet {
   Turtle t1;
	
    public void setup() {
        size(600, 600);
        
        t1 = new Turtle(300, 300, 0, this);
        
        drawShape(t1, 300, 1);
        
        noLoop();
    }

    public void drawShape(Turtle t, float size, int level) {
    	if (level == 0) {
    		t.forward(size);
    		return;
    	}
    	
    	float newSize = size / 4;
    	
    	drawShape(t, newSize, level-1);
    	t.turnLeft((float)Math.PI/2);
    	drawShape(t, newSize, level-1);
    	t.turnRight((float)Math.PI/2);
    	drawShape(t, newSize, level-1);
    	t.turnRight((float)Math.PI/2);
    	drawShape(t, newSize, level-1);
    	drawShape(t, newSize, level-1);
    	// keep it going...
    	
    }

    


}
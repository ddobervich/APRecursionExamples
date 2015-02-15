package net.dobervich.aprecursion;

import java.util.ArrayList;
import processing.core.PApplet;

public class Main2 extends PApplet {
    Turtle t1, t2, t3;
    float length = 600;

    public void setup() {
        size(600, 600);
        t1 = new Turtle((int) (width/2.0 - length/2.0), (int) (height/6.0), 0, this);
        t2 = new Turtle((int) (width/2.0 - length/2.0), (int) (3*height/6.0), 0, this);
        t3 = new Turtle((int) (width/2.0 - length/2.0), (int) (5*height/6.0), 0, this);

        drawShape(t1, length, 1);
        drawShape(t2, length, 5);
        drawShape(t3, length, 3);

        noLoop();
    }

    public void drawShape(Turtle t, float size, int iteration) {
        if (iteration < 1) {							// if iteration is 0, just draw a line.
            t.forward(size);
        } else {
            float newSize = size/4;						// new size if 1/4th the old size
            drawShape(t, newSize, iteration - 1);		//    draw a smaller copy of the shape
            t.turnLeft(90);							//    turn left by 90 degrees
            drawShape(t, newSize, iteration - 1);		//    draw a smaller copy of the shape
            t.turnRight(90);							//	  turn right by 90 degrees
            drawShape(t, newSize, iteration - 1);		//	  draw a smaller copy of the shape
            t.turnRight(90);							//	  turn right by 90 degrees
            drawShape(t, newSize, iteration - 1);		//    draw a smaller copy
            drawShape(t, newSize, iteration - 1);		//    draw a smaller copy
            t.turnLeft(90);							//    etc.
            drawShape(t, newSize, iteration - 1);
            t.turnLeft(90);
            drawShape(t, newSize, iteration - 1);
            t.turnRight(90);
            drawShape(t, newSize, iteration - 1);
        }
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"artproject1.Main2"});
    }


}
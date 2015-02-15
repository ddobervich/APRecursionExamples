package net.dobervich.aprecursion;

import java.util.ArrayList;
import processing.core.PApplet;

public class Main2 extends PApplet {
	Turtle t1, t2, t3;
	float length = 600;

	public void setup() {
		size(600, 600);
		t1 = new Turtle((int) (width / 2.0 - length / 2.0),
				(int) (height / 6.0), 0, this);
		t2 = new Turtle((int) (width / 2.0 - length / 2.0),
				(int) (3 * height / 6.0), 0, this);
		t3 = new Turtle((int) (width / 2.0 - length / 2.0),
				(int) (5 * height / 6.0), 0, this);

		drawShape(t1, length, 1);
		drawShape(t2, length, 5);
		drawShape(t3, length, 3);

		noLoop();
	}

	public void drawShape(Turtle t, float size, int iteration) {
		if (iteration < 1) {
			t.forward(size);
		} else {
			float newSize = size / 4;
			drawShape(t, newSize, iteration - 1);

			t.turnLeft(90);
			drawShape(t, newSize, iteration - 1);

			t.turnRight(90);
			drawShape(t, newSize, iteration - 1);

			t.turnRight(90);
			drawShape(t, newSize, iteration - 1);
			drawShape(t, newSize, iteration - 1);
			t.turnLeft(90);
			drawShape(t, newSize, iteration - 1);
			t.turnLeft(90);
			drawShape(t, newSize, iteration - 1);
			t.turnRight(90);
			drawShape(t, newSize, iteration - 1);
		}
	}
}
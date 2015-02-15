package net.dobervich.aprecursion;

import java.util.ArrayList;
import processing.core.PApplet;

public class Main extends PApplet {

    int circle_size = 18;
    float center_x, center_y;
    float PIXELS_PER_INCH = (float) 4.8;
    ArrayList<Point> side1, side2, side3;
    ArrayList<Vector> spiralVectors;

    public void setup() {
        size(600, 600);

        center_x = width / 2;
        center_y = height / 2;

        side1 = new ArrayList<Point>();
        side2 = new ArrayList<Point>();
        side3 = new ArrayList<Point>();
        spiralVectors = new ArrayList<Vector>();


        drawShape(width / 2, height / 2, 10, (float) (2 * PI / 13), 15, 60);
        noLoop();
    }

    public void drawShape(float x, float y, int n, float angleIncrement, float sideLength, float sideLengthIncrement) {
        side1.clear();
        side2.clear();
        side3.clear();
        float ongoingAngle = 0;

        float innerTriangleHeight = pixelToInch(sqrt(sideLength * sideLength / 2));
        System.out.println("************************");
        System.out.println("Inner triangle height = " + innerTriangleHeight);

        for (int i = 0; i < n; i++) {
            addTriangle(sideLength, ongoingAngle);
            sideLength += sideLengthIncrement;
            ongoingAngle += angleIncrement;
        }

        drawConnectingLines();
        drawCircles();

        displaySpiralInfo();
    }

    public void addTriangle(float sideLength, float a) {
        float angle = (float) a;
        float x = 0;
        float y = sideLength / sqrt(3);
        Point p1 = new Point(x * cos(angle) - y * sin(angle) + center_x,
                x * sin(angle) + y * cos(angle) + center_y);
        side1.add(p1);

        x = sideLength / 2;
        y = -sideLength / sqrt(12);
        Point p2 = new Point(x * cos(angle) - y * sin(angle) + center_x,
                x * sin(angle) + y * cos(angle) + center_y);
        side2.add(p2);

        x = -sideLength / 2;
        y = -sideLength / sqrt(12);
        Point p3 = new Point(x * cos(angle) - y * sin(angle) + center_x,
                x * sin(angle) + y * cos(angle) + center_y);
        side3.add(p3);
    }

    public void drawCircles() {
        for (Point p : side1) {
            this.ellipse(p.x, p.y, circle_size, circle_size);
        }

        for (Point p : side2) {
            this.ellipse(p.x, p.y, circle_size, circle_size);
        }

        for (Point p : side3) {
            this.ellipse(p.x, p.y, circle_size, circle_size);
        }
    }

    public float pixelToInch(float p) {
        return p / PIXELS_PER_INCH;
    }

    public String d(float p) {
        int t = (int)Math.round((p - Math.floor(p))*8);
        return (int)Math.floor(p) + " " + t + "/8";
    }
    
    public void drawConnectingLines() {
        Point p1, p2;
        p1 = side2.get(0);
        p2 = side1.get(0);
        spiralVectors.add(new Vector(p1.x, p1.y, p2.x, p2.y));
        
        for (int i = 0; i < side1.size() - 1; i++) {
            p1 = side1.get(i);
            p2 = side1.get(i + 1);
            line(p1.x, p1.y, p2.x, p2.y);
            spiralVectors.add(new Vector(p1.x, p1.y, p2.x, p2.y));

            p1 = side2.get(i);
            p2 = side2.get(i + 1);
            line(p1.x, p1.y, p2.x, p2.y);

            p1 = side3.get(i);
            p2 = side3.get(i + 1);
            line(p1.x, p1.y, p2.x, p2.y);
        }

        for (int i = side1.size() - 1; i > 2; i--) {
            p1 = side1.get(i);
            p2 = side2.get(i - 2);
            line(p1.x, p1.y, p2.x, p2.y);

            p1 = side2.get(i);
            p2 = side3.get(i - 2);
            line(p1.x, p1.y, p2.x, p2.y);

            p1 = side3.get(i);
            p2 = side1.get(i - 2);
            line(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public void displaySpiralInfo() {
        Vector v1, v2;
        stroke(255, 0, 0);

        for (int i = 0; i < spiralVectors.size()-1; i++) {
            v1 = spiralVectors.get(i);
            line(v1.p1.x, v1.p1.y, v1.p2.x, v1.p2.y);
            v2 = spiralVectors.get(i+1);
            System.out.print("Spiral Length: " + pixelToInch(v1.mag()));
            System.out.println("\t\t" + d(pixelToInch(v1.mag())));
            System.out.println("Angle to next: " + degrees(v1.angleTo(v2)));
        }

        v1 = spiralVectors.get(spiralVectors.size() - 1);
        line(v1.p1.x, v1.p1.y, v1.p2.x, v1.p2.y);
        System.out.print("Spiral Length: " + pixelToInch(v1.mag()));
        System.out.println("\t\t" + d(pixelToInch(v1.mag())));
        
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"artproject1.Main"});
    }

    public class Vector {
        public Point p1, p2;
        
        public Vector (float x1, float y1, float x2, float y2) {
            p1 = new Point(x1, y1);
            p2 = new Point(x2, y2);
        }
        
        public float mag() {
            //return dist(p1.x, p1.y, p2.x, p2.y);
            double dx = p1.x-p2.x;
            double dy = p1.y - p2.y;
            return (float)Math.sqrt(dx*dx + dy*dy);
        }

        public float angleTo(Vector v) {
            return acos(((p1.x-p2.x)*(v.p1.x-v.p2.x) + (p1.y-p2.y)*(v.p1.y-v.p2.y))/(this.mag()*v.mag()));
        }
    }
    
    public class Point {
        public float x, y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}

package net.dobervich.aprecursion;

import processing.core.PApplet;

public class RecursiveTree extends PApplet{
    Turtle t;
    public void setup(){
        size(600,600);    
        t = new Turtle(100, 300, 0, this);
    }
    public void draw(){
        //background(255);


    }
    public void mouseReleased(){
        background(255);
        drawTree(t,10,200);

        save("c:/data/myimage.jpg");
    }
    
    public void drawTree(Turtle t, int lvl,float length){
        
        if(lvl == 0) return;        
        t.forward(length);
        t.turnLeft((float)(30));
        drawTree(t,lvl-1, (float)(length*0.6));
        t.turnRight((float)(60));
        drawTree(t,lvl-1,(float)(length*0.6));
        t.turnRight((float)(5*30));    
        t.forward(length);
        t.turnRight((float)(180));
        
    }

}
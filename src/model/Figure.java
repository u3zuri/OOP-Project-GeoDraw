package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Figure implements Serializable{
    protected Point position; 
    protected Color color;
    protected boolean selected;
    
    //Constructor for Figure.
    public Figure(Point position, Color color){
        this.position = position;
        this.color = color;
        this.selected = false; 
    }

    //Getters and Setters
    public Point getPosition(){
        return position;
    }

    public void setPosition(Point position){
        this.position = position;
    }

    public Color getColor(){
        this.color = color;
    }

    public boolean isSelected(){
        return selected;
    }

    public void setSelected(boolean selected){
        this.selected = selected; 
    }

    //Abstract method to draw the figure.
    public abstract void draw(Graphics g);

    //Abstract method to check if a point is inside the figure. 
    public abstract boolean contains(Point p); 

    //Abstract method to get the figure's name/type.
    public abstract String getType();
    }

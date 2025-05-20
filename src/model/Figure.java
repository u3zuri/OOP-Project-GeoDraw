package model;

import java.awt.*;
import java.io.Serializable;

public abstract class Figure implements Serializable {
    protected Point position;
    protected int size;
    protected Color color;

    public Figure(Point position, int size, Color color) {
        this.position = position;
        this.size = size;
        this.color = color;
    }

    public abstract void draw(Graphics g);
    public void setColor(Color color) { this.color = color; }
    public abstract boolean contains(int x, int y);

    public void moveTo(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }   
}
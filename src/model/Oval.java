package model;

import java.awt.*;

public class Oval extends Figure {
    public Oval(Point position, int size, Color color) {
        super(position, size, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(position.getX(), position.getY(), size + 20, size);
    }

    @Override
    public boolean contains(int x, int y) {
        double dx = x - (position.getX() + (size + 20) / 2.0);
        double dy = y - (position.getY() + size / 2.0);
        return (dx * dx) / Math.pow((size + 20) / 2.0, 2) + (dy * dy) / Math.pow(size / 2.0, 2) <= 1;
    }
}
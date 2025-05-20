package model;

import java.awt.*;

public class Triangle extends Figure {
    public Triangle(Point position, int size, Color color) {
        super(position, size, color);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int[] xPoints = { position.getX(), position.getX() + size / 2, position.getX() + size };
        int[] yPoints = { position.getY() + size, position.getY(), position.getY() + size };
        g.fillPolygon(xPoints, yPoints, 3);
    }
}
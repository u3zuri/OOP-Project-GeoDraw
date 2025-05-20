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

    @Override
    public boolean contains(int x, int y) {
        int x1 = position.getX();
        int y1 = position.getY() + size;
        int x2 = position.getX() + size / 2;
        int y2 = position.getY();
        int x3 = position.getX() + size;
        int y3 = position.getY() + size;
        double a = 0.5 * (-y2 * x3 + y1 * (-x2 + x3) + x1 * (y2 - y3) + x2 * y3);
        double s = 1 / (2 * a) * (y1 * x3 - x1 * y3 + (y3 - y1) * x + (x1 - x3) * y);
        double t = 1 / (2 * a) * (x1 * y2 - y1 * x2 + (y1 - y2) * x + (x2 - x1) * y);
        return s > 0 && t > 0 && (1 - s - t) > 0;
    }
}
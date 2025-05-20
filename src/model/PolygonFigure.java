package model;

import java.awt.*;

public class PolygonFigure extends Figure {
    public PolygonFigure(Point position, int size, Color color) {
        super(position, size, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int[] xPoints = {position.getX(), position.getX() + size / 2, position.getX() + size};
        int[] yPoints = {position.getY() + size, position.getY(), position.getY() + size};
        g.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public boolean contains(int x, int y) {
        int[] xPoints = { position.getX(), position.getX() + size / 2, position.getX() + size };
        int[] yPoints = { position.getY() + size, position.getY(), position.getY() + size };
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        return triangle.contains(x, y);
    }
}
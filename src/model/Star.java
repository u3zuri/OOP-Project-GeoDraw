package model;

import java.awt.*;

public class Star extends Figure {
    public Star(Point position, int size, Color color) {
        super(position, size, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int[] xPoints = new int[10];
        int[] yPoints = new int[10];
        double angle = Math.PI / 5;
        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? size : size / 2.5;
            xPoints[i] = position.getX() + (int)(r * Math.cos(i * angle));
            yPoints[i] = position.getY() + (int)(r * Math.sin(i * angle));
        }
        g.fillPolygon(xPoints, yPoints, 10);
    }
}

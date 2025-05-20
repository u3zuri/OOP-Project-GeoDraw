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
        double centerX = position.getX();
        double centerY = position.getY();
        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? size : size / 2.5;
            double theta = -Math.PI / 2 + i * angle;  // Rotate starting angle upward
            xPoints[i] = (int) (centerX + r * Math.cos(theta));
            yPoints[i] = (int) (centerY + r * Math.sin(theta));
        }
        g.fillPolygon(xPoints, yPoints, 10);
    }


    @Override
    public boolean contains(int x, int y) {
        // Approximate with bounding box (optional improvement: polygon contains)
        return x >= position.getX() - size && x <= position.getX() + size &&
               y >= position.getY() - size && y <= position.getY() + size;
    }
}

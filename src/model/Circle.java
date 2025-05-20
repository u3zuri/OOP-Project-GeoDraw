package model;

import java.awt.*;

public class Circle extends Figure {
    public Circle(Point position, int size, Color color) {
        super(position, size, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(position.getX(), position.getY(), size, size);
    }

    @Override
    public boolean contains(int x, int y) {
        int centerX = position.getX() + size / 2;
        int centerY = position.getY() + size / 2;
        int dx = x - centerX;
        int dy = y - centerY;
        return dx * dx + dy * dy <= (size / 2) * (size / 2);
    }
}
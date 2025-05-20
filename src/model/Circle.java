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
}
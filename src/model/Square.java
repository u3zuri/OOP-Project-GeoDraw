package model;

import java.awt.*;

public class Square extends Figure {
    public Square(Point position, int size, Color color) {
        super(position, size, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(position.getX(), position.getY(), size, size);
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= position.getX() && x <= position.getX() + size &&
               y >= position.getY() && y <= position.getY() + size;
    }
}

package model;

import java.awt.*;

public class House extends Figure {
    public House(Point position, int size, Color color) {
        super(position, size, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(position.getX(), position.getY() + size / 2, size, size / 2);
        int[] xPoints = {position.getX(), position.getX() + size / 2, position.getX() + size};
        int[] yPoints = {position.getY() + size / 2, position.getY(), position.getY() + size / 2};
        g.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public boolean contains(int x, int y) {
        int baseX = position.getX();
        int baseY = position.getY() + size / 2;
        int w = size;
        int h = size / 2;
        boolean inBase = x >= baseX && x <= baseX + w && y >= baseY && y <= baseY + h;
        boolean inRoof = y >= position.getY() && y <= baseY && x >= baseX && x <= baseX + w;
        return inBase || inRoof;
    }
}

package control;

import model.Figure;
import java.awt.*;
import java.io.*;
import java.util.*;

public class System {
    protected List<Figure> figures = new ArrayList<>();
    protected Figure selected = null;

    public void addFigure(Figure figure) {
        figures.add(figure);
    }
    public void removeSelected() {
        if (selected != null) {
            figures.remove(selected);
            selected = null;
        }
    }
    public void clear() {
        figures.clear();
        selected = null;
    }
    public List<Figure> getFigures() {
        return figures;
    }
    public void selectAt(int x, int y) {
        for (Figure f : figures) {
            if (f.contains(x, y)) {
                selected = f;
                return;
            }
        }
        selected = null;
    }
    public void moveSelectedTo(int x, int y) {
        if (selected != null) {
            selected.position.setX(x);
            selected.position.setY(y);
        }
    }
    public void changeSelectedColor(Color color) {
        if (selected != null) {
            selected.setColor(color);
        }
    }
    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(figures);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            figures = (List<Figure>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Map<String, Integer> getFigureStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        for (Figure f : figures) {
            String name = f.getClass().getSimpleName();
            stats.put(name, stats.getOrDefault(name, 0) + 1);
        }
        return stats;
    }
}

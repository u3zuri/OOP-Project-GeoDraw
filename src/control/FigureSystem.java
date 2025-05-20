package control;

import model.Figure;
import java.awt.Color;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class FigureSystem implements Serializable {
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
    }
    

    public abstract void moveTo(int x, int y);
    public void moveSelectedTo(int x, int y) {
        if (selected != null) {
            // Assuming Figure has setPosition(int x, int y) or similar method
            // If not, you need to implement such a method in Figure and its subclasses
            selected.moveTo(x, y);
        }
    }

    public void changeSelectedColor(Color color) {
        if (selected != null) {
            selected.setColor(color);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            figures = (List<Figure>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(figures);
        } catch (IOException e) {
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

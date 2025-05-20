package control;

import model.*;
import view.MyWindow;
import java.awt.Color;

public class SystemWithDrawings extends FigureSystem {
    private MyWindow window;

    public void moveTo(int x, int y) {
        moveSelectedTo(x, y);
    }
    
    
    public void launch() {
        window = new MyWindow(this);
        window.setupUI();
        window.setVisible(true);
    }
    public void addCircle(Point p, Color color) {
        addFigure(new Circle(p, 60, color));
        window.repaintCanvas();
    }
    public void addSquare(Point p, Color color) {
        addFigure(new Square(p, 60, color));
        window.repaintCanvas();
    }
    public void addTriangle(Point p, Color color) {
        addFigure(new Triangle(p, 60, color));
        window.repaintCanvas();
    }
    public void addOval(Point p, Color color) {
        addFigure(new Oval(p, 60, color));
        window.repaintCanvas();
    }
    public void addStar(Point p, Color color) {
        addFigure(new Star(p, 40, color));
        window.repaintCanvas();
    }
    public void addHouse(Point p, Color color) {
        addFigure(new House(p, 60, color));
        window.repaintCanvas();
    }
    public void deleteSelectedFigure() {
        removeSelected();
        window.repaintCanvas();
    }
    public void changeSelectedFigureColor(Color color) {
        changeSelectedColor(color);
        window.repaintCanvas();
    }
    public void clearCanvas() {
        clear();
        window.repaintCanvas();
    }
    public void saveToFile() {
        saveToFile("shapes.dat");
    }
    public void loadFromFile() {
        loadFromFile("shapes.dat");
        window.repaintCanvas();
    }
    public void printStats() {
        System.out.println("--- Figure Statistics ---");
        var stats = getFigureStatistics();
        stats.forEach((name, count) -> System.out.println(name + ": " + count));
    }
    public void handleCanvasClick(Point p) {
        selectAt(p.getX(), p.getY());
    }
    public void handleCanvasDrag(Point p) {
        moveSelectedTo(p.getX(), p.getY());
        window.repaintCanvas();
    }
    public void handleCanvasRelease() {
        // Optional
    }
}
package view;

import control.SystemWithDrawings;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;

public class MyWindow extends JFrame {
    private SystemWithDrawings controller;
    private DrawingCanvas canvas;
    private JPanel sidePanel;
    private JButton selectedColorButton;
    private Color currentColor;

    public MyWindow(SystemWithDrawings controller) {
        this.controller = controller;
        this.currentColor = Color.RED;
    }

    public void setupUI() {
        setTitle("GeoDraw");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        canvas = new DrawingCanvas();
        sidePanel = createSidePanel();
        mainPanel.add(canvas, BorderLayout.CENTER);
        mainPanel.add(sidePanel, BorderLayout.EAST);

        JToolBar toolbar = createToolbar();
        mainPanel.add(toolbar, BorderLayout.NORTH);

        setContentPane(mainPanel);
    }

    private JPanel createSidePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setPreferredSize(new Dimension(200, getHeight()));

        JLabel titleLabel = new JLabel("Add Figures");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10));

        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectedColorButton = new JButton();
        selectedColorButton.setPreferredSize(new Dimension(50, 25));
        selectedColorButton.setBackground(currentColor);
        selectedColorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Choose Color", currentColor);
            if (newColor != null) {
                currentColor = newColor;
                selectedColorButton.setBackground(currentColor);
                controller.changeSelectedFigureColor(currentColor);
            }
        });
        colorPanel.add(new JLabel("Color: "));
        colorPanel.add(selectedColorButton);
        panel.add(colorPanel);
        panel.add(Box.createVerticalStrut(10));

        String[] figureTypes = {"Circle", "Square", "Triangle", "Oval", "Star", "House"};
        for (String type : figureTypes) {
            JButton button = new JButton("Add " + type);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(150, 30));
            button.addActionListener(e -> {
                Random rand = new Random();
                int x = rand.nextInt(canvas.getWidth() - 100) + 50;
                int y = rand.nextInt(canvas.getHeight() - 100) + 50;
                Point position = new Point(x, y);
                switch (type) {
                    case "Circle" -> controller.addCircle(position, currentColor);
                    case "Square" -> controller.addSquare(position, currentColor);
                    case "Triangle" -> controller.addTriangle(position, currentColor);
                    case "Oval" -> controller.addOval(position, currentColor);
                    case "Star" -> controller.addStar(position, currentColor);
                    case "House" -> controller.addHouse(position, currentColor);
                }
            });
            panel.add(button);
            panel.add(Box.createVerticalStrut(5));
        }

        panel.add(Box.createVerticalStrut(15));
        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setMaximumSize(new Dimension(150, 30));
        deleteButton.addActionListener(e -> controller.deleteSelectedFigure());
        panel.add(deleteButton);

        panel.add(Box.createVerticalStrut(15));
        JButton randomColorButton = new JButton("Random Color");
        randomColorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        randomColorButton.setMaximumSize(new Dimension(150, 30));
        randomColorButton.addActionListener(e -> {
            Random rand = new Random();
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            currentColor = randomColor;
            selectedColorButton.setBackground(currentColor);
            controller.changeSelectedFigureColor(currentColor);
        });
        panel.add(randomColorButton);

        panel.add(Box.createVerticalGlue());
        JTextArea instructions = new JTextArea(
            "Instructions:\n" +
            "- Click to select a figure\n" +
            "- Drag to move selected\n" +
            "- Change color with button\n" +
            "- Save/load from toolbar"
        );
        instructions.setEditable(false);
        instructions.setBackground(panel.getBackground());
        instructions.setFont(new Font("Arial", Font.ITALIC, 12));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(instructions);

        return panel;
    }

    private JToolBar createToolbar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> controller.saveToFile());
        toolbar.add(saveButton);

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> controller.loadFromFile());
        toolbar.add(loadButton);

        toolbar.addSeparator();

        JButton clearButton = new JButton("Clear Canvas");
        clearButton.addActionListener(e -> controller.clearCanvas());
        toolbar.add(clearButton);

        toolbar.addSeparator();

        JButton statsButton = new JButton("Statistics");
        statsButton.addActionListener(e -> controller.printStats());
        toolbar.add(statsButton);

        return toolbar;
    }

    public void repaintCanvas() {
        if (canvas != null) {
            canvas.repaint();
        }
    }

    private class DrawingCanvas extends JPanel {
        public DrawingCanvas() {
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            MouseAdapter mouseAdapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    Point p = new Point(e.getX(), e.getY());
                    controller.handleCanvasClick(p);
                }
                @Override
                public void mouseDragged(MouseEvent e) {
                    Point p = new Point(e.getX(), e.getY());
                    controller.handleCanvasDrag(p);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    controller.handleCanvasRelease();
                }
            };
            addMouseListener(mouseAdapter);
            addMouseMotionListener(mouseAdapter);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            List<Figure> figures = controller.getFigures();
            for (Figure figure : figures) {
                figure.draw(g);
            }
        }
    }
}

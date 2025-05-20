package model.control;

import model.*;
import view.MyWindow; 
import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser; 
import javax.swing.JOptionPane; 
import javax.swing.filechooser.FileNameExtensionFilter;

//Main controller class that extends System, adda the UI connection and provides the main method.

public class SystemWithDrawings extends System {
    private MyWindow window; 
    private Point dragStart;
    private Figure selectedFigure; 

    //Constructor for SystemWithDrawings

    public SystemWithDrawings(){
        super();
        window = new MyWindow(this);
        dragStart = null: 
        selectedFigure = null;
    }

    //Initialise the window and add sample figures. 

    public void initialise(){
        //Set up and display the window
        window.setupUI();
        window.setVisible(true);

        //Add some sample figures.
        private void addSampleFigures(){
            //Create and add a circle
            addFigure(new Circle(newPoint(100, 100), Color.RED, 50));

            //Create and add a square
            addFigure(new Square(newPoint(200, 200), Color.BLUE, 80));

            //Create and add a triangle
            addFigure(new Triangle(newPoint(350, 150), Color.GREEN, 100, 80));

            //Create and add an oval
            addFigure(new Oval(new Point(400, 300), Color.ORANGE, 120, 60));

            //Create and add a star
            addFigure(new Star (new Point(200, 350), Color.MAGENTA, 50, 25, 5));
            
            //Create and add a house
            addFigure(new House(new Point(500, 200), Color,CYAN, 100));

            //Request window repaint to show the figures.
            window.repaintCanvas();

        }

        //Hangle a click on the canvas. 
        public void handleCanvasClick(Point p){
            //Deselect all figures firstr
            deselectAll();

            //Find the figuree that was clicked on.
            Figure clickedFigure = getFigureAt(p); 
            if (clickeFigure != null){
                //If its not null, select it.
                clickedFigure.setSelected(true);
                selectedFigure = clickedFigure; 

                //Save the click position for potential dragging. 
                dragStart = p; 
            } else {
                //If no figure was clicked, deselect all.
                selectedFigure = null;  
            }

            //Repaint to show selection changes. 
            window.repaintCanvas();
        }

        //Handle mouse drag on the canvas.
        public void handleCanvasDrag(Point p) {
            if (selectedFigure != null && dragStart != null) {
                //Calculate the displacement / Drag distance.
                int dx = p.getX() - dragStart.getX();
                int dy = p.getY() - dragStart.getY();

                //Get current position
                Point position = selectedFigure.getPosition();

                //Update position
                selectedFigure.setPosition(new Point(position.getX() + dx, position.getY() + dy));
                
                //Update drag start for next drag start.
                dragStart = p; 

                //Repaint to show the moved figure.
                window.repaintCanvas();
            }
        }

        //HAndle mouse release on the canvas
        public void handleCanvasRelease(){
            dragStart = null; //Reset drag start
        }

        //Add a new Circle figure at a position with a color and radius.
        public void addCircle(Point position, Color color){
            addFigure(new Circle(position, color, 50));
            window.repaintCanvas();
        }

        //Add a new Square figure at a position with a color and size.
        public void addSquare(Point position, Color color){
            addFigure(new Square(position, color, 80));
            window.repaintCanvas();
        }

        //Add a new Triangle figure at a position with a color and size.    
        public void addTriangle(Point position, Color color){
            addFigure(new Triangle(position, color, 100, 80));
            window.repaintCanvas(); 
        }

        //Add a new Oval figure at a position with a color and size.    
        public void addOval(point position, Color color)
        addFigure(new Oval(position, color, 120, 60));
        window.repaintCanvas();
        }

        //Add a new Star figure at a position with a color and size.
        public void addStar(Point position, Color color){
            addFigure(new Star(position, color, 50, 25, 5));
            window.repaintCanvas(); 
        }

        //Add a new House figure at a position with a color and size.   
        public void addHouse(Point position, Color color){
            addFigure(new House(position, color, 100));
            window.repaintCanvas();
        }

        //Change color of the selected figure.
        public void changeSelectedFigureColor(Color color){
            if (selectedFigure != null){
                selectedFigure.setColor(color);
                window.repaintCanvas();
            }
        }   

        //Delete the currently selected figure.
        public void deleteSelectedFigure(){
            if (selectedFigure != null){
                removeFigure(selectedFigure);
                selectedFigure = null; //Deselect after deletion
                window.repaintCanvas();
            }
        }

        //Save figures to a file with a file chooser dialog.
        public void saveToFile(){
            JFileChooser fildeChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter ("GeoDraw Files (*.geo)", "geo"));

            if (fileChooser.showSaveDialog(window) == JFileChooser.APPROVE_OPTION){
                try { 
                    //Ensure the file has the correct extension.
                    String filename = fileChooser.getSelectedFile().getAbsolutePath();
                    if (!filename.toLowerCase().endswith(".geo")){
                        filename += ".geo";
                    }
                    
                    saveFigures(filename);
                    JOptionPane.showMessageDialog(window, "Figures saved successfully.");

                    //Write statistics to console.
                    java.lang.system.out.println("\n--- STATISTICS ---");
                    java.lang.System.out.println(getStatistics());
                    java.lang.System.out.println("----------------");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(window, "Error saving figures: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }

        //Load figures from a file with a file chooser dialog 
        public void loadFromFile(){
            JFileChooser fileChooser = new JFileChooser());
            fileChooser.setFileFilter(new FileNameExtensionFilter("GeoDraw Files (*.geo)", geo"));

            if (fileChooser.showOpenDialog(window) == JFileChooser.APPROVE_OPTION){
                try {
                    String filename = fileChooser.getSelectedFile().getAbsolutePath();
                    loadFigures(filename);
                    JOptionPane.showMessageDialog(window, "Figures loaded successfully.");
                    
                    //Deselect all figures after loading
                    deselectAll();
                    selectedFigure = null;
                    
                    //Repaint to show loaded figures
                    window.repaintCanvas();
                    
                    //Write statistics to console
                    java.lang.System.out.println("\n ---STATISTICS AFTER LOADING ---"); 
                    java.lang.System.out.println(getStatistics());
                    java.lang.System.out.println("----------------");
               } catch (Exception e) {
                    JOPtionPane.showMessageDialog(window, "Error loading figures: " + e.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }

        //Clear all figures from the canvas





    }

    }

}

package model;

import java.io.Serializable; 

public class Point {
    //Represent a 2D point with x and y coordinates
    public class Point implements Serializable{
        private int x; 
        private int y;

        //Constructor for Point
        public Point(int x, int y){
            this.x = x;
            this.y = y; 
        }

        //Getters and Setters
        public int getX(){
            return x; 
        }

        public void setX(int x){
            this.x = x;
        }

        public int getY(){
            return y;
        }

        public void setY(int y){
            this.y = y;
        }

        //Calculate the distance between this point and another point.
        public double distance(Point other){
            int dx = this.x - other.x;
            int dy = this.y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }

        public String toString(){
            return "Point(" + x + ", " + y + ")";
        }
    }
}

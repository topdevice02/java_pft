package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public static void main (String[] args) {
    Point p1 = new Point(8,7);
    Point p2 = new Point(-3,3);

    System.out.println("Расстояние между точками (" + p1.x + "," + p1.y + ") и (" + p2.x + "," + p2.y + ") = " + p1.distance(p1,p2));

  }

  public Point(double x, double y){
      this.x = x;
      this.y = y;
  }

  public double distance (Point p1, Point p2){
    return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y));
  }
}



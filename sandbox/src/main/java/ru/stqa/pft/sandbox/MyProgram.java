package ru.stqa.pft.sandbox;

public class MyProgram
{
  public static void main (String[] args) {
    Point p1 = new Point(8,7);
    Point p2 = new Point(-3,3);

    System.out.println("Расстояние между точками (" + p1.x + "," + p1.y + ") и (" + p2.x + "," + p2.y + ") = " + distance(p1,p2));

    Point p3 = new Point(10,4);
    Point p4 = new Point(0,2);

    System.out.println("Расстояние между точками (" + p3.x + "," + p3.y + ") и (" + p4.x + "," + p4.y + ") = " + p3.distance(p3,p4));

  }

  public static double distance (Point p1, Point p2)
  {
    return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y));
  }

}
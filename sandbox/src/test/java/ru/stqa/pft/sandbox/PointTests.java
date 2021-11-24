package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPointFirst(){
    Point p1 = new Point(7,5);
    Point p2 = new Point(4,5);
    Assert.assertEquals(p1.distance(p1,p2), 3.0);
  }

  @Test
  public void testPointSecond(){
    Point p3 = new Point(-3,11);
    Point p4 = new Point(-3,7);
    assert p3.distance(p3,p4) == 4;
  }
}

//Oscar Cansino
import java.util.*;
import java.io.*;
import java.awt.geom.Point2D;

public class CuriousHungryRobot extends Robot{
   
   public double Robot(int purpose){
       setenergyInitialCapacity(400);
      
      setLocationInit(0,0);
      
      
      CheckState();
      CheckLocation();
      generateGoal();
      generateEnergyNodes(purpose);
      
      ArrayList<Point2D.Double> returnVal = move(purpose);
      Point2D.Double temp = returnVal.get(0);
      
      double x1 = temp.getX();
      double y1 = temp.getY();
      double x2 = 0;
      double y2 = 0;
      return Point2D.distance(x1, y1, x2, y2);
      

   }
      
      
      

   
}
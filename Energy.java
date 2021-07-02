//Oscar Cansino
import java.util.*;
import java.awt.Point;
import java.awt.geom.Point2D;
public class Energy{
   private double energyInitialCapacity;
   private double energy;
   Deque<Point2D.Double> stack = new ArrayDeque<>();
   Deque<Point2D.Double> queue = new ArrayDeque<>();
   
   
   public double getenergy(){
      return this.energy;
   }
   
   public void setenergy(double energy){
      this.energy = energy;
   }

   public double getenergyInitialCapacity(){
      return this.energyInitialCapacity;
   }
   
   public void setenergyInitialCapacity(double energyInitialCapacity){
      this.energyInitialCapacity = energyInitialCapacity;
      setenergy(energyInitialCapacity);
      
   }
   
   
   public double CheckEnergy(){
      
      return energy;
   }
   
   public void consumeEnergy(double energyUsed){
      double energyLeft = energy - energyUsed;
      setenergy(energyLeft);
      CheckEnergy();
   }

   public Deque generateEnergyNodes(int purpose){
      
      Random rand = new Random();
         if (purpose == 1){
         
         for(int x = 0; x < 33; x++){
            double cordX = -200 + (200 - (-200)) * rand.nextDouble();
            double cordY = -200 + (200 - (-200)) * rand.nextDouble();
            Point2D.Double node = new Point2D.Double(cordX, cordY);
            stack.push(node);
         
         
         }
         
         return stack;
         
         
      }
      else{
         
         for(int x = 0; x < 33; x++){
            double cordX = -200 + (200 - (-200)) * rand.nextDouble();
            double cordY = -200 + (200 - (-200)) * rand.nextDouble();
            Point2D.Double node = new Point2D.Double(cordX, cordY);
            queue.offer(node);
         }
         
         return queue;
         
      }
      
   }
   public Point2D.Double getEnergyOrb(int purpose){
      if(purpose == 1){
         Point2D.Double y = stack.pop();
         return y;
      }
      else{
         Point2D.Double y = queue.remove();
         return y;
      }
   }

}
//Oscar Cansino
import java.util.*;
import java.awt.geom.Point2D;
public class Robot extends Energy{
   private String state;
   ArrayList<Point2D.Double> coordinate = new ArrayList<Point2D.Double>();
   ArrayList<Point2D.Double> location = new ArrayList<Point2D.Double>(); 
   ArrayList<Point2D.Double> curiousGoal= new ArrayList<Point2D.Double>();
   
      
   public String CheckState(){
      double CurrentEnergy = CheckEnergy();
      if (CurrentEnergy > 200){
         state = CuriousState();
         return state;
      }
      else if(CurrentEnergy <= 200 && CurrentEnergy > 0){
         state = HungryState();
         return state;
      }
      else{
         state = InactiveState();
         return state;
      }
   }
   
   public String CuriousState(){
      return "Curious";
   }
   
   public String HungryState(){
      
      return "Hungry";
   }
   
   public String InactiveState(){
      return "Inactive";
   }
   
   public ArrayList<Point2D.Double> generateGoal(){
      Point2D.Double init = new Point2D.Double(0, 1);
      curiousGoal.add(init);
      
      Random rand = new Random();
      double goalX = -200 + (200 - (-200)) * rand.nextDouble();
      double goalY = -200 + (200 - (-200)) * rand.nextDouble();
      
      
      Point2D.Double curious = new Point2D.Double(goalX, goalY);
      curiousGoal.set(0, curious);
      return curiousGoal;
   }
   
   public void setLocationInit(double x, double y){
      Point2D.Double init = new Point2D.Double(0, 0);
      coordinate.add(init);
      Point2D.Double location = new Point2D.Double(x, y);
      coordinate.set(0, location);
   }
   
   public Point2D setLocation(double x, double y){
      Point2D.Double location = new Point2D.Double(x, y);
      coordinate.set(0, location);
      
      return location;
   }
   
   public ArrayList<Point2D.Double> CheckLocation(){
      return coordinate;
   }
   
   public ArrayList<Point2D.Double> move(int purpose){ 
      
      int t = 0;
      do{
      
      Point2D.Double current = coordinate.get(0);
      Point2D.Double goal = curiousGoal.get(0);
      
      
      
      double currentX = current.getX();
      double currentY = current.getY();
      
      
      String choiceX;
      String choiceY;
      
      if (goal.getX() > currentX){
         choiceX = "forward";
      }
      else if(goal.getX() < currentX){
         choiceX = "backward";
      }
      else{
         choiceX = "stay";
      }
      
      if (goal.getY() > currentY){
         choiceY = "forward";
      }
      else if(goal.getY() < currentY){
         choiceY = "backward";
      }
      else{
         choiceY = "stay";
      }
      
      int XenergyUsed;
      int YenergyUsed;
      if (choiceX == "forward"){
         currentX += 13;
         XenergyUsed = 13;
      }
      else if (choiceX == "backward"){
         currentX -= 13;
         XenergyUsed = 13;
      }
      else{
         
         XenergyUsed = 0;
      }
      
      if (choiceY == "forward"){
         currentY += 13;
         YenergyUsed = 13;
      }
      else if (choiceY == "backward"){
         currentY -= 13;
         YenergyUsed = 13;
      }
      else{
         
         YenergyUsed = 0;
      }
      
      
      double energyUsed = XenergyUsed + YenergyUsed;
      Point2D.Double movement = new Point2D.Double(currentX, currentY);
      
      consumeEnergy(energyUsed);
      setLocation(currentX, currentY);
      checkSnap(currentX, currentY, goal);
      if(checkSnap(currentX, currentY, goal) == true){
         double newCurrX = goal.getX();
         double newCurrY = goal.getY();
         setLocation(newCurrX, newCurrY);
         generateGoal();
         
      }
      if(purpose == 1){
         checkHungry(1);
      }
      else if(purpose == 2){
         checkHungry(2);
      }
      }while(CheckState() != "Inactive");
      
      return coordinate; 
   }
   public boolean checkSnap(double currX, double currY, Point2D.Double goal){
      double val1 = currX - goal.getX();
      double val2 = currY - goal.getY();
      if((val1 <= 9) && (val1 >= -9) && (val2 <= 9) && (val2 >= -9)){
         return true;
      }
      else{
         return false;
      }
   }

   public void refresh(){
      coordinate.remove(0);
      curiousGoal.remove(0);
   }
   public void checkHungry(int purpose){
      if(purpose == 1 && CheckEnergy() <= 200 && stack.size() > 0){
         Point2D.Double newGoal = new Point2D.Double();
         curiousGoal.add(0, getEnergyOrb(purpose));
         move(purpose);
      }
      else if(purpose ==2 && CheckEnergy() <= 200 && queue.size() > 0){
      Point2D.Double newGoal = new Point2D.Double();
      curiousGoal.add(0, getEnergyOrb(purpose));
      move(purpose);

      }
  }


}
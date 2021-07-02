//Oscar Cansino
//Application class
//purpose is a placeholder for the memory type being used, 1= stack 2= queue


import java.util.*;
import java.io.*;
import java.awt.geom.Point2D;
public class Sample extends CuriousHungryRobot{
   private double mean = 0.0016;
   private double median;
   private double stdDev = 0.0205;
   double sum;
   int t = 0;
   ArrayList<Double> list1 = new ArrayList<Double>();
   
   
   public void Sample(double mean, double stdDev){
      this.mean = mean;
      this.stdDev = stdDev;
   }

   public void computeStats(ArrayList<Double> data){
      Random random = new Random();
                  
      Collections.sort(data);
      Collections.reverse(data);
      
      //size
      System.out.print("Size: " + data.size());
      
      //min
      System.out.print(" Min: " + data.get(data.size() - 1));
      
      //max
      System.out.print(" Max: " + data.get(0));
      
      //mean
      for(int x = 0; x < data.size(); x++){
         sum += data.get(x);
         
      }
      double average = sum / data.size();
      System.out.println(" Mean: " + average);
      
      //median
      int middle;
      if (data.size() > 0 && data.size() % 2 == 0){
         middle = (data.size() / 2) - 1;
      }
      else{
         middle = (data.size() / 2);
      }
      System.out.print("Median: " + data.get(middle));
      double med = data.get(middle);
      
      //std dev
      double temp = 0;
      for (int z = 0; z < data.size(); z++)
      {
        double val = data.get(z);
        double numSquare = Math.pow(val - mean, 2);
        temp += numSquare;
      }
      double StandardDev = (double) temp / (double) (data.size());
      System.out.println(" Standard Deviation: " + Math.sqrt(StandardDev));
      
      
   }
   public static void main(String[] args) {
      Sample one = new Sample();
      one.Run(1);
      one.Run(2); 
   }
   public void Run(int purpose){
      CuriousHungryRobot Robbie = new CuriousHungryRobot();
      ArrayList<Double> travel = new ArrayList<Double>();
      int run = 0;
      do{
      travel.add(Robbie.Robot(purpose));
      
      Robbie.refresh();
      run++;
      }while(run < 1000);
      
      int arraySize = travel.size();
      
      computeStats(travel);

   
    
   }
   
   public ArrayList<Double> read(){
      StringBuilder str = new StringBuilder();
        String strLine = "";
        ArrayList<String> list = new ArrayList<String>();
        try {
             BufferedReader reader = new BufferedReader(new FileReader("SP500-Weekly.txt"));
             while (strLine != null){
                str.append(strLine);
                str.append(System.lineSeparator());
                strLine = reader.readLine();
                if (strLine==null){
                   break;
                }
                list.add(strLine);
             }            
             reader.close();
         } catch (FileNotFoundException e) {
            System.err.println("File not found");
         } catch (IOException e) {
            System.err.println("Can't read the file.");
         }   
            
         do{
         list1.add(convert(list, t));  //convert data to double
         t++;
         }while(t < list.size());
         return list1;    
   }       
   
   public double convert(ArrayList<String> list, int t){
         ArrayList<String> tres = new ArrayList<String>();
            String tem = list.get(t);
            for (int s = (tem.indexOf("	") + 1); s < tem.length(); s++){ //remove date and space before the return data
               char b = tem.charAt(s);
               String f = String.valueOf(b);
               tres.add(f);
            }
            
            String r = String.join("", tres);
            double k = Double.parseDouble(r);
            return k;
     }  
}


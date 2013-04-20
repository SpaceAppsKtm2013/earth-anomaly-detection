/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Analysis;

import Model.coordinateId;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author paradise lost
 */
public class spatialPopulation {
    
   private float leftbottom_lat; 
   private float leftbottom_long;
   private float righttop_lat;
   private float righttop_long;
   public static int breath;
   public static int length;
    
   public spatialPopulation()
   {
//   this.leftbottom_lat =(float) 26.25;
//   this.leftbottom_long=(float) 67.25;
//   this.righttop_lat=(float)   36.25;//46.25;
//   this.righttop_long=(float) 77.25;//109.25;  
   this.leftbottom_lat =(float) 26.25;
   this.leftbottom_long=(float) 79.25;
   this.righttop_lat=(float)   29.25;//46.25;
   this.righttop_long=(float) 85.25;//109.25;   
   }
   
   public spatialPopulation(float b,float a, float y, float x)
   {
   this.leftbottom_lat =b;
   this.leftbottom_long=a;
   this.righttop_lat=y;
   this.righttop_long=x;   
   }
   
    
   public ArrayList<String> populateLocation() throws SQLException{
   spatialPopulation.breath=(int) ((int) (righttop_lat-leftbottom_lat)/0.5+1);
   spatialPopulation.length=(int) ((int) (righttop_long-leftbottom_long)/0.5);
   //System.out.println(breath+":"+length+":"+length*breath);
   ArrayList<String> idList=new ArrayList<String>();
   coordinateId coordinates = new coordinateId();  
   int list_iter=0;
   for(float lat=(float)leftbottom_lat;lat<=righttop_lat;)
   {
   for(float longi=(float) leftbottom_long;longi<righttop_long;)
   {       
   idList.add(coordinates.getId(lat,longi));//adding the populated location to a ArrayList<String>
   longi=(float) (longi+0.5);
   }
   lat=(float) (lat+0.5);
   }
   
   return idList;
   } 
}

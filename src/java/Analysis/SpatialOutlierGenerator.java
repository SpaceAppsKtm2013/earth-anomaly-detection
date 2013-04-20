package Analysis;

import Model.tempData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SpatialOutlierGenerator {
    private String year;
    private String month;
    ArrayList<Float> regionROw;
    private String regionName;
   
    public SpatialOutlierGenerator(String year, String month , ArrayList<Float> regionROw){
    this.year=year; 
    this.month=month;    
    this.regionROw=regionROw;
    }
    
    public SpatialOutlierGenerator(String year, String month ,String regionName ){
    this.year=year; 
    this.month=month;
    this.regionName=regionName;
    }
    
   private float distance(float t1, float t2){
   return (float)(Math.abs(t1-t2)/1.35);//distance function
   }
   
   private float inverseTangent(float del){
   return (float)((float)(1-Math.exp(del))*(-1)/(1+Math.exp(del)));//inverse Tangent function [0,-1]
   }
   
   private float anoiValue(float east , float west , float north , float south , float middle){
       return  (float)(Math.exp(inverseTangent(distance(east,middle))+inverseTangent(distance(west,middle))+
                   inverseTangent(distance(north,middle))+inverseTangent(distance(south,middle))));   
   }//anoi function 
   
   private Map idTemps() throws SQLException{
   Map IdTemp=new HashMap();   
   spatialPopulation sp = new spatialPopulation(regionROw.get(0),regionROw.get(1),regionROw.get(2),regionROw.get(3));
   String databaseTableName="air_temp_"+this.year;
   tempData data=new tempData(databaseTableName);
   ArrayList<String> locationIds=sp.populateLocation();
   System.out.println("id and temperature values ");
   for(String id: locationIds)
   { 
     IdTemp.put(id,data.getTempValue(id,month));   
   }
   return IdTemp;    
   }
   
   public Map getAnois() throws SQLException {
   //float sum=0;
   Map IdTemp=this.idTemps();
   Map IdAnois=new HashMap();
  ArrayList<String> values=new ArrayList<String>();
  int iter=0;
  values.add("");
  for (Object key: IdTemp.keySet()) { 
      values.add((String)IdTemp.get(key));
} 
iter=0;
int length=spatialPopulation.length;
int breath=spatialPopulation.breath;
System.out.println(length*breath+"no of locations");
for (Object key: IdTemp.keySet()) {
iter++;
if(iter<(length+1) || (iter+length)>(length*breath) || iter%length==0 || (iter-1)%length==0)
continue ;//skipping the borders 
Float north,south,east,west,current;
current=new Float(values.get(iter)); 
south=new Float(values.get(iter-1));
east=new Float(values.get(iter+length));
west=new Float(values.get(iter-length));
north=new Float(values.get(iter+1));
float value=(float) (anoiValue(east,west,north,south,current)/54.6);
IdAnois.put(key,value);
//System.out.println(key.toString()+":"+value);
//sum=sum+value;
}  
//float mean=sum/iter;
//Map resultAnoi=filterAnoi(IdAnois,mean,25);
//return resultAnoi;
return IdAnois;         // because we dont have to filter the anois , 
                        // this function just returns the anoi values and Ids in the map
   }
   
   public Map filterAnoi(Map IdAnois,float mean,float percentConfidence){   
   Map resultAnoi=new HashMap();
   float confidence= (float) (percentConfidence*mean*2/100);
  float sd=0;
  for (Object key: IdAnois.keySet()) {
      if(new Float(IdAnois.get(key).toString())<confidence)
  resultAnoi.put(key,Math.ceil((0.2-new Float(IdAnois.get(key).toString()))*200000));
  }
   return resultAnoi;
   }
   
   
//  public static void main(String[] args) throws SQLException
//  {
//  SpatialOutlierGenerator g1= new SpatialOutlierGenerator("2000","JAN");
//  Map anois=g1.getAnois();
//  System.out.println("See the anois getting sprout");
//  for (Object key: anois.keySet()) {
//      System.out.println(anois.get(key));
//  }
//
//  }
     
}
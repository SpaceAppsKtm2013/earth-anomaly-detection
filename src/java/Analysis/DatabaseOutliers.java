/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Analysis;

import AnoiModel.AnoiData;
import AnoiModel.RegionIdData;
import Model.CoordinateData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author oscine
 */
public class DatabaseOutliers {
    
    String Year;
    String Month;
    String regionId;
    
  public DatabaseOutliers(String Year,String Month,String regionId)
    {
    this.Month=Month.toUpperCase();
    this.Year=Year;
    this.regionId=regionId;
    }
    
    public Map getAnois() throws SQLException
    {
        RegionIdData rd=new RegionIdData();
        String regionList=rd.getRegionSubList(this.regionId);
        //debuggers check 
        System.out.println("Region List String "+regionList);
        String[] regionListArray=regionList.split(" ");
        // debuggers check
        System.out.println(regionListArray[0]);
    Map BigidAndAnoi=new HashMap(); 
    for(int i=0;i<regionListArray.length;i++)
    {
        //debuggers code 
        System.out.println("entered inside the for loop");
    Map idAndAnoi=new HashMap();   
    AnoiData a1=new AnoiData("anoi_"+this.Year);
    idAndAnoi= a1.getAnoiValue(regionListArray[i], this.Month);
    
     for (Object key: idAndAnoi.keySet()) {
         BigidAndAnoi.put(key,idAndAnoi.get(key));
         System.out.println(key+":"+BigidAndAnoi.get(key));
     }
    }
    
    return BigidAndAnoi;
    
    }
    
    
    public ArrayList<ArrayList<String>> getOutliers() throws SQLException
    {
        Map anois=getAnois();
        ArrayList<ArrayList<String>> results= new ArrayList<ArrayList<String>>();
        CoordinateData c1=new CoordinateData();
         for (Object key: anois.keySet()) {
        ArrayList<String> list=new ArrayList<String>();
         list=c1.getLongLat(new Long(key.toString()));
         list.add(anois.get(key).toString());
         list.add(key.toString());
         results.add(list);
          } 
         return results;
    }
    
    
    
    //filter anois and retieve lat,long for the coorninates and send it to jsp page
}

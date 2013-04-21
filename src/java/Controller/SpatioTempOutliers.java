/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Analysis.DatabaseOutliers;
import AnoiModel.AnoiData;
import AnoiModel.RegionIdData;
import Model.CoordinateData;
import STAnalysis.SpTempOutliers;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author paradise lost
 */
class SpatioTempOutliers  {
private int year1;
private int year2;
private String month1;
private String month2;
private String region;
AnoiData an1;

    public SpatioTempOutliers(int year1, int year2, String month1, String month2, String region) {
        this.year1=year1;
        this.year2=year2;
        this.month1=month1.toUpperCase();
        this.month2=month2.toUpperCase();
        this.region=region;        
        //To change body of generated methods, choose Tools | Templates.
    }

    HashSet getAllspTempOutliers() throws SQLException {
        
     ArrayList<ArrayList<String>> results= new ArrayList<ArrayList<String>>();
     RegionIdData rid=new RegionIdData();     
    ArrayList<String> smallList=rid.getCentralCoordinate(region);
    results.add(smallList);

     String months="JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DECM";
    String[] monthList=months.split(" ");
    Map mthMap=new HashMap();
    ArrayList<String> mthList=new ArrayList<String>();
    for(int i=0;i<monthList.length;i++)
    {
      mthMap.put(monthList[i],i);
      mthList.add(monthList[i]);
    }
    System.out.println(mthMap);
    int startMonth=(Integer)mthMap.get(month1);
    int endMonth=(Integer)mthMap.get(month2);
    HashSet OutLierId=new HashSet();
  
    for(int year=year1;year<=year2;year++)
    {
    for(int monthVal=startMonth; monthVal<=endMonth; monthVal++)
    {
       DatabaseOutliers o1=new DatabaseOutliers(year+"",mthList.get(monthVal),region); 
       HashMap IDanoi=(HashMap) o1.getAnois();
       for (Object key: IDanoi.keySet()) {
          OutLierId.add(key.toString());
              }
    }
    }
    HashMap<String, ArrayList<Double>> datas = new HashMap<String, ArrayList<Double>>();
    for(Object value:OutLierId)
    { 
        ArrayList<Double> idUnique=new ArrayList<Double>();
        double maximum=0;
    double sum=0;
     for(int year=year1;year<=year2;year++)
    {
        double valueAnoi=0;
        an1=new AnoiData("anoi_"+year);
    for(int monthVal=startMonth; monthVal<=endMonth; monthVal++)
    {
valueAnoi=an1.getAnoiData(value.toString(),mthList.get(monthVal));
idUnique.add(valueAnoi);
sum+=valueAnoi;
 if(maximum<valueAnoi)
     maximum=valueAnoi;
    }
    }
     datas.put(value.toString(),idUnique);
    }
    return OutLierId;
        //To change body of generated methods, choose Tools | Templates.
    
}

public static void main(String[] args) throws SQLException{
SpatioTempOutliers sto=new SpatioTempOutliers(2000,2002,"JAN","DECM","1");
HashSet OutLierId=sto.getAllspTempOutliers();
System.out.println(OutLierId);
}    

}

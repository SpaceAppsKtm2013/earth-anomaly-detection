/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Analysis.DatabaseOutliers;
import AnoiModel.RegionIdData;
import Model.CoordinateData;
import STAnalysis.SpTempOutliers;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

    SpatioTempOutliers(int year1, int year2, String month1, String month2, String region) {
        this.year1=year1;
        this.year2=year2;
        this.month1=month1;
        this.month2=month2;
        this.region=region;        
        //To change body of generated methods, choose Tools | Templates.
    }

    ArrayList<ArrayList<String>> getAllspTempOutliers() throws SQLException {
        
     ArrayList<ArrayList<String>> results= new ArrayList<ArrayList<String>>();
     RegionIdData rid=new RegionIdData();     
    ArrayList<String> smallList=rid.getCentralCoordinate(region);
    results.add(smallList);

     String months="FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DECM";
    String[] monthList=months.split(" ");
    Map mthMap=new HashMap();
    ArrayList<String> mthList=new ArrayList<String>();
    for(int i=0;i<monthList.length;i++)
    {
      mthMap.put(monthList[i],i);
      mthList.add(monthList[i]);
    }
    int startMonth=(Integer)mthMap.get(month1);
    int endMonth=(Integer)mthMap.get(month2);
    HashSet hs=new HashSet();
    for(int year=year1;year<=year2;year++)
    {
    for(int monthVal=startMonth; monthVal<=endMonth; monthVal++)
    {
        
    }
    }
    
        return null;
        //To change body of generated methods, choose Tools | Templates.
    }

    

}

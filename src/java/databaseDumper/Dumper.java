/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseDumper;

import Analysis.SpatialOutlierGenerator;
import Model.regionData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author paradise lost
 */
public class Dumper {
private String[]  monthsList,yearList,regionList;

    public Dumper(){
   String months="JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DECM";
   monthsList=months.split(" ");
   String years="2000 2001 2002 2003 2004";
   yearList=years.split(" ");
   String regionIds="27";
   regionList=regionIds.split(" ");
       }
    
    public void startDumping() throws SQLException
    {
    SpatialOutlierGenerator g1;// = new SpatialOutlierGenerator("2000","JAN","1");
    String year=new String();
    for(int i=0;i<regionList.length;i++){//for loop for regions
        System.out.println("entered : regionList "+regionList[i]);
   regionData r1=new regionData();
   ArrayList<Float> regionCoordinate=r1.getRegionData(regionList[i]);//extracted the two ends of rectangular coordinate
   System.out.println("Extracted region coordinate "+regionCoordinate);
    for(int j=0;j<yearList.length;j++){//for loop for year list
       System.out.println("entered : regionList "+regionList[i]);
    System.out.println("table created");
    for(int k=0;k<monthsList.length;k++){// for loop for months list 
    System.out.println("month List entered  "+monthsList[k]);
    g1= new SpatialOutlierGenerator(yearList[j],monthsList[k],regionCoordinate);
    Map anois=g1.getAnois();
    AnoiTableUpdator a=new AnoiTableUpdator(yearList[j],monthsList[k],regionList[i]);
    a.InjectValues(anois);
    }
  }
  }
  }
  
    public static void main(String [] args) throws SQLException
{
Dumper d1=new Dumper();
d1.startDumping();

}
    
  }

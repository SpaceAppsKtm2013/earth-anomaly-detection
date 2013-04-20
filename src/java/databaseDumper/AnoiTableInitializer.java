/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseDumper;

import Analysis.spatialPopulation;
import Model.Connector;
import Model.regionData;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author paradise lost
 */
public class AnoiTableInitializer {
    private String databaseTable;
    private Connector connector;
    private Statement st;  
     private String[]  monthsList,yearList,regionList;
   
    public AnoiTableInitializer() throws SQLException{
        
   String months="JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DECM";
   monthsList=months.split(" ");
   String years="2000 2001 2002 2003 2004";
   yearList=years.split(" ");
   String regionIds="10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26";
   regionList=regionIds.split(" ");
    this.connector=new Connector();
    this.st=(Statement)connector.returnStatement();
        for(int j=0;j<yearList.length;j++){
            this.databaseTable="anoi_"+yearList[j];
    String query="CREATE TABLE IF NOT EXISTS `"+this.databaseTable+"` (`id` int(11) NOT NULL,`RegionId` int(3) NOT NULL"
            + ",`JAN` float NOT NULL,`FEB` float NOT NULL,`MAR` float NOT NULL,`APR` float NOT NULL,"
            + "  `MAY` float NOT NULL,`JUN` float NOT NULL,`JUL` float NOT NULL,`AUG` float NOT NULL,"
            + "`SEP` float NOT NULL,`OCT` float NOT NULL,`NOV` float NOT NULL,`DECM` float NOT NULL,"
            + "KEY `id` (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;";
   st.executeUpdate(query);
        }
    }
    
    public void InsertZeros() throws SQLException{
        
        
      spatialPopulation sp1;// = new SpatialOutlierGenerator("2000","JAN","1");
    String year=new String();
    for(int i=0;i<regionList.length;i++){
    String regionId;
    if("1".equals(regionList[i])||"2".equals(regionList[i])||"3".equals(regionList[i]))
        regionId="2";
    else if("4".equals(regionList[i])||"5".equals(regionList[i])||"6".equals(regionList[i])||"7".equals(regionList[i])||"8".equals(regionList[i]))
        regionId="1";
     else if("9".equals(regionList[i]))
        regionId="3";
     else if("10".equals(regionList[i])||"11".equals(regionList[i])||"12".equals(regionList[i])||"13".equals(regionList[i]))
        regionId="4";
     else if("14".equals(regionList[i])||"15".equals(regionList[i]))
        regionId="5";
     else if("16".equals(regionList[i])||"17".equals(regionList[i])||"18".equals(regionList[i])||"19".equals(regionList[i]))
        regionId="6";
     else if("20".equals(regionList[i])||"21".equals(regionList[i])||"22".equals(regionList[i])||"23".equals(regionList[i])||"24".equals(regionList[i])||"24".equals(regionList[i])||"25".equals(regionList[i])||"26".equals(regionList[i]))
        regionId="7";
          else 
          regionId="8";
        System.out.println("entered : regionList "+regionList[i]);
   regionData r1=new regionData();
   ArrayList<Float> regionCoordinate=r1.getRegionData(regionList[i]);
       sp1= new spatialPopulation(regionCoordinate.get(0),regionCoordinate.get(1),regionCoordinate.get(2),regionCoordinate.get(3));
   System.out.println("Extracted region coordinate "+regionCoordinate);
   ArrayList<String> IdList=sp1.populateLocation();
    for(int j=0;j<yearList.length;j++){
            String DatabaseTable="anoi_"+yearList[j];
   for(String ids: IdList)
   { 
       
        String query="INSERT INTO `stdata`.`"+DatabaseTable+"`"
            + "(`id`,`RegionId`)"
            + " VALUES ('"+ids+"','"+regionList[i]+"');";   
     st.executeUpdate(query);    
   }  
    }
    }
    }
    
    public static void main(String [] args) throws SQLException
    {
 AnoiTableInitializer a1=new AnoiTableInitializer();
 a1.InsertZeros();
 
    }
    
    
    
}

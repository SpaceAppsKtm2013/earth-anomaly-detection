/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package STModel;

import Model.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Smrita
 */
public class RegionDataRetrieval
{
    public double breadth;
    public double length;
    
    Connector connector;
    Statement st;
    String regionsDbTable;  
    
    
    public RegionDataRetrieval(String tableName) throws SQLException
    {
             Connector connector=new Connector();
             st=connector.returnStatement();
             System.out.println("connected");
             regionsDbTable=tableName;
    }
    
    private double getLeftBottomLat(String regionsId) throws SQLException
    {        double bottomLat = 0;
            
             String query="SELECT * FROM `"+regionsDbTable+"` WHERE id="+regionsId;  
             ResultSet rs=st.executeQuery(query);
             while(rs.next())
            {
               bottomLat=rs.getInt("leftbottomLat");
             }
        
            return bottomLat; 
   
    }
    
     private double getRightTopLat(String regionsId) throws SQLException
    {        double topLat = 0;
            
             String query="SELECT * FROM `"+regionsDbTable+"` WHERE id="+regionsId;  
             ResultSet rs=st.executeQuery(query);
             while(rs.next())
            {
               topLat=rs.getInt("righttopLat");
             }
        
            return topLat; 
   
    }
      private double getLeftBottomLong(String regionsId) throws SQLException
    {        double bottomLong = 0;
            
             String query="SELECT * FROM `"+regionsDbTable+"` WHERE id="+regionsId;  
             ResultSet rs=st.executeQuery(query);
             while(rs.next())
            {
               bottomLong=rs.getInt("leftbottomLong");
             }
        
            return bottomLong; 
   
    }
       private double getRightTopLong(String regionsId) throws SQLException
    {        double topLong = 0;
            
             String query="SELECT * FROM `"+regionsDbTable+"` WHERE id="+regionsId;  
             ResultSet rs=st.executeQuery(query);
             while(rs.next())
            {
               topLong=rs.getInt("righttopLong");
             }
        
            return topLong; 
   
    }
       
       public int getLength(String regionsId) throws SQLException
       {
              double maxLong;
              double minLong;
              int length;
              maxLong=getRightTopLong(regionsId);
              minLong=getLeftBottomLong(regionsId);
              length=(int)((int)(maxLong-minLong)/0.5);
              return length;
              
       }
       
       public int getBreadth(String regionsId) throws SQLException
       {
           double maxLat;
           double minLat;
           int breadth;
           maxLat=getRightTopLat(regionsId);
           minLat=getLeftBottomLat(regionsId);
           breadth=(int) ((int)(maxLat-minLat)/0.5+1);
           return breadth;
            
       }
       
       public static void main(String[] args) throws SQLException
       {
           int b,l;
           RegionDataRetrieval rdr=new RegionDataRetrieval("regions");
           b=rdr.getBreadth("1");
           l=rdr.getLength("1");
           
           System.out.println("breadth\tlength"+b+"\t"+l);
          
       
       }
    
}

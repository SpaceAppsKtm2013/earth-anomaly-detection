/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnoiModel;

import Model.Connector;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author paradise lost
 */
public class RegionIdData {
    
    
    Connector connector;
    Statement st;
    
    public RegionIdData() throws SQLException
    {
   
    connector=new Connector();
    st=(Statement)connector.returnStatement();
    }
    
    public String getRegionSubList(String bigRegionId) throws SQLException
    {
    String query="SELECT IdString"+" FROM regionidtable"+" WHERE Id ="+bigRegionId+";";   
     ResultSet rs=st.executeQuery(query);
     System.out.println("result set for the subregion List"+rs);
     String IdString ="27";
    while(rs.next()){   
        IdString=rs.getString("IdString");
        System.out.println(IdString+"here it is");
    }
    return IdString; 
    
    }
    
    public ArrayList<String> getCentralCoordinate(String bigRegionId) throws SQLException
    {
     ArrayList<String> centralCoordinates = new ArrayList<String>();
      String query="SELECT centralLatitude, centralLongitude FROM regionidtable WHERE id ="+bigRegionId;
      ResultSet rs=st.executeQuery(query);
      while(rs.next())
      {
      centralCoordinates.add(0,rs.getString("centralLatitude")); 
      centralCoordinates.add(1,rs.getString("centralLongitude")); 
      }
      centralCoordinates.add(2,"1");//for population radius
      return centralCoordinates;
    }
}

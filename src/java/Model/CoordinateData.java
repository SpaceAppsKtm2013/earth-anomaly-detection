/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author paradise lost
 */
public class CoordinateData {
    private String databaseTable="spatial_coordinate";
    Connector connector;
    Statement st;
    
    public  CoordinateData() throws SQLException
    {  
    connector=new Connector();
    st=(Statement)connector.returnStatement();
    }
    
    public ArrayList<String> getLongLat(long Id) throws SQLException
    {
     String query="SELECT * FROM `spatial_coordinate` WHERE id="+Id+" LIMIT 1";   
     ResultSet rs=st.executeQuery(query);     
    ArrayList<String> list=new ArrayList<String>();
    while(rs.next()){
    list.add(rs.getString("latitude"));
    list.add(rs.getString("longitude"));    
    }
    return list;
    }
    

    
    
}

/* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author paradise lost
 */
public class coordinateId {
    private String databaseTable="spatial_coordinate";
    Connector connector;
    Statement st;
    
    public  coordinateId() throws SQLException
    {  
    connector=new Connector();
    st=(Statement)connector.returnStatement();
    }
    
    public String getId(float latitude,float longitude) throws SQLException
    {
     String query="SELECT * FROM `"+databaseTable+"` WHERE latitude = "+latitude+" AND longitude = "+longitude;   
     ResultSet rs=st.executeQuery(query);
     String rowa="";
    while(rs.next()){
    rowa=rs.getString("Id");
    }
    if( "".equals(rowa))
        System.out.println("Skipped"+latitude+","+longitude);
    
    return rowa;    
    } 
}

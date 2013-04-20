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
public class tempData {
    private String databaseTable;
    Connector connector;
    Statement st;
    
    public  tempData(String databaseTableName) throws SQLException
    {  
    this.databaseTable=databaseTableName;    
    connector=new Connector();
    st=(Statement)connector.returnStatement();
    }
    
    public String getTempValue(String Id,String Month) throws SQLException
    { String tempValue ="";
     String query="SELECT * FROM `"+databaseTable+"` WHERE id="+Id+" LIMIT 1";   
     ResultSet rs=st.executeQuery(query);
    while(rs.next()){
    tempValue=rs.getString(Month);
    //System.out.println(tempValue);
    }
    return tempValue;    
    }
    

    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseDumper;

import Model.Connector;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author paradise lost
 */
public class AnoiTableUpdator {
    private String databaseTable;
    private String month;
    private Connector connector;
    private Statement st;  
    private String RegionId;
   
   
    public AnoiTableUpdator(String year,String month,String RegionId) throws SQLException{
    this.databaseTable="anoi_"+year;  
    this.month=month;
    this.connector=new Connector();
    this.st=(Statement)connector.returnStatement();
    this.RegionId=RegionId;
    System.out.println("database table name : "+this.databaseTable);
    }
    
    public void InjectValues(Map anoiData) throws SQLException{
        
        for (Object key:anoiData.keySet())
        {
         String query="UPDATE `stdata`.`"+this.databaseTable+"` SET `"+month.toUpperCase()+"` = '"+anoiData.get(key)+"' WHERE `"+this.databaseTable+"`.`id`"
                 + " ="+key.toString()+";";   
         st.executeUpdate(query);
        }
        }  
}
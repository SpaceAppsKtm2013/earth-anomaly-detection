/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package STModel;


import Model.Connector;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author Smrita
 */
public class AnoiDataRetrival 
{
    Connector connector;
    Statement st;
    String anoiDbTable;    
    double anoiValue;
    double[] threshold=new double[12];
     
    
    
    public  AnoiDataRetrival(String anoiDbTableName) throws SQLException
      {
             Connector connector=new Connector();
             st=connector.returnStatement();
             anoiDbTable=anoiDbTableName;
             System.out.println("connected");
             
           
       }

    
    public double getAnoiData(int sn,String month) throws SQLException
    {        
             String query="SELECT * FROM `"+anoiDbTable+"` WHERE SN="+sn;  
             ResultSet rs=st.executeQuery(query);
             while(rs.next())
             {
                 anoiValue=rs.getDouble(month);
             }
            return anoiValue;     
   
    }
    

    
    public int count_rows() throws SQLException
    {
            int row_count = 0;
            String query="SELECT count(*) FROM "+anoiDbTable;
            ResultSet rs= st.executeQuery(query);
            while(rs.next())
                row_count=rs.getInt(1);
            
            
            return row_count;
    
    }
    
    public int count_col()throws SQLException
    {  
        int col_count=0;
        String query="SELECT count(*) FROM"+anoiDbTable;
        ResultSet rs=st.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        col_count=rsmd.getColumnCount();

        return col_count;
    
    }
  public int getSn(String id) throws SQLException
    {
        int sn =0;
        String query="SELECT * FROM `"+anoiDbTable+"` WHERE id="+id; 
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
            sn=rs.getInt("SerialId");
        }
        
        return sn;
    }
    
    
    
    public static void main(String[] args) throws SQLException
    {
           
    }
             AnoiDataRetrival ad=new AnoiDataRetrival("anoi_");
//             ad.getAnoiData(7, "JAN");
//             ad.count_rows();
//             ad.count_col();
//    }
    }
    


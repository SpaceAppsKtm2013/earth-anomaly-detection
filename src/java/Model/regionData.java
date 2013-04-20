package Model;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class regionData {

    Connector connector;
    Statement statement;
    ResultSet resultset;

    public regionData() throws SQLException {
        connector = new Connector();
        statement = (Statement) connector.returnStatement();        
    }

    public ArrayList<Float> getRegionData(String Id) throws SQLException {
        String query = "SELECT * FROM `regions` WHERE id=" + Id;
        resultset = statement.executeQuery(query);
        ArrayList<Float> row = new ArrayList<Float>();
        while (resultset.next()) {
            
             row.add(resultset.getFloat("leftbottomLat"));
             row.add(resultset.getFloat("leftbottomLong"));
             row.add(resultset.getFloat("righttopLat"));
             row.add(resultset.getFloat("righttopLong"));
            
                }

        return row;
    }
}

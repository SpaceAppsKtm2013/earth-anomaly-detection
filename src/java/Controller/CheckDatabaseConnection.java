/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CoordinateData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author paradise lost
 */
public class CheckDatabaseConnection {
    
   public static void main(String [] args) throws SQLException
    {
Map m1=new HashMap();
m1.put("a",2);
m1.put("b",1);
m1.put("c",3);
m1.put("d",5);
m1.put("e",1);

Map m2=new HashMap();
m2.put("b",1);
m2.put("c",3);
m2.put("d",5);


for(Object key:m1.keySet())
{ float value;
        if(m2.get(key)==null)
            value=0;
        else 
            value=new Integer(m2.get(key).toString());
m1.put(key,new Float(m1.get(key).toString())+value);
}

for(Object key:m1.keySet())
{
System.out.println(key.toString()+":"+m1.get(key));
}

    }
}

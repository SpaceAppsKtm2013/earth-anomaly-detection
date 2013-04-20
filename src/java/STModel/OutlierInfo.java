/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package STModel;

import Analysis.*;


public class OutlierInfo 
{
    public String month;
    public int year;
    public String id;
    public int serialId;
    
//    public void setValue(String monthh,int yearr,String idd)
//    {
//        month=monthh;
//        year=yearr;
//        id=idd;
//    }
    public void setSerialId(int serialid)
    {
        serialId=serialid;
    }
    public void setMonth(String monthh)
    {
        month=monthh;
    }
    public void setYear(int yearr)
    {
        year=yearr;
    }
    public void setId(String idd)
    {  
        id=idd;
    }
    
    
}

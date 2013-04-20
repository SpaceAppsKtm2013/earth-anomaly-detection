/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package STModel;

import Analysis.*;
import AnoiModel.AnoiData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;



    public class Neighbours
    {
    
        public double[] nbr1TimeStampBack=new double[9];
        public double[] nbrInThisTimeStamp=new double[9];
        public double[] nbr1TimeStampAhead=new double[9];
        public double[] nbr2TimeStampAhead=new double[9];

        spatialPopulation spopn=new spatialPopulation();
        ArrayList<String> result=spopn.populateLocation();
        
        int year,control;
        int edgeflag=16;
        int year1,year2;
        RegionDataRetrieval rdr;
        int breadth,length;
        String regionId;
        
        
        
        String anoiDbTable;
        String[] months={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DECM"};
        
        AnoiData anoi,anoi1,anoi2;
        
        DecimalFormat df=new DecimalFormat("#.####");
        
        
        public Neighbours(String regionid)throws SQLException
        {
          regionId=regionid;  
          //System.out.println("dont forget to pass regionid here"); 
          rdr=new RegionDataRetrieval("regions");
          breadth=rdr.getBreadth(regionid);
          length=rdr.getLength(regionid);
          edgeflag=length;
        }
        public void initializeNeighbours(int yearr) throws SQLException
        {        System.out.println("year\t"+yearr);
                 year1=yearr-1;
                 year2=yearr+1;
                 anoi=new AnoiData("anoi_"+yearr);
                 anoi1=new AnoiData("anoi_"+year1);
                 anoi2=new AnoiData("anoi_"+year2);
        }
        public void setNeighbours(int sn,String month) throws SQLException
        {
                 
                 String anoidbtable1,anoidbtable2;
                 int monthindx=0;
                                                  
                 for(int i=0;i<12;i++)
                 {
                      if(months[i]==month)
                          monthindx=i;
                 }
                 
                 System.out.println("sn\t"+sn);
                 
                 for(int index=0;index<9;index++)
                 {  
                     if(index==0)
                         control=sn-edgeflag-1;//sn-edgeflagbreath-1;
                     if(index==3)
                         control=sn-1;
                     if(index==6)
                         control=sn+edgeflag-1;//sn-edgeflagbreath-1;
                     
                    System.out.println("control "+control);
                     if(month=="JAN")
                     {   //System.out.println("jan anoi1 in effect");
                         nbr1TimeStampBack[index]= anoi1.getAnoiData(control, "DECM",regionId);
                     }
                             
                     else
                     {   
                         nbr1TimeStampBack[index]= anoi.getAnoiData(control, months[monthindx-1],regionId);
                     }
                          
                     nbrInThisTimeStamp[index]=anoi.getAnoiData(control, months[monthindx],regionId);
                     
                     if(month=="NOV"||month=="DECM")
                        {  
                            if(month=="NOV")
                               { 
                                
                                 nbr2TimeStampAhead[index]=anoi2.getAnoiData(control,"JAN",regionId);
                                 nbr1TimeStampAhead[index]=anoi.getAnoiData(control, months[monthindx+1],regionId);
                               }
                               if(month=="DECM")
                               { 
                                 nbr2TimeStampAhead[index]=anoi2.getAnoiData(control,"FEB",regionId);
                                 nbr1TimeStampAhead[index]=anoi2.getAnoiData(control,"JAN",regionId);
                               }
                        }
                        else
                        {
                            nbr2TimeStampAhead[index]=anoi.getAnoiData(control, months[monthindx+2],regionId);
                            nbr1TimeStampAhead[index]=anoi.getAnoiData(control, months[monthindx+1],regionId);
                        }
                    control++;
             }
                 
               System.out.println("month\t"+months[monthindx]);  
               System.out.println("candidate spatial outlier\t"+nbrInThisTimeStamp[4]);
               for (int index=0;index<9;index++)
               {    
                   System.out.println("\t"+df.format(nbr1TimeStampBack[index])+"\t"+df.format(nbrInThisTimeStamp[index])+"\t"+df.format(nbr1TimeStampAhead[index])+"\t"+df.format(nbr2TimeStampAhead[index]));
               
               }
        }
    }

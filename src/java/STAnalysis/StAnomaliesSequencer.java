/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package STAnalysis;

import STModel.OutlierInfo;
import java.util.ArrayList;

/**
 /***
 * @author Smrita
 */
public class StAnomaliesSequencer {

    ArrayList<OutlierInfo> stAnomalies = new ArrayList();//arraylist of spatio-temporal outliers
    ArrayList<ArrayList<OutlierInfo>> sequencedStAnomalies = new ArrayList<ArrayList<OutlierInfo>>();
    int firstElement;

    public StAnomaliesSequencer() {
        firstElement = 0;
    }

    public  void sequenceStAnomalies(ArrayList<OutlierInfo> sTanomalies) {

        int loopSizeDeterminer;//determines teh size of the inner loop and is dynamic.

        for (int index = 0; index < sTanomalies.size(); index++) {
            OutlierInfo stOutlier = new OutlierInfo();
            stOutlier = sTanomalies.get(index);
            stAnomalies.add(index, stOutlier);
        }

        loopSizeDeterminer = stAnomalies.size();

        while (stAnomalies.size() != 0) 
        {
            OutlierInfo stOutlier1 = new OutlierInfo();
            ArrayList<OutlierInfo> tracker = new ArrayList();//arraylist that stores the grouped Outliers to be deleted later
            stOutlier1 = stAnomalies.get(firstElement);
            ArrayList<OutlierInfo> similarOutlierList = new ArrayList();//similaroutliers are kept in an arraylist
            similarOutlierList.add(stOutlier1);
            tracker.add(stOutlier1);

            for (int index = 1; index < loopSizeDeterminer; index++)
            {
                OutlierInfo similarStOutlier = new OutlierInfo();
                similarStOutlier = stAnomalies.get(index);

                //if(stOutlier1.serialId==stOutlier2.serialId)
                if (stOutlier1.id.equals(similarStOutlier.id)) 
                {
                    similarOutlierList.add(similarStOutlier);
                    tracker.add(similarStOutlier);
                }
            }

            sequencedStAnomalies.add(similarOutlierList);//adding arraylist of similar outliers to the arraylist of arraylist

            for (int index = 0; index < tracker.size(); index++)
            {
                OutlierInfo groupedOutlier = new OutlierInfo();
                groupedOutlier = tracker.get(index);
                stAnomalies.remove(groupedOutlier);//grouped outliers are removed from original arraylist containing spatio-temporal outliers to reduce redundancy
            }
          
            loopSizeDeterminer = stAnomalies.size();
        }
        
    }
    
    
    public ArrayList<ArrayList<String>> getSequencedAnomalies()
    {
        
          ArrayList<ArrayList<String>> bigList=new ArrayList<ArrayList<String>>();
          
        
         for(int i=0;i<sequencedStAnomalies.size();i++)
       {   
            ArrayList<OutlierInfo> sAnomalies=new ArrayList();
            ArrayList<String> id = new ArrayList<String>();
             
            
            sAnomalies=sequencedStAnomalies.get(i);
            OutlierInfo outlier=new OutlierInfo();
            outlier=sAnomalies.get(i);
                     id.add(outlier.id.toString());
                     System.out.println(outlier.id); 
                     bigList.add(id);
                    
            for(int j=0;j<sAnomalies.size();j++)
            {   
               OutlierInfo outlier1=new OutlierInfo();
               outlier1=sAnomalies.get(j);
               String yEar=outlier1.year+"";
              //System.out.println(outlier1.id+"\t"+outlier1.month+"\t"+outlier1.year);
               
            ArrayList<String> time_stamp=new ArrayList<String>();
               time_stamp.add(outlier1.month.toString());
               time_stamp.add(yEar);
               bigList.add(time_stamp);
               
            }
    }
         return bigList;
}
    
    
}
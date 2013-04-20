/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Analysis.DatabaseOutliers;
import AnoiModel.RegionIdData;
import Model.CoordinateData;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author paradise lost
 */
@WebServlet(name = "YearlySpatialOutliers", urlPatterns = {"/YearlySpatialOutliers"})
public class YearlySpatialOutliers extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet YearlySpatialOutliers</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet YearlySpatialOutliers at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String year,month;
                year=request.getParameter("year");
                month=request.getParameter("month");
                String region=request.getParameter("region");
                System.out.print(year+month+":"+region);
           String months="FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DECM";
            
     ArrayList<ArrayList<String>> results= new ArrayList<ArrayList<String>>();
     RegionIdData rid=new RegionIdData();     
    ArrayList<String> smallList=rid.getCentralCoordinate(region);
    results.add(smallList);
    
    DatabaseOutliers o1=new DatabaseOutliers(year,"JAN",region);
    Map anois_sum=o1.getAnois();         
            
    String[] monthList=months.split(" ");
    for(int i=0;i<monthList.length;i++)
    {       
            o1=new DatabaseOutliers(year,monthList[i],region);        
            Map anois_initial=o1.getAnois();
            for(Object key:anois_sum.keySet())
{ float value;
        if(anois_initial.get(key)==null)
           value=0;
        else 
        {
           value=new Float(anois_initial.get(key).toString());
           System.out.println("found now  : "+value);
           System.out.println("found before  : "+anois_sum.get(key));
           
        }
anois_sum.put(key,new Float(anois_sum.get(key).toString())/5+value);
System.out.println("summed : "+anois_sum.get(key));

}
    }
    
    
             CoordinateData c1=new CoordinateData();
             for (Object key: anois_sum.keySet()) {
            ArrayList<String> list=new ArrayList<String>();
             list=c1.getLongLat(new Long(key.toString()));
             list.add(new Float(anois_sum.get(key).toString())+"");
             list.add(key.toString());
             results.add(list);
              }
             
                request.setAttribute("result",results);   
                RequestDispatcher dispatcher=request.getRequestDispatcher("OutlierVisualisation.jsp");
                dispatcher.forward(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(MonthlySpatialOutliers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

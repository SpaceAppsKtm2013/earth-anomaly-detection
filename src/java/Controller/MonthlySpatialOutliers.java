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
 * @author oscine
 */
@WebServlet(name = "MonthlySpatialOutliers", urlPatterns = {"/MonthlySpatialOutliers"})
public class MonthlySpatialOutliers extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        ArrayList<String> smallList=new ArrayList<String>();
smallList.add("41.878113");//choose according to regions
smallList.add("87.629798");
smallList.add("8");
        results.add(smallList);
        //debuggers code check
        System.out.println(results+"result list added ");
        request.setAttribute("result",results);   
        RequestDispatcher dispatcher=request.getRequestDispatcher("OutlierVisualisation.jsp");
        dispatcher.forward(request,response);
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
   
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String year,month;
                year=request.getParameter("year");
                month=request.getParameter("month");
                String region=request.getParameter("region");
                System.out.print(year+month+":"+region);
           
            
     ArrayList<ArrayList<String>> results= new ArrayList<ArrayList<String>>();
     RegionIdData rid=new RegionIdData();     
    ArrayList<String> smallList=rid.getCentralCoordinate(region);
    results.add(smallList);

             DatabaseOutliers o1=new DatabaseOutliers(year,month,region);
            Map anois=o1.getAnois();
            CoordinateData c1=new CoordinateData();
             for (Object key: anois.keySet()) {
            ArrayList<String> list=new ArrayList<String>();
             list=c1.getLongLat(new Long(key.toString()));
             list.add(anois.get(key).toString());
             list.add(key.toString());
             results.add(list);
             System.out.println(list);
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

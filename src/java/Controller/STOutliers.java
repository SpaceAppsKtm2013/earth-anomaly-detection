/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Analysis.DatabaseOutliers;
import AnoiModel.RegionIdData;
import Model.CoordinateData;
import STAnalysis.SpTempOutliers;
import STModel.OutlierInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@WebServlet(name = "STOutliers", urlPatterns = {"/STOutliers"})
public class STOutliers extends HttpServlet {

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
// rabindra kharel
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
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String month1,month2;
            int year1,year2;
                year1=Integer.parseInt(request.getParameter("year1"));
                month1=request.getParameter("month1");
                year2=Integer.parseInt(request.getParameter("year2"));
                month2=request.getParameter("month2");
                String region=request.getParameter("region");                
        SpatioTempOutliers st2=new SpatioTempOutliers(year1,year2,month1,month2,region);
        HashSet hs=st2.getAllspTempOutliers();
        ArrayList<String> lists = new ArrayList<String>(hs);
        
        ArrayList<ArrayList<String>> results= new ArrayList<ArrayList<String>>();
         CoordinateData c1=new CoordinateData();
             for (int i=0;i<lists.size();i++) {
           ArrayList<String> list=new ArrayList<String>();
             list=c1.getLongLat(new Long(lists.get(i)));
             list.add("450000");
             results.add(list);
             System.out.println(list);
              }
             
                request.setAttribute("result",results);   
                RequestDispatcher dispatcher=request.getRequestDispatcher("OutlierVisualisation.jsp");
                dispatcher.forward(request,response);
        }
        catch (SQLException ex) {
            Logger.getLogger(STOutliers.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Analysis.SpatialOutlierGenerator;
import Model.CoordinateData;
import java.io.IOException;
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
@WebServlet(name = "SpatialOutlier", urlPatterns = {"/SpatialOutlier"})
public class SpatialCoordinate extends HttpServlet {


    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       SpatialOutlierGenerator g1= new SpatialOutlierGenerator("2000","JAN","nepal");
        Map anois=g1.getAnois();
        ArrayList<ArrayList<String>> results= new ArrayList<ArrayList<String>>();
        CoordinateData c1=new CoordinateData();
         for (Object key: anois.keySet()) {
        ArrayList<String> list=new ArrayList<String>();
         list=c1.getLongLat(new Long(key.toString()));
         list.add(anois.get(key).toString());
         list.add(key.toString());
         results.add(list);
          }
         
         for(ArrayList<String> str:results)
         {
         System.out.println(str);
         }
         
        request.setAttribute("result",results);   
        RequestDispatcher dispatcher=request.getRequestDispatcher("SpatialOutlierVisualisation.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SpatialCoordinate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
            year=request.getParameter("Year");
            month=request.getParameter("month");
            System.out.println(year+" :  "+month);
            SpatialOutlierGenerator g1= new SpatialOutlierGenerator(year,month.toUpperCase(),"1");
            Map anois=g1.getAnois();
            ArrayList<ArrayList<String>> results= new ArrayList<ArrayList<String>>();
            CoordinateData c1=new CoordinateData();
             for (Object key: anois.keySet()) {
            ArrayList<String> list=new ArrayList<String>();
             list=c1.getLongLat(new Long(key.toString()));
             list.add(anois.get(key).toString());
             list.add(key.toString());
             results.add(list);
              }
             
             for(ArrayList<String> str:results)
             {
             System.out.println(str);
             }
             
            request.setAttribute("result",results);   
            RequestDispatcher dispatcher=request.getRequestDispatcher("SpatialOutlierVisualisation.jsp");
            dispatcher.forward(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(SpatialCoordinate.class.getName()).log(Level.SEVERE, null, ex);
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

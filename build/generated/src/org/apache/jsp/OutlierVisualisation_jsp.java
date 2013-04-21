package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;

public final class OutlierVisualisation_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\">\n");
      out.write("<meta charset=\"utf-8\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\"\n");
      out.write("  xmlns:h=\"http://java.sun.com/jsf/html\">\n");
      out.write("<head>\n");
      out.write("    <title>Anomaly detection </title>\n");
      out.write("<link href=\"default.css\" rel=\"stylesheet\">\n");
      out.write("<script src=\"https://maps.googleapis.com/maps/api/js?sensor=false\"></script>\n");
 
ArrayList<ArrayList<String>> finalResult=(ArrayList<ArrayList<String>>)request.getAttribute("result");
 System.out.println(finalResult);
String centralX=finalResult.get(0).get(0);
String centralY=finalResult.get(0).get(1);

      out.write("\n");
      out.write("<script type=\"text/javascript\" > // Create an object containing LatLng, population.\n");
      out.write("var citymap = {};\n");
      out.write("var value1=\n");
 int iter=0; 
for(ArrayList<String> inneResult:finalResult)
{iter++; 
      out.write(" \n");
      out.write("citymap['chica");
      out.print(iter);
      out.write("'] = {\n");
      out.write("    center: new google.maps.LatLng(");
      out.print(inneResult.get(0) );
      out.write(',');
      out.print(inneResult.get(1) );
      out.write("),\n");
      out.write("    population: ");
      out.print(inneResult.get(2));
      out.write(",\n");
      out.write("    id: 121\n");
      out.write("};\n");
}
      out.write("\n");
      out.write("var cityCircle;\n");
      out.write("\n");
      out.write("function initialize() {\n");
      out.write("var mapOptions = {\n");
      out.write("  zoom: 5,\n");
      out.write("  center: new google.maps.LatLng(");
      out.print(centralX);
      out.write(',');
      out.print(centralY);
      out.write("),\n");
      out.write("  mapTypeId: google.maps.MapTypeId.TERRAIN\n");
      out.write("};\n");
      out.write("\n");
      out.write("var map = new google.maps.Map(document.getElementById(\"map_canvas\"),\n");
      out.write("    mapOptions);\n");
      out.write("\n");
      out.write("for (var city in citymap) {\n");
      out.write("  // Construct the circle for each value in citymap. We scale population by 20.\n");
      out.write("  var populationOptions = {\n");
      out.write("    strokeColor: \"#FF0000\",\n");
      out.write("    strokeOpacity: 0.3,\n");
      out.write("    strokeWeight: 1,\n");
      out.write("    fillColor: \"#FF0000\",\n");
      out.write("    fillOpacity: 0.3,\n");
      out.write("    map: map,\n");
      out.write("    center: citymap[city].center,\n");
      out.write("    radius: citymap[city].population/40\n");
      out.write("  };\n");
      out.write("  cityCircle = new google.maps.Circle(populationOptions);\n");
      out.write("   google.maps.event.addListener(cityCircle, 'click',function open_chart_popup() {\n");
      out.write("        window.open('highchart.html', 'chart popup title', 'width=600px height=400px');\n");
      out.write("    });\n");
      out.write("}\n");
      out.write("} </script></head>\n");
      out.write("<body onload=\"initialize()\">\n");
      out.write("    <div class=\"formLayout\">\n");
      out.write("\t<form action=\"MonthlySpatialOutliers\" method=\"POST\">\n");
      out.write("\t<fieldset class=\"fset\">\n");
      out.write("\t<div><label>Monthly Anomaly</label><br/>\n");
      out.write("\t   <label>Regions</label>\n");
      out.write("        <select name=\"region\">\n");
      out.write("            <option value=\"4\">South Asia</option>\n");
      out.write("            <option value=\"2\">Africa</option>\n");
      out.write("            <option value=\"1\">Europe</option>\n");
      out.write("            <option value=\"7\">North America</option>\n");
      out.write("            <option value=\"6\">South America</option>\n");
      out.write("            <option value=\"5\">Australia</option>\n");
      out.write("            <option value=\"8\">Nepal</option>\n");
      out.write("\t\t\n");
      out.write("        </select>\n");
      out.write("\t<div>\n");
      out.write("        <label>\tYear</label>\n");
      out.write("\t    <select name=\"year\" >\n");
      out.write("          <option value=\"2000\">2000</option>\n");
      out.write("          <option value=\"2001\">2001</option>\n");
      out.write("          <option value=\"2002\">2002</option>\n");
      out.write("          <option value=\"2003\">2003</option>\n");
      out.write("          <option value=\"2004\">2004</option>\n");
      out.write("        </select>\n");
      out.write("\t</div>\n");
      out.write("\t\t\t<div>\n");
      out.write("        <label>Month</label>\n");
      out.write("\t\t\t<select name=\"month\">\n");
      out.write("              <option value=\"jan\">January</option>\n");
      out.write("              <option value=\"feb\">February</option>\n");
      out.write("              <option value=\"mar\">March</option>\n");
      out.write("              <option value=\"apr\">April</option>\n");
      out.write("              <option value=\"may\">May</option>\n");
      out.write("              <option value=\"jun\">June</option>\n");
      out.write("              <option value=\"jul\">July</option>\n");
      out.write("              <option value=\"aug\">August</option>\n");
      out.write("              <option value=\"sep\">September</option>\n");
      out.write("              <option value=\"oct\">October</option>\n");
      out.write("              <option value=\"nov\">November</option>\n");
      out.write("              <option value=\"decm\">December</option>\n");
      out.write("            </select>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div id=\"button\">\n");
      out.write("\t\t\t  <input type=\"submit\" value=\"Load\"/>\n");
      out.write("\t\t\t</div>\n");
      out.write("</fieldset>\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("<form action=\"YearlySpatialOutliers\" method=\"POST\">\n");
      out.write("<fieldset class=\"fset\">\t\n");
      out.write("\t<label>Yearly Anomaly</label>\n");
      out.write("\t   <br>\n");
      out.write("\t\t<div>\n");
      out.write("            <label>Regions</label>\n");
      out.write("            <select name=\"region\">\n");
      out.write("            <option value=\"4\">South Asia</option>\n");
      out.write("            <option value=\"2\">Africa</option>\n");
      out.write("            <option value=\"1\">Europe</option>\n");
      out.write("            <option value=\"7\">North America</option>\n");
      out.write("            <option value=\"6\">South America</option>\n");
      out.write("            <option value=\"5\">Australia</option>\n");
      out.write("            <option value=\"8\">Nepal</option>\n");
      out.write("        </select>\n");
      out.write("        <label>Year</label>\n");
      out.write("        <select name=\"year\" >\n");
      out.write("                <option value=\"2000\">2000</option>\n");
      out.write("                <option value=\"2001\">2001</option>\n");
      out.write("                <option value=\"2002\">2002</option>\n");
      out.write("                <option value=\"2003\">2003</option>\n");
      out.write("                <option value=\"2004\">2004</option>\n");
      out.write("          </select>\n");
      out.write("\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t<div id=\"button\">\n");
      out.write("\t  <input type=\"submit\" value=\"Load\"/>\n");
      out.write("\t</div>\n");
      out.write("\t</fieldset>\n");
      out.write("</form>\n");
      out.write("<form action=\"STOutliers\" method=\"POST\">\n");
      out.write("<fieldset class=\"fset\">\n");
      out.write("\t<label>S-T Anomaly:From</label>\n");
      out.write("\t\n");
      out.write("\t   <br/>\n");
      out.write("\t   \n");
      out.write("\t\t<div>\n");
      out.write("                       <label>Regions</label>\n");
      out.write("        <select name=\"region\">\n");
      out.write("            <option value=\"4\">South Asia</option>\n");
      out.write("            <option value=\"2\">Africa</option>\n");
      out.write("            <option value=\"1\">Europe</option>\n");
      out.write("            <option value=\"7\">North America</option>\n");
      out.write("            <option value=\"6\">South America</option>\n");
      out.write("            <option value=\"5\">Australia</option>\n");
      out.write("            <option value=\"8\">Nepal</option>\n");
      out.write("\t\t\n");
      out.write("        </select>\n");
      out.write("\n");
      out.write("                    \n");
      out.write("        <label>Year</label>\n");
      out.write("        <select name=\"year1\" >\n");
      out.write("                <option value=\"2000\">2000</option>\n");
      out.write("                <option value=\"2001\">2001</option>\n");
      out.write("                <option value=\"2002\">2002</option>\n");
      out.write("                <option value=\"2003\">2003</option>\n");
      out.write("                <option value=\"2004\">2004</option>\n");
      out.write("          </select>\n");
      out.write("\t\n");
      out.write("\t\t<label>Month</label>\n");
      out.write("       <select name=\"month1\">\n");
      out.write("            <option value=\"jan\">January</option>\n");
      out.write("            <option value=\"feb\">February</option>\n");
      out.write("            <option value=\"mar\">March</option>\n");
      out.write("            <option value=\"apr\">April</option>\n");
      out.write("            <option value=\"may\">May</option>\n");
      out.write("            <option value=\"jun\">June</option>\n");
      out.write("            <option value=\"jul\">July</option>\n");
      out.write("            <option value=\"aug\">August</option>\n");
      out.write("            <option value=\"sep\">September</option>\n");
      out.write("            <option value=\"oct\">October</option>\n");
      out.write("            <option value=\"nov\">November</option>\n");
      out.write("            <option value=\"decm\">December</option>\n");
      out.write("        </select></div>\n");
      out.write("\t\t<label>To</label><br/>\n");
      out.write("\t\t<div>\n");
      out.write("        <label>Year</label>\n");
      out.write("        <select name=\"year2\" >\n");
      out.write("                <option value=\"2000\">2000</option>\n");
      out.write("                <option value=\"2001\">2001</option>\n");
      out.write("                <option value=\"2002\">2002</option>\n");
      out.write("                <option value=\"2003\">2003</option>\n");
      out.write("                <option value=\"2004\">2004</option>\n");
      out.write("          </select>\n");
      out.write("\t\n");
      out.write("\t\t<label>Month</label>\n");
      out.write("       <select name=\"month2\">\n");
      out.write("            <option value=\"jan\">January</option>\n");
      out.write("            <option value=\"feb\">February</option>\n");
      out.write("            <option value=\"mar\">March</option>\n");
      out.write("            <option value=\"apr\">April</option>\n");
      out.write("            <option value=\"may\">May</option>\n");
      out.write("            <option value=\"jun\">June</option>\n");
      out.write("            <option value=\"jul\">July</option>\n");
      out.write("            <option value=\"aug\">August</option>\n");
      out.write("            <option value=\"sep\">September</option>\n");
      out.write("            <option value=\"oct\">October</option>\n");
      out.write("            <option value=\"nov\">November</option>\n");
      out.write("            <option value=\"decm\">December</option>\n");
      out.write("        </select></div>\n");
      out.write("\t<div id=\"button\">\n");
      out.write("\t  <input type=\"submit\" value=\"Load\"/>\n");
      out.write("\t</div>\n");
      out.write("\t</fieldset>\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("<div id=\"map_canvas\"></div>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

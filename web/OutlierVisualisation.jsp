<%-- 
Document   : SpatialOurlierVisualisation
Created on : Jul 22, 2012, 3:05:44 PM
Author     : paradise lost
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">
<html xmlns="http://www.w3.org/1999/xhtml"
  xmlns:h="http://java.sun.com/jsf/html">
<head>
    <title>Anomaly detection </title>
<link href="default.css" rel="stylesheet">
<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<% 
ArrayList<ArrayList<String>> finalResult=(ArrayList<ArrayList<String>>)request.getAttribute("result");
 System.out.println(finalResult);
String centralX=finalResult.get(0).get(0);
String centralY=finalResult.get(0).get(1);
%>
<script type="text/javascript" > // Create an object containing LatLng, population.
var citymap = {};
var value1=
<% int iter=0; 
for(ArrayList<String> inneResult:finalResult)
{iter++; %> 
citymap['chica<%=iter%>'] = {
    center: new google.maps.LatLng(<%=inneResult.get(0) %>,<%=inneResult.get(1) %>),
    population: <%=inneResult.get(2)%>,
    id: '121'
};
<%}%>
var cityCircle={};

function initialize() {
var mapOptions = {
  zoom: 5,
  center: new google.maps.LatLng(<%=centralX%>,<%=centralY%>),
  mapTypeId: google.maps.MapTypeId.TERRAIN
};

var map = new google.maps.Map(document.getElementById("map_canvas"),
    mapOptions);
var i=0;
for (var city in citymap) {
  // Construct the circle for each value in citymap. We scale population by 20.
  var populationOptions = {
    strokeColor: "#FF0000",
    strokeOpacity: 0.3,
    strokeWeight: 1,
    fillColor: "#FF0000",
    fillOpacity: 0.3,
    map: map,
    center: citymap[city].center,
    id : 123,
    radius: citymap[city].population/40
  };
   cityCircle = new google.maps.Circle(populationOptions);
   var radius = cityCircle.getRadius();
   var latt = cityCircle.getCenter().lat();
   var longg = cityCircle.getCenter().lng();
   google.maps.event.addListener(cityCircle, 'click',function open_chart_popup() {
   window.open('highchart.html?id='+latt+'b'+longg,'chart popup title', 'width=600px height=400px');   
    });
}
} </script></head>
<body onload="initialize()">
    <div class="formLayout">
	<form action="MonthlySpatialOutliers" method="POST">
	<fieldset class="fset">
	<div><label>Monthly Anomaly</label><br/>
	   <label>Regions</label>
        <select name="region">
            <option value="4">South Asia</option>
            <option value="2">Africa</option>
            <option value="1">Europe</option>
            <option value="7">North America</option>
            <option value="6">South America</option>
            <option value="5">Australia</option>
            <option value="8">Nepal</option>
		
        </select>
	<div>
        <label>	Year</label>
	    <select name="year" >
          <option value="2000">2000</option>
          <option value="2001">2001</option>
          <option value="2002">2002</option>
          <option value="2003">2003</option>
          <option value="2004">2004</option>
        </select>
	</div>
			<div>
        <label>Month</label>
			<select name="month">
              <option value="jan">January</option>
              <option value="feb">February</option>
              <option value="mar">March</option>
              <option value="apr">April</option>
              <option value="may">May</option>
              <option value="jun">June</option>
              <option value="jul">July</option>
              <option value="aug">August</option>
              <option value="sep">September</option>
              <option value="oct">October</option>
              <option value="nov">November</option>
              <option value="decm">December</option>
            </select>
			</div>
			<div id="button">
			  <input type="submit" value="Load"/>
			</div>
</fieldset>
</form>

<form action="YearlySpatialOutliers" method="POST">
<fieldset class="fset">	
	<label>Yearly Anomaly</label>
	   <br>
		<div>
            <label>Regions</label>
            <select name="region">
            <option value="4">South Asia</option>
            <option value="2">Africa</option>
            <option value="1">Europe</option>
            <option value="7">North America</option>
            <option value="6">South America</option>
            <option value="5">Australia</option>
            <option value="8">Nepal</option>
        </select>
        <label>Year</label>
        <select name="year" >
                <option value="2000">2000</option>
                <option value="2001">2001</option>
                <option value="2002">2002</option>
                <option value="2003">2003</option>
                <option value="2004">2004</option>
          </select>
	</div>
			
	<div id="button">
	  <input type="submit" value="Load"/>
	</div>
	</fieldset>
</form>
<form action="STOutliers" method="POST">
<fieldset class="fset">
	<label>S-T Anomaly:From</label>
	
	   <br/>
	   
		<div>
                       <label>Regions</label>
        <select name="region">
            <option value="4">South Asia</option>
            <option value="2">Africa</option>
            <option value="1">Europe</option>
            <option value="7">North America</option>
            <option value="6">South America</option>
            <option value="5">Australia</option>
            <option value="8">Nepal</option>
		
        </select>

                    
        <label>Year</label>
        <select name="year1" >
                <option value="2000">2000</option>
                <option value="2001">2001</option>
                <option value="2002">2002</option>
                <option value="2003">2003</option>
                <option value="2004">2004</option>
          </select>
	
		<label>Month</label>
       <select name="month1">
            <option value="jan">January</option>
            <option value="feb">February</option>
            <option value="mar">March</option>
            <option value="apr">April</option>
            <option value="may">May</option>
            <option value="jun">June</option>
            <option value="jul">July</option>
            <option value="aug">August</option>
            <option value="sep">September</option>
            <option value="oct">October</option>
            <option value="nov">November</option>
            <option value="decm">December</option>
        </select></div>
		<label>To</label><br/>
		<div>
        <label>Year</label>
        <select name="year2" >
                <option value="2000">2000</option>
                <option value="2001">2001</option>
                <option value="2002">2002</option>
                <option value="2003">2003</option>
                <option value="2004">2004</option>
          </select>
	
		<label>Month</label>
       <select name="month2">
            <option value="jan">January</option>
            <option value="feb">February</option>
            <option value="mar">March</option>
            <option value="apr">April</option>
            <option value="may">May</option>
            <option value="jun">June</option>
            <option value="jul">July</option>
            <option value="aug">August</option>
            <option value="sep">September</option>
            <option value="oct">October</option>
            <option value="nov">November</option>
            <option value="decm">December</option>
        </select></div>
	<div id="button">
	  <input type="submit" value="Load"/>
	</div>
	</fieldset>
</form>

</div>
<div id="map_canvas"></div>
</body>
</html>
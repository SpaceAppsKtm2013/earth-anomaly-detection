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
%>
<script type="text/javascript" > // Create an object containing LatLng, population.
var citymap = {};
<% int iter=0; 
for(ArrayList<String> inneResult:finalResult)
{iter++; %> 
citymap['chica<%=iter%>'] = {
    center: new google.maps.LatLng(<%=inneResult.get(0) %>,<%=inneResult.get(1) %>),
    population: <%=inneResult.get(2) %>
};
<%}%>
var cityCircle;

function initialize() {
var mapOptions = {
  zoom: 5,
  center: new google.maps.LatLng(43.25, 105.75),
  mapTypeId: google.maps.MapTypeId.TERRAIN
};

var map = new google.maps.Map(document.getElementById("map_canvas"),
    mapOptions);

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
    radius: citymap[city].population/40
  };
  cityCircle = new google.maps.Circle(populationOptions);
}
} </script></head>
<body onload="initialize()">
    <div id="visualize"><h1>Visualization</h1>
    <div id="selectForm" > Month Stamp Visualisation
    <form action="SpatialOutlier" method="post">            
     <select name="Year" >
                <option value="2000">2000</option>
                <option value="2001">2001</option>
                <option value="2002">2002</option>
                <option value="2003">2003</option>
                <option value="2004">2004</option>
            </select>
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
            <select name="region">
            <option value="1">South Asia</option>
            <option value="2">East Asia</option>
            <option value="4">china</option>
            <option value="5">Africa</option>
            <option value="6">Europe</option>
            <option value="7">North America</option>
            <option value="8">South America</option>
            <option value="9">Australia</option>
            <option value="10">Ohio State</option>
            <option value="11">Middle East</option>
            <option value="12">Nepal</option>
        </select>
        <input type="submit" value="Visualize" name="submit" />
        </div>    
    <div id="selectForm" > Monthly Anomaly Visualization <br/>
    <form action="SpatialOutlier" method="post">            
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
            <select name="region">
            <option value="1">South Asia</option>
            <option value="2">East Asia</option>
            <option value="4">china</option>
            <option value="5">Africa</option>
            <option value="6">Europe</option>
            <option value="7">North America</option>
            <option value="8">South America</option>
            <option value="9">Australia</option>
            <option value="10">Ohio State</option>
            <option value="11">Middle East</option>
            <option value="12">Nepal</option>
        </select>
        <input type="submit" value="Visualize" name="submit" />
        </div>
    <div id="selectForm" > Adversial locations Visualisation  <br/>
    <form action="SpatialOutlier" method="post">          
            <select name="region">
            <option value="1">South Asia</option>
            <option value="2">East Asia</option>
            <option value="4">china</option>
            <option value="5">Africa</option>
            <option value="6">Europe</option>
            <option value="7">North America</option>
            <option value="8">South America</option>
            <option value="9">Australia</option>
            <option value="10">Ohio State</option>
            <option value="11">Middle East</option>
            <option value="12">Nepal</option>
        </select>
        <input type="submit" value="Visualize" name="submit" />
        </div>
     <div id="map_canvas"  align="right" ></div>
</div>
</body>    
</html>
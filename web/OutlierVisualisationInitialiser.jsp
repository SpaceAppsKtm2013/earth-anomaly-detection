
<%@page import="java.util.ArrayList"%>
<html>
<head>
<link href="default.css" rel="stylesheet">
<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<% 
//ArrayList<ArrayList<String>> finalResult=(ArrayList<ArrayList<String>>)request.getAttribute("result");
ArrayList<ArrayList<String>> finalResult=new ArrayList<ArrayList<String>>();
ArrayList<String> smallList=new ArrayList<String>();
smallList.add("41");
smallList.add("87");
smallList.add("1");
finalResult.add(smallList);
//=(ArrayList<ArrayList<String>>)request.getAttribute("result");
%>
<script>

  // Create an object containing LatLng, population.
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
      zoom: 3,
      center: new google.maps.LatLng(41,87),
      mapTypeId: google.maps.MapTypeId.TERRAIN
    };

    var map = new google.maps.Map(document.getElementById("map_canvas"),
        mapOptions);

    for (var city in citymap) {
      // Construct the circle for each value in citymap. We scale population by 20.
      var populationOptions = {
        strokeColor: "#FF0000",
        strokeOpacity: 0.8,
        strokeWeight: 2,
        fillColor: "#FF0000",
        fillOpacity: 0.35,
        map: map,
        center: citymap[city].center,
        radius: citymap[city].population / 20
      };
      cityCircle = new google.maps.Circle(populationOptions);
    }
  }
</script>
</head>
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
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*" %>
<%@ page import="de.rentoudu.mensa.*" %>
<%@ page import="de.rentoudu.mensa.ratings.v1.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	ChartsViewController controller = new ChartsViewController(); 
	Map<String, List<Rating>> menuRatings = controller.getMenuRatings();
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Mensa Furtwangen - Charts</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="Florian Sauter">
	
	<!-- Le styles -->
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<style>
	body {
		padding-top: 30px;
		/* 60px to make the container go all the way to the bottom of the topbar */
	}
	</style>
	<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	      <script src="bootstrap/js/html5shiv.js"></script>
	    <![endif]-->
	
	<!-- Fav and touch icons -->
	<link rel="apple-touch-icon-precomposed" sizes="144x144"
		href="bootstrap/ico/apple-touch-icon-144-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114"
		href="bootstrap/ico/apple-touch-icon-114-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72"
		href="bootstrap/ico/apple-touch-icon-72-precomposed.png">
	<link rel="apple-touch-icon-precomposed"
		href="bootstrap/ico/apple-touch-icon-57-precomposed.png">
	<link rel="shortcut icon" href="favicon.ico">
</head>

<body>

	<div class="container">

		<h1><img class="brand" src="img/logo.png" /> Mensa Furtwangen - Charts</h1>
		
		<% for(String menuId : menuRatings.keySet()) { %>
		<div class="row-fluid">
			<div id="<%=menuId.hashCode()%>-chart" style="width: 900px; height: 500px;"></div>
		</div>
		<% } %>
				
		
	</div>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawCharts);
      function drawCharts() {
        
    	<% for(String menuId : menuRatings.keySet()) { %>
    	  
    	
    	<% } %>
    	var data = google.visualization.arrayToDataTable([
          ['Sterne', 'Bewertungen'],
          ['1 Stern',  1000],
          ['2 Sterne',  1170],
          ['3 Sterne',  660],
          ['4 Sterne',  1030],
          ['5 Sterne',  1060]
        ]);

        var options = {
          title: 'Bewertung Kässpätzle',
          colors: ['#99CC00', 'red'],
          legend: 'none'
        };

        
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(google.visualization.arrayToDataTable([
        	['Sterne', 'Bewertungen'],
        	['1 Stern',  1000],
			['2 Sterne',  1170],
			['3 Sterne',  660],
			['4 Sterne',  1030],
			['5 Sterne',  1060]
		]), {
			title: 'Bewertung Kässpätzle',
			colors: ['#99CC00'],
			legend: 'none'
		});
      }
      
      function drawChart(data, options) {
    	  
      }
    </script>

</body>
</html>

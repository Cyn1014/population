<%-- 
    Document   : Regression
    Created on : Nov 30, 2013, 4:40:07 PM
    Author     : hduser
--%>

<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            google.load("visualization", "1", {packages:["corechart"]});
            google.setOnLoadCallback(drawChart);
function drawChart() {
    
  var data = google.visualization.arrayToDataTable([
    ["Median Income", "Average House Size"],
    [10000, ${sizebean.a+sizebean.b*10000}], [35000, ${sizebean.a+sizebean.b*35000}],[60000, ${sizebean.a+sizebean.b*60000}]]);

  var options = {
    title: 'Median Income vs Average House Size',
    hAxis: {title: 'Median Income'},
    vAxis: {title: 'Average House Size'},
    legend: 'none',
    trendlines: { 0: {} }    // Draw a trendline for data series 0.
  };

  var chart = new google.visualization.ScatterChart(document.getElementById('chart_size'));
  chart.draw(data, options);
}
        </script>
         <script type="text/javascript">
            google.load("visualization", "1", {packages:["corechart"]});
            google.setOnLoadCallback(drawChart);
function drawChart() {
    
  var data = google.visualization.arrayToDataTable([
    ['Median Income', 'Median House Value'],
    [10000, ${valuebean.a+valuebean.b*10000}], [35000, ${valuebean.a+valuebean.b*35000}],[60000, ${valuebean.a+valuebean.b*60000}]]);

  var options = {
    title: 'Median Income vs Median House Value',
    hAxis: {title: 'Median Income'},
    vAxis: {title: 'Median House Value'},
    legend: 'none',
    trendlines: { 0: {} }    // Draw a trendline for data series 0.
  };

  var chart = new google.visualization.ScatterChart(document.getElementById('chart_value'));
  chart.draw(data, options);
       };
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regression</title>
    </head>
    <body>
    <center>
    
        <h1>Population Analysis of US by Location</h1>
        <p><a href="/populationAnalysis/summary">Summary of States</a>
            <a href="/populationAnalysis/state">Location</a>
            <a href="/populationAnalysis/regression">Data Analysis</a></p>
            <div id="chart_size"></div>
        <p>Equation is: y=${sizebean.a}+${sizebean.b}*x</p>
        <p>Standard Error is:${sizebean.e}</p>
        <p>R value is:${sizebean.r}</p>
        <div id="chart_value"></div>
        <p>Equation is: y=${valuebean.a}+${valuebean.b}*x</p>
        <p>Standard Error is:${valuebean.e}</p>
        <p>R value is:${valuebean.r}</p>
            <hr>
        <p><form action="regression" method="post">
            median household income:<input type="text" name="income" value="${income}"/>
            <input type="submit" value="search"/>
        </form><br/>
        possible state:${state}
        </p>
        <p>
            total test data: ${total}<br/>
            match data:${right}<br/>
            percent:${right*100/total}
        </p>
        <p>
             Confusion Matrix
        </p>
        <div>
            <table border="1">
                <tr>
                    <c:forEach items="${states}" var="st">
                        <th>${st}</th>
                    </c:forEach>
                        <th>Total</th>
                </tr>
                <c:forEach items="${matrix}" var="row">
                    <tr>
                    <c:forEach items="${row}" var="col">
                        <td>${col} </td>
                    </c:forEach>
                    </tr>
               </c:forEach>
            </table>
        </div>
        
</center>
    </body>
</html>

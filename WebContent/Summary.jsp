<%-- 
    Document   : Summary
    Created on : Nov 30, 2013, 4:39:13 PM
    Author     : hduser
--%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Summary</title>
    </head>
    <body>
    <center>
        <h1>Population Analysis of US by Location</h1>
        <p><a href="/populationAnalysis/summary">Summary of States</a>
            <a href="/populationAnalysis/state">Location</a>
            <a href="/populationAnalysis/regression">Data Analysis</a></p>
        <table border="1">
            <tr>
                <th>State</th>
                <th>Average house size</th>
                <th>Median house value</th>
                <th>Median household income</th>
                <th>Total population</th>
            </tr>
            <c:forEach items="${summarybean}" var="bean">
                <tr>
                    <td><a href="/populationAnalysis/state?state=${bean.state}">${bean.state}</a></td>
                    <td>${bean.house_size}</td>
                    <td>${bean.house_value}</td>
                    <td>${bean.household_income}</td>
                    <td>${bean.total_population}</td>
                </tr>
            </c:forEach>
        </table>
        </center>
    </body>
</html>

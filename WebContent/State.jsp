<%-- 
    Document   : State
    Created on : Nov 30, 2013, 4:39:49 PM
    Author     : hduser
--%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {
        
        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['Male', ${infobean.pct_male}],
          ['Female', ${infobean.pct_female}]
          
        ]);

        // Set chart options
        var options = {'title':'Population By Gender',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_gender'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['age 0 to 9', ${infobean.pct_0_9}],
          ['age 10 to 17', ${infobean.pct_10_17}],
          ['age 18 to 24', ${infobean.pct_18_24}],
          ['age 25 to 34', ${infobean.pct_25_34}],
          ['age 35 to 49', ${infobean.pct_35_49}],
          ['age 50 to 64', ${infobean.pct_50_64}],
          ['age greater than 65', ${infobean.pct_65}],
          
        ]);

        // Set chart options
        var options = {'title':'Population By Age',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_age'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['White', ${infobean.pct_white}],
          ['Black', ${infobean.pct_black}],
          ['Native', ${infobean.pct_native}],
          ['Asian', ${infobean.pct_asian}],
          ['Pacific', ${infobean.pct_pacific}],
          ['Mixed Race', ${infobean.pct_mixed_race}],
          
        ]);

        // Set chart options
        var options = {'title':'Population By Race',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_race'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['Hispanic', ${infobean.pct_hispanic}],
          ['Non-Hispanic', ${infobean.pct_nonhispanic}]
          
        ]);

        // Set chart options
        var options = {'title':'Population By Hispanic',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_hispanic'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['Drive Alone', ${infobean.pct_drive_alone}],
          ['Carpool',${infobean.pct_carpool}],
          ['Public Trans', ${infobean.pct_public_trans}],
          ['Other Trans', ${infobean.pct_other_trans}],
          ['Work At Home', ${infobean.pct_work_at_home}],
          
        ]);

        // Set chart options
        var options = {'title':'Go to Work Method',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_work'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['Less than Highschool', ${infobean.pct_lt_hs}],
          ['Highschool Graduate', ${infobean.pct_hs_graduate}],
          ['Some College', ${infobean.pct_some_college}],
          ['Greater than Above', ${infobean.pct_gte_ba}],
          
        ]);

        // Set chart options
        var options = {'title':'Education Level',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_education'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['Owning', ${infobean.pct_owning_home}],
          ['Renting', ${infobean.pct_renting_home}]
          
        ]);

        // Set chart options
        var options = {'title':'Owning House Situation',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_house'));
        chart.draw(data, options);
      }
    </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>State Detail Information</title>
    </head>
    <body>
    <center>
        <h1>Population Analysis of US by Location</h1>
         <p><a href="/populationAnalysis/summary">Summary of States</a>
            <a href="/populationAnalysis/state">Location</a>
            <a href="/populationAnalysis/regression">Data Analysis</a></p>
        <p><form method="get" action="/populationAnalysis/state">
            State Initial/Region ID:<input type="text" name="state"/>
            <input type="submit" value="go"/>
        </form></p>
        <h1>Summary of Population for ${infobean.state}</h1>
        <p>Average House Size:${infobean.house_size}</p>
        <p>Median House Value:${infobean.house_value}</p>
        <p>Median Household Income:${infobean.household_income}</p>
        <p>Total Population:${infobean.total_population}</p>
        <div id="chart_gender"></div>
        <div id="chart_age"></div>
        <div id="chart_race"></div>
        <div id="chart_hispanic"></div>
        <div id="chart_work"></div>
        <div id="chart_education"></div>
        <div id="chart_house"></div>
</center>
    </body>
</html>

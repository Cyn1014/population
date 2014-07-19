/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bigdata.servlet;

import com.bigdata.bean.RegressionBean;
import com.bigdata.bean.SummaryBean;
import com.bigdata.datamining.DataClassification;
import com.bigdata.datamining.Regression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.classifier.bayes.InvalidDatastoreException;

/**
 *
 * @author hduser
 */
@WebServlet(name = "regressionservlet", urlPatterns = {"/regression"})
public class RegressionServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InvalidDatastoreException {
  
         String in=String.valueOf(request.getParameter("income"));
         ArrayList<SummaryBean> beans=new ArrayList<>();
         FileSystem fs = FileSystem.get(HadoopConfig.conf());
         Path input =new Path("/user/hduser/regression/part-m-00000");
         System.out.println(fs.exists(input));
    try (BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(input)))){
            //(new FileReader("/home/hduser/population/population/populationAnalysis/regression/part-m-00000"))) 
            
        
        String line = br.readLine();

        while (line != null) {
            SummaryBean bean=new SummaryBean();
            int count=0;
            int p=line.indexOf(",");
            while(p>-1){
                String value=line.substring(0, p);
                switch(count){
                    case 0:bean.setState(value);break;
                    case 1:bean.setHouse_size(Double.parseDouble(value));break;
                    case 2:bean.setHouse_value(Double.parseDouble(value));break;
                    case 3:bean.setHousehold_income(Double.parseDouble(value));break;
                    case 4:bean.setTotal_population(Double.parseDouble(value));break;
                    case 5:bean.setTotal_work_population(Double.parseDouble(value));break;
                }
                count=count+1;
                line=line.substring(p+1);
                p=line.indexOf(",");
            }
            bean.setEducated_population(Double.parseDouble(line));
            beans.add(bean);
            line = br.readLine();
        }
       
    }
       int n=beans.size();
       double[] size=new double[n];
       double[] value=new double[n];
       double[] income=new double[n];
       double[] population=new double[n];
       double[] work=new double[n];
       double[] education=new double[n];
       for(int i=0;i<n;i++){
           size[i]=beans.get(i).getHouse_size();
           value[i]=beans.get(i).getHouse_value();
           income[i]=beans.get(i).getHousehold_income();
           population[i]=beans.get(i).getTotal_population();
           work[i]=beans.get(i).getTotal_work_population();
           education[i]=beans.get(i).getEducated_population();
       }
       
       RegressionBean sizebean=Regression.doRegression(income, size);
       RegressionBean valuebean=Regression.doRegression(income, value);
       DataClassification c=new DataClassification();
       int[][] matrix=c.getMatrix();
//       for(int i=0;i<54;i++){
//            for(int j=0;j<54;j++){
//               System.out.print(matrix[i][j]+" ");
//            }
//            System.out.println();
//       }
       if(in!=null&& in!=""&&in!="null"){
           request.setAttribute("income", in);
           request.setAttribute("state", c.searchLabel(in));
           //request.setAttribute("state", DataClassification.searchlable(in));
       }
       request.setAttribute("states", DataClassification.states);
       int right=0;
       int total=0;
       for(int i=0;i<54;i++){
           right=right+matrix[i][i];
           total=total+matrix[i][54];
       }
       request.setAttribute("right", right);
      request.setAttribute("total", total);
       request.setAttribute("matrix", matrix);
       request.setAttribute("sizebean", sizebean);
       request.setAttribute("valuebean", valuebean);
       request.getRequestDispatcher("Regression.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
        } catch (InvalidDatastoreException ex) {
            Logger.getLogger(RegressionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            processRequest(request, response);
        } catch (InvalidDatastoreException ex) {
            Logger.getLogger(RegressionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

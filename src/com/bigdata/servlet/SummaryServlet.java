/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bigdata.servlet;

import com.bigdata.bean.SummaryBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 *
 * @author hduser
 */
@WebServlet(name = "summaryservlet", urlPatterns = {"/summary"})
public class SummaryServlet extends HttpServlet {

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
            throws ServletException, IOException {
        ArrayList<SummaryBean> beans=new ArrayList<>();
        FileSystem fs = FileSystem.get(HadoopConfig.conf());
         Path input =new Path("/user/hduser/summary/part-r-00000");
         System.out.println(fs.exists(input)); 
    try (BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(input)))){
            //new FileReader("/home/hduser/population/population/populationAnalysis/summary/part-r-00000"))) {
        
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
        
       request.setAttribute("summarybean", beans);
       request.getRequestDispatcher("Summary.jsp").forward(request, response);
        
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
        processRequest(request, response);
       
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
        processRequest(request, response);
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

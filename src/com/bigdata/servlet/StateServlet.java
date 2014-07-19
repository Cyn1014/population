/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bigdata.servlet;

import com.bigdata.bean.PopulationInfoBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 *
 * @author hduser
 */
@WebServlet(name = "stateservlet", urlPatterns = {"/state"})
public class StateServlet extends HttpServlet {

    /**
	 * 
	 */
	private Testing t=new Testing();
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
        PopulationInfoBean bean=null;
        if(request.getParameter("state")!=null){
        	if(request.getParameter("state").length()<3){
          FileSystem fs = FileSystem.get(HadoopConfig.conf());
         Path input =new Path("/user/hduser/state_summary_output/part-r-00000");
         System.out.println(fs.exists(input));  
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(input)))){
                //new FileReader("/home/hduser/population/population/populationAnalysis/state_summary_output/part-r-00000"))) {
        System.out.println("br:"+br);
        	String state=request.getParameter("state").toString();
        String line = br.readLine();
         
        while (line != null) {
            if(line.substring(0,line.indexOf(",")).equalsIgnoreCase(state))
            {
            bean=new PopulationInfoBean();
            int count=0;
            int p=line.indexOf(",");
            
            while(p>-1){
                String value=line.substring(0, p);
                System.out.println("value");
                switch(count){
                    case 0:bean.setState(value);break;
                    case 1:bean.setHouse_size(Double.parseDouble(value));break;
                    case 2:bean.setHouse_value(Double.parseDouble(value));break;
                    case 3:bean.setHousehold_income(Double.parseDouble(value));break;
                    case 4:bean.setTotal_population(Double.parseDouble(value));break;
                    case 5:bean.setPct_male(Double.parseDouble(value));break;
                    case 6:bean.setPct_female(Double.parseDouble(value));break;
                    case 7:bean.setPct_0_9(Double.parseDouble(value));break;
                    case 8:bean.setPct_10_17(Double.parseDouble(value));break;
                    case 9:bean.setPct_18_24(Double.parseDouble(value));break;
                    case 10:bean.setPct_25_34(Double.parseDouble(value));break;
                    case 11:bean.setPct_35_49(Double.parseDouble(value));break;
                    case 12:bean.setPct_50_64(Double.parseDouble(value));break;
                    case 13:bean.setPct_65(Double.parseDouble(value));break;
                    case 14:bean.setPct_white(Double.parseDouble(value));break;
                    case 15:bean.setPct_black(Double.parseDouble(value));break;
                    case 16:bean.setPct_native(Double.parseDouble(value));break;
                    case 17:bean.setPct_asian(Double.parseDouble(value));break;
                    case 18:bean.setPct_pacific(Double.parseDouble(value));break;
                    case 19:bean.setPct_mixed_race(Double.parseDouble(value));break;
                    case 20:bean.setPct_nonhispanic(Double.parseDouble(value));break;
                    case 21:bean.setPct_hispanic(Double.parseDouble(value));break;
                    case 22:bean.setTotal_work_population(Double.parseDouble(value));break;
                    case 23:bean.setPct_drive_alone(Double.parseDouble(value));break;
                    case 24:bean.setPct_carpool(Double.parseDouble(value));break;
                    case 25:bean.setPct_public_trans(Double.parseDouble(value));break;
                    case 26:bean.setPct_other_trans(Double.parseDouble(value));break;
                    case 27:bean.setPct_work_at_home(Double.parseDouble(value));break;
                    case 28:bean.setEducated_population(Double.parseDouble(value));break;
                    case 29:bean.setPct_lt_hs(Double.parseDouble(value));break;
                    case 30:bean.setPct_hs_graduate(Double.parseDouble(value));break;
                    case 31:bean.setPct_some_college(Double.parseDouble(value));break;
                    case 32:bean.setPct_gte_ba(Double.parseDouble(value));break;
                    case 33:bean.setTotal_owning(Double.parseDouble(value));break;
                    case 34:bean.setPct_owning_home(Double.parseDouble(value));break;
                }
                count=count+1;
                line=line.substring(p+1);
                p=line.indexOf(",");
            }
            bean.setPct_renting_home(Double.parseDouble(line));
            break;
            }
            line = br.readLine();
        }
       
    }}else{
    	Configuration config = HBaseConfiguration.create();
        bean=new PopulationInfoBean();
    	HTable table = new HTable(config, "population");
    	Get g = new Get(Bytes.toBytes(request.getParameter("state")));
        Result r = table.get(g);
        System.out.print(r.isEmpty());
        bean.setPct_0_9(Double.parseDouble(getvalue(r,"detail_info","pct_age_0_to_9")));
        bean.setPct_10_17(Double.parseDouble(getvalue(r,"detail_info","pct_age_10_to_17")));
        bean.setPct_18_24(Double.parseDouble(getvalue(r,"detail_info","pct_age_18_to_24")));
        bean.setPct_25_34(Double.parseDouble(getvalue(r,"detail_info","pct_age_25_to_34")));
        bean.setPct_35_49(Double.parseDouble(getvalue(r,"detail_info","pct_age_35_to_49")));
        bean.setPct_50_64(Double.parseDouble(getvalue(r,"detail_info","pct_age_50_to_64")));
        bean.setPct_65(Double.parseDouble(getvalue(r,"detail_info","pct_age_gte_65")));
        bean.setPct_asian(Double.parseDouble(getvalue(r,"detail_info","pct_asian")));
        bean.setPct_black(Double.parseDouble(getvalue(r,"detail_info","pct_black")));
        bean.setPct_carpool(Double.parseDouble(getvalue(r,"detail_info","pct_carpool")));
        bean.setPct_drive_alone(Double.parseDouble(getvalue(r,"detail_info","pct_drive_alone")));
        bean.setPct_female(Double.parseDouble(getvalue(r,"detail_info","pct_female")));
        bean.setPct_gte_ba(Double.parseDouble(getvalue(r,"detail_info","pct_gte_ba")));
        bean.setPct_hispanic(Double.parseDouble(getvalue(r,"detail_info","pct_hispanic")));
        bean.setPct_hs_graduate(Double.parseDouble(getvalue(r,"detail_info","pct_hispanic")));
        bean.setPct_lt_hs(Double.parseDouble(getvalue(r,"detail_info","pct_lt_hs")));
        bean.setPct_male(Double.parseDouble(getvalue(r,"detail_info","pct_male")));
        bean.setPct_mixed_race(Double.parseDouble(getvalue(r,"detail_info","pct_mixed_race")));
        bean.setPct_native(Double.parseDouble(getvalue(r,"detail_info","pct_native")));
        bean.setPct_nonhispanic(Double.parseDouble(getvalue(r,"detail_info","pct_nonhispanic")));
        bean.setPct_other_trans(Double.parseDouble(getvalue(r,"detail_info","pct_other_trans")));
        bean.setPct_owning_home(Double.parseDouble(getvalue(r,"detail_info","pct_owning_home")));
        bean.setPct_pacific(Double.parseDouble(getvalue(r,"detail_info","pct_pacific")));
        bean.setPct_public_trans(Double.parseDouble(getvalue(r,"detail_info","pct_public_trans")));
        bean.setPct_renting_home(Double.parseDouble(getvalue(r,"detail_info","pct_renting_home")));
        bean.setPct_some_college(Double.parseDouble(getvalue(r,"detail_info","pct_some_college")));
        bean.setPct_white(Double.parseDouble(getvalue(r,"detail_info","pct_white")));
        bean.setPct_work_at_home(Double.parseDouble(getvalue(r,"detail_info","pct_work_at_home")));
        bean.setHouse_size(Double.parseDouble(getvalue(r,"general_info","avg_house_size")));
        bean.setEducated_population(Double.parseDouble(getvalue(r,"general_info","educated_population")));
        bean.setState(getvalue(r,"general_info","location"));
        bean.setHouse_value(Double.parseDouble(getvalue(r,"general_info","median_house_value")));
        bean.setHousehold_income(Double.parseDouble(getvalue(r,"general_info","median_household_income")));
        bean.setTotal_owning(Double.parseDouble(getvalue(r,"general_info","own_house_population")));
        bean.setTotal_population(Double.parseDouble(getvalue(r,"general_info","total_population")));
        bean.setTotal_work_population(Double.parseDouble(getvalue(r,"general_info","work_population")));
        
    }
        }
        if(bean==null){
            FileSystem fs = FileSystem.get(HadoopConfig.conf());
         Path input =new Path("/user/hduser/states_summary_output/part-r-00000");
         System.out.println(fs.exists(input)); 
            try (BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(input)))){
                   // new FileReader("/home/hduser/population/population/populationAnalysis/states_summary_output/part-r-00000"))) {
            	System.out.println("br:"+br);
            	String line = br.readLine();
         
        while (line != null) {
      
            
            bean=new PopulationInfoBean();
            int count=0;
            int p=line.indexOf(",");
            bean.setState("US");
            while(p>-1){
                count=count+1;
                String value=line.substring(0, p);
                System.out.println(value);
                switch(count){
                    case 1:bean.setHouse_size(Double.parseDouble(value));break;
                    case 2:bean.setHouse_value(Double.parseDouble(value));break;
                    case 3:bean.setHousehold_income(Double.parseDouble(value));break;
                    case 4:bean.setTotal_population(Double.parseDouble(value));break;
                    case 5:bean.setPct_male(Double.parseDouble(value));break;
                    case 6:bean.setPct_female(Double.parseDouble(value));break;
                    case 7:bean.setPct_0_9(Double.parseDouble(value));break;
                    case 8:bean.setPct_10_17(Double.parseDouble(value));break;
                    case 9:bean.setPct_18_24(Double.parseDouble(value));break;
                    case 10:bean.setPct_25_34(Double.parseDouble(value));break;
                    case 11:bean.setPct_35_49(Double.parseDouble(value));break;
                    case 12:bean.setPct_50_64(Double.parseDouble(value));break;
                    case 13:bean.setPct_65(Double.parseDouble(value));break;
                    case 14:bean.setPct_white(Double.parseDouble(value));break;
                    case 15:bean.setPct_black(Double.parseDouble(value));break;
                    case 16:bean.setPct_native(Double.parseDouble(value));break;
                    case 17:bean.setPct_asian(Double.parseDouble(value));break;
                    case 18:bean.setPct_pacific(Double.parseDouble(value));break;
                    case 19:bean.setPct_mixed_race(Double.parseDouble(value));break;
                    case 20:bean.setPct_nonhispanic(Double.parseDouble(value));break;
                    case 21:bean.setPct_hispanic(Double.parseDouble(value));break;
                    case 22:bean.setTotal_work_population(Double.parseDouble(value));break;
                    case 23:bean.setPct_drive_alone(Double.parseDouble(value));break;
                    case 24:bean.setPct_carpool(Double.parseDouble(value));break;
                    case 25:bean.setPct_public_trans(Double.parseDouble(value));break;
                    case 26:bean.setPct_other_trans(Double.parseDouble(value));break;
                    case 27:bean.setPct_work_at_home(Double.parseDouble(value));break;
                    case 28:bean.setEducated_population(Double.parseDouble(value));break;
                    case 29:bean.setPct_lt_hs(Double.parseDouble(value));break;
                    case 30:bean.setPct_hs_graduate(Double.parseDouble(value));break;
                    case 31:bean.setPct_some_college(Double.parseDouble(value));break;
                    case 32:bean.setPct_gte_ba(Double.parseDouble(value));break;
                    case 33:bean.setTotal_owning(Double.parseDouble(value));break;
                    case 34:bean.setPct_owning_home(Double.parseDouble(value));break;
                }
                line=line.substring(p+1);
                p=line.indexOf(",");
            }
            bean.setPct_renting_home(Double.parseDouble(line));
            
            
            line = br.readLine();
        }
       
    }
        }
        
        request.setAttribute("infobean", bean);
        request.getRequestDispatcher("State.jsp").forward(request, response);
    }
    private String getvalue(Result r,String cfname,String quality){
    	String valueStr="0";
    	byte[] value = r.getValue(Bytes.toBytes(cfname), Bytes.toBytes(quality));
        valueStr = Bytes.toString(value);
        if(!quality.equalsIgnoreCase("location")){
        	try{
        		Double.valueOf(valueStr);
        	}catch(Exception e){
        		valueStr="0";
        	}
        }
    	return valueStr;
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

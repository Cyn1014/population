/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bigdata.datamining;

import com.bigdata.bean.RegressionBean;

/**
 *
 * @author hduser
 */
public class Regression {
    
    public static RegressionBean doRegression(double[] x,double[] y){
        RegressionBean bean=new RegressionBean();
        int n=x.length;
        double mx=mean(x);
        double my=mean(y);
        double b=0;
        double c=0;
        for(int i=0;i<n;i++){
            b=b+(x[i]-mx)*(y[i]-my);
            c=c+(x[i]-mx)*(x[i]-mx);
        }
        if(c!=0)bean.setB(b/c);
        bean.setA(my-bean.getB()*mx);
        b=0;
        c=0;
        for(int i=0;i<n;i++){
            b=b+(y[i]-bean.getA()-bean.getB()*x[i])*(y[i]-bean.getA()-bean.getB()*x[i]);
            c=c+(y[i]-my)*(y[i]-my);
        }
        bean.setE(Math.sqrt(b/(n-2)));
        bean.setR(Math.sqrt(1-(b/c)));
        return bean;
    }
    
    private static double mean(double[] z){
        double m=0;
        int n=z.length;
        for(double a:z){
            m=m+a;
        }
        return m/n;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bigdata.bean;


/**
 *
 * @author hduser
 */

public class SummaryBean {
    private String state;
    private double house_size;
    private double house_value;
    private double household_income;
    private double total_population;
    private double total_work_population;
    private double educated_population;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getHouse_size() {
        return house_size;
    }

    public void setHouse_size(double house_size) {
        this.house_size = house_size;
    }

    public double getHouse_value() {
        return house_value;
    }

    public void setHouse_value(double house_value) {
        this.house_value = house_value;
    }

    public double getHousehold_income() {
        return household_income;
    }

    public void setHousehold_income(double household_income) {
        this.household_income = household_income;
    }

    public double getTotal_population() {
        return total_population;
    }

    public void setTotal_population(double total_population) {
        this.total_population = total_population;
    }

    public double getTotal_work_population() {
        return total_work_population;
    }

    public void setTotal_work_population(double total_work_population) {
        this.total_work_population = total_work_population;
    }

    public double getEducated_population() {
        return educated_population;
    }

    public void setEducated_population(double educated_population) {
        this.educated_population = educated_population;
    }
    
    
}

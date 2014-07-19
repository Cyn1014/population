/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bigdata.servlet;

import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;

/**
 *
 * @author hduser
 */
public class HadoopConfig {
    
    public static Configuration conf(){
        Configuration config=new Configuration();
        config.set("hadoop.tmp.dir", "/app/hadoop/tmp");
        config.set("fs.default.name", "hdfs://master:54310");
        config.set("dfs.replication", "2");
        
        
        
        return config;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package config;

import java.io.IOException;
import org.apache.pig.PigServer;

/**
 *
 * @author hduser
 */
public class Config {
    
    public static void main(String arg[]) throws IOException{
       // Configuration config =new Configuration();

        PigServer pigServer=new PigServer("mapred");
      
        pigServer.registerScript("hbase_sample.pig");
        pigServer.registerScript("mapred.pig");
        pigServer.registerScript("wholestate.pig");
        pigServer.registerScript("summary.pig");
        pigServer.registerScript("regression.pig");
        pigServer.registerScript("training.pig");

        pigServer.shutdown();
    }
    
}

package test;


import com.bigdata.datamining.DataClassification;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.mahout.classifier.bayes.InvalidDatastoreException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hduser
 */
public class TestClassification {
    
    public static void main(String arg[]) throws InvalidDatastoreException, IOException{
    	
        DataClassification c=new DataClassification("/user/hduser/data1","/user/hduser/classifier1");
        int[][] matrix=c.buildMatrix();
        //System.out.println(c.searchLabel("64635.0"));
        File file=new File("matrix.txt");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
        for(int i=0;i<54;i++){
             for(int j=0;j<55;j++){
                bw.write(matrix[i][j]+"\t");
             }
             bw.write("\n");;
        }
        bw.close();
        int right=0;
        int total=0;
        for(int i=0;i<54;i++){
            right=right+matrix[i][i];
            total=total+matrix[i][54];
        }
        
        System.out.println(right+" "+total);
           
        
    }
    
}

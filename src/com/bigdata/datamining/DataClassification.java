/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bigdata.datamining;

import com.bigdata.servlet.HadoopConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.classifier.ClassifierResult;
import org.apache.mahout.classifier.bayes.Algorithm;
import org.apache.mahout.classifier.bayes.CBayesAlgorithm;
import org.apache.mahout.classifier.bayes.BayesParameters;
import org.apache.mahout.classifier.bayes.ClassifierContext;
import org.apache.mahout.classifier.bayes.Datastore;
import org.apache.mahout.classifier.bayes.InMemoryBayesDatastore;
import org.apache.mahout.classifier.bayes.InvalidDatastoreException;



/**
 *
 * @author hduser
 */
public class DataClassification {
    
    private ClassifierContext classifier;
    public static String[] states={"AL","AK","AZ","AR","CA","CO","CT","DC","DE","FL","GA",
                            "HI","ID","IL","IN","IA","KS","KY","LA","ME","MD",
                            "MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ",
                            "NM","NY","NC","ND","OH","OK","OR","PA","PR","RI","SC",
                            "SD","TN","TX","US","UT","VT","VA","WA","WV","WI","WY","OTHER"
                            };
    private String input="/user/hduser/data";
    private String output="/user/hduser/classifier";
    
    
	public DataClassification(String input,String output){
    	this.input=input;
    	this.output=output;
        Algorithm algorithm = new CBayesAlgorithm();
        BayesParameters params=buildBayesParam();
      Datastore datastore = new InMemoryBayesDatastore( params );
   
      classifier = new ClassifierContext( algorithm, datastore );
        try {
  
            classifier.initialize();
        } catch (InvalidDatastoreException ex) {
            Logger.getLogger(DataClassification.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public DataClassification(){
        Algorithm algorithm = new CBayesAlgorithm();
        BayesParameters params=buildBayesParam();
      Datastore datastore = new InMemoryBayesDatastore( params );
   
      classifier = new ClassifierContext( algorithm, datastore );
        try {
  
            classifier.initialize();
        } catch (InvalidDatastoreException ex) {
            Logger.getLogger(DataClassification.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    public String searchLabel(String contentToClassify)
   throws InvalidDatastoreException, IOException {
      
  
  ClassifierResult result = classifier.classifyDocument( 
                           new String[]{contentToClassify}, 
                           "OTHER" ); 
  
  return result.getLabel();
}
    public String searchLabel(String[] contentToClassify)
    		   throws InvalidDatastoreException, IOException {
    		      
    		  
    		  ClassifierResult result = classifier.classifyDocument( 
    		                           contentToClassify, 
    		                           "OTHER" ); 
    		  
    		  return result.getLabel();
    		}
    public int[][] getMatrix() throws IOException{
    	int[][] matrix=new int[54][55];
    	FileSystem fs = FileSystem.get(HadoopConfig.conf());
        Path input =new Path("/user/hduser/matrix.txt");
        System.out.println(fs.exists(input));
        BufferedReader reader = new BufferedReader(new FileReader("/home/hduser/workspace/populationAnalysis/matrix.txt"));
        String line = reader.readLine();
        int i=0;
        while( line != null) {
            
            String[] value=line.split("\t");
            int j=0;
            for(String s:value){
            	matrix[i][j]=Integer.parseInt(s);
            	j=j+1;
            }
            line=reader.readLine();
            i=i+1;
        }
        
        return matrix;
    	
    }
    
    public int[][] buildMatrix() throws InvalidDatastoreException, IOException{
        int[][] matrix=new int[54][55];
        for(int i=0;i<54;i++)
            for(int j=0;j<55;j++)
             matrix[i][j]=0;
        for(int k=0;k<2;k++)
        {
            String filename=input+"/part-m-0000"+k;
            int[][] m=testData(filename);
            for(int i=0;i<54;i++)
            for(int j=0;j<55;j++)
             matrix[i][j]=matrix[i][j]+m[i][j];
        
        }
        
        return matrix;
        
    }
    public int[][] testData(String filename) throws InvalidDatastoreException, FileNotFoundException, IOException{
        int[][] matrix=new int[54][55];
        for(int i=0;i<54;i++)
            for(int j=0;j<55;j++)
             matrix[i][j]=0;
      FileSystem fs = FileSystem.get(HadoopConfig.conf());
      Path input =new Path(filename);
      System.out.println(fs.exists(input));
      BufferedReader reader = new BufferedReader(new InputStreamReader(fs.open(input)));
      String line = reader.readLine();
      
      while( line != null ) {
        
            String[] value=line.split("\t");

          ClassifierResult result = classifier.classifyDocument( 
                           new String[]{value[1]}, 
                           "OTHER" ); 
          int i=53;
          int j=53;
          for(int k=0;k<54;k++){
              if(states[k].equalsIgnoreCase(value[0]))
                  i=k;
              if(states[k].equalsIgnoreCase(result.getLabel()))
                  j=k;
          }
          matrix[i][j]=matrix[i][j]+1;
          matrix[i][54]=matrix[i][54]+1;
          
          line = reader.readLine();
      }
      return matrix;
    }
     private BayesParameters buildBayesParam() {
  BayesParameters params = new BayesParameters();
 params.setGramSize( 1 );
  params.set( "verbose", "true" );
  params.set("encoding", "UTF-8");
  params.set( "classifierType", "bayes" );
  params.set( "defaultCat", "OTHER" );
  params.set( "alpha_i", "1.0" );
  params.set( "dataSource", "hdfs" );
  params.setBasePath(output );
  return params;
 }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.mahout.classifier.bayes.TrainClassifier;
import org.apache.mahout.classifier.bayes.BayesParameters;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

/**
 *
 * @author hduser
 */
public class DataTrainning {
    
    public static void main( final String[] args ) throws IOException {

     FileSystem fs = FileSystem.get(new Configuration());
  BayesParameters params = new BayesParameters();
  params.setGramSize( 1 );
  params.set( "verbose", "true" );
  params.set("encoding", "UTF-8");
  params.set( "classifierType", "bayes" );
  params.set( "defaultCat", "OTHER" );
  params.set( "alpha_i", "1.0" );
  params.set( "dataSource", "hdfs" );
  params.setBasePath("/user/hduser/classifier1" );
 
  Path input =new Path("/user/hduser/data1");
  System.out.println(fs.exists(input));
  Path output =new Path("/user/hduser/classifier1");
      TrainClassifier.trainCNaiveBayes( input,output , params );
   
      
 
 }
    
   
        
    }

  
    

package restaurantRevenuePrediction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.RBFNetwork;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Predict {
	public static void main(String args[]){
		
		runPredict("F:/study/Experiment/Restaurant Revenue Prediction/train.arff",
				"F:/study/Experiment/Restaurant Revenue Prediction/test.arff");
	}
	
	public static Double runPredict(String trainFile, String testFile) {		
		double predictOrder = 0.0;
		double trueOrder = 0.0;
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"F:/study/Experiment/Restaurant Revenue Prediction/predict.csv")));
			bw.write("Id,Prediction");
			bw.newLine();			
			
			String trainWekaFileName=trainFile;	
			String testWekaFileName=testFile;	
			
			Instances train = DataSource.read(trainWekaFileName);
			Instances test = DataSource.read(testWekaFileName); 

			train.setClassIndex(train.numAttributes()-1);
			test.setClassIndex(train.numAttributes()-1);
			
			
			
			//AdditiveRegression classifier = new AdditiveRegression();
					
			
			RBFNetwork classifier = new RBFNetwork();
			String[] options =weka.core.Utils.splitOptions("-B 50 -S 1 -R 1.0E-8 -M -1 -W 0.1"); 
			classifier.setOptions(options);

					
		    classifier.buildClassifier(train);			
			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(classifier, test);		
			
			System.out.println(eval.toSummaryString("\nResults\n\n", true));
			System.out.println(eval.toClassDetailsString());
			System.out.println(eval.toMatrixString());
			int k=0;
			for(int i=0; i<test.numInstances(); i++){
				predictOrder = classifier.classifyInstance(test.instance(i));	
				trueOrder = test.instance(i).classValue();			
				//System.out.println((k++)+","+predictOrder+","+trueOrder);
				bw.write((k++)+","+predictOrder);
				bw.newLine();
				bw.flush();
			}
			
			bw.close();	
		}catch(Exception e){
			e.printStackTrace();
		}		
		return predictOrder;
	}	
}

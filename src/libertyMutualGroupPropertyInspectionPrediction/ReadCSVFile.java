package libertyMutualGroupPropertyInspectionPrediction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.RBFNetwork;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ReadCSVFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public static void readFile(){
		try{
					
			
			String trainWekaFileName="F:/study/Experiment/Liberty Mutual Group Property Inspection Prediction";	
			String testWekaFileName="";	
			
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
				double predictOrder = classifier.classifyInstance(test.instance(i));	
				double trueOrder = test.instance(i).classValue();			
				System.out.println((k++)+","+predictOrder+","+trueOrder);
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

}

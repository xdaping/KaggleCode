package titanicMachineLearningfromDisaster;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Classify {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		runClassify("F:/study/Experiment/Titanic/average/train.arff",
				"F:/study/Experiment/Titanic/average/test.arff");
	}
	
	public static Double runClassify(String trainFile, String testFile) {		
		double predictOrder = 0.0;
		double trueOrder = 0.0;
		try{						
			String trainWekaFileName=trainFile;	
			String testWekaFileName=testFile;	
			
			Instances train = DataSource.read(trainWekaFileName);
			Instances test = DataSource.read(testWekaFileName); 

			train.setClassIndex(0);
			test.setClassIndex(0);					
			
			train.deleteAttributeAt(8);
			test.deleteAttributeAt(8);
			train.deleteAttributeAt(6);
			test.deleteAttributeAt(6);
			train.deleteAttributeAt(5);
			test.deleteAttributeAt(5);
			train.deleteAttributeAt(4);
			test.deleteAttributeAt(4);


			
			
			
			//AdditiveRegression classifier = new AdditiveRegression();
					
			//NaiveBayes classifier = new NaiveBayes();
			
			RandomForest classifier = new RandomForest();
			//LibSVM classifier = new LibSVM();
					
		    classifier.buildClassifier(train);			
			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(classifier, test);		
			
			System.out.println(eval.toSummaryString("\nResults\n\n", true));
			//System.out.println(eval.toClassDetailsString());
			//System.out.println(eval.toMatrixString());
			int k=892;
			for(int i=0; i<test.numInstances(); i++){
				predictOrder = classifier.classifyInstance(test.instance(i));	
				trueOrder = test.instance(i).classValue();			
				System.out.println((k++)+","+(int)predictOrder);
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}		
		return predictOrder;
	}

}

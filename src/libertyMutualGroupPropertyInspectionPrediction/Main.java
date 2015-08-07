package libertyMutualGroupPropertyInspectionPrediction;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		run();
	}

	private static void run() {
		String trainFile = "F:/study/Experiment/Liberty Mutual Group Property Inspection Prediction/train.csv";
		String testFile = "F:/study/Experiment/Liberty Mutual Group Property Inspection Prediction/test.csv";
		
		WekeDataFormat data = new WekeDataFormat(); 
		data.getDataAttributes(trainFile);
		data.getTrainTestDataFormat(trainFile, "Train");
		data.getTrainTestDataFormat(testFile, "Test");
		
		
		
	}

}

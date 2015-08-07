package restaurantRevenuePrediction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Preprocess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getWekaData();
	}
	
	public static void getWekaData(){
		getTrainTestWekaData("F:/study/Experiment/Restaurant Revenue Prediction/train", "train");
		getTrainTestWekaData("F:/study/Experiment/Restaurant Revenue Prediction/test", "test");
	}

	private static void getTrainTestWekaData(String fileNamePath, String string2) {
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(fileNamePath)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileNamePath+".arff")));
			
			getWekaHeaderFile(bw);
			
			
			String line = "";
			br.readLine();
			while(null != (line=br.readLine())){
				String words[]=line.split("	");
				//System.out.println(words.length);
				for(int i=0; i<words.length-1; i++){
					if(i==0 || i==4){
						continue;
					}
					
					if(i==5){
						bw.write(words[i].replaceAll(" ", "")+",");
					}else{
						bw.write(words[i]+",");
					}
				}
				bw.write(words[words.length-1]);
				bw.newLine();
				bw.flush();
			}
						
			br.close();
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	private static void getWekaHeaderFile(BufferedWriter bw) {
		try{
			bw.write("@relation 'Restaurant Revenue Prediction'");
			bw.newLine();
			bw.newLine();
			
			for(int i=1; i<=3; i++){
				bw.write("@attribute time"+i+" numeric");
				bw.newLine();
			}
			
			bw.write("@attribute CityGroup {BigCities,Other}");
			bw.newLine();
			
			bw.write("@attribute Type {FC,IL,DT,MB}");
			bw.newLine();
			
			for(int i=1; i<=37; i++){
				bw.write("@attribute p"+i+" numeric");
				bw.newLine();
			}
			
			bw.write("@attribute revenue numeric");
			bw.newLine();
			bw.newLine();
			
			bw.write("@data");
			bw.newLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	

}

package libertyMutualGroupPropertyInspectionPrediction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class WekeDataFormat {
	
	public static void main(String[] args) {
		String str = "39,1,4,6,4,N,K,N,B,B,E,2,J,B,10,2,A,R,N,81,8,N,9,A,2,40,1,4,4,N,N,D,4,4";
		    System.out.print(str.substring(str.indexOf(",")+1));
		
	} 
	
	ArrayList<String> attributes;
	HashMap<String, HashSet<String>> attributeValues;
	

	public WekeDataFormat() {
		this.attributes = new ArrayList<String>();
		this.attributeValues = new HashMap<String, HashSet<String>>();
	}


	public void getDataAttributes(String fileNamePath) {
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(fileNamePath)));
			String line= br.readLine();
			String[] words=line.split(",");
			for(int i=0; i<words.length; i++){
				attributes.add(words[i]);				
				attributeValues.put(words[i], new HashSet<String>());
			}			
			//System.out.print(attributes);			
			while(null != (line=br.readLine())){
				words=line.split(",");				
				for(int i=0; i<words.length; i++){
					if (i==1 ||!Character.isDigit(words[i].charAt(0))){							
					    attributeValues.get(attributes.get(i)).add(words[i]);					  
					}
				}
			}			
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	
	public void getTrainTestDataFormat(String fileNamePath, String TrainTest) {
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(fileNamePath)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileNamePath.replaceAll("csv", "arff"))));
			br.readLine();
			writeWekaHeaderFile(bw);
			
			String line="";
			if(TrainTest.equals("Train")){
				while(null != (line = br.readLine())){
					bw.write(line.substring(line.indexOf(",")+1));
					bw.newLine();
					bw.flush();
				}			
			}else{
				while(null != (line = br.readLine())){
					bw.write("0,"+line.substring(line.indexOf(",")+1));
					bw.newLine();
					bw.flush();
				}
			}
			br.close();
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}


	private void writeWekaHeaderFile(BufferedWriter bw) {
		try{
			bw.write("@relation LibertyMutualGroupPropertyInspectionPrediction");
			bw.newLine();
			bw.newLine();
			for(int i=1; i<attributes.size(); i++){
				HashSet<String> values = attributeValues.get(attributes.get(i));
				if(values.size()>0){
					bw.write("@attribute "+attributes.get(i)+" "+
							values.toString().replaceAll("\\[", "{").replaceAll("\\]", "}"));
					bw.newLine();
				}else{
					bw.write("@attribute "+attributes.get(i)+" numeric");
					bw.newLine();
				}
			}
			bw.newLine();
			bw.write("@data");
			bw.newLine();
			bw.flush();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	

}

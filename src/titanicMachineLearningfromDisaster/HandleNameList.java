package titanicMachineLearningfromDisaster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class HandleNameList {
	public static void main(String args[]){
		//getNameList();
		lableNameList();
	}	
	
	
	private static void lableNameList() {
		HashSet<String> nameList = new HashSet<String>();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File("F:/study/Experiment/Titanic/nameList/TitanicSurvivors.txt")));
			String line = "";
			while(null != (line=br.readLine())){
				String words[]=line.split("	");
				//String name = words[0].replaceAll("[ ,.\\']", "");
				String name = words[0].toLowerCase();
				//System.out.println(name);
				nameList.add(name);
			}
			
			br = new BufferedReader(new FileReader(new File("F:/study/Experiment/Titanic/test.txt")));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("F:/study/Experiment/Titanic/nameList/nameListPredict.csv")));
			bw.write("PassengerId,Survived");
			bw.newLine();
			bw.flush();
			
			int k=0;
			while(null != (line=br.readLine())){
				String words[]=line.split("	");
				String id = words[0];
				String name = words[3].toLowerCase();//.replaceAll("[\" ,.\\']", "");
				
				int maxN=0;
				for(String nameTemp : nameList){
					
					int n = similarityWords(nameTemp, name);
					
					
					if(n>maxN){
						maxN = n;
					}
					
				}
				
				if(maxN>1){
					//System.out.println(nameTemp+"===="+name);
					System.out.println(id+",1");
					bw.write(id+",1");
					bw.newLine();
					k++;
				}else{
					System.out.println(id+",0");
					bw.write(id+",0");
					bw.newLine();
				}
				bw.flush();
				
			}	
			//System.out.println(k);
			br.close();
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}




	private static int similarityWords(String nameTemp, String name) {
		int n=0;
		String words1[]=nameTemp.split("[ ,()]");
		HashSet<String> nameHash = new HashSet<String>();
		for(String word : words1){
			if(word.equals("mr") || word.equals("mrs") || word.equals("miss")){
				continue;
			}
			if(word.length()>0 ){
				nameHash.add(word);
			}
		}
		
		String words2[]=name.split("[ ,()]");
		for(String word : words2){
			if(word.length()>0){
				if(nameHash.contains(word)){
					n++;
				}				
			}
		}
		return n;
	}


	public static void getNameList(){
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File("F:/study/Experiment/Titanic/nameList/Titanic Survivors raw.txt")));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("F:/study/Experiment/Titanic/nameList/TitanicSurvivors.txt")));
			String line = "";
			int k=0;
			while(null != (line=br.readLine())){
				String words[]=line.split("	");
				System.out.println(words.length);
				if(words.length == 1){
					continue;
				}
				
				if(words.length == 2){
					bw.write("	"+line);
					continue;
				}
				
				bw.write(line);
				k++;
				if(k==1){
					bw.write("	");
				}
				
				if(k==2){
					bw.newLine();
					k=0;
				}
			}
			
			
			
			br.close();
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

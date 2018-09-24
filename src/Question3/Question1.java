package Question3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question1 {
	public static void main(String[] args) {
		Question1 q1=new Question1();
		q1.readDictionary("new1/dictionary.txt");
		q1.readDictionary("new/");
		q1.readDictionary("../../new/dictionary.txt");
		q1.readDictionary("new/dictionary.txt");
	}
	
	public Map<String,List<String>> m;
	
	public boolean doesFileExist(String path) {
		File directory = new File(path);
		try {
			System.out.println("Looking file in :"+directory.getCanonicalPath());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!directory.exists())
			System.out.println("File or directory not found");
	
		return directory.exists();
	}
	
	public void readDictionary(String path) {		
		if(!doesFileExist(path)) {
			return;
		}
		
		m = new HashMap<String,List<String>>();
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			String currentLine = null;
			while((currentLine = br.readLine()) != null){//while there is content on the current line
				String key=currentLine.substring(0, currentLine.indexOf('¨C')).trim();
				List<String> l;
				if(!m.containsKey(key)) {
					l = new ArrayList<String>();
					
				}
				else {
					l = m.get(key);
					
				}
				
				currentLine= currentLine.substring(currentLine.indexOf('¨C')+1);
				while(currentLine.contains(",")) {
					l.add(currentLine.substring(0, currentLine.indexOf(',')));
					currentLine= currentLine.substring(currentLine.indexOf(',')+1);
				}
				l.add(currentLine);
					
				m.put(key,new ArrayList<String>(l));
				l.clear();
				 // print the current line
			}
			
			for(String k:m.keySet()) {
				System.out.println(k+":");
				List<String> temp=m.get(k);
				for(int i=0;i<temp.size();i++) {
					System.out.println("  "+temp.get(i));
				}
			}
		}catch(IOException ex){
			System.out.println(ex.getMessage());  //handle an exception here
		}

	
	}

}

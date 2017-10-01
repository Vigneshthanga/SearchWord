package DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import DataStructure.PosDocument;
import DataStructure.WordDocument;
import IMDatabase.InmemoryDB;

public class DataIngest {
	
	public void buildIndex(String path, InmemoryDB DBObj) {
	File dir = new File(path);
	FileFilter filter = new FileFilter();

	if (dir.isDirectory()) {
		String file_str;
		File[] files_ptr = dir.listFiles(filter);
		
		for (File file_ptr : files_ptr) {
			Scanner input = null;
			try {
				input = new Scanner(file_ptr);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String line = null;
			String file_name = file_ptr.getName();

			int pos = 0;
			while (input.hasNext()) {
				line = input.nextLine();
				Scanner sc = new Scanner(line);
				int count = 0;
				while (sc.hasNext()) {
					sc.next();
					count++;
				}
				sc.close();
				int end = count + pos;
				while (pos < end) {
					PosDocument pd = new PosDocument(pos, file_name);
					DBObj.pd_index.put(pd, line);
					pos++;
				}
			}
			input.close();
		}
		
		for(File file_ptr : files_ptr) {
			
			Scanner input = null;
			
			try {				
			input = new Scanner(file_ptr);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			String word = null;
			int pos = 0;
			file_str = file_ptr.getName(); 
			while(input.hasNext()) {
			     
				word = input.next();
			     
			     if (!DBObj.invert_index.containsKey(word)) {
			    	 DBObj.invert_index.put(word, new HashSet<String>());
			     }
		    	 DBObj.invert_index.get(word).add(file_str);
			     
				 WordDocument wd_object = new WordDocument(word, file_str); 
				    
				 if (!DBObj.wd_index.containsKey(wd_object)) {
					 DBObj.wd_index.put(wd_object, new ArrayList<Integer>());
				 }
				    
				 DBObj.wd_index.get(wd_object).add(pos);
			     		     			     
			     pos++;
			}

			input.close();
			
		}
	}
	}
}

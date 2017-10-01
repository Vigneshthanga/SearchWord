package DAO;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import DataStructure.PosDocument;
import DataStructure.WordDocument;
import IMDatabase.InmemoryDB;

public class QuerySearch {
	
	public String searchWord(InmemoryDB DBObj, String Qstr) {
	String result = "";
	
	if (!DBObj.invert_index.containsKey(Qstr))
		return result;
	
	Set<String> set_files = DBObj.invert_index.get(Qstr);
	Iterator<String> it = set_files.iterator();
	
	System.out.println("word is found in following files: ");
	while(it.hasNext()) {
		String file_name = it.next();

		WordDocument key = new WordDocument(Qstr,file_name);
	
		List<Integer> list = DBObj.wd_index.get(key);
		Iterator<Integer> it1 = list.iterator();

		while (it1.hasNext()) {
			int val = it1.next();
			String snippet = "";
				PosDocument pd = new PosDocument(val,file_name);
				if (DBObj.pd_index.containsKey(pd)) {
					snippet += DBObj.pd_index.get(pd);
			}
			result += file_name+": "+snippet+"\n";
		}
	}
	return result;
	}
	
	public String searchPhrase(InmemoryDB DBObj, String Qstr) {
		
		String result ="";
		
		Scanner sc = new Scanner(Qstr);
		String word = sc.next();
		
		if (!DBObj.invert_index.containsKey(word)) {
			return result;
		}
		
		Set<String> set_files = DBObj.invert_index.get(word);
		Iterator<String> it = set_files.iterator();
			
		while(it.hasNext()) {
			String file_name = it.next();

			WordDocument key = new WordDocument(word,file_name);
		
			List<Integer> list = DBObj.wd_index.get(key);
			Iterator<Integer> it1 = list.iterator();

			while (it1.hasNext()) {
				int val = it1.next();
				String snippet = "";
					PosDocument pd = new PosDocument(val,file_name);
					if (DBObj.pd_index.containsKey(pd)) {
						if(DBObj.pd_index.get(pd).indexOf(Qstr) < 0)
							continue;
						else
							snippet += DBObj.pd_index.get(pd);
					val++;
				}
				result += file_name+": "+snippet+"\n";
			}
		}
		sc.close();
		return result;
	}
}

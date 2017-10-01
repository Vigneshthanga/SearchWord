package Clients;

import java.util.Scanner;

import DAO.DataIngest;
import DAO.QuerySearch;
import IMDatabase.InmemoryDB;

public class Client {
public static void main(String[] args) {
	String url = "/Users/vthangarajan/Downloads/redis-unstable/src/";
	InmemoryDB DBObj = InmemoryDB.getDBinstance();
	DataIngest indexing = new DataIngest();
	int count = 0;
	String result = "";
	
	indexing.buildIndex(url, DBObj);
	QuerySearch qs = new QuerySearch();
	Scanner input = new Scanner(System.in);
	
	System.out.println("Enter word to search:");
	String query = input.nextLine();

	Scanner sc = new Scanner(query);
	while(sc.hasNext()) {
		sc.next();
		count++;
	}
	if (count == 1) {
		long time1 = System.nanoTime();
		result = qs.searchWord(DBObj, query);
		long time2 = System.nanoTime();
		long timeTaken = time2 - time1;  
		System.out.println("Time taken " + timeTaken + " ns"); 
	}
	else {
		long time1 = System.nanoTime();
		result = qs.searchPhrase(DBObj,query);
		long time2 = System.nanoTime();
		long timeTaken = time2 - time1;  
		System.out.println("Time taken " + timeTaken + " ns"); 
	}
	
	if (result.length() > 1)
		System.out.println(result);
	else
		System.out.println("not found");
	
	sc.close();
	input.close();
}

}

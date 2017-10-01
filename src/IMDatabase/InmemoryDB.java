package IMDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import DataStructure.PosDocument;
import DataStructure.WordDocument;

public class InmemoryDB {
	private static InmemoryDB DBObj = null;
	public HashMap<String, Set<String>> invert_index;
	public HashMap<WordDocument, List<Integer>> wd_index;
	public HashMap<PosDocument, String> pd_index;
	
	private InmemoryDB() {
		invert_index = new HashMap<String, Set<String>>();
		wd_index = new HashMap<WordDocument, List<Integer>>();
		pd_index = new HashMap<PosDocument, String>();
	}
	
	public static InmemoryDB getDBinstance() {
		if (DBObj == null)
			DBObj = new InmemoryDB();
		
		return DBObj;
	}
}

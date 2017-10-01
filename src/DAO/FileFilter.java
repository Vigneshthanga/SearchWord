package DAO;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilter implements FilenameFilter {

	public boolean accept(File dir, String name) {
		if (dir != null) {
			return (name.endsWith(".c") || name.endsWith(".h")) ? true : false;
		}
		
		return false;
	}
}
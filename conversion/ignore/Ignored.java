package scruf.conversion.ignore;

import java.io.*;
import scruf.io.*;

public class Ignored {
	private String ignoredList[];
	public Ignored(File directory) {
		File ignoredFile = new File(directory,".ignored");
		if(ignoredFile.exists()) {
			ignoredList = new ReadFile(ignoredFile).split("\n");
		}
	}
	public boolean ignored(File subdirectory) {
		boolean ignored = false;
		if(ignoredList!=null) {
			for(String dir:ignoredList) {
				if(subdirectory.getName().matches(dir)) {
					System.out.println("Ignoring Directory: "+
									   subdirectory.getAbsolutePath());
					ignored = true;
					break;
				}
			}
		}
		return ignored;
	}
}

/**
   CVS/
  /home/rsd/projects/scruf/www/CVS/
 */
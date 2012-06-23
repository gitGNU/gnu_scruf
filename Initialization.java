package scruf;

import java.io.*;
import scruf.io.*;

public class Initialization {
    // it is a directory.
    private File scruf;
    // it is a file.
    private File list;
    /**
     * @param dir this parameter is the name of the directory
     * whose files must be converted into html. This directory
     * will be added to the <i>list</i> file under <i>~/.scruf/</i>.
     */
    public Initialization(String[] dirs) {
	String home = System.getenv("HOME");
	System.out.println("Home directory: "+home);
	scruf = new File(home+"/.scruf");
	list = new File(scruf,"list");
	// create directory if it does not exists.
	if(!scruf.exists() || !scruf.isDirectory()) {
	    scruf.mkdir();
	    try {
		list.createNewFile();
	    }catch(IOException e) {
		System.err.println("Error while creating"+
				   list.getAbsolutePath()+ 
				   "file.");
	    }
	}
	for(String dir:dirs)
	    new WriteFile(list,dir).append();
    }
    public File getListFile() {
	return list; 
    }
}
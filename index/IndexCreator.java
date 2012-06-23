package scruf.index;

import java.io.*;
import java.util.regex.*;
import scruf.io.*;
import scruf.conversion.*;

public class IndexCreator {
    private File directory;
    private File index;
    private StringBuilder indexContent;
    // set to true, if index file is modified.
    boolean modified = false;
    public IndexCreator(File directory) {
	this.directory = directory;
	index = new File(directory,"index");
	indexContent = new StringBuilder();
	if(index.exists()) {
	    indexContent.append(new ReadFile(index).
				getContent());
	}
    }
    public void add(File file) {
	String fileName = file.getName();
	if(shouldAdd(fileName)) {
	    System.out.println("New Entry: "+fileName);
	    indexContent.append("[[./");
	    indexContent.append(fileName+".html");
	    indexContent.append("|");
	    indexContent.append(PresentFile.name);
	    indexContent.append("]]\n");
	    modified=true;
	}
    }
    public boolean write() {
	if(modified)
	    new WriteFile(index,indexContent.toString()).write();
	return modified;
    }
    public File indexFile() {
	return index;
    }
    private  boolean shouldAdd(String fileName) {
	String regex = ".*"+fileName+".*";
	// checks if fileName is already there in index.
	boolean check1 = !(Pattern.compile(regex).
			matcher(indexContent.toString()).find());
	// checks if fileName is index itself.
	boolean check2 = !(Pattern.matches(fileName,"index"));
	boolean add = (check1 && check2);
	return add;
    }
}
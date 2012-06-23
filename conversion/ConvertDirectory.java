package scruf.conversion;

import java.io.*;
import scruf.index.*;

public class ConvertDirectory {
    private ConvertFile html;
    private CanConvert canConvert;
    private boolean can;
    public ConvertDirectory() {
	html = new ConvertFile();
	canConvert = new CanConvert();
    }
    public void convert(File directory) {
	if(!directory.isDirectory()) {
	    System.out.println(directory+" is not a Directory."+
			       " No conversion done on.");
	    return;
	}
	// index creator for the present directory.
	IndexCreator index = new IndexCreator(directory);
	// iterate through the directory.
	for(File file:directory.listFiles(new FileSieve())) {
	    if(file.isFile()) {
		can = canConvert.check(file);
		if(can) {
		    html.convert(file);
		    index.add(file);
		}
	    }
	    else if(file.isDirectory()) {
		this.convert(file);
	    }
	}
	
	boolean modified = index.write();
	if(modified)
	    html.convert(index.indexFile());
    }
}
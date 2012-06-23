package scruf.io;

import java.io.*;

public class WriteFile {
    private File outputFile;
    private String content;
    public WriteFile(File outputFile, String content) {
	this.outputFile = outputFile.getAbsoluteFile();
	this.content = content;
    }
    public void write() {
	try {
	    System.out.println("Writing..."+outputFile.getName());
	    BufferedWriter bwriter = new BufferedWriter
		(new FileWriter(outputFile));
	    // write content to file.
	    bwriter.write(content);
	    bwriter.close();
	}catch(IOException e) {
	    System.err.println("Error occured while writing"+
			       " file : "+outputFile);
	}
    }
    public void append() {
	StringBuilder sbuilder = new StringBuilder(
						   new ReadFile(outputFile).getContent());
	sbuilder.append(content);
	// new content
	content = sbuilder.toString();
	write();
    }
}
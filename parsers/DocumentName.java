package scruf.parsers;

import java.io.*;
import scruf.io.*;

public class DocumentName implements Parser {
    public String parse(String fileContent) {
	BufferedReader read = 
	    new BufferedReader(new StringReader(fileContent));
	try {
	    PresentFile.name = read.readLine();
	}catch(IOException e) {
	    System.err.println("Error reading string "+e);
	}
	fileContent = new NullIt().nullIt(fileContent,PresentFile.name);
	return fileContent;
    }
}
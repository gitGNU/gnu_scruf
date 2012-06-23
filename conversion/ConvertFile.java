package scruf.conversion;

import scruf.io.*;
import scruf.parsers.*;
import java.util.*;
import java.io.*;

public class ConvertFile {
    private List<Parser> parsers;
    private ReadFile readFile;
    public ConvertFile() {
	parsers = new ParserList().list();
    }
    public void convert(File file) {
	/**
	 * footer is optional, so it is null
	 * by default.
	 */
	PresentFile.footer = null;
	/**
	 * takes the present file reference
	 * for use outside this method.
	 */
	PresentFile.file = file;
	readFile = new ReadFile(file);
	String fileContent = readFile.getContent();
	// start conversion.
	for(Parser p:parsers) {
	    fileContent = p.parse(fileContent);
	}

	// Write converted file to respective html file.
	File outputFile = new File(file.getAbsolutePath()+".html");
	new WriteFile(outputFile,fileContent).write();
    }
}

package scruf.parsers;

import java.io.*;
import scruf.io.*;

public class BackButton implements Parser {
    // this method doesn't modify the filContent.
    public String parse(String fileContent) {
	StringBuilder button = new StringBuilder();
	button.append("\n<div class=\"back\">\n");
	button.append("<a href=\"");
	if(PresentFile.file.getName().equals("index")) {
	    button.append("../\"> home ");
	}else {
	    button.append("./\"> back ");
	}
	button.append("</a>\n");
	button.append("</div>");
	PresentFile.backButton = button.toString();
	return fileContent;
    }
}
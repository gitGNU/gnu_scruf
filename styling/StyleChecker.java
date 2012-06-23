package scruf.styling;

import scruf.io.*;
import java.io.*;
public class StyleChecker {
    private File styleSheet;
    private File curDir;
    private String styleContent;

    public void resolve(File curDir) {
	this.curDir = curDir.getAbsoluteFile();
	styleSheet = new File(curDir,"style.css");
	// if style shee doesn't exists, copy default sheet
	// to the directory.
	if(!styleSheet.exists()) {
	    styleContent = new ReadFile(new File("scruf/styling/style.css")).getContent();
	    new WriteFile(styleSheet,styleContent).write();	    
	}
    }
}
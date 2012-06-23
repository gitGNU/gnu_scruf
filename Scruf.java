package scruf;

import java.io.*;
import scruf.io.*;
import scruf.conversion.*;
import scruf.styling.*;
import scruf.index.*;

public class Scruf {
    public static void main(String[] args) {
	Initialization init = new Initialization(args);
	File list = init.getListFile();
	ReadFile readList = new ReadFile(list);
	String dirs[] = readList.split("\n");
	File directory;
	ConvertDirectory html = new ConvertDirectory();
	StyleChecker styleSheet = new StyleChecker();
	for(String dir:dirs) {
	    // if empty string, do nothing.
	    if(dir.length()==0) {
		continue;
	    }
	    directory = new File(dir).getAbsoluteFile();
	    styleSheet.resolve(directory);
	    html.convert(directory);
	}
    }
}
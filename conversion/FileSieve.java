package scruf.conversion;

import java.io.*;
import java.util.regex.*;

public class FileSieve implements FileFilter {
    // this method return true, if this file doesn't represent
    // a html file.
    public boolean accept(File pathname) {
	Pattern pattern = Pattern.compile("(.+\\.(html|png|jpg|css|tar))|(.+?\\~)|(index)|(\\..+)");
	Matcher matcher = pattern.matcher(pathname.getName());
	boolean bool = matcher.find();
	return !bool;
    }    
}
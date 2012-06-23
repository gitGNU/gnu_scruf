package scruf.conversion;

import java.io.*;

public class CanConvert {
    private File htmlFile;
    private long modified1, modified2;
    public boolean check(File file) {
	htmlFile = new File(file.getAbsolutePath()+".html");
	modified1 = file.lastModified();
	modified2 = htmlFile.lastModified();
	if(modified1 > modified2) {
	    // should convert.
	    return true;
	}else {
	    // should not convert.
	    return false;
	}
    }
}
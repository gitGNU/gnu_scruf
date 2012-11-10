package scruf.conversion;

import java.io.*;
import java.util.regex.*;
import scruf.status.*;

public class CreateHtmlFile {
	private Pattern pattern = Pattern.compile("(.+?\\.)scruffy");
	private Matcher matcher;
	public File create() {
		File htmlFile=null;
		matcher = pattern.matcher(PresentFile.file.getName());
		if(matcher.find()) {
			htmlFile = new File(PresentFile.file.getParentFile(),
								matcher.group(1)+"html");
		}else {
			System.err.println("ERROR: something wrong with scruf: unable to create html file"+
							   " for "+PresentFile.file.getName());
		}
		return htmlFile;
	}
}
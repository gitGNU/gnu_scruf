package scruf.parsers;

import java.util.regex.*;
import scruf.io.*;
public class Footer implements Parser {
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("\\-{70}\\n(.+)\\n\\-{70}");
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	while(matcher.find()) {
	    PresentFile.footer = "<footer>"+matcher.group(1)+"</footer>";
	    fileContent = new NullIt().nullIt(fileContent,matcher.group());
	}

	return fileContent;
    }
}
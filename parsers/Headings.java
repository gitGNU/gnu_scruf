package scruf.parsers;

import java.util.regex.*;

public class Headings implements Parser {
    public String parse(String fileContent) {
	int size;
	Pattern pattern = Pattern.compile("(\\={10,})\\n(.+?)\\n(\\1)");
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	while(matcher.find()) {
	    size = (matcher.group(1).length())/10;
	    matcher.appendReplacement(sbuffer,
				      "<h"+size+"> "+
				      "$2 "+
				      "</h"+size+">");
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}

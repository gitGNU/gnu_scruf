package scruf.parsers;

import java.util.regex.*;

public class LineBreak implements Parser {
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("\\n");
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	while(matcher.find()) {
	    matcher.appendReplacement(sbuffer,"<br />");
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}
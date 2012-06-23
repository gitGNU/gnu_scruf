package scruf.parsers;

import java.util.regex.*;

public class Links implements Parser {
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("\\[\\[(.+?)\\|(.+?)\\]\\]");
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	String replacementString = "<a href=\"$1\" target=\"_blank\">$2</a>";
	while(matcher.find()) {
	    matcher.appendReplacement(sbuffer,replacementString);
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}
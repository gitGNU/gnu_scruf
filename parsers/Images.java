package scruf.parsers;

import java.util.regex.*;

public class Images implements Parser {
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("\\{\\{(.+\\.(png|jpg))\\|(.+)\\}\\}");
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	StringBuilder replacementString = new StringBuilder();
	replacementString.append("<img src=\"$1\" alt=\"$3\" title=\"$3\"/>");
	while(matcher.find()) {
	    matcher.appendReplacement(sbuffer,replacementString.toString());
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}
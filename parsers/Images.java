package scruf.parsers;

import java.util.regex.*;

public class Images implements Parser {
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("\\{\\{(.+\\.(png|jpg))\\|(.+)\\}\\}");
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	StringBuilder replacementString = new StringBuilder();
	replacementString.append("<div class=\"img\">");
	replacementString.append("<image src=\"$1\" alt=\"$3\" />");
	replacementString.append("</div>");
	while(matcher.find()) {
	    matcher.appendReplacement(sbuffer,replacementString.toString());
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}
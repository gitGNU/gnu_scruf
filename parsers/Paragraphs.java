package scruf.parsers;

import java.util.regex.*;

public class Paragraphs implements Parser {

    public String parse(String fileContent) {

	Pattern pattern = Pattern.compile("((^.+$)\\n)+",Pattern.MULTILINE);
	Matcher matcher = pattern.matcher(fileContent);
	StringBuilder sbuilder = new StringBuilder();
	while(matcher.find()) {
	    sbuilder.append("\n<p>\n");
	    sbuilder.append(matcher.group());
	    sbuilder.append("</p>\n");
	}
	return sbuilder.toString();
    }
}
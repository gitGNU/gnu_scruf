package scruf.parsers;

import java.util.regex.*;

public class Links implements Parser {
    // set of strings to build the html link
    private String openTag = "<a href=\"$1\">";
    private String closeTag = "</a>";
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("\\[\\[(.+?)(\\|(.+?))?\\]\\]", Pattern.DOTALL);
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	StringBuilder replacementString = new StringBuilder();
	while(matcher.find()) {
	    // delete text already there.
	    replacementString.delete(0,replacementString.length());
	    // start the <a> tag.
	    replacementString.append(openTag);
	    // add link name, if given
	    if(matcher.group(3)!=null) {
		replacementString.append(matcher.group(3));
	    }
	    else {
		replacementString.append(matcher.group(1));
	    }
	    // close the <a> tag
	    replacementString.append(closeTag);
	    matcher.appendReplacement(sbuffer,replacementString.toString());
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}
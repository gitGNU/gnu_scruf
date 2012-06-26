package scruf.parsers;

import java.util.regex.*;

public class Images implements Parser {
    // set of strings to build the img tag
    private String openTag = "<img src=\"$1\" alt=\"$";
    private String closeTag = " />";
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("\\{\\{(.+?\\.(png|jpg))(\\|(.+?))?\\}\\}", Pattern.DOTALL);
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	StringBuilder replacementString =new StringBuilder();
	while(matcher.find()) {
	    // empty the builder.
	    replacementString.delete(0,replacementString.length());
	    // add the _img_ tag
	    replacementString.append(openTag);
	    // if the "title" is given add to the _img_ tag.
	    if(matcher.group(3)!=null) {
		replacementString.append("4\" title=\"$4\"");
	    }
	    else {
		replacementString.append("1\"");
	    }
	    // close the _img_ tag.
	    replacementString.append(closeTag);
	    matcher.appendReplacement(sbuffer,replacementString.toString());
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}
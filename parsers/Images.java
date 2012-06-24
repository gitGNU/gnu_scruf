package scruf.parsers;

import java.util.regex.*;

public class Images implements Parser {
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("\\{\\{(.+\\.(png|jpg))(\\|(.+))?\\}\\}");
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	StringBuilder replacementString =new StringBuilder();
	while(matcher.find()) {
	    // empty the builder.
	    replacementString.delete(0,replacementString.length());
	    // add the _img_ tag
	    replacementString.append("<img src=\"$1\"");
	    // if the "title" is given add to the _img_ tag.
	    if(matcher.group(3)!=null) {
		replacementString.append("alt=\"$4\" title=\"$4\"");
	    }
	    // close the _img_ tag.
	    replacementString.append(" />");
	    matcher.appendReplacement(sbuffer,replacementString.toString());
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}
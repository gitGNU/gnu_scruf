package scruf.parsers;

import java.util.regex.*;

public class Paragraphs implements Parser {
    private String paragraph = "<p>\n $5</p>\n";
    public String parse(String fileContent) {
	/**
	 * This regex contains two parts seperated by a '|';
	 * the first part is regex for a html Heading (See Heading.java)
	 * and the second part is the regex for a paragraph. For an input,
	 * if the first part of the regex is matched, then it is necessarily
	 * a Heading, so, we ignore it; but if the second part of the regex is
	 * matched for an input, then it is a paragraph, so, we put the necessary
	 * tags in place.
	 */
	Pattern pattern = Pattern.compile("((\\={10,})\\n(.+?)\\n(\\2))|((^.+$\\n)+)",Pattern.MULTILINE);
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	while(matcher.find()) {
	    // group 1 contains the regex for the Heading, so
	    // if that is null, then it means that we have actually
	    // found a paragraph.
	    if(matcher.group(1)==null) 
		matcher.appendReplacement(sbuffer,paragraph);
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}
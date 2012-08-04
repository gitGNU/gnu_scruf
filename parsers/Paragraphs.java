package scruf.parsers;

import java.util.regex.*;

public class Paragraphs implements Parser {
    private String paragraph = "<p>\n$0</p>\n";
    public String parse(String fileContent) {
	/**
	 * This regex contains two parts seperated by a '|'; the first
	 * part is regex for a html Heading (See Heading.java) and the
	 * second part is the regex for a paragraph. For an input, if
	 * the first part of the regex is matched, then it is
	 * necessarily a Heading, so, we ignore it; but if the second
	 * part of the regex is matched for an input, then it is a
	 * paragraph, so, we put the necessary tags in place.
	 */
	Pattern pattern = Pattern.compile("(^.+$\\n)+",Pattern.MULTILINE);
	/**
	 * This htmlTagPattern has a regex to deduct a html tag.
	 */
	Pattern htmlTagPattern = Pattern.compile("^\\<.+?\\>\\n");
	Matcher matcher = pattern.matcher(fileContent);
	Matcher htmlTag;
	StringBuffer sbuffer = new StringBuffer();
	while(matcher.find()) {
	    /**
	     * give the paragraph that is identified htmlTagPattern
	     * and see whether the "paragraph" that is actually
	     * deducted is some other html block like <h1> (heading)
	     * or <blockquote>, etcetera.  "matcher.find()" has
	     * actually found a html block then we don't need to do
	     * the conversion.
	     */
	    htmlTag = htmlTagPattern.matcher(matcher.group());
	    /**
	     * if "matcher.find()" _has not_ deducted a html block,
	     * then we do the conversion.
	     */
	    if(!htmlTag.find()) {
		matcher.appendReplacement(sbuffer,paragraph);
	    }else {
		System.out.println("MATCH$"+matcher.group()+" MATCH$$");
	    }
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}
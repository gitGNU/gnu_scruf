package scruf.parsers;

import java.util.regex.*;

public class CodeBlocks implements Parser {
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("(\\#\\#\\#)\\n*(.+?)\\n*(\\1)",
					  Pattern.DOTALL);
	Matcher matcher = pattern.matcher(fileContent);
	LineBreak lbreak = new LineBreak();
	StringBuffer sbuffer = new StringBuffer();
	StringBuilder replacement = new StringBuilder();
	while(matcher.find()) {
	    replacement.delete(0,replacement.length());
	    replacement.append("<div class=\\\"code\\\">");
	    replacement.append(quote(lbreak.parse(matcher.group(2))));
	    replacement.append("</div>");
	    matcher.appendReplacement(sbuffer,replacement.toString());
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
    /**
     * this method quotes special characters -- `\` &
     * `$`, in the replacment string as they have 
     * special meaning.
     * 
     */
    private String quote(String string) {
	Pattern pattern = Pattern.compile("\\$|\\\\");
	Matcher matcher = pattern.matcher(string);
	StringBuffer sbuffer = new StringBuffer();
	String rep;
	while(matcher.find()) {
	    rep = "\\\\\\"+matcher.group();
	    matcher.appendReplacement(sbuffer,rep);

	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}
package scruf.parsers;

import java.util.regex.*;
import java.util.*;
import java.io.*;
import scruf.io.*;

public class WordDecoration implements Parser {
    private HashMap<String, String> tagMap;
    public WordDecoration() {
	tagMap = new HashMap<String, String>();
	tagMap.put("''","<i>$6</i>");
	tagMap.put("__","<u>$6</u>");
	tagMap.put("'''","<b>$6</b>");
	tagMap.put("%%%","<blockquote>$6</blockquote>");
    }
    public String parse(String fileContent) {
	Pattern pattern = 
	    Pattern.compile("((\\'\\'\\')|(\\_\\_)|(\\'\\')|(\\%\\%\\%))(.+?)((\\2)|(\\3)|(\\4)|(\\5))",
			    Pattern.DOTALL);
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	String replacement;
	while(matcher.find()) {
	    replacement = tagMap.get(matcher.group(1));
	    matcher.appendReplacement(sbuffer,replacement);
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}
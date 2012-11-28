package scruf.parsers;

import java.util.regex.*;
import scruf.status.*;

/**
 * this class deals with searching the 'scruffy' marked-up document
 * for meta-tag related things.
 */ 

public class MetaParser implements Parser {
	private Pattern pattern;
	private Matcher matcher;
	private NullIt nullIt;
	public MetaParser() {
		pattern = Pattern.compile("^meta\\-(.+?)\\:(.+)",
								  Pattern.MULTILINE);
		nullIt = new NullIt();
		
	}
	public String parse(String fileContent) {
		String value;
		matcher = pattern.matcher(fileContent);
		while(matcher.find()) {
			value =  matcher.group(2);
			if(matcher.group(1).equals("author")) {
				PresentFile.author = value;
			}
			else if(matcher.group(1).equals("title")) {
				PresentFile.name = value;
			} 
			// remove the found 'meta' markup to an empty string.
			fileContent = nullIt.nullIt(fileContent,matcher.group());
			// reset the matcher with the new file Content.
			matcher.reset(fileContent);
		}
		return fileContent;
	}
}
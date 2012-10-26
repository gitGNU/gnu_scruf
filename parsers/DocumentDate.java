package scruf.parsers;

import java.util.regex.*;

public class DocumentDate implements Parser {
	public String parse(String fileContent) {
		String timeTag = "<div class=\"time\"> $2 </div> \n";
		StringBuffer sbuffer = new StringBuffer();
		Pattern pattern = Pattern.compile("(\\$\\$\\$)(.+?)(\\1)");
		Matcher matcher = pattern.matcher(fileContent);
		while(matcher.find()) {
			matcher.appendReplacement(sbuffer,timeTag);
		}
		matcher.appendTail(sbuffer);
		return sbuffer.toString();
	}
}
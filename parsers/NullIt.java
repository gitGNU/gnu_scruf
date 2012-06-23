package scruf.parsers;

import java.util.regex.*;

public class NullIt {
    public String nullIt(String content,String regex) {
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(content);
	while(matcher.find()) {
	    content = matcher.replaceFirst("");
	    break;
	}
	return content;
    }
}
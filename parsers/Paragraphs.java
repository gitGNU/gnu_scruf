/*+
 *   Copyright 2012, 2013 rsiddharth <rsiddharth@ninthfloor.org>
 * 
 *   This file is part of Scruf.
 *
 *   Scruf is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package scruf.parsers;

import java.util.regex.*;
import scruf.status.*;
public class Paragraphs implements Parser {
    private String paragraph = "<p>$0</p>";
	private DetectHTMLTag detectTag;
    public String parse(String fileContent) {
	detectTag = new DetectHTMLTag();
	/**
	 * The pattern for matching paragraphs
	 */
	Pattern pattern = Pattern.compile("(^.+$\\n)+",Pattern.MULTILINE);
	/**
	 * This htmlTagPattern has a regex to deduct a html tag.
	 */
	Pattern htmlTagPattern = Pattern.compile("^\\<.+?\\>(\\n?)");
	Matcher matcher = pattern.matcher(fileContent);
	Matcher htmlTag;
	StringBuffer sbuffer = new StringBuffer();
	System.out.println("Paragraph BANG.");
	while(matcher.find()) {
	    htmlTag = htmlTagPattern.matcher(matcher.group());

		String subString = fileContent.substring(matcher.start());
		boolean htmlTagP = htmlTag.find();
		boolean htmlInScruffy = detectTag.isHtmlInScruffy(subString);
		boolean pWrap = !htmlTagP && !htmlInScruffy;
	    if(pWrap) {
			matcher.appendReplacement(sbuffer,paragraph);
	    }
	}
	matcher.appendTail(sbuffer);
	System.out.println("Paragraph BANG EXIT.");
	return sbuffer.toString();
    }
}

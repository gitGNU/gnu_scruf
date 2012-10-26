/*+
 *   Copyright 2012 rsiddharth
 *   Email: <rsiddharth@ninthfloor.org> 
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
	Pattern htmlTagPattern = Pattern.compile("^\\<.+?\\>(\\n?)");
	Matcher matcher = pattern.matcher(fileContent);
	Matcher htmlTag;
	StringBuffer sbuffer = new StringBuffer();
	while(matcher.find()) {
	    /**
	     * give the paragraph that is identified htmlTagPattern
	     * and see whether the "paragraph" that is actually
	     * deducted is some other html block like <h1> (heading)
	     * or <blockquote>, etcetera. If "matcher.find()" has
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
	    }
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}

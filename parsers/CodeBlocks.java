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

public class CodeBlocks implements Parser {
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("(\\#\\#\\#)(.+?)(\\1)",
					  Pattern.DOTALL);
	Matcher matcher = pattern.matcher(fileContent);
	LineBreak lbreak = new LineBreak();
	StringBuffer sbuffer = new StringBuffer();
	StringBuilder replacement = new StringBuilder();
	while(matcher.find()) {
	    replacement.delete(0,replacement.length());
	    replacement.append("<div class=\"code\">");
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

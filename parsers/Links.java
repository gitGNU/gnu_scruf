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

public class Links implements Parser {
    // set of strings to build the html link
    private String openTag = "<a href=\"$1\">";
    private String closeTag = "</a>";
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("\\[\\[(.+?)(\\|(.+?))?\\]\\]", Pattern.DOTALL);
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	StringBuilder replacementString = new StringBuilder();
	while(matcher.find()) {
	    // delete text already there.
	    replacementString.delete(0,replacementString.length());
	    // start the <a> tag.
	    replacementString.append(openTag);
	    // add link name, if given
	    if(matcher.group(3)!=null) {
		replacementString.append(matcher.group(3));
	    }
	    else {
		replacementString.append(matcher.group(1));
	    }
	    // close the <a> tag
	    replacementString.append(closeTag);
	    matcher.appendReplacement(sbuffer,replacementString.toString());
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}

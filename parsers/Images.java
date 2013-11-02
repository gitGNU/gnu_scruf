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

public class Images implements Parser {
    // set of strings to build the img tag
    private String openTag = "<img src=\"$1\" alt=\" \\[ $";
    private String closeTag = " />";
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("\\{\\{(.+?\\.(png|jp[e]?g|svg))(\\|(.+?))?\\}\\}", Pattern.DOTALL);
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	StringBuilder replacementString =new StringBuilder();
	while(matcher.find()) {
	    // empty the builder.
	    replacementString.delete(0,replacementString.length());
	    // add the _img_ tag
	    replacementString.append(openTag);
	    // if the "title" is given add to the _img_ tag.
	    if(matcher.group(3)!=null) {
		replacementString.append("4 \\]\" title=\"$4\"");
	    }
	    else {
		replacementString.append("1 \\]\"");
	    }
	    // close the _img_ tag.
	    replacementString.append(closeTag);
	    matcher.appendReplacement(sbuffer,replacementString.toString());
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}

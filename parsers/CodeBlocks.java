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
import java.util.*;

public class CodeBlocks implements Parser {
	private SymbolMap symbolMap = new SymbolMap();
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("(\\#\\#\\#)(\\n)(.+?)(\\n)(\\1)",
					  Pattern.DOTALL);
	Matcher matcher = pattern.matcher(fileContent);
	LineBreak lbreak = new LineBreak();
	StringBuffer sbuffer = new StringBuffer();
	StringBuilder replacement = new StringBuilder();
	while(matcher.find()) {
	    replacement.delete(0,replacement.length());
	    replacement.append("<pre><code>");
		replacement.append(lbreak.parse(symbolMap.quote(matcher.group(3))));
	    replacement.append("</code></pre>");
	    matcher.appendReplacement(sbuffer,replacement.toString());
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}

/**

Special Case:
   
###

.
{
###
oced
###
.
###
code
###
.
###
fizz
###
}
.
###


*/

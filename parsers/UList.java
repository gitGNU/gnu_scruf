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

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UList implements Parser {
	private Pattern pattern = Pattern.compile("^\\*\\*( )+((.+$\\n)+)",Pattern.MULTILINE);
	private String list = "<li>\n$1$2</li>";
	/**
	 * This htmlTagPattern has a regex to deduct a html tag.
	 */
	private Pattern htmlTagPattern = Pattern.compile("^\\<.+?\\>(\\n?)");
	private Matcher matcher;
	private Matcher htmlTag;
    public String parse(String fileContent) {
		StringBuffer sbuffer = new StringBuffer();
		matcher = pattern.matcher(fileContent);
		
		int lastEnd=0;
		while(matcher.find()) {
			int diff = matcher.start() - lastEnd;

			if(lastEnd == 0) {
				// first list found
				matcher.appendReplacement(sbuffer, "<ul>\n " + list);
			}
			else if(diff>1) {
				// means, we are at a new list now.
				// so, got close the ol' one.
				sbuffer.append("\n</ul>\n");
				
				// open the new list.
				matcher.appendReplacement(sbuffer, "<ul>\n " + list);
			}
			else {
				matcher.appendReplacement(sbuffer, list);
			}
			lastEnd = matcher.end();
			
		}
		// close the last found list
		if(lastEnd !=0) // meaning a list was found.
			sbuffer.append("\n</ul>\n");
		matcher.appendTail(sbuffer);

		return sbuffer.toString();
    }

}

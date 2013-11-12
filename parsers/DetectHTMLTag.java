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

import java.util.*;
import java.util.regex.*;

public class DetectHTMLTag {
	private List<String> closingHtmlTags = new ArrayList<String>();
	private Pattern htmlTagPattern = Pattern.compile("^\\<((\\w+?).*?)\\>.*?(\\<\\/\\2\\>)",
													 Pattern.DOTALL);
	private boolean insideHtmlBlock = false;
	public boolean isHtmlTag(String text) {
		Matcher htmlTagMatcher = htmlTagPattern.matcher(text);

		if(htmlTagMatcher.find()) {
			closingHtmlTags.add(htmlTagMatcher.group(3));
			insideHtmlBlock = true;
			return true;
		}else {
			return isClosingHtmlTag(text);
		}
	}

	private boolean isClosingHtmlTag(String text) {
		for(String closingTag: closingHtmlTags) {
			if(text.startsWith(closingTag)) {
				closingHtmlTags.remove(closingTag);
				return true;
			}
		}
		if(closingHtmlTags.isEmpty())
			insideHtmlBlock = false;
		return false;
	}

	public boolean insideHtmlBlock() {
		return insideHtmlBlock;
	}
}

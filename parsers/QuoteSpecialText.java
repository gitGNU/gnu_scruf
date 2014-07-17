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

import scruf.status.*;
import java.util.*;
import java.util.regex.*;

public class QuoteSpecialText implements Parser {
	private Map<String,String> qmap;
	private DetectHTMLTag detectTag;
	public QuoteSpecialText() {
		qmap = new HashMap<String,String>();
		qmap.put("&","&amp;");
		qmap.put("<","&lt;");
	}
	public String parse(String fileContent) {
		detectTag = new DetectHTMLTag();
		Pattern pattern = Pattern.compile("(\\&(\\w+|\\#\\d+)\\;)|(\\<)|(\\&)");
		Pattern loneHtmlPattern = Pattern.compile("^\\<\\w+? .*?\\/\\>", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(fileContent);
		StringBuffer sbuffer = new StringBuffer();
		while(matcher.find()) {
			if(matcher.group(1)!=null) {
				// found HTML code, don't do anything
				// continue.
				continue;
			}
			String subString = fileContent.substring(matcher.start());
			boolean quote = !detectTag.isHtmlTag(subString) &&
				!detectTag.insideHtmlBlock() &&
				!loneHtmlPattern.matcher(subString).find();
			if(quote) {
				matcher.appendReplacement(sbuffer,
										  qmap.get(matcher.group()));
			}else {
				System.out.println("Not Escaping" + subString.split(">")[0] + ">");
			}
		}
		matcher.appendTail(sbuffer);
		System.out.println("BANGA BANG!");
		return sbuffer.toString();
	}
}

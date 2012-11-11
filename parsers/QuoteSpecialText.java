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

import java.util.*;
import java.util.regex.*;

public class QuoteSpecialText implements Parser {
	Map<String,String> qmap;
	public QuoteSpecialText() {
		qmap = new HashMap<String,String>();
		qmap.put("&","&amp;");
		qmap.put("<","&lt;");
		qmap.put(">","&gt;");
	}
	public String parse(String fileContent) {
		Pattern pattern = Pattern.compile("(\\&\\#35\\;)|(\\&)|(\\<)|(\\>)");
		Matcher matcher = pattern.matcher(fileContent);
		StringBuffer sbuffer = new StringBuffer();
		while(matcher.find() && matcher.group(1)==null) {
			matcher.appendReplacement(sbuffer,
									  qmap.get(matcher.group()));
		}
		matcher.appendTail(sbuffer);
		return sbuffer.toString();
	}
}

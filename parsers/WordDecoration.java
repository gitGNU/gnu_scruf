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
import java.util.*;
import java.io.*;
import scruf.io.*;

public class WordDecoration implements Parser {
    private HashMap<String, String> tagMap;
    public WordDecoration() {
	tagMap = new HashMap<String, String>();
	tagMap.put("''","<i>$7</i>");
	tagMap.put("__","<u>$7</u>");
	tagMap.put("'''","<b>$7</b>");
	tagMap.put("%%%","<blockquote>$7</blockquote>");
	tagMap.put("`","<span class=\\\"monospace\\\">$7</span>");
    }
    public String parse(String fileContent) {
	Pattern pattern = 
	    Pattern.compile("((\\'\\'\\')|(\\_\\_)|(\\'\\')|(\\%\\%\\%)|(\\`))(.+?)((\\2)|(\\3)|(\\4)|(\\5)|(\\6))",
			    Pattern.DOTALL);
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	String replacement;
	while(matcher.find()) {
	    replacement = tagMap.get(matcher.group(1));
	    matcher.appendReplacement(sbuffer,replacement);
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}

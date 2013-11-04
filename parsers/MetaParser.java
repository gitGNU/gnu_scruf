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
import scruf.status.*;

/**
 * this class deals with searching the 'scruffy' marked-up document
 * for meta-tag related things.
 */ 

public class MetaParser implements Parser {
	private Pattern pattern;
	private Matcher matcher;
	private NullIt nullIt;
	public MetaParser() {
		pattern = Pattern.compile("^meta\\-(.+?)\\:(.+)",
								  Pattern.MULTILINE);
		nullIt = new NullIt();
		
	}
	public String parse(String fileContent) {
		String value;
		matcher = pattern.matcher(fileContent);
		while(matcher.find()) {
			value =  matcher.group(2);
			if(matcher.group(1).equals("author")) {
				PresentFile.author = value;
			}
			else if(matcher.group(1).equals("title")) {
				PresentFile.name = value;
			} 
			// remove the found 'meta' markup to an empty string.
			fileContent = nullIt.nullIt(fileContent,matcher.group());
			// reset the matcher with the new file Content.
			matcher.reset(fileContent);
		}
		return fileContent;
	}
}

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
import scruf.io.*;
public class Footer implements Parser {
    public String parse(String fileContent) {
	Pattern pattern = Pattern.compile("\\-{70}\\n(.+)\\n\\-{70}");
	Matcher matcher = pattern.matcher(fileContent);
	StringBuffer sbuffer = new StringBuffer();
	while(matcher.find()) {
	    PresentFile.footer = "\n<footer>"+matcher.group(1)+"</footer>\n";
	    fileContent = new NullIt().nullIt(fileContent,matcher.group());
	}

	return fileContent;
    }
}

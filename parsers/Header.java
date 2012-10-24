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

import scruf.io.*;

public class Header implements Parser {
    
    private String fileContent;
    private StringBuilder sbuilder;
    
    public String parse(String fileContent) {

	sbuilder = new StringBuilder();

	// Embed necessay headers.
	sbuilder.append("<!DOCTYPE html> \n");
	sbuilder.append("<head> \n");
	sbuilder.append("<meta charset=\"UTF-8\">\n");
	sbuilder.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /> \n");
	sbuilder.append("<title>");
	sbuilder.append(PresentFile.name);
	sbuilder.append("</title>");
	sbuilder.append("</head> \n");
	sbuilder.append("<body> \n");
	// File Content goes inside <article> tag
	sbuilder.append("<article>\n");
	// insert File Content.
	sbuilder.append(fileContent);
	// add footer if footer is available.
	if(PresentFile.footer!=null) {
	    sbuilder.append(PresentFile.footer);
	}
	// insert back button.
	sbuilder.append(PresentFile.backButton);
	sbuilder.append("</article>\n");
	// add "powered by scruf" at bottom of page.
	sbuilder.append("\n<div class=\"scruf\">\n");
	sbuilder.append("powered by scruf");
	sbuilder.append("\n</div>\n");
	// Close body tag
	sbuilder.append("\n</body>\n");
	sbuilder.append("</html>\n");

	return sbuilder.toString();
    }

}

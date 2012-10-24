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

import java.io.*;
import scruf.io.*;

public class BackButton implements Parser {
    // this method doesn't modify the filContent.
    public String parse(String fileContent) {
	StringBuilder button = new StringBuilder();
	button.append("\n<div class=\"back\">\n");
	button.append("<a href=\"");
	if(PresentFile.file.getName().equals("index")) {
	    button.append("../\"> home ");
	}else {
	    button.append("./\"> back ");
	}
	button.append("</a>\n");
	button.append("</div>\n");
	PresentFile.backButton = button.toString();
	return fileContent;
    }
}

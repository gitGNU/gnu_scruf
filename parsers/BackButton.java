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

import java.io.*;
import scruf.io.*;
import scruf.status.*;

public class BackButton implements Parser {
    // this method doesn't modify the filContent.
    public String parse(String fileContent) {
		StringBuilder fileBuilder = new StringBuilder(fileContent);
		String fileName = PresentFile.file.getName();
		/**
		 * Back button is added only if the present directory being
		 * parsed is not 'root'.
		 */
		if(DirectoryInfo.level!=0 || !(fileName.equals("index.scruffy"))) {
			fileBuilder.append("\n<div class=\"back\">\n");
			fileBuilder.append("<a href=\"");
			if(fileName.equals("index.scruffy")) {
				fileBuilder.append("../\"> back ");
			}else {
				fileBuilder.append("./\"> back ");
			}
			fileBuilder.append("</a>\n");
			fileBuilder.append("</div>\n");
		}
		return fileBuilder.toString();
    }
}

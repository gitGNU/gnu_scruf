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


package scruf.conversion;

import scruf.io.*;
import scruf.parsers.*;
import java.util.*;
import java.io.*;
import scruf.status.*;

public class ConvertFile {
    private List<Parser> parsers;
    private ReadFile readFile;
	private CreateHtmlFile htmlFile;
    public ConvertFile() {
		parsers = new ParserList().list();
		htmlFile = new CreateHtmlFile();
    }
    public void convert(File file) {
		/**
		 * takes the present file reference
		 * for use outside this method.
		 */
		PresentFile.file = file;
		PresentFile.name = null;
		PresentFile.author = null;
		readFile = new ReadFile(file);
		String fileContent = readFile.getContent();
		if(!fileContent.equals("")) {
			// start conversion.
			for(Parser p:parsers) {
				fileContent = p.parse(fileContent);
				
			}
		}
		// Write to corresponding html file.
		new WriteFile(htmlFile.create(),fileContent).write();
    }
}

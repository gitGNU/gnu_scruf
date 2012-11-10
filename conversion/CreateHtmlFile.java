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


package scruf.conversion;

import java.io.*;
import java.util.regex.*;
import scruf.status.*;

public class CreateHtmlFile {
	private Pattern pattern = Pattern.compile("(.+?\\.)scruffy");
	private Matcher matcher;
	private File htmlFile=null;
	/** 
	 *	This method uses PresentFile.file as the 'file' for
	 *  which a corresponding 'htmlFile' has to be created.
	 */
	public File create() {
		return create(PresentFile.file);
	}
	public File create(File file) {
		htmlFile=null;
		matcher = pattern.matcher(file.getName());
		if(matcher.find()) {
			htmlFile = new File(file.getParentFile(),
								matcher.group(1)+"html");
		}else {
			System.err.println("ERROR: something wrong with scruf: unable to create html file"+
							   " for "+PresentFile.file.getName());
		}
		return htmlFile;
	}
}

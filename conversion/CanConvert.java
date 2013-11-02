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

import java.io.*;

public class CanConvert {
    private File htmlFile;
	private CreateHtmlFile createHtml = new CreateHtmlFile();
    private long modified1, modified2;
    public boolean check(File file) {
		htmlFile =  createHtml.create(file);
	modified1 = file.lastModified();
	modified2 = htmlFile.lastModified();
	if(modified1 > modified2) {
	    // should convert.
	    return true;
	}else {
	    // should not convert.
	    return false;
	}
    }
}

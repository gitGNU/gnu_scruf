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


package scruf.styling;

import scruf.io.*;
import java.io.*;
public class StyleChecker {
    private File styleSheet;
    private File curDir;
    private String styleContent;

    public void resolve(File curDir) {
	this.curDir = curDir.getAbsoluteFile();
	styleSheet = new File(curDir,"style.css");
	// if style shee doesn't exists, copy default sheet
	// to the directory.
	if(!styleSheet.exists()) {
	    styleContent = new ReadFile(new File("scruf/styling/style.css")).getContent();
	    new WriteFile(styleSheet,styleContent).write();	    
	}
    }
}

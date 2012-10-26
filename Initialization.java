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


package scruf;

import java.io.*;
import scruf.io.*;

public class Initialization {
    // it is a directory.
    private File scruf;
    // it is a file.
    private File list;
    /**
     * @param dir this parameter is the name of the directory
     * whose files must be converted into html. This directory
     * will be added to the <i>list</i> file under <i>~/.scruf/</i>.
     */
    public Initialization(String[] dirs) {
	String home = System.getenv("HOME");
	System.out.println("Home directory: "+home);
	scruf = new File(home+"/.scruf");
	list = new File(scruf,"list");
	// create directory if it does not exists.
	if(!scruf.exists() || !scruf.isDirectory()) {
	    scruf.mkdir();
	    try {
		list.createNewFile();
	    }catch(IOException e) {
		System.err.println("Error while creating"+
				   list.getAbsolutePath()+ 
				   "file.");
	    }
	}
	for(String dir:dirs)
	    new WriteFile(list,dir).append();
    }
    public File getListFile() {
	return list; 
    }
}

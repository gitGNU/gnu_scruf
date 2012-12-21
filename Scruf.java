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
import scruf.conversion.*;
import scruf.styling.*;
import scruf.status.*;
import scruf.index.*;

public class Scruf {
    public static void main(String[] args) {
	Initialization init = new Initialization(args);
	File list = init.getListFile();
	ReadFile readList = new ReadFile(list);
	String dirs[] = readList.split("\n");
	File directory;
	ConvertDirectory html = new ConvertDirectory();
	for(String dir:dirs) {
	    // if empty string, do nothing.
	    if(dir.length()==0) {
		continue;
	    }
	    directory = new File(dir).getAbsoluteFile();
		DirectoryInfo.level=0;
	    html.convert(directory);
	}
    }
}

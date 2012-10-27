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
import scruf.index.*;

public class ConvertDirectory {
    private ConvertFile html;
    private CanConvert canConvert;
    private boolean can;
    public ConvertDirectory() {
	html = new ConvertFile();
	canConvert = new CanConvert();
    }
    public void convert(File directory) {
	if(!directory.isDirectory()) {
	    System.out.println(directory+" is not a Directory."+
			       " No conversion done on.");
	    return;
	}
	// index creator for the present directory.
	IndexCreator index = new IndexCreator(directory);
	// iterate through the directory.
	for(File file:directory.listFiles(new FileSieve())) {
	    if(file.isFile()) {
		can = canConvert.check(file);
		if(can) {
		    System.out.println("Converting..."+file.getName());
		    html.convert(file);
		    index.add(file);
		}
	    }
	    else if(file.isDirectory()) {
		this.convert(file);
	    }
	}
	
	boolean convertIndex = (index.shouldConvert() || 
							canConvert.check(index.indexFile()));
	if(convertIndex)
	    html.convert(index.indexFile());
    }
}

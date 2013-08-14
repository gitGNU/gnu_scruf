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
import scruf.status.*;
import scruf.conversion.ignore.*;
import scruf.styling.*;

public class ConvertDirectory {
    private ConvertFile html;
    private CanConvert canConvert;
    private boolean can;
	private StyleChecker styleSheet;
    public ConvertDirectory() {
		html = new ConvertFile();
		canConvert = new CanConvert();
		styleSheet = new StyleChecker();
    }
    public void convert(File directory) {
	if(!directory.isDirectory()) {
	    System.out.println(directory+" is not a Directory."+
			       " No conversion done on.");
	    return;
	}
	// Ignored object maintains a list of 'ignored' sub-directories
	// in this directory.
	Ignored ignored = new Ignored(directory);
	// index creator for the present directory.
	IndexCreator index = new IndexCreator(directory);
	// iterate through the directory.
	System.out.println("Current Directory: "+directory.getAbsolutePath());
	// the FileSieve parse for .scruffy files (except index.scruffy):
	for(File file:directory.listFiles(new FileSieve())) {
	    if(file.isFile()) {
			can = canConvert.check(file);
			if(can) {
				System.out.println("Converting..."+file.getAbsolutePath());
				html.convert(file);
				index.add(file);
			}
	    }
	    else if(file.isDirectory()) {
			// Perform conversion, only if, directory
			// is not a ignored directory.
			if(!ignored.ignored(file)) {
				++DirectoryInfo.level;
				this.convert(file);
			}
	    }
	}
	boolean convertIndex = (index.shouldConvert() || 
							canConvert.check(index.indexFile()));
	if(convertIndex) {
		System.out.println("Converting..."+index.indexFile().getAbsolutePath());
	    html.convert(index.indexFile());
	}
	// check whether this directory has scruffy files:
	if(index.indexFile().exists()) {
		// create/update stylesheet if need.
		styleSheet.check(directory);
	}
	--DirectoryInfo.level;
    }

}

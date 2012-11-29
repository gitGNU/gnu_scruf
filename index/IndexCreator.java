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


package scruf.index;

import java.io.*;
import java.util.regex.*;
import scruf.io.*;
import scruf.conversion.*;
import scruf.status.*;

public class IndexCreator {
    private File directory;
    private File index;
	private CreateHtmlFile htmlFile;
    private StringBuilder indexContent;
    // set to true, if index file is modified.
    boolean modified = false;
    public IndexCreator(File directory) {
		this.directory = directory;
		index = new File(directory,"index.scruffy");
		indexContent = new StringBuilder();
		if(index.exists()) {
			indexContent.append(new ReadFile(index).
								getContent());
		}
		htmlFile = new CreateHtmlFile();
    }
    public void add(File file) {
		String fileName = htmlFile.create().getName();
	if(shouldAdd(fileName)) {
	    System.out.println("New Entry: "+fileName);
	    indexContent.append(" [[./");
	    indexContent.append(fileName);
	    indexContent.append("|");
	    indexContent.append(PresentFile.name);
	    indexContent.append("]]\n");
	    modified=true;
	}
    }
    public boolean shouldConvert() {
		if(modified)
			new WriteFile(index,indexContent.toString()).write();
		return modified;
    }
    public File indexFile() {
		return index;
    }
    private  boolean shouldAdd(String fileName) {
		String regex = ".*"+fileName+".*";
		// checks if fileName is already there in index.
		boolean check1 = !(Pattern.compile(regex).
						   matcher(indexContent.toString()).find());
		// checks if fileName is index itself.
		boolean check2 = !(Pattern.matches(fileName,"index.scruffy"));
		boolean add = (check1 && check2);
		return add;
    }
}

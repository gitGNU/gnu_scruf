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


package scruf.io;

import java.io.*;

public class WriteFile {
    private File outputFile;
    private String content;
    public WriteFile(File outputFile, String content) {
	this.outputFile = outputFile.getAbsoluteFile();
	this.content = content;
    }
    public void write() {
	try {
	    System.out.println("Writing..."+outputFile.getName());
	    BufferedWriter bwriter = new BufferedWriter
		(new FileWriter(outputFile));
	    // write content to file.
	    bwriter.write(content);
	    bwriter.close();
	}catch(IOException e) {
	    System.err.println("Error occured while writing"+
			       " file : "+outputFile);
	}
    }
    public void append() {
	StringBuilder sbuilder = new StringBuilder(
						   new ReadFile(outputFile).getContent());
	sbuilder.append(content);
	// new content
	content = sbuilder.toString();
	write();
    }
}

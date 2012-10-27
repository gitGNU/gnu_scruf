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

public class ReadFile {
    private String fileContent;
    private File file;
    public ReadFile(File file) {
	this.file = file.getAbsoluteFile();
	fileContent = read();
    }
    public String getContent() {
	return fileContent;
    }

    public String[] split(String regex) {
	return fileContent.split(regex);
    }
    private String read() {
	BufferedReader buf;
	try {
	     buf = new BufferedReader(
				      new FileReader(file));
	}catch(FileNotFoundException e) {
	    throw new RuntimeException("Unable to open file :"
				       +file.getAbsolutePath());
	}
	String line;
	StringBuilder sbuilder = new StringBuilder();
	try {
	    while((line=buf.readLine())!=null) {
		sbuilder.append(line);
		sbuilder.append("\n");
	    }
	    buf.close();
	}catch(IOException io) {
	    throw new RuntimeException
		("ERROR while reading from file.",
		 io);
	}
	return sbuilder.toString();
    }
    
}


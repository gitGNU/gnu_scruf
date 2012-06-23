/*+
 *   Copyright (C) 2012-2013 rsiddharth
 *   Contact me : rsiddharth@ninthfloor.org 
 *  
 *   This program is free software: you can redistribute it and/or modify
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

package scruf.license;

import java.io.*;
import java.util.regex.*;
import scruf.io.*;
    
public class Liberate {
    private String gpl;

    public Liberate() {
	gpl = new ReadFile(new File("./scruf/license/gpl")).getContent();
    }

    private Pattern pattern;
    private Matcher matcher;
    private PrintWriter writer;
    
    public void baptize(String directory, String regex) {
	File dir = new File(directory).getAbsoluteFile();
	File[] dirContents = dir.listFiles();
	String fileContent;
	pattern = pattern.compile(regex);
	for(File file:dirContents) {
	    if(file.isFile()) {
		matcher = pattern.matcher(file.getName());
		if(matcher.find()) {
		    fileContent = new ReadFile(file).getContent();
		    gpl(file,fileContent);
		}
	    }
	    else {
		// is a Directory.
		baptize(file.getPath(),regex);
	    }
	}
    }

    private void gpl(File file, String fileContent) {
	Matcher matcher = Pattern.compile("^/\\*\\+").matcher(fileContent);
	if(!(matcher.find())) {
	    StringBuilder sbuilder = new StringBuilder();
	    sbuilder.append(gpl+"\n");
	    sbuilder.append(fileContent);
	    // write baptized content to file.
	    new WriteFile(file,fileContent).write();
	}
    }

    public static void main(String[] args) {
	    Liberate libre = new Liberate();
	    libre.baptize("./scruf/",".+\\.java");
    }
    
}
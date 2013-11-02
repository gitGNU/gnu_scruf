/*+
 *   Copyright (C) 2012, 2013 rsiddharth <rsiddharth@ninthfloor.org>
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
	
	// Used in iterate()
    private Pattern pattern;
    private Matcher matcher;

	// Used in update()
	private String oldString;
	private String newString;
    
	// TYPES:
	public static int BAPTIZE = 1, UPDATE = 2;

	// For Baptize
    public Liberate(String dir, String regex, int TYPE) {
		gpl = new ReadFile(new File("./scruf/license/gpl")).getContent();
		iterate(dir, regex, TYPE);
    }

	// For Update
	public Liberate(String dir, String regex, int TYPE,
					String oldString, String newString) {
		this.oldString = oldString;
		this.newString = newString;
		iterate(dir, regex, TYPE);
	}

    public void iterate(String directory, String regex, int TYPE) {
		File dir = new File(directory).getAbsoluteFile();
		File[] dirContents = dir.listFiles();
		String fileContent;
		pattern = pattern.compile(regex);
		for(File file:dirContents) {
			if(file.isFile()) {
				matcher = pattern.matcher(file.getName());
				if(matcher.find()) {
					fileContent = new ReadFile(file).getContent();
					if(TYPE == BAPTIZE)
						baptize(file,fileContent);
					else if(TYPE == UPDATE)
						update(file, fileContent);
				}
			}
			else {
				// is a Directory.
				iterate(file.getPath(), regex, TYPE);
			}
		}
    }
	
	public void update(File file, 
					   String fileContent) {
		Matcher matcher = Pattern.compile(oldString).matcher(fileContent);
		if(matcher.find()) {
			System.out.println("Updating "+file+"...");
			StringBuffer sbuffer = new StringBuffer();
			matcher.appendReplacement(sbuffer, newString);
			matcher.appendTail(sbuffer);
			// write updated content to file.
			new WriteFile(file, sbuffer.toString()).write();
		}else {
			System.out.println("ol' string not found in " + file);
		}
	}

    private void baptize(File file, 
						 String fileContent) {
		Matcher matcher = Pattern.compile("^/\\*\\+").matcher(fileContent);
		if(!(matcher.find())) {
			System.out.println("Baptizing "+file+"...");
			StringBuilder sbuilder = new StringBuilder();
			sbuilder.append(gpl+"\n");
			sbuilder.append(fileContent);
			// write baptized content to file.
			new WriteFile(file,sbuilder.toString()).write();
		}
    }
	
    public static void main(String[] args) {
		/*	    Liberate copyrightUpdate = new Liberate("./scruf/",
									  ".+\\.java$", 
									  Liberate.UPDATE,
									  "Copyright 2012 rsiddharth",
									  "Copyright 2012, 2013 rsiddharth <rsiddharth@ninthfloor.org>");
		Liberate emailDelete = new Liberate("./scruf/",
									  ".+\\.java$", 
									  Liberate.UPDATE,
									  " \\*   Email: <rsiddharth@ninthfloor.org> \n \\*",
									  " *");*/

		Liberate baptize = new Liberate("./scruf/",
										".+\\.java$",
										Liberate.BAPTIZE
										);
    }
    
}

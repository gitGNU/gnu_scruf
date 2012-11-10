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


package scruf.conversion.ignore;

import java.io.*;
import scruf.io.*;

public class Ignored {
	private String ignoredList[];
	public Ignored(File directory) {
		File ignoredFile = new File(directory,".ignored");
		if(ignoredFile.exists()) {
			ignoredList = new ReadFile(ignoredFile).split("\n");
		}
	}
	public boolean ignored(File subdirectory) {
		boolean ignored = false;
		if(ignoredList!=null) {
			for(String dir:ignoredList) {
				if(subdirectory.getName().matches(dir)) {
					System.out.println("Ignoring Directory: "+
									   subdirectory.getAbsolutePath());
					ignored = true;
					break;
				}
			}
		}
		return ignored;
	}
}

/**
   CVS/
  /home/rsd/projects/scruf/www/CVS/
 */

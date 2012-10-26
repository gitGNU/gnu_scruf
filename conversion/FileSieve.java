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
import java.util.regex.*;

public class FileSieve implements FileFilter {
    // this method return true, if this file doesn't represent
    // a html file.
    public boolean accept(File pathname) {
	Pattern pattern = Pattern.compile("(.+\\.(html|png|jpg|css|tar|ttf))|(.+?\\~)|(index)|(\\..+)|(\\#.+?\\#)");
	Matcher matcher = pattern.matcher(pathname.getName());
	boolean bool = matcher.find();
	return !bool;
    }    
}

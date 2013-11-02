/*+
 *   Copyright 2012, 2013 rsiddharth <rsiddharth@ninthfloor.org>
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


package scruf.parsers;

import java.text.*;
import java.util.*;

/**
 * This class produces a string of the current date. This 'current
 * date' string is used append at end of the html page page. 
 */

class LastUpdate implements Parser {
	private String startBlock, endBlock;
	private DateFormat date;
	private StringBuilder sbuilder;
	public LastUpdate() {
		 sbuilder = new StringBuilder();
		 startBlock = "\n<div class=\"lastupdate\">\n Last Updated on: ";
		 date = DateFormat.getDateInstance(DateFormat.LONG);
		 endBlock = "</div>\n";
	}
	public String parse(String fileContent) {
		sbuilder.delete(0,sbuilder.length());
		sbuilder.append(fileContent);
		sbuilder.append(startBlock);
		// date.format() returns the date at this instance in time.
		sbuilder.append(date.format(new Date()));
		sbuilder.append(endBlock);
		return sbuilder.toString();
	}
}

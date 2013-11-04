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

/**
 * this class, inserts the main content of the html into the <article>
 * block. 
 */

public class CloseHtmlTags implements Parser {
	private StringBuilder sbuilder;
	/**
	 * the fileContent has its <head> and <article> fields filled. 
	 */
	public String parse(String fileContent) {
		sbuilder = new StringBuilder();
		sbuilder.append(fileContent);
		// add "powered by scruf" at bottom of page.
		sbuilder.append("\n<div class=\"scruf\">\n");
		sbuilder.append("<a href=\"http://nongnu.org/scruf/\">powered by scruf</a>");
		sbuilder.append("\n</div>\n");
		// Close body tag
		sbuilder.append("\n</body>\n");
		sbuilder.append("</html>\n");
		return sbuilder.toString();
	}
}

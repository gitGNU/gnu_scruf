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


package scruf.parsers;

import java.util.*;

public class ParserList {
    private List<Parser> parsers;
    public ParserList() {
	parsers = new ArrayList<Parser>();
	// add Parsers. NOTE: parser order is significant.
	parsers.add(new DocumentName());
	parsers.add(new WordDecoration());
	parsers.add(new CodeBlocks());
	parsers.add(new Headings());
	parsers.add(new Paragraphs());
	parsers.add(new Links());
	parsers.add(new Images());
	parsers.add(new Footer());
	parsers.add(new BackButton());
	parsers.add(new Header());
    }
    public List<Parser> list() {
	return new ArrayList<Parser>(parsers);
    }
    public static void main(String[] args) {
	ParserList pl = new ParserList();
	List<Parser> parser = pl.list();
	parser.remove(0);
	for(Parser p:pl.list())
	    System.out.println(p);
    }
}

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

import java.util.regex.*;
import java.util.*;

/**
 * Map of Symbols & their HTML equivalent numbers.
 */
public class SymbolMap extends HashMap<String, String> {
	public SymbolMap() {
		put("!","&#33;");
		put("\"","&#34;");
		put("#","&#35;");
		put("$","&#36;");
		put("%","&#37;");
		put("&","&#38;");
		put("'","&#39;");
		put("(","&#40;");
		put(")","&#41;");
		put("*","&#42;");
		put("+","&#43;");
		put(",","&#44;");
		put("-","&#45;");
		put(".","&#46;");
		put("/","&#47;");
		put(":","&#58;");
		put(";","&#59;");
		put("<","&#60;");
		put("=","&#61;");
		put(">","&#62;");
		put("?","&#63;");
		put("@","&#64;");
		put("[","&#91;");
		put("\\","&#92;");
		put("]","&#93;");
		put("^","&#94;");
		put("_","&#95;");
		put("`","&#96;");
		put("{","&#123;");
		put("|","&#124;");
		put("}","&#125;");
		put("~","&#126;");
	}
	/**
     * this method quotes symbols to a HTML number.
     */
    public String quote(String string) {
	Pattern pattern = Pattern.compile("(\\&(amp|lt|gt|(\\#35))\\;)|(\\p{Punct})");
	Matcher matcher = pattern.matcher(string);
	StringBuffer sbuffer = new StringBuffer();
	while(matcher.find()) {
		if(matcher.group(4)!=null) {
			matcher.appendReplacement(sbuffer,
									  this.get(matcher.group()));
		}
	}
	matcher.appendTail(sbuffer);
	return sbuffer.toString();
    }
}


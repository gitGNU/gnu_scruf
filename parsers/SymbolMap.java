
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


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
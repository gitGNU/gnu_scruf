package scruf.parsers;

import scruf.io.*;

public class Header implements Parser {
    
    private String fileContent;
    private StringBuilder sbuilder;
    
    public String parse(String fileContent) {

	sbuilder = new StringBuilder();

	// Embed necessay headers.
	sbuilder.append("<!DOCTYPE html> \n");
	sbuilder.append("<head> \n");
	sbuilder.append(" <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /> \n");
	sbuilder.append("<title>");
	sbuilder.append(PresentFile.name);
	sbuilder.append("</title>");
	sbuilder.append("</head> \n");
	sbuilder.append("<body> \n");
	// File Content goes inside <article> tag
	sbuilder.append("<article>\n");
	// insert File Content.
	sbuilder.append(fileContent);
	// insert back button.
	sbuilder.append(PresentFile.backButton);
	// add footer if footer is available.
	if(PresentFile.footer!=null) {
	    sbuilder.append(PresentFile.footer);
	}
	sbuilder.append("</article>\n");
	// add "powered by scruf" at bottom of page.
	sbuilder.append("<div class=\"scruf\">\n");
	sbuilder.append("powered by scruf");
	sbuilder.append("</div>");
	// Close body tag
	sbuilder.append("\n</body>\n");
	sbuilder.append("</html>\n");

	return sbuilder.toString();
    }

}
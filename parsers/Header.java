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
	// insert File Content.
	sbuilder.append(fileContent);
	// insert back button.
	sbuilder.append(PresentFile.backButton);
	// Close body
	sbuilder.append("\n</body>\n");
	if(PresentFile.footer!=null) {
	    sbuilder.append(PresentFile.footer);
	}
	sbuilder.append("</html>\n");

	return sbuilder.toString();
    }

}
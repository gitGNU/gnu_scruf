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

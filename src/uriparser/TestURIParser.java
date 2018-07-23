package uriparser;  // DO NOT CHANGE PACKAGE NAME

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * SOFTENG 254 2018 Assignment 1 submission
 *
 * Author: (Preet Patel, ppat504)
 **/

public class TestURIParser {// DO NOT CHANGE THE CLASS NAME

    // Tests a basic http url scheme
    @Test
    public void testWebUrlScheme() {
        URIParser parse = new URIParser();
        URI uri = parse.parse("htTp://www.youtube.com/watch?id=9992#player");
        assertEquals("htTp", uri.getScheme());
    }

    // Tests a basic http url Authority
    @Test
    public void testWebUrlAuthority() {
        URIParser parse = new URIParser();
        URI uri = parse.parse("http://www.youtube.com/watch?id=9992#player");
        assertEquals("www.youtube.com", uri.getAuthority());
    }

    // Tests a basic http url Path
    @Test
    public void testWebUrlPath() {
        URIParser parse = new URIParser();
        URI uri = parse.parse("http://www.youtube.com/watch?id=9992#player");
        assertEquals("/watch", uri.getPath());
    }

    // Tests a basic http url Query
    @Test
    public void testWebUrlQuery() {
        URIParser parse = new URIParser();
        URI uri = parse.parse("http://www.youtube.com/watch?id=9992#player");
        assertEquals("id=9992", uri.getQuery());
    }

    // Tests a basic http url Fragment
    @Test
    public void testWebUrlFragment() {
        URIParser parse = new URIParser();
        URI uri = parse.parse("http://www.youtube.com/watch?id=9992#player");
        assertEquals("player", uri.getFragment());
    }

    @Test
    public void testNullInput() {
        try {
            new URIParser().parse(null);
            fail();
        }
        catch(ParseException e) {
            //Nothing to do here
        }
    }

    @Test
    public void testFileURIScheme() {
        URI uri = new URIParser().parse("file:///hello/hi");
        assertEquals("file", uri.getScheme());
        
    }

    @Test
    public void testFileURIAuthority() {
        URI uri = new URIParser().parse("file:///hello/hi");
        assertEquals("", uri.getAuthority());

    }

    @Test
    public void testFileURIPath() {
        URI uri = new URIParser().parse("file:///hello/hi");
        assertEquals("/hello/hi", uri.getPath());
        System.out.println(uri.getPath());

    }

    @Test
    public void testURISchemePath() {
        URI uri = new URIParser().parse("file:books");
        assertEquals("books", uri.getPath());

    }

    @Test
    public void testURISchemeQuery() {
        URI uri = new URIParser().parse("file:?books");
        assertEquals("books", uri.getQuery());

    }

    @Test
    public void testURISchemeFragment() {
        URI uri = new URIParser().parse("file:#books");
        assertEquals("books", uri.getFragment());

    }

    @Test
    public void testURIAuthorityPath() {
        URI uri = new URIParser().parse("//www.google.com/uri");
        assertEquals("/uri", uri.getPath());

    }

    @Test
    public void testURIPathQuery() {
        URI uri = new URIParser().parse("http://www.google.com/uri#?fragment");
        assertEquals("?fragment", uri.getFragment());

    }

    @Test
    public void testStrings() {
        String[] strings = {"$", "-", "_", ".", "+", "!", "*", "\'", "(", ")", ",", ";", "/", "?", ":", "@", "&", "=", "%", "#"};
        for (String lol: strings
             ) {
            URI uri = new URIParser().parse("http://www.google.com/uri#" + lol + "fragment");
            assertEquals( lol +"fragment", uri.getFragment());
        }
    }


}

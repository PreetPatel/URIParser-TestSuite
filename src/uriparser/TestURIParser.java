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
    public void testRegularURI() {
        URIParser parse = new URIParser();
        URI uri = parse.parse("http://www.preetpatel.com/watch?id=999#player");
        assertEquals("http", uri.getScheme());

        assertEquals("www.preetpatel.com", uri.getAuthority());

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

    //------------------------Testing Blank URI------------------------------------//

    @Test
    public void testBlankURIScheme() {
        URI uri = new URIParser().parse("");
        assertEquals( null, uri.getScheme());
    }

    @Test
    public void testBlankURISAuthority() {
        URI uri = new URIParser().parse("");
        assertEquals( null, uri.getAuthority());
    }

    @Test
    public void testBlankURIPath() {
        URI uri = new URIParser().parse("");
        assertEquals( null, uri.getPath());
    }

    @Test
    public void testBlankURIQuery() {
        URI uri = new URIParser().parse("");
        assertEquals( null, uri.getQuery());
    }

    @Test
    public void testBlankURIFragment() {
            URI uri = new URIParser().parse("");
            assertEquals( null, uri.getFragment());
    }

    //------------------------------------------------------------------//

    //------------------------Testing Blank URI------------------------------------//

    @Test
    public void test20URIScheme() {
        URI uri = new URIParser().parse("%20hi:www.preet.com");
        assertEquals( "%20hi", uri.getScheme());
    }

    @Test
    public void test20URISAuthority() {
        URI uri = new URIParser().parse("http://%20hi.com");
        assertEquals( "%20hi.com", uri.getAuthority());
    }

    @Test
    public void test20URIPath() {
        URI uri = new URIParser().parse("/%20hi");
        assertEquals( "/%20hi", uri.getPath());
    }

    @Test
    public void test20URIQuery() {
        URI uri = new URIParser().parse("?%20hi");
        assertEquals( "%20hi", uri.getQuery());
    }

    @Test
    public void test20URIFragment() {
        URI uri = new URIParser().parse("#%20hi");
        assertEquals( "%20hi", uri.getFragment());
    }

    //------------------------------------------------------------------//

    //-------------------Test Full Acceptance Range---------------------//
    @Test
    public void testFullSchemeRange() {
        URI uri = new URIParser().parse("h.t-t+p://hi.com:2000/cpanel");
        assertEquals( "h.t-t+p", uri.getScheme());
    }

    @Test
    public void testFullAuthorityRange() {
        URI uri = new URIParser().parse("http://hi@preetpatel.com:2000/cpanel");
        assertEquals( "hi@preetpatel.com:2000", uri.getAuthority());
    }

    @Test
    public void testFullPathRange() {
        URI uri = new URIParser().parse("mailto:iam@preetpatel.com");
        assertEquals( "iam@preetpatel.com", uri.getPath());
    }

    @Test
    public void testMailToPathRange() {
        URI uri = new URIParser().parse("mailto:iam@preet.patel.com");
        assertEquals( "iam@preet.patel.com", uri.getPath());
    }

    //-------------------------Testing Blank URI Parts--------------//

    @Test
    public void testBlankAuthority() {
        URI uri = new URIParser().parse("http://");
        assertEquals( "", uri.getAuthority());
    }

    @Test
    public void testBlankPath() {
        URI uri = new URIParser().parse("mailto://preetpatel.com/");
        assertEquals( "/", uri.getPath());
    }

    @Test
    public void testBlankQuery() {
        URI uri = new URIParser().parse("mailto://preetpatel.com/get?");
        assertEquals( "", uri.getQuery());
    }

    @Test
    public void testBlankFragment() {
        URI uri = new URIParser().parse("mailto://preetpatel.com#");
        assertEquals( "", uri.getFragment());
    }


    @Test
    public void testpathragment() {
        URI uri = new URIParser().parse("scheme:/");
        assertEquals( "/", uri.getPath());
    }





}

package uriparser;  // DO NOT CHANGE PACKAGE NAME

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * SOFTENG 254 2018 Assignment 1 submission
 *
 * Author: (Preet Patel, ppat504)
 **/

public class TestURIParser {// DO NOT CHANGE THE CLASS NAME

    // Test All Permutations of Scheme
    @Test
    public void testURIScheme() {
        assertEquals("scheme", new URIParser().parse("scheme:").getScheme());
        assertNull(new URIParser().parse("").getScheme());
        assertEquals("scheme.-+", new URIParser().parse("scheme.-+:").getScheme());
        assertNull(new URIParser().parse("scheme/:").getScheme());
        assertNull(new URIParser().parse("scheme?:").getScheme());
        assertNull(new URIParser().parse("scheme#:").getScheme());
        assertEquals("scheme", new URIParser().parse("scheme::").getScheme());
    }


    // Test All Permutations of Authority
    @Test
    public void testURIAuthority() {
        assertEquals("authority", new URIParser().parse("scheme://authority").getAuthority());
        assertEquals("authority", new URIParser().parse("//authority").getAuthority());
        assertEquals("",new URIParser().parse("///").getAuthority());
        assertNull(new URIParser().parse("").getAuthority());
        assertEquals("test@authority.com",new URIParser().parse("//test@authority.com").getAuthority());
        assertEquals("",new URIParser().parse("//").getAuthority());
        assertEquals("",new URIParser().parse("//?authority").getAuthority());
        assertEquals("",new URIParser().parse("//#authority").getAuthority());

    }

    // Test All Permutations of Path
    @Test
    public void testURIPath() {
        assertEquals("/path", new URIParser().parse("/path").getPath());
        assertEquals("/path/path", new URIParser().parse("/path/path").getPath());
        assertEquals("path", new URIParser().parse("path").getPath());
        assertNull(new URIParser().parse("").getPath());
        assertEquals("/", new URIParser().parse("/").getPath());
        assertEquals("/path", new URIParser().parse("/path?query").getPath());
        assertEquals("/path", new URIParser().parse("/path#fragment").getPath());
        assertEquals("path:550", new URIParser().parse("scheme:path:550").getPath());
        assertEquals("/path", new URIParser().parse("scheme:/path").getPath());
        assertEquals("/path", new URIParser().parse("//authority/path").getPath());

    }

    // Test All Permutations of Query
    @Test
    public void testURIQuery() {
        assertEquals("query", new URIParser().parse("?query").getQuery());
        assertNull(new URIParser().parse("").getQuery());
        assertEquals("", new URIParser().parse("?").getQuery());
        assertEquals("query", new URIParser().parse("scheme:?query").getQuery());
        assertEquals("query", new URIParser().parse("//authority?query").getQuery());
        assertEquals("query", new URIParser().parse("/path/?query").getQuery());
        assertEquals("query", new URIParser().parse("/path?query").getQuery());

    }

    // Test All Permutations of Fragment
    @Test
    public void testURIFragment() {
        assertEquals("fragment", new URIParser().parse("#fragment").getFragment());
        assertEquals("?fragment", new URIParser().parse("#?fragment").getFragment());
        assertNull(new URIParser().parse("").getFragment());
        assertEquals("", new URIParser().parse("#").getFragment());
        assertEquals("fragment", new URIParser().parse("scheme:#fragment").getFragment());
        assertEquals("fragment", new URIParser().parse("//authority#fragment").getFragment());
        assertEquals("fragment", new URIParser().parse("/path/#fragment").getFragment());
        assertEquals("fragment", new URIParser().parse("/path#fragment").getFragment());
        assertEquals("fragment", new URIParser().parse("?query#fragment").getFragment());
        assertEquals("fragment", new URIParser().parse("?#fragment").getFragment());
    }

    // Test for invalid string input
    @Test
    public void testParseExceptionHandling() {
        try {
            new URIParser().parse(null).getFragment();
            fail("URIParseException was expected");
        }
        catch (ParseException e) {
                //Do nothing which essentially passes the test
            }
    }
}

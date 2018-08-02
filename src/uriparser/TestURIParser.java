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

public class TestURIParser { // DO NOT CHANGE THE CLASS NAME

    /**
     * Tests the scheme only without any other parts.
     */
    @Test(timeout=5000)
    public void testURISchemeOnly() {
        assertEquals("scheme", new URIParser().parse("scheme:").getScheme());
    }
    
    /**
     * Tests the full range of acceptable characters in the scheme part 
     */
    @Test(timeout=5000) 
    public void testURILegalCharsScheme() {
        assertEquals("scheme.-+", new URIParser().parse("scheme.-+:").getScheme());
    }

    /**
     * Tests placing the / (path) character before the scheme is complete
     */
    @Test(timeout=5000) 
    public void testURIPathScheme() {
        assertNull(new URIParser().parse("scheme/:").getScheme());
    }

    /**
     * Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000) 
    public void testURIQueryScheme() {
        assertNull(new URIParser().parse("scheme?:").getScheme());
    }

    /**
     * Tests placing the # (Fragment) character before the scheme is complete
     */
    @Test(timeout=5000) 
    public void testURIFragmentScheme() {
        assertNull(new URIParser().parse("scheme#:").getScheme());
    }

    /**
     * Tests getting the scheme with double ':'s
     */
    @Test(timeout=5000) 
    public void testURIDoubleSchemeColons() {
        assertEquals("scheme", new URIParser().parse("scheme::").getScheme());
    }

    //---------------------------------------------------------------------------------------------------------//
    
    /**
     * Tests an only scheme and authority legal URI to get Authority  
     */
    @Test(timeout=5000)
    public void testURITrueSchemeAuthority() {
        assertEquals("authority", new URIParser().parse("scheme://authority").getAuthority());
    }

    /**
     * Tests only the legal authority part of URI to get Authority 
     */
    @Test(timeout=5000)
    public void TestOnlyAuthority () {
        assertEquals("authority", new URIParser().parse("//authority").getAuthority());
    }

    /**
     * Tests empty authority and path URI to get Authority
     */
    @Test(timeout=5000)
    public void testURIiBlankAuthorityWithPath() {
        assertEquals("",new URIParser().parse("///").getAuthority());
    }

    /**
     *Tests blank authority URI to get Authority
     */
    @Test(timeout=5000)
    public void testURIAuthorityWithNoString() {
        assertEquals("",new URIParser().parse("//").getAuthority());
    }

    /**
     *Tests an authority part with a ? (query) character in the URI to get Authority 
     */
    @Test(timeout=5000)
    public void testURIAuthorityWithQuery() {
        assertEquals("",new URIParser().parse("//?authority").getAuthority());
    }

    /**
     *Tests an authority part with a # (fragment) character in the URI to get Authority
     */
    @Test(timeout=5000)
    public void testURIAuthorityWithFragment() {
        assertEquals("",new URIParser().parse("//#authority").getAuthority());
    }


    //----------------------------------------------------------------------------------------//

    /**
     *Tests a valid path only URI to get the path
     */
    @Test(timeout=5000)
    public void testURIOnlyPath() {
        assertEquals("/path", new URIParser().parse("/path").getPath());
    }

    /**
     *Tests a valid double path only URI to get the path
     */
    @Test(timeout=5000)
    public void testURIOnlyPathDouble() {
        assertEquals("/path/path", new URIParser().parse("/path/path").getPath());
    }

    /**
     * Tests a single string without special characters to get the path
     */
    @Test(timeout=5000)
    public void testURIPathJustString() {
        assertEquals("path", new URIParser().parse("path").getPath());
    }

    /**
     * Tests a single / character URI to get path as it might have been confused with an authority by the parser
     */
    @Test(timeout=5000)
    public void testURIPathOnlySlash() {
        assertEquals("/", new URIParser().parse("/").getPath());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIPathWithQuery() {
        assertEquals("/path", new URIParser().parse("/path?query").getPath());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIPathWithFragment() {
        assertEquals("/path", new URIParser().parse("/path#fragment").getPath());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIPathContainingSchemeChar() {
        assertEquals("path:550", new URIParser().parse("scheme:path:550").getPath());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURISchemePath() {
        assertEquals("/path", new URIParser().parse("scheme:/path").getPath());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIAuthorityPath() {
        assertEquals("/path", new URIParser().parse("//authority/path").getPath());
    }

    //---------------------------------------------------------------------------------------//
    // Test All Permutations of Query

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIJustQuery() {
        assertEquals("query", new URIParser().parse("?query").getQuery());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIQueryCharOnly() {
        assertEquals("", new URIParser().parse("?").getQuery());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURISchemeQuery() {
        assertEquals("query", new URIParser().parse("scheme:?query").getQuery());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIAuthorityQuery() {
        assertEquals("query", new URIParser().parse("//authority?query").getQuery());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIPathWithAdditionalSlashQuery() {
        assertEquals("query", new URIParser().parse("/path/?query").getQuery());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIPathQuery() {
        assertEquals("query", new URIParser().parse("/path?query").getQuery());
    }

    //----------------------------------------------------------------------------------------------//
    // Test All Permutations of Fragment

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIOnlyFragment() {
        assertEquals("fragment", new URIParser().parse("#fragment").getFragment());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIFragmentWithQueryChar() {
        assertEquals("?fragment", new URIParser().parse("#?fragment").getFragment());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIFragmentCharOnly() {
        assertEquals("", new URIParser().parse("#").getFragment());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURISchemeAndFragment() {
        assertEquals("fragment", new URIParser().parse("scheme:#fragment").getFragment());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIAuthorityAndFragment() {
        assertEquals("fragment", new URIParser().parse("//authority#fragment").getFragment());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIPathWithSlashFragment() {
        assertEquals("fragment", new URIParser().parse("/path/#fragment").getFragment());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIPathWithoutSlashFragment() {
        assertEquals("fragment", new URIParser().parse("/path#fragment").getFragment());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIQueryAndFragment() {
        assertEquals("fragment", new URIParser().parse("?query#fragment").getFragment());
    }

    /**
     *Tests placing the ? (query) character before the scheme is complete
     */
    @Test(timeout=5000)
    public void testURIBlankQueryAndFragment() {
        assertEquals("fragment", new URIParser().parse("?#fragment").getFragment());
    }


    /**
     * Tests a null string to ensure it is considered illegal by the parser and the ParseException is thrown
     */
    @Test(timeout=5000)
    public void testParseExceptionHandlingNullURI() {
        try {
            new URIParser().parse(null).getScheme();
            fail("URIParseException was expected");
        }
        catch (ParseException e) {
                //Do nothing which essentially passes the test
            }
    }

    /**
     * Tests a spaced empty string to ensure it is considered illegal by the parser and the ParseException is thrown
     */
    @Test(timeout=5000)
    public void testParseExceptionHandlingSpacedURI() {
        try {
            new URIParser().parse(" ").getScheme();
            fail("URIParseException was expected");
        }
        catch (ParseException e) {
            //Do nothing which essentially passes the test
        }
    }

    /**
     * Tests parsing a blank URI which should return null on all parts of the URI being parsed. This should not throw a
     * ParseException as an empty URI is valid but has null parts
     */
    @Test(timeout=5000)
    public void testEmptyURI() {
        URI uri = new URIParser().parse("");
        assertNull(uri.getScheme());
        assertNull(uri.getAuthority());
        assertNull(uri.getPath());
        assertNull(uri.getQuery());
        assertNull(uri.getFragment());
    }
}

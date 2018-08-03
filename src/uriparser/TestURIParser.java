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

    //-----------------------------------------Test All Permutations of Scheme----------------------------------------//

    /**
     * Tests the scheme only without any other parts to ensure that the correct result is parsed.
     */
    @Test(timeout=5000)
    public void testURISchemeOnly() {
        assertEquals("scheme", new URIParser().parse("scheme:").getScheme());
    }

    /**
     * Tests placing the / (path) character before the scheme is complete as / before : makes the scheme invalid
     */
    @Test(timeout=5000) 
    public void testURIPathScheme() {
        assertNull(new URIParser().parse("scheme/:").getScheme());
    }

    /**
     * Tests placing the ? (query) character before the scheme is complete as ? before : makes the scheme invalid
     */
    @Test(timeout=5000) 
    public void testURIQueryScheme() {
        assertNull(new URIParser().parse("scheme?:").getScheme());
    }

    /**
     * Tests placing the # (Fragment) character before the scheme is complete as ? before : makes the scheme invalid
     */
    @Test(timeout=5000) 
    public void testURIFragmentScheme() {
        assertNull(new URIParser().parse("scheme#:").getScheme());
    }

    /**
     * Tests getting the scheme with double ':'s as a scheme cannot contain ':'
     */
    @Test(timeout=5000) 
    public void testURIDoubleSchemeColons() {
        assertEquals("scheme", new URIParser().parse("scheme::").getScheme());
    }

    //--------------------------------------Test All Permutations of Authority----------------------------------------//
    
    /**
     * Tests an only scheme and authority legal URI to get Authority to see if the parser reports the correct authority
     */
    @Test(timeout=5000)
    public void testURITrueSchemeAuthority() {
        assertEquals("authority", new URIParser().parse("scheme://authority").getAuthority());
    }

    /**
     * Tests only the legal authority part of URI to get Authority as // is not part of the authority
     */
    @Test(timeout=5000)
    public void TestOnlyAuthority () {
        assertEquals("authority", new URIParser().parse("//authority").getAuthority());
    }

    /**
     * Tests empty authority and path URI to get Authority as a single slash '/' is not part of the authority
     */
    @Test(timeout=5000)
    public void testURIiBlankAuthorityWithPath() {
        assertEquals("",new URIParser().parse("///").getAuthority());
    }

    /**
     * Tests blank authority URI to get Authority to ensure the parser recognises the input as the authority but with no
     * strings with it
     */
    @Test(timeout=5000)
    public void testURIAuthorityWithNoString() {
        assertEquals("",new URIParser().parse("//").getAuthority());
    }

    /**
     * Tests an authority part with a ? (query) character in the URI to get Authority  as the ? character is illegal in
     * the authority
     */
    @Test(timeout=5000)
    public void testURIAuthorityWithQuery() {
        assertEquals("",new URIParser().parse("//?authority").getAuthority());
    }

    /**
     * Tests an authority part with a # (fragment) character in the URI to get Authority as the # character is illegal
     * in the authority
     */
    @Test(timeout=5000)
    public void testURIAuthorityWithFragment() {
        assertEquals("",new URIParser().parse("//#authority").getAuthority());
    }


    //-----------------------------------------Test All Permutations of Path------------------------------------------//

    /**
     *Tests a valid path only URI to get the path to ensure the parser reports the correct result for a valid path
     */
    @Test(timeout=5000)
    public void testURIOnlyPath() {
        assertEquals("/path", new URIParser().parse("/path").getPath());
    }

    /**
     *Tests a valid double path only URI to get the path as the parser could misinterpret the second / as not a path
     */
    @Test(timeout=5000)
    public void testURIOnlyPathDouble() {
        assertEquals("/path/path", new URIParser().parse("/path/path").getPath());
    }

    /**
     * Tests a single string without special characters to get the path. This tests if the parser returns the string as
     * the path and not any other part that 'requires' special characters to be valid
     */
    @Test(timeout=5000)
    public void testURIPathStringWithoutAnySpecialCharacters() {
        URI uri = new URIParser().parse("path");
        assertEquals("path", uri.getPath());
        assertNull(uri.getScheme());
        assertNull(uri.getAuthority());
        assertNull(uri.getQuery());
        assertNull(uri.getFragment());
    }

    /**
     * Tests a single / character URI to get path as it might have been confused with an authority by the parser
     */
    @Test(timeout=5000)
    public void testURIPathOnlySlash() {
        assertEquals("/", new URIParser().parse("/").getPath());
    }

    /**
     * Tests placing the ? (query) character after the path is complete to ensure it is not recognised as being in the
     * path by the parser
     */
    @Test(timeout=5000)
    public void testURIPathWithQuery() {
        assertEquals("/path", new URIParser().parse("/path?query").getPath());
    }

    /**
     * Tests placing the # (fragment) character after the path is complete to ensure it is not recognised as being in the
     * path by the parser
     */
    @Test(timeout=5000)
    public void testURIPathWithFragment() {
        assertEquals("/path", new URIParser().parse("/path#fragment").getPath());
    }

    /**
     * Tests a path containing a : (scheme) character to ensure the path is not recognised as a scheme by the parser
     */
    @Test(timeout=5000)
    public void testURIPathContainingSchemeChar() {
        assertEquals("path:550", new URIParser().parse("scheme:path:550").getPath());
    }

    /**
     * Tests if the transition from scheme to path is picked up correctly by the parser
     */
    @Test(timeout=5000)
    public void testURISchemePath() {
        assertEquals("/path", new URIParser().parse("scheme:/path").getPath());
    }

    /**
     * Tests if the transition from authority to path is picked up correctly by the parser
     */
    @Test(timeout=5000)
    public void testURIAuthorityPath() {
        assertEquals("/path", new URIParser().parse("//authority/path").getPath());
    }

    //-----------------------------------------Test All Permutations of Query-----------------------------------------//
    // Test All Permutations of Query

    /**
     * Tests an isolated query part from a URI to ensure the parser can accurately recognise the query
     */
    @Test(timeout=5000)
    public void testURIJustQuery() {
        assertEquals("query", new URIParser().parse("?query").getQuery());
    }

    /**
     * Tests just the query character '?' to ensure that the parser picks it up as a query and reports an empty string
     */
    @Test(timeout=5000)
    public void testURIQueryCharOnly() {
        assertEquals("", new URIParser().parse("?").getQuery());
    }

    /**
     * Tests the transition from scheme to query as ':' is not part of the query
     */
    @Test(timeout=5000)
    public void testURISchemeQuery() {
        assertEquals("query", new URIParser().parse("scheme:?query").getQuery());
    }

    /**
     * Tests the transition from the authority to query as '//' is not part of the query
     */
    @Test(timeout=5000)
    public void testURIAuthorityQuery() {
        assertEquals("query", new URIParser().parse("//?query").getQuery());
    }

    /**
     * Tests the transition from path to query is picked up correctly by the parser
     */
    @Test(timeout=5000)
    public void testURIPathWithAdditionalSlashQuery() {
        assertEquals("query", new URIParser().parse("/path/?query").getQuery());
    }

    /**
     * Tests the '/' character in the query part as it could have been misinterpreted as a path by the parser
     */
    @Test(timeout=5000)
    public void testURIWithSlashQuery() {
        assertEquals("/query", new URIParser().parse("?/query").getQuery());
    }

    /**
     * Tests the transition of URI from path to query is picked up correctly by the parser
     */
    @Test(timeout=5000)
    public void testURIPathQuery() {
        assertEquals("query", new URIParser().parse("/path?query").getQuery());
    }

    //---------------------------------------Test All Permutations of Fragment----------------------------------------//

    /**
     * Tests an isolated fragment part from a URI to ensure the parser can accurately recognise the fragment
     */
    @Test(timeout=5000)
    public void testURIOnlyFragment() {
        assertEquals("fragment", new URIParser().parse("#fragment").getFragment());
    }

    /**
     * Tests placing the ? (query) character inside the fragment part as the parser may recognise it as a query by
     * mistake
     */
    @Test(timeout=5000)
    public void testURIFragmentWithQueryChar() {
        assertEquals("?fragment", new URIParser().parse("#?fragment").getFragment());
    }

    /**
     * Tests placing the # (fragment) character on its own to ensure it is picked up by the parser as a fragment but
     * the string is reported as empty
     */
    @Test(timeout=5000)
    public void testURIFragmentCharOnly() {
        assertEquals("", new URIParser().parse("#").getFragment());
    }

    /**
     * Tests if the transition from the scheme to fragment is picked up correctly by the parser
     */
    @Test(timeout=5000)
    public void testURISchemeAndFragment() {
        assertEquals("fragment", new URIParser().parse("scheme:#fragment").getFragment());
    }

    /**
     * Tests if the transition from the authority to fragment is picked up correctly by the parser
     */
    @Test(timeout=5000)
    public void testURIAuthorityAndFragment() {
        assertEquals("fragment", new URIParser().parse("//authority#fragment").getFragment());
    }

    /**
     * Tests if the transition from the authority without text to fragment is picked up correctly by the parser
     */
    @Test(timeout=5000)
    public void testURIAuthorityWithoutTextAndFragment() {
        assertEquals("fragment", new URIParser().parse("//#fragment").getFragment());
    }

    /**
     * Tests if the transition from the path to fragment is picked up correctly by the parser
     */
    @Test(timeout=5000)
    public void testURIPathWithSlashFragment() {
        assertEquals("fragment", new URIParser().parse("/path/#fragment").getFragment());
    }

    /**
     * Tests if the transition from the path to fragment is picked up correctly by the parser
     */
    @Test(timeout=5000)
    public void testURIPathWithoutSlashFragment() {
        assertEquals("fragment", new URIParser().parse("/path#fragment").getFragment());
    }

    /**
     * Tests if the transition from the query to fragment is picked up correctly by the parser
     */
    @Test(timeout=5000)
    public void testURIQueryAndFragment() {
        assertEquals("fragment", new URIParser().parse("?query#fragment").getFragment());
    }

    /**
     * Tests if the transition from the query without text to fragment is picked up correctly by the parser and not
     * confused as a query
     */
    @Test(timeout=5000)
    public void testURIBlankQueryAndFragment() {
        assertEquals("fragment", new URIParser().parse("?#fragment").getFragment());
    }

    //--------------------------------------Test Parse Exceptions and extreme cases-----------------------------------//

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

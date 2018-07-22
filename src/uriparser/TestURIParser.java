package uriparser;  // DO NOT CHANGE THIS OR YOU WILL GET ZERO

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * SOFTENG 254 2018 Assignment 1 submission
 *
 * Author: (name, username)
 **/

public class TestURIParser {// DO NOT CHANGE THE CLASS NAME OR YOU WILL GET ZERO

    // Your tests here.
    @Test
    public void testWebUrl() {
        URIParser parse = new URIParser();
        URI uri = parse.parse("http://www.youtube.com");
        assertEquals("http", uri.getScheme());
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
}

package com.diasparsoftware.htmlunitx.test;

import java.io.*;
import java.net.URL;

import com.diasparsoftware.gsbase.StreamUtil;
import com.diasparsoftware.htmlunitx.*;

public class FileSystemWebResponseTest extends
    AbstractWebResponseTestCase {

    private FileInputStream expectedContentAsStream;
    private InputStreamWebResponse actualWebResponse;
    private String expectedContentAsString;
    private byte[] expectedResponseBody;

    protected void setUp() throws Exception {
        expectedContentAsStream = new FileInputStream(
            "test/data/webResponse.html");

        actualWebResponse = new FileSystemWebResponse(new URL(
            "http://foo"), new File(
            "test/data/webResponse.html"));

        // Need to read from a different stream, otherwise
        // we have a race condition on who gets to read the
        // stream first. I'm not sure how to solve this problem.

        expectedContentAsString = StreamUtil
            .getContentAsString(new FileInputStream(
                "test/data/webResponse.html"));

        expectedResponseBody = expectedContentAsString
            .getBytes(actualWebResponse.getContentCharSet());

        super.setUp();
    }

    protected TestableWebResponse makeActualWebResponse()
        throws Exception {

        return actualWebResponse;
    }

    protected byte[] getExpectedResponseBody() throws Exception {
        return expectedResponseBody;
    }

    protected InputStream getExpectedContentAsStream()
        throws Exception {

        return expectedContentAsStream;
    }

    protected String getExpectedContentAsString()
        throws Exception {
        return expectedContentAsString;
    }
}

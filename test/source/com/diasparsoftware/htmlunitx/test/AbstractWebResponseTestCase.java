package com.diasparsoftware.htmlunitx.test;

import java.io.InputStream;
import java.net.URL;

import junit.framework.TestCase;
import junitx.framework.ArrayAssert;

import com.diasparsoftware.gsbase.StreamUtil;
import com.diasparsoftware.htmlunitx.TestableWebResponse;

public abstract class AbstractWebResponseTestCase extends
    TestCase {
    private TestableWebResponse webResponse;

    protected void setUp() throws Exception {
        webResponse = makeActualWebResponse();
    }

    protected abstract TestableWebResponse makeActualWebResponse()
        throws Exception;

    protected abstract byte[] getExpectedResponseBody()
        throws Exception;

    protected abstract InputStream getExpectedContentAsStream()
        throws Exception;

    protected abstract String getExpectedContentAsString()
        throws Exception;

    public void testStatusCode() {
        assertEquals(200, webResponse.getStatusCode());
        webResponse.setStatusCode(500);
        assertEquals(500, webResponse.getStatusCode());
    }

    public void testStatusMessage() {
        assertEquals("", webResponse.getStatusMessage());
        webResponse.setStatusMessage("A status message");
        assertEquals("A status message", webResponse
            .getStatusMessage());
    }

    public void testContentAsStream() throws Exception {
        ArrayAssert
            .assertEquals(
                          StreamUtil
                              .getContentAsBytes(getExpectedContentAsStream()),
                          StreamUtil
                              .getContentAsBytes(webResponse
                                  .getContentAsStream()));
    }

    public void testContentAsString() throws Exception {
        assertEquals(getExpectedContentAsString(), webResponse
            .getContentAsString());
    }

    public void testContentCharSet() {
        assertEquals("ISO-8859-1", webResponse
            .getContentCharSet());
        webResponse.setContentCharSet("Big5");
        assertEquals("Big5", webResponse.getContentCharSet());
    }

    public void testContentType() {
        assertEquals("application/octet-stream", webResponse
            .getContentType());
        webResponse.setContentType("text/plain");
        assertEquals("text/plain", webResponse.getContentType());
    }

    public void testHasStopwatch() {
        assertNotNull(webResponse.getStopwatch());
    }

    public void testLoadTime() throws Exception {
        FakeStopwatch fakeStopwatch = new FakeStopwatch(100);
        webResponse.setStopwatch(fakeStopwatch);
        webResponse.getContentAsStream();
        assertEquals(100, webResponse
            .getLoadTimeInMilliSeconds());
    }

    public void testResponseBody() throws Exception {
        ArrayAssert.assertEquals(getExpectedResponseBody(),
                                 webResponse.getResponseBody());
    }

    public void testResponseHeaderValue() {
        assertEquals(null, webResponse
            .getResponseHeaderValue("notThereYet"));
        webResponse.addResponseHeaderValue("thereNow",
                                           "a value");
        assertEquals("a value", webResponse
            .getResponseHeaderValue("thereNow"));

    }

    public void testUrl() throws Exception {
        assertEquals(new URL("http://foo"), webResponse
            .getUrl());
    }

}

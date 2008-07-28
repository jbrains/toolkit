package com.diasparsoftware.htmlunitx.test;

import java.io.*;
import java.io.InputStream;
import java.net.*;
import java.net.URL;
import java.util.Collections;

import junit.framework.TestCase;

import com.diasparsoftware.htmlunitx.*;
import com.gargoylesoftware.htmlunit.*;

public class TestableWebConnectionTest extends TestCase {
    public void testEmptyResponse() throws Exception {
        doTest("");
    }

    public void testNonEmptyResponse() throws Exception {
        doTest("Come here, Watson; I want you");
    }

    private void doTest(String content) throws MalformedURLException,
            IOException {

        URL url = new URL("http://foo");
        InputStream contentAsStream = createInputStreamWithContent(content);

        TestableWebConnection connection = createConnectionWithResponseFor(url,
                contentAsStream);

        InputStreamWebResponse expectedResponse = new InputStreamWebResponse(
                url, contentAsStream);

        WebResponse response = connection.getResponse(url, SubmitMethod.GET,
                Collections.EMPTY_LIST, Collections.EMPTY_MAP);

        assertEquals(expectedResponse, response);
    }

    private TestableWebConnection createConnectionWithResponseFor(URL url,
            InputStream contentAsStream) {
        InputStreamWebResponse inputStreamWebResponse = new InputStreamWebResponse(
                url, contentAsStream);
        inputStreamWebResponse.setContentType("text/plain");

        TestableWebConnection connection = new TestableWebConnection(null);
        connection.setupResponse(inputStreamWebResponse);
        return connection;
    }

    private InputStream createInputStreamWithContent(String textContent) {
        String contentAsString = textContent;
        InputStream contentAsStream = TextUtil.toInputStream(contentAsString);
        return contentAsStream;
    }
}

package com.diasparsoftware.htmlunitx;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import org.apache.commons.httpclient.HttpState;

import com.gargoylesoftware.htmlunit.*;

public class TestableWebConnection extends WebConnection {
    private Map responses = new HashMap();

    public TestableWebConnection(WebClient client) {
        super(client);
    }

    public WebResponse getResponse(URL url, SubmitMethod submitMethod,
            List parameters, Map requestHeaders) throws IOException {

        return (WebResponse) responses.get(url);
    }

    public void setupResponse(WebResponse response) {
        responses.put(response.getUrl(), response);
    }

    public HttpState getStateForUrl(URL url) {
        return new HttpState();
    }
}

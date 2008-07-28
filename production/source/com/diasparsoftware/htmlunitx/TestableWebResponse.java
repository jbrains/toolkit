package com.diasparsoftware.htmlunitx;

import java.net.URL;
import java.util.Properties;

import com.diasparsoftware.gsbase.*;
import com.gargoylesoftware.htmlunit.WebResponse;


public abstract class TestableWebResponse implements WebResponse {
    private int statusCode = 200;
    private String statusMessage = "";
    private String contentType = "application/octet-stream";
    private String contentCharSet = "ISO-8859-1";
    private Properties responseHeaderValues = new Properties();
    private Stopwatch stopwatch = new SystemClockStopwatch();

    private URL url;
    
    public TestableWebResponse(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public int getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getResponseHeaderValue(String headerName) {
        return responseHeaderValues.getProperty(headerName);
    }

    public void addResponseHeaderValue(String name, String value) {
        responseHeaderValues.put(name, value);
    }

    public String getContentCharSet() {
        return contentCharSet;
    }

    public void setContentCharSet(String contentCharSet) {
        this.contentCharSet = contentCharSet;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    public Stopwatch getStopwatch() {
        return stopwatch;
    }

    public void setStopwatch(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }

}

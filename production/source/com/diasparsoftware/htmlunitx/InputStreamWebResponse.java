package com.diasparsoftware.htmlunitx;

import java.io.*;
import java.net.URL;

import com.diasparsoftware.gsbase.StreamUtil;

public class InputStreamWebResponse extends TestableWebResponse {
    private InputStream contentAsStream;

    public InputStreamWebResponse(URL url,
        InputStream contentAsStream) {

        super(url);

        this.contentAsStream = contentAsStream;
    }

    public String getContentAsString() throws IOException {
        return StreamUtil
            .getContentAsString(getContentAsStream());
    }

    public InputStream getContentAsStream() {
        getStopwatch().start();
        getStopwatch().stop();
        return contentAsStream;
    }

    public long getLoadTimeInMilliSeconds() {
        return getStopwatch().getLastTime();
    }

    public byte[] getResponseBody() {
        try {
            return getContentAsBytes();
        } catch (IOException wrapped) {
            throw new RuntimeException(wrapped);
        }
    }

    protected byte[] getContentAsBytes()
        throws UnsupportedEncodingException, IOException {
        return getContentAsString().getBytes(
            getContentCharSet());
    }

    public boolean equals(Object other) {
        if (other != null && getClass() == other.getClass()) {
            InputStreamWebResponse that = (InputStreamWebResponse) other;
            return this.getUrl().equals(that.getUrl()) && this
                .getContentAsStream().equals(
                    that.getContentAsStream());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "an InputStreamWebResponse[contentAsStream=" + contentAsStream + "]";
    }
}

package com.diasparsoftware.javax.servlet.http;

import java.util.*;


public class FakeHttpSession extends HttpSessionAdapter {
    private Map attributes;

    public FakeHttpSession(Map attributes) {
        this.attributes = attributes;
    }
    
    public Object getAttribute(String name) {
        return attributes.get(name);
    }
    
    public Enumeration getAttributeNames() {
        return new Vector(attributes.keySet()).elements();
    }
    
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }
}

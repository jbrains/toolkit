package com.diasparsoftware.javax.servlet.test;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

public class HardcodedHttpSession implements HttpSession {
    private Map attributes = new HashMap();

    public long getCreationTime() {
        return 0;
    }

    public String getId() {
        return null;
    }

    public long getLastAccessedTime() {
        return 0;
    }

    public ServletContext getServletContext() {
        return null;
    }

    public void setMaxInactiveInterval(int arg0) {
    }

    public int getMaxInactiveInterval() {
        return 0;
    }

    public HttpSessionContext getSessionContext() {
        return null;
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    public Object getValue(String arg0) {
        return null;
    }

    public Enumeration getAttributeNames() {
        return new Vector(attributes.keySet()).elements();
    }

    public String[] getValueNames() {
        return null;
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public void putValue(String arg0, Object arg1) {
    }

    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    public void removeValue(String arg0) {
    }

    public void invalidate() {
        attributes.clear();
    }

    public boolean isNew() {
        return false;
    }
}

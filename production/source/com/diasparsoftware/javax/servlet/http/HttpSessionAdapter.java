package com.diasparsoftware.javax.servlet.http;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

public class HttpSessionAdapter implements HttpSession {
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

    public void setMaxInactiveInterval(int maxInactiveInterval) {
    }

    public int getMaxInactiveInterval() {
        return 0;
    }

    public HttpSessionContext getSessionContext() {
        return null;
    }

    public Object getAttribute(String name) {
        return null;
    }

    public Object getValue(String name) {
        return null;
    }

    public Enumeration getAttributeNames() {
        return null;
    }

    public String[] getValueNames() {
        return null;
    }

    public void setAttribute(String name, Object value) {
    }

    public void putValue(String name, Object value) {
    }

    public void removeAttribute(String name) {
    }

    public void removeValue(String name) {
    }

    public void invalidate() {
    }

    public boolean isNew() {
        return false;
    }
}

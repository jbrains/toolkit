package com.diasparsoftware.java.util;

import java.util.Map;

public abstract class ExceptionalMapEntryClosure
    implements ExceptionalClosure {

    public Object execute(Object parameters) throws Exception {
        Map.Entry each = (Map.Entry) parameters;
        eachMapEntry(each.getKey(), each.getValue());
        return null;
    }

    protected abstract void eachMapEntry(Object key, Object value)
        throws Exception;
}

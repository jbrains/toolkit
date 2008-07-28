package com.diasparsoftware.java.util;

import java.util.Map;

import org.apache.commons.collections.Closure;

public abstract class MapEntryClosure implements Closure {
    public final void execute(Object each) {
        Map.Entry eachEntry = (Map.Entry) each;
        eachMapEntry(eachEntry.getKey(), eachEntry.getValue());
    }

    public abstract void eachMapEntry(Object key, Object value);
}

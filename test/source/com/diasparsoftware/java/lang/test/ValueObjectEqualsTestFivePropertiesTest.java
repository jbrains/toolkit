package com.diasparsoftware.java.lang.test;

import java.util.*;

import com.diasparsoftware.util.junit.ValueObjectEqualsTest;

public class ValueObjectEqualsTestFivePropertiesTest
    extends ValueObjectEqualsTest {

    protected List keyPropertyNames() {
        return Arrays.asList(
            new String[] { "key1", "key2", "key3", "key4", "key5" });
    }

    protected Object createControlInstance() throws Exception {
        return new FiveKeys(1, 2, 3, 4, 5);
    }

    protected Object createInstanceDiffersIn(String keyPropertyName)
        throws Exception {

        if ("key1".equals(keyPropertyName))
            return new FiveKeys(6, 2, 3, 4, 5);
        else if ("key2".equals(keyPropertyName))
            return new FiveKeys(1, 6, 3, 4, 5);
        else if ("key3".equals(keyPropertyName))
            return new FiveKeys(1, 2, 6, 4, 5);
        else if ("key4".equals(keyPropertyName))
            return new FiveKeys(1, 2, 3, 6, 5);
        else if ("key5".equals(keyPropertyName))
            return new FiveKeys(1, 2, 3, 4, 6);

        return null;
    }
}

package com.diasparsoftware.java.util.test;

import java.util.*;

import junit.framework.TestCase;

import com.diasparsoftware.java.util.CollectionUtil;

public class CaseInsensitiveStringCollectionSearchTest
    extends TestCase {
    private Collection empty;
    private Collection notAStringCollection;
    private Collection singletonHello;

    protected void setUp() throws Exception {
        empty = Collections.EMPTY_LIST;
        notAStringCollection =
            new HashSet(
                Arrays.asList(
                    new Object[] {
                        "this is a string",
                        new Integer(762),
                        "this is also a string" }));

        singletonHello = Collections.singleton("hello");
    }

    public void testEmptyCollection() {
        assertFalse(
            CollectionUtil.stringCollectionContainsIgnoreCase(
                empty,
                "hello"));
    }

    public void testNotAStringCollection() {
        try {
            CollectionUtil.stringCollectionContainsIgnoreCase(
                notAStringCollection,
                "");
            fail("How did you compare a String to not a String?!");
        }
        catch (ClassCastException expected) {
        }
    }

    public void testSingleItemMatches() {
        assertTrue(
            CollectionUtil.stringCollectionContainsIgnoreCase(
                singletonHello,
                "hello"));
    }

    public void testSingleItemDoesNotMatch() {
        assertFalse(
            CollectionUtil.stringCollectionContainsIgnoreCase(
                singletonHello,
                "goodbye"));
    }
}

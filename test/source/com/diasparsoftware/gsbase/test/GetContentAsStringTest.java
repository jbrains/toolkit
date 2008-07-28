/*
 * Copyright (C) 1998, 2003 Gargoyle Software. All rights reserved.
 * 
 * This file is part of GSBase. For details on use and redistribution please
 * refer to the license.html file included with these sources.
 */
package com.diasparsoftware.gsbase.test;

import java.io.*;

import junit.framework.TestCase;

import com.diasparsoftware.gsbase.StreamUtil;
import com.gargoylesoftware.base.util.DetailedNullPointerException;

public class GetContentAsStringTest extends TestCase {
    public void testNull() throws Exception {
        try {
            StreamUtil.getContentAsString(null);
            fail("Allowed null parameter");
        }
        catch (DetailedNullPointerException expected) {
            // Expected path
        }
    }

    public void testEmptyStream() throws Exception {
        InputStream emptyStream = new ByteArrayInputStream(
            new byte[0]);
        assertEquals("", StreamUtil
            .getContentAsString(emptyStream));
    }

    public void testHappyPath() throws Exception {
        InputStream happyPathStream = new ByteArrayInputStream(
            "Happy path".getBytes("ISO-8859-1"));
        assertEquals("Happy path", StreamUtil
            .getContentAsString(happyPathStream));
    }

}

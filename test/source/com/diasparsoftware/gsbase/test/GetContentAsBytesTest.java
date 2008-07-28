/*
 *  Copyright (C) 1998, 2003 Gargoyle Software. All rights reserved.
 *
 *  This file is part of GSBase. For details on use and redistribution
 *  please refer to the license.html file included with these sources.
 */
package com.diasparsoftware.gsbase.test;

import java.io.*;
import java.util.Arrays;

import junit.framework.TestCase;
import junitx.framework.ArrayAssert;

import com.diasparsoftware.gsbase.StreamUtil;
import com.gargoylesoftware.base.util.DetailedNullPointerException;

public class GetContentAsBytesTest extends TestCase {
    public void testNull() throws Exception {
        try {
            StreamUtil.getContentAsBytes(null);
            fail("Allowed null parameter");
        }
        catch (DetailedNullPointerException expected) {
            // Expected path
        }
    }

    public void testEmptyStream() throws Exception {
        InputStream emptyStream = new ByteArrayInputStream(new byte[0]);
        ArrayAssert.assertEquals(
            new byte[0],
            StreamUtil.getContentAsBytes(emptyStream));
    }

    public void testSimpleHappyPath() throws Exception {
        InputStream happyPathStream =
            new ByteArrayInputStream(new byte[] { 1, 2, 3, 4 });

        ArrayAssert.assertEquals(
            new byte[] { 1, 2, 3, 4 },
            StreamUtil.getContentAsBytes(happyPathStream));
    }

    public void testLargeBuffer() throws Exception {
        byte[] largeBuffer = new byte[0xFEDCB];
        byte[] copyOfLargeBuffer = new byte[0xFEDCB];

        Arrays.fill(largeBuffer, (byte) 62);
        Arrays.fill(copyOfLargeBuffer, (byte) 62);

        InputStream happyPathStream =
            new ByteArrayInputStream(largeBuffer);

        ArrayAssert.assertEquals(
            copyOfLargeBuffer,
            StreamUtil.getContentAsBytes(happyPathStream));
    }

}

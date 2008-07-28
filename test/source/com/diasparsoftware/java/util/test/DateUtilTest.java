package com.diasparsoftware.java.util.test;

import junit.framework.TestCase;

import com.diasparsoftware.java.util.DateUtil;

public class DateUtilTest extends TestCase {
    public void testGetYear() throws Exception {
        assertEquals(
            2004,
            DateUtil.getYear(DateUtil.makeDate(2004, 6, 1)));
    }
}

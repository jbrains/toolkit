package com.diasparsoftware.io.test;

import java.io.*;
import java.io.StringReader;

import junit.framework.TestCase;

import com.diasparsoftware.java.io.ReaderUtil;

public class GetContentAsStringTest extends TestCase {
    public void testEmptyReader() throws Exception {
        Reader empty = new InputStreamReader(
            new ByteArrayInputStream(new byte[0]));
        
        assertEquals("", ReaderUtil.getContentAsString(empty));
    }
    
    public void testFiveCharacters() throws Exception {
        Reader empty = new InputStreamReader(
            new ByteArrayInputStream(new byte[] {'a', 'b', 'c', 'd', 'e'}));
        
        assertEquals("abcde", ReaderUtil.getContentAsString(empty));
    }

}

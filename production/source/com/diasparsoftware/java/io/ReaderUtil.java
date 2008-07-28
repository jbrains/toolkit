package com.diasparsoftware.java.io;

import java.io.*;

public class ReaderUtil {
    /***
     * Obtain a string representation of the content that
     * a reader reads.
     * 
     * @param reader
     * @return
     * @throws IOException  Thrown if an underlying read operation
     * fails.
     */
    public static String getContentAsString(Reader reader)
        throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer contentBuffer = new StringBuffer();

        while (true) {
            int readResult = bufferedReader.read();
            if (readResult == -1) {
                break;
            }

            contentBuffer.append((char) readResult);
        }

        return contentBuffer.toString();
    }
}

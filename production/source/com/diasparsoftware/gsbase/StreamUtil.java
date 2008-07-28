/*
 *  Copyright (C) 1998, 2003 Gargoyle Software. All rights reserved.
 *
 *  This file is part of GSBase. For details on use and redistribution
 *  please refer to the license.html file included with these sources.
 */
package com.diasparsoftware.gsbase;

import java.io.*;
import java.util.*;

/***
 * Provides some utility methods for working with streams.
 * 
 * @version $Revision: 1.1 $
 * @author <a href="mailto:jbr@diasparsoftware.com">J. B. Rainsberger</a>
 */
public class StreamUtil {

    /***
     * Reads the content coming from the specified input stream
     * and presents it as a string.
     * 
     * @param stream  Any input stream containing text
     * @return  A string representing the contents of the
     * underlying stream
     * @throws IOException  Thrown by the underlying I/O operations
     */
    public static String getContentAsString(InputStream characterStream)
        throws IOException {

        Assert.notNull("characterStream", characterStream);

        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(characterStream));

        StringWriter stringWriter = new StringWriter();
        while (true) {
            int character = bufferedReader.read();
            if (character == -1)
                break;

            stringWriter.write(character);
        }

        return stringWriter.toString();
    }

    /***
     * Reads the content coming from the specified input stream
     * and presents it as an array of bytes.
     * 
     * @param stream  Any input stream
     * @return  An array of bytes representing the contents of the
     * underlying stream
     * @throws IOException  Thrown by the underlying I/O operations
     */
    public static byte[] getContentAsBytes(InputStream byteStream)
        throws IOException {

        Assert.notNull("byteStream", byteStream);

        DataInputStream dataInputStream =
            new DataInputStream(byteStream);

        List segments = new ArrayList();

        boolean endOfStream = false;
        int segmentIndex = 0;
        final int segmentLength = 4096;
        int lastSegmentLength = 0;
        while (!endOfStream) {
            int offset = segmentIndex * segmentLength;
            byte[] buffer = new byte[segmentLength];

            int thisSegmentLength =
                dataInputStream.read(buffer, offset, segmentLength);

            if (thisSegmentLength == -1) {
                endOfStream = true;
            }
            else {
                segments.add(buffer);
                lastSegmentLength = thisSegmentLength;
            }
        }

        return flattenByteSegments(
            segments,
            segmentLength,
            lastSegmentLength);
    }

    /***
     * Turns a List of equally-sized byte segments (buffers) 
     * into a single buffer
     * by concatenating them one by one.
     * 
     * @param byteSegments  The buffers to "flatten"
     * @param eachSegmentButLastLength  The length of each buffer
     * but the last -- they are assumed all to be the same size
     * @param lastSegmentLength  The length of the last buffer,
     * since it is likely not the same length as the others
     * @return
     */
    public static byte[] flattenByteSegments(
        List byteSegments,
        int eachSegmentButLastLength,
        int lastSegmentLength) {

        if (byteSegments.isEmpty())
            return new byte[0];
        else {
            int totalBytes =
                eachSegmentButLastLength * (byteSegments.size() - 1)
                    + lastSegmentLength;

            byte[] flattenedBytes = new byte[totalBytes];

            int nextSegmentOffset = 0;

            for (int i = 0; i < byteSegments.size() - 1; i++) {
                System.arraycopy(
                    byteSegments.get(i),
                    0,
                    flattenedBytes,
                    nextSegmentOffset,
                    eachSegmentButLastLength);

                nextSegmentOffset += eachSegmentButLastLength;
            }

            System.arraycopy(
                truncateByteSegment(
                    (byte[]) byteSegments.get(byteSegments.size() - 1),
                    lastSegmentLength),
                0,
                flattenedBytes,
                nextSegmentOffset,
                lastSegmentLength);

            return flattenedBytes;
        }
    }

    private static byte[] truncateByteSegment(
        byte[] byteSegment,
        int toSize) {

        byte[] newSegment = new byte[toSize];
        System.arraycopy(byteSegment, 0, newSegment, 0, toSize);
        return newSegment;
    }
}

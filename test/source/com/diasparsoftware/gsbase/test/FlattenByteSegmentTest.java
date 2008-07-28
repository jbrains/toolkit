package com.diasparsoftware.gsbase.test;

import java.util.*;

import junit.framework.*;
import junitx.framework.ArrayAssert;

import com.diasparsoftware.gsbase.StreamUtil;

public class FlattenByteSegmentTest extends TestCase {
    private byte[] expectedByteArray;
    private List byteSegments;
    private int eachSegmentButLastLength;
    private int lastSegmentLength;

    public FlattenByteSegmentTest(
        String testName,
        byte[] expectedByteArray,
        List byteSegments,
        int eachSegmentButLastLength,
        int lastSegmentLength) {

        super(testName);

        this.expectedByteArray = expectedByteArray;
        this.byteSegments = byteSegments;
        this.eachSegmentButLastLength = eachSegmentButLastLength;
        this.lastSegmentLength = lastSegmentLength;
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Flatten Byte Segment Tests");

        suite.addTest(
            new FlattenByteSegmentTest(
                "empty",
                new byte[0],
                Collections.EMPTY_LIST,
                0,
                0));

        suite.addTest(
            new FlattenByteSegmentTest(
                "single, stripped segment",
                new byte[] { 12 },
                Collections.singletonList(new byte[] { 12 }),
                0,
                1));

        byte[] singlePaddedRow = new byte[] { 12, 0 };

        suite.addTest(
            new FlattenByteSegmentTest(
                "single, padded segment",
                new byte[] { 12 },
                Collections.singletonList(singlePaddedRow),
                0,
                1));

        byte[] fullRow = new byte[] { 12, 13, 12, 13, 12, 13 };
        byte[] fullRowThenSinglePaddedRow =
            new byte[] { 12, 13, 12, 13, 12, 13, 12 };

        suite.addTest(
            new FlattenByteSegmentTest(
                "full segment, then single padded segment",
                fullRowThenSinglePaddedRow,
                Arrays.asList(
                    new Object[] { fullRow, singlePaddedRow }),
                fullRow.length,
                1));

        byte[] twoFullRowsThenSinglePaddedRow =
            new byte[] {
                12,
                13,
                12,
                13,
                12,
                13,
                12,
                13,
                12,
                13,
                12,
                13,
                12 };

        suite.addTest(
            new FlattenByteSegmentTest(
                "2 full segments, then single padded segment",
                twoFullRowsThenSinglePaddedRow,
                Arrays.asList(
                    new Object[] { fullRow, fullRow, singlePaddedRow }),
                fullRow.length,
                1));

        return suite;
    }

    protected void runTest() {
        ArrayAssert.assertEquals(
            expectedByteArray,
            StreamUtil.flattenByteSegments(
                byteSegments,
                eachSegmentButLastLength,
                lastSegmentLength));
    }
}

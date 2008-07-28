package com.diasparsoftware.java.util.test;

import java.util.*;

import junit.framework.*;

import com.diasparsoftware.java.lang.ArrayUtil;

public class MakeMapFromArrayTest extends TestCase {
    private int testIndex;
    private Object[][] array;
    private Map expectedMap;

    public static Test suite() {
        TestSuite suite = new TestSuite();

        Map testData = new HashMap();
        testData.put(Collections.EMPTY_MAP, new Object[0][0]);
        testData
            .put(
                Collections.singletonMap(new Integer(762), "hello"),
                new Object[][] { { new Integer(762), "hello" }
        });

        testData
            .put(
                Collections.singletonMap(null, "hello"),
                new Object[][] { { null, "hello" }
        });

        int testIndex = 0;
        for (Iterator i = testData.entrySet().iterator();
            i.hasNext();
            ) {

            Map.Entry eachEntry = (Map.Entry) i.next();

            suite.addTest(
                new MakeMapFromArrayTest(
                    testIndex,
                    (Map) eachEntry.getKey(),
                    (Object[][]) eachEntry.getValue()));

            testIndex++;
        }

        return suite;
    }

    public MakeMapFromArrayTest(
        int testIndex,
        Map expectedMap,
        Object[][] array) {

        super("makeMapFromArrayTest #" + testIndex);
        this.testIndex = testIndex;
        this.expectedMap = expectedMap;
        this.array = array;
    }

    protected void runTest() throws Throwable {
        Map actualMap = ArrayUtil.asMap(array);
        assertEquals(expectedMap, actualMap);
    }
}

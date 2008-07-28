/*
 *  Copyright (C) 1998, 2003 Gargoyle Software. All rights reserved.
 *
 *  This file is part of GSBase. For details on use and redistribution
 *  please refer to the license.html file included with these sources.
 */
package com.diasparsoftware.gsbase.test;

import com.diasparsoftware.gsbase.SystemClockStopwatch;

public class SystemClockStopwatchTest extends StopwatchTestCase {
    protected void setUp() throws Exception {
        setStopwatch(new SystemClockStopwatch());
    }

    public void testLastTime() throws Exception {
        stopwatch.start();
        Thread.sleep(200);
        stopwatch.stop();
        long lastTime = stopwatch.getLastTime();
        assertTrue(
            "Stopwatch reads " + lastTime + " ms",
            lastTime >= 200);
    }
}

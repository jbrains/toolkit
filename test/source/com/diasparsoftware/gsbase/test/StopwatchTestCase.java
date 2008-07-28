/*
 *  Copyright (C) 1998, 2003 Gargoyle Software. All rights reserved.
 *
 *  This file is part of GSBase. For details on use and redistribution
 *  please refer to the license.html file included with these sources.
 */
package com.diasparsoftware.gsbase.test;

import junit.framework.TestCase;

import com.diasparsoftware.gsbase.Stopwatch;

/***
 * If you decide to implement @link{Stopwatch} then you should
 * pass these tests.
 * 
 * @version $Revision: 1.1 $
 * @author <a href="mailto:jbr@diasparsoftware.com">J. B. Rainsberger</a>
 */
public abstract class StopwatchTestCase extends TestCase {
    protected Stopwatch stopwatch;

    /***
     * Providing the stopwatch implementation is a subclass
     * responsibility.
     */    
    protected void setUp() throws Exception {
        fail("Override me and invoke setStopwatch()");
    }
    
    protected void setStopwatch(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    public void testLastTimeWithoutStarting() {
        assertEquals(0, stopwatch.getLastTime());
    }

    public void testReset() {
        stopwatch.reset();
        assertEquals(0, stopwatch.getLastTime());
    }

    public void testLastTimeWithoutStopping() {
        stopwatch.start();
        assertEquals(0, stopwatch.getLastTime());
    }

    /***
     * The expected behavior when reading the last time depends
     * on the implementation, so it is a subclass responsibility.
     * Usually you will start the stopwatch, pause for some time,
     * stop the stopwatch then verify the reading somehow.
     * 
     * @throws Exception  Just in case the underlying implementation
     * uses an API that throws an Exception
     */
    public abstract void testLastTime() throws Exception;

    public void testResetAfterUse() throws Exception {
        stopwatch.start();
        Thread.sleep(200);
        stopwatch.stop();
        stopwatch.reset();
        assertEquals(0, stopwatch.getLastTime());
    }
}

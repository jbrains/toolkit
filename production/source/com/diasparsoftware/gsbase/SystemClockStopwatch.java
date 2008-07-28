/*
 *  Copyright (C) 1998, 2003 Gargoyle Software. All rights reserved.
 *
 *  This file is part of GSBase. For details on use and redistribution
 *  please refer to the license.html file included with these sources.
 */
package com.diasparsoftware.gsbase;

/***
 * An production-quality implementation of 
 * com.gargoylesoftware.base.util.Stopwatch
 * that uses the system clock.
 * 
 * @version $Revision: 1.1 $
 * @author <a href="mailto:jbr@diasparsoftware.com">J. B. Rainsberger</a>
 */
public class SystemClockStopwatch implements Stopwatch {
    private boolean started = false;
    private long startTime = -1;

    private boolean stopped;
    private long stopTime = -1;

    public void start() {
        startTime = System.currentTimeMillis();
        started = true;
    }

    public void stop() {
        stopped = true;
        stopTime = System.currentTimeMillis();
    }

    public void reset() {
        started = false;
        stopped = false;
    }

    public long getLastTime() {
        if (started && stopped) {
            return stopTime - startTime;
        }
        else {
            return 0;
        }
    }
}

/*
 *  Copyright (C) 2003 Gargoyle Software. All rights reserved.
 *
 *  This file is part of GSBase. For details on use and redistribution
 *  please refer to the license.html file included with these sources.
 */
package com.diasparsoftware.gsbase;

/***
 * A simple timing device you can use to time any event.
 * 
 * Sample use.
 * 
 * <pre>
 * Stopwatch stopwatch = new SystemClockStopwatch();
 * // Your event starts...
 * stopwatch.start();
 * // Your event goes on...
 * // Your event ends...
 * stopwatch.stop();
 * long timeInMilliseconds = stopwatch.getLastTime();
 * stopwatch.reset();
 * // Next event starts...
 * stopwatch.start();
 * // And so on...
 * </pre> 
 * 
 * @version $Revision: 1.1 $
 * @author <a href="mailto:jbr@diasparsoftware.com">J. B. Rainsberger</a>
 */
public interface Stopwatch {
    /***
     * Start the stopwatch. This method will restart the stopwatch 
     * if it is already running.
     */
    void start();

    /***
     * Stops the stopwatch. This method has no effect if the stopwatch
     * is already stopped. 
     *
     */
    void stop();

    /***
     * Resets the stopwatch, clearing the last time and preparing
     * the shopwatch to be started again.
     *
     */
    void reset();

    /***
     * Returns the last time interval recorded by the stopwatch.
     * Formally, this is the difference in time between when you 
     * last invoked <code>stop()</code> after having invoked <code>start()</code>.
     * @return  The last time interval recorded by the stopwatch,
     * or 0 if the stopwatch has been reset or never used. 
     */
    long getLastTime();
}

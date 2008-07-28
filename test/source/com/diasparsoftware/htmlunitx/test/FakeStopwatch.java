package com.diasparsoftware.htmlunitx.test;

import com.diasparsoftware.gsbase.Stopwatch;


public class FakeStopwatch implements Stopwatch {
    private boolean started = false;
    private boolean stopped = false;
    
    private long lastTime;

    public FakeStopwatch(long lastTime) {
        this.lastTime = lastTime;
    }

    public void start() {
        started = true;
    }

    public void stop() {
        stopped = true;
    }

    public long getLastTime() {
        if (started && stopped) {
            return lastTime;
        }
        else {
            return 0;
        }
    }
    
    public void reset() {
        started = false;
        stopped = false;
    }
}

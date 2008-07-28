package com.diasparsoftware.java.util;


/***
 * A <code>Closure</code> whose <code>execute</code>
 * method might throw an exception.
 */
public interface ExceptionalClosure {
    Object execute(Object parameters) throws Exception;
}

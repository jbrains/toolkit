/*
 * Copyright (C) 1998, 2003 Gargoyle Software. All rights reserved.
 * 
 * This file is part of GSBase. For details on use and redistribution please
 * refer to the license.html file included with these sources.
 */
package com.diasparsoftware.gsbase;

import com.gargoylesoftware.base.util.DetailedNullPointerException;

/***
 * A utility for runtime assertions.
 * 
 * @version $Revision: 1.1 $
 * @author <a href="mailto:jbr@diasparsoftware.com">J. B. Rainsberger </a>
 */
public class Assert {
    // TODO Replace me with the Assert class in HTMLUnit.

    /***
     * Throws a runtime exception if the specified value is null.
     * 
     * @param argumentName
     *            The name of the argument you are checking
     * @param argumentValue
     *            The value of the argument you are checking
     * @throws DetailedNullPointerException
     *             Thrown if <code>argumentValue</code> is null
     */
    public static final void notNull(final String argumentName,
        final Object argumentValue) {

        if (argumentValue == null) { throw new DetailedNullPointerException(
            argumentName); }
    }
}

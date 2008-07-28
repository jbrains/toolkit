package com.diasparsoftware.java.lang.test;

import java.util.*;

import com.diasparsoftware.java.util.Money;
import com.diasparsoftware.util.junit.ValueObjectEqualsTest;

public class ValueObjectEqualsTestSinglePropertyTest
    extends ValueObjectEqualsTest {

    protected Object createControlInstance() throws Exception {
        return new Money(100, 0);
    }

    protected Object createInstanceDiffersIn(String keyPropertyName)
        throws Exception {
            
        return new Money(200, 0);
    }

    protected List keyPropertyNames() {
        return Collections.singletonList("cents");
    }

}

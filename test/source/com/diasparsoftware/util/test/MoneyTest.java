package com.diasparsoftware.util.test;

import junit.framework.TestCase;

import com.diasparsoftware.java.util.Money;

public class MoneyTest extends TestCase {
    public void testFactory() {
        Money money = Money.dollars(7, 50);
        assertEquals(750, money.inCents());
    }

    public void testZeroConstant() {
        assertEquals(0, Money.ZERO.inCents());
    }

    public void testMultiply() {
        Money money = Money.dollars(7, 50);
        Money fourTimesMoney = Money.dollars(30);

        assertEquals(fourTimesMoney, money.multipliedBy(4));
    }

    public void testLessThan() {
        Money one = Money.dollars(1);
        Money two = Money.dollars(2);

        assertTrue(one.compareTo(two) < 0);
    }

    public void testEquals() {
        Money one = Money.dollars(1);
        Money alsoOne = Money.dollars(1);

        assertTrue(one.compareTo(alsoOne) == 0);
    }
}

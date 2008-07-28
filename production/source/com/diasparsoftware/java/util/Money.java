package com.diasparsoftware.java.util;

import java.io.Serializable;
import java.text.*;
import java.util.*;

public class Money implements Cloneable, Serializable, Comparable {
    public static Money ZERO = Money.dollars(0);

    public static Money cents(int cents) {
        return new Money(cents);
    }

    public static Money dollars(int dollars) {
        return new Money(dollars, 0);
    }

    public static Money dollars(int dollars, int cents) {
        return new Money(dollars, cents);
    }

    public static Money parse(String moneyAsString) {
        try {
            return new Money(parseToCents(moneyAsString));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int parseToCents(String moneyAsString)
        throws ParseException {

        double amountInDollars =
            NumberFormat
                .getCurrencyInstance()
                .parse(moneyAsString)
                .doubleValue();
        return (int) (amountInDollars * 100);
    }

    private int cents;

    public Money() {
        this.cents = 0;
    }

    public Money(int cents) {
        this.cents = cents;
    }

    public Money(int dollars, int cents) {
        this(100 * dollars + cents);
    }

    public Money(String moneyAsString) throws ParseException {
        this(parseToCents(moneyAsString));
    }

    public Money add(Money augend) {
        return new Money(this.cents + augend.cents);
    }

    public Object clone() {
        return new Money(cents);
    }

    public boolean equals(Object other) {
        if (other != null && getClass() == other.getClass()) {
            Money that = (Money) other;
            return this.cents == that.cents;
        }
        return false;
    }

    public int hashCode() {
        return cents;
    }

    public int inCents() {
        return cents;
    }

    private double inDollars() {
        return ((double) inCents()) / 100.0d;
    }

    public boolean isValid() {
        return true;
    }

    public Money multipliedBy(int times) {
        return Money.cents(this.inCents() * times);
    }

    public Money multipliedBy(float times) {
        return Money.cents(Math.round(this.inCents() * times));
    }

    public Money negate() {
        return null;
    }

    public Money roundToNearestDollar() {
        return null;
    }

    public List split(int nWays) {
        List result = new ArrayList();
        int baseSplitInCents = inCents() / nWays;
        int centsLeftOver = inCents() - baseSplitInCents * nWays;

        for (int i = 0; i < nWays; i++) {
            int eachSplitInCents;
            if (i < centsLeftOver) {
                eachSplitInCents = baseSplitInCents + 1;
            }
            else {
                eachSplitInCents = baseSplitInCents;
            }

            result.add(new Money(eachSplitInCents));
        }

        return result;
    }

    public String toString() {
        return NumberFormat.getCurrencyInstance().format(inDollars());
    }

    public boolean valueInCentsIs(int expected) {
        return this.cents == expected;
    }

    public int compareTo(Object other) {
        Money that = (Money) other;
        return this.inCents() - that.inCents();
    }
}

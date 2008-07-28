package com.diasparsoftware.java.util.test;

import java.math.BigDecimal;

import junit.framework.TestCase;

import com.diasparsoftware.java.util.Quantity;
import com.gargoylesoftware.base.testing.TestUtil;

public class QuantityTest extends TestCase {
    public void testAdd() {
        Quantity first = new Quantity(new Integer(3), "CAD");
        Quantity second = new Quantity(new Integer(4), "CAD");

        assertEquals(
            new Quantity(new Integer(7), "CAD"),
            first.add(second));
    }

    public void testAddDifferentUnits() {
        Quantity first = new Quantity(new Integer(3), "CAD");
        Quantity second = new Quantity(new Integer(4), "USD");

        try {
            first.add(second);
            fail("Added quantities with different units?!");
        }
        catch (ClassCastException expected) {
        }
    }

    public void testZeroOf() {
        Quantity zero = Quantity.zeroOf("XXX");
        assertEquals(new Quantity(new BigDecimal(0), "XXX"), zero);
    }

    public void testSerialization() throws Exception {
        Quantity quantity = new Quantity(new Integer(5), "kg");
        TestUtil.testSerialization(quantity, true);
    }

    public void testAddSubclass() throws Exception {
        MeterQuantity addend = new MeterQuantity(5);
        MeterQuantity augend = new MeterQuantity(10);

        Quantity sum = addend.add(augend);
        assertTrue(
            "Sum is of the wrong type; subclass does not override clone()",
            sum instanceof MeterQuantity);
            
        assertEquals(new MeterQuantity(15), sum);
    }

    public static class MeterQuantity extends Quantity {
        public MeterQuantity(int magnitude) {
            super(new BigDecimal(magnitude), "m");
        }

        public Object clone() {
            return new MeterQuantity(magnitude.intValue());
        }
    }
}

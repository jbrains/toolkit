package com.diasparsoftware.java.util;

import java.io.Serializable;
import java.math.BigDecimal;

public class Quantity implements Serializable, Cloneable {
    private static Object NULL_UNIT_OF_MEASURE = new Object();
    public static Quantity ZERO = new Quantity(new BigDecimal(0), null);

    protected BigDecimal magnitude;
    protected Object unitOfMeasure;

    public Quantity(Integer magnitude, Object unitOfMeasure) {
        this(new BigDecimal(magnitude.toString()), unitOfMeasure);
    }

    public Quantity(BigDecimal magnitude, Object unitOfMeasure) {
        if (unitOfMeasure == null) {
            this.unitOfMeasure = NULL_UNIT_OF_MEASURE;
        }
        this.magnitude = magnitude;
        this.unitOfMeasure = unitOfMeasure;
    }

    /***
     * If you do not override clone(), then the add() operation
     * will fail for subclasses of Quantity, returning a Quantity
     * object rather than one of the specific subclass type.
     */
    public Object clone() {
        return new Quantity(magnitude, unitOfMeasure);
    }
    
    public Quantity add(Quantity that) {
        if (this.unitOfMeasure.equals(that.unitOfMeasure) == false)
            throw new ClassCastException(
                "Cannot add a ["
                    + unitOfMeasure
                    + "] and a ["
                    + that.unitOfMeasure
                    + "]");

        Quantity sum = (Quantity) this.clone();
        sum.magnitude = sum.magnitude.add(that.magnitude);
        
        return sum;
    }

    public boolean equals(Object other) {
        if (other instanceof Quantity) {
            Quantity that = (Quantity) other;
            return this.magnitude.equals(that.magnitude)
                && this.unitOfMeasure.equals(that.unitOfMeasure);
        }
        else {
            return false;
        }
    }
    
    public int hashCode() {
        return magnitude.hashCode();
    }

    public String toString() {
        return "<" + magnitude + ", " + unitOfMeasure + ">";
    }

    public static Quantity zeroOf(Object unitOfMeasure) {
        return new Quantity(new BigDecimal(0), unitOfMeasure);
    }
}

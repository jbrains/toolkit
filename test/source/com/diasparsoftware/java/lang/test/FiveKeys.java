package com.diasparsoftware.java.lang.test;

public class FiveKeys {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public FiveKeys(int a, int b, int c, int d, int e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }

    public boolean equals(Object other) {
        if (other instanceof FiveKeys) {
            FiveKeys that = (FiveKeys) other;
            return this.a == that.a
                && this.b == that.b
                && this.c == that.c
                && this.d == that.d
                && this.e == that.e;
        }
        else {
            return false;
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "FiveKeys[" + a + b + c + d + e + "]";
    }
}

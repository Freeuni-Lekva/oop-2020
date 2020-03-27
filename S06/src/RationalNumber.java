package com.freeuni.oop.seminar6.repinvariant;

/**
 * Immutable type representing a rational number.
 */
public class RationalNumber {

    private final int numerator;

    private final int denominator;

    // Representation invariant:
    //   denominator > 0
    //   numerator/denominator is in reduced form, i.e.
    //   gcd(|numerator|, denominator) = 1
    // Abstraction Function:
    //   represents the rational number numerator / denominator
    // Safety from representation exposure:
    //   All fields are private, and all types in the
    //   representation are immutable.

    /**
     * Constructs a Fraction n/d.
     *
     * @param n is the numerator, assumed non-negative.
     * @param d is the denominator, assumed positive.
     */
    public RationalNumber(int n, int d) {
        // Reduce ratio to lowest terms
        int gcd = Math.abs(gcd(n, d));
        n /= gcd;
        d /= gcd;

        // Make denominator positive
        if (d < 0) {
            numerator = -n;
            denominator = -d;
        } else {
            numerator = n;
            denominator = d;
        }
        checkRepresentation();
    }

    /**
     * Constructs a Fraction n/1.
     *
     * @param n is the numerator, assumed non-negative.
     */
    public RationalNumber(int n) {
        this(n, 1);
    }

    /**
     * Copy constructor
     *
     * @param other The Fraction instance we need to copy from
     */
    public RationalNumber(RationalNumber other) {
        // TODO: will be further discussed and compared to clone()
        this(other.numerator, other.denominator);
    }

    // Check that the rep invariant is true
    // *** Warning: this does nothing unless you turn on
    //     assertion checking
    // by passing -enableassertions to Java [or -ea] (for JVM run config)
    private void checkRepresentation() {
        assert denominator > 0;
        assert Math.abs(gcd(numerator, denominator)) == 1;
    }

    @Override
    public String toString() {
        int thisGcd = gcd(numerator, denominator);

        return (numerator / thisGcd + "/" + denominator / thisGcd);
    }

    public double evaluate() {
        return ((double) numerator / denominator);
    }

    /**
     * Add f2 to this fraction and return the result.
     *
     * @param f2 is the fraction to be added.
     * @return the result of adding f2 to this Fraction.
     */
    public RationalNumber add(RationalNumber f2) {
        return new RationalNumber((numerator * f2.denominator) +
                (f2.numerator * denominator),
                (denominator * f2.denominator));
    }

    /**
     * Multiply f2 to this fraction and return the result.
     *
     * @param f2 is the fraction to be multiplied.
     * @return the result of multiplying f2 to this Fraction.
     */
    public RationalNumber multiply(RationalNumber f2) {
        return new RationalNumber(numerator * f2.numerator,
                denominator * f2.denominator);
    }

    // recursive implementation
    private static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        } else {
            return gcd(q, p % q);
        }
    }

    // non-recursive implementation
    private static int gcd2(int p, int q) {
        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            // We could have used if (!(obj instanceof Fraction)) {}
            return false;
        }

        RationalNumber other = (RationalNumber) obj;
        return this.evaluate() == other.evaluate();
    }

    @Override
    public int hashCode() {
        // TODO: will be further discussed
        return Integer.valueOf(denominator).hashCode()
                & Integer.valueOf(numerator).hashCode();
    }

}

package com.freeuni.oop.seminar6.repinvariant;

import java.util.HashSet;
import java.util.Set;

/**
 * Demonstrates RationalNumber class
 */
public class RationalNumberDemonstration {

    public static void main(String[] args) {
        // Adding 1/2 to 1/2
        System.out.println("Addition:\n");
        RationalNumber fr1 = new RationalNumber(1, -2);
        RationalNumber fr2 = new RationalNumber(-1, -2);

        RationalNumber sum = fr1.add(fr2);

        System.out.println("Sum of " + fr1 + " and " + fr2 + " is " + sum);
        System.out.println("Sum evaluation: " + sum.evaluate() + "\n");

        // Comparing
        System.out.println("Comparing:\n");
        System.out.println(fr1.equals(fr2));
        System.out.println(fr1.equals(new RationalNumber(4)));

        RationalNumber fr1Copy = new RationalNumber(fr1);

        System.out.println(fr1.equals(fr1Copy));

        RationalNumber fr1Same = fr1;
        System.out.println(fr1.equals(fr1Same));

        // Putting Fraction objects in Set
        fractionsInSet();
    }

    private static void fractionsInSet() {
        System.out.println("\nSet:\n");
        Set<RationalNumber> s = new HashSet<>();

        RationalNumber fr1 = new RationalNumber(1, 2);
        RationalNumber fr2 = new RationalNumber(1, 2);
        RationalNumber fr3 = new RationalNumber(fr1);
        RationalNumber fr4 = fr1;
        RationalNumber fr5 = new RationalNumber(123);

        s.add(fr1);
        s.add(fr2);
        s.add(fr3);
        s.add(fr4);
        s.add(fr5);

        System.out.println(s.size());
    }

}

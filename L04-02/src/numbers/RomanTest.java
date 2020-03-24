package numbers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RomanTest {

    @Test
    public void test10() {
        Roman roman = new Roman("X");
        assertEquals("10", roman.toArabic());
    }

    @Test
    public void test100() {
        Roman roman = new Roman("C");
        assertEquals("100", roman.toArabic());
    }

    @Test
    public void test1000() {
        Roman roman = new Roman("M");
        assertEquals("1000", roman.toArabic());
    }

    @Test
    public void test8() {
        Roman roman = new Roman("VIII");
        assertEquals("8", roman.toArabic());
    }

    @Test
    public void test9() {
        Roman roman = new Roman("IX");
        assertEquals("9", roman.toArabic());
    }

    @Test
    public void testInvalidNumber() {
        Roman roman = new Roman("Z");
        assertNull(roman.toArabic());
    }

    @Test
    public void test498() {
        Roman roman = new Roman("CDXCVIII");
        assertEquals("498", roman.toArabic());
    }

    @Test
    public void test55() {
        Roman roman = new Roman("LV");
        assertEquals("55", roman.toArabic());
    }

}
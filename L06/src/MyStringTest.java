// testing strategy for each operation of MyString:
//
// valueOf():
//    true, false
// length():
//    string len = 0, 1, n
//    string = produced by valueOf(), produced by substring()
// charAt():
//    string len = 1, n
//    i = 0, middle, len-1
//    string = produced by valueOf(), produced by substring()
// substring():
//    string len = 0, 1, n
//    start = 0, middle, len
//    end = 0, middle, len
//    end-start = 0, n
//    string = produced by valueOf(), produced by substring()

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStringTest {
    @Test
    public void testValueOfTrue() {
        MyString s = MyString.valueOf(true);
        assertEquals(4, s.length());
        assertEquals('t', s.charAt(0));
        assertEquals('r', s.charAt(1));
        assertEquals('u', s.charAt(2));
        assertEquals('e', s.charAt(3));
    }

    @Test public void testValueOfFalse() {
        MyString s = MyString.valueOf(false);
        assertEquals(5, s.length());
        assertEquals('f', s.charAt(0));
        assertEquals('a', s.charAt(1));
        assertEquals('l', s.charAt(2));
        assertEquals('s', s.charAt(3));
        assertEquals('e', s.charAt(4));
    }

    @Test public void testEndSubstring() {
        MyString s = MyString.valueOf(true).substring(2, 4);
        assertEquals(2, s.length());
        assertEquals('u', s.charAt(0));
        assertEquals('e', s.charAt(1));
    }

    @Test public void testMiddleSubstring() {
        MyString s = MyString.valueOf(false).substring(1, 2);
        assertEquals(1, s.length());
        assertEquals('a', s.charAt(0));
    }

    @Test public void testSubstringIsWholeString() {
        MyString s = MyString.valueOf(false).substring(0, 5);
        assertEquals(5, s.length());
        assertEquals('f', s.charAt(0));
        assertEquals('a', s.charAt(1));
        assertEquals('l', s.charAt(2));
        assertEquals('s', s.charAt(3));
        assertEquals('e', s.charAt(4));
    }

    @Test public void testSubstringOfEmptySubstring() {
        MyString s = MyString.valueOf(false).substring(1, 1).substring(0, 0);
        assertEquals(0, s.length());
    }
}
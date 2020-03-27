/** MyString represents an immutable sequence of characters. */
public class MyString {
    private char[] a;
    private int start;
    private int end;

    //////////////////// Example of a creator operation ///////////////
    /** @param b a boolean value
     *  @return string representation of b, either "true" or "false" */
    public static MyString valueOf(boolean b) {
        MyString s = new MyString();
        s.a = b ? new char[] { 't', 'r', 'u', 'e' }
                : new char[] { 'f', 'a', 'l', 's', 'e' };
        s.start = 0;
        s.end = s.a.length;
        return s;
    }

    //////////////////// Examples of observer operations ///////////////
    /** @return number of characters in this string */
    public int length() {
        return end - start;
    }

    /** @param i character position (requires 0 <= i < string length)
     *  @return character at position i */
    public char charAt(int i) {
        checkBounds(i);
        return a[start + i];
    }

    //////////////////// Example of a producer operation ///////////////    
    /** Get the substring between start (inclusive) and end (exclusive).
     *  @param start starting index
     *  @param end ending index.  Requires 0 <= start <= end <= string length.
     *  @return string consisting of charAt(start)...charAt(end-1) */
    public MyString substring(int start, int end) {
        checkBounds(start);
        checkBounds(end);
        MyString that = new MyString();
        that.a = a;
        that.start = this.start + start;
        that.end = this.start + end;
        // System.arraycopy(this.a, start, that.a, 0, end - start);
        return that;
    }

    private void checkBounds(int i) {
        if (i < 0 || i > end - start) {
            throw new StringIndexOutOfBoundsException("bla");
        }
    }
}
public class FizzBuzz {
    public String evaluate(int i) {
        if (i % 15 == 0 || (containsDigit(i, "3") && containsDigit(i, "5"))) {
            return "fizzbuzz";
        } else if (i % 3 == 0 || containsDigit(i, "3")) {
            return "fizz";
        } else if (i % 5 == 0 || containsDigit(i, "5")) {
            return "buzz";
        } else {
            return String.valueOf(i);
        }
    }

    private boolean containsDigit(int i, String s) {
        return String.valueOf(i).contains(s);
    }
}

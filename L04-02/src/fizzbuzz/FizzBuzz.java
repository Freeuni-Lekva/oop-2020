package fizzbuzz;

public class FizzBuzz {

    public String evaluate(int i) {
        if (isMultiple(i, 3) && isMultiple(i, 5)) {
            return "FizzBuzz";
        }
        if (isMultiple(i, 3)) {
            return "Fizz";
        }
        if (isMultiple(i, 5)) {
            return "Buzz";
        }
        return String.valueOf(i);
    }

    public boolean isMultiple(int n, int m) {
        return n % m == 0;
    }

}


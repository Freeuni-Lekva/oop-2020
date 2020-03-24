package numbers;

public class Roman {

    private String number;

    public Roman(String number) {
        this.number = number;
    }

    public String toArabic() {

        if (isValidRoman()) {
            int result = 0;
            int lastDigit = 0;
            int current = 0;

            for (int i = 0; i < number.length(); i++) {

                if (number.charAt(i) == 'I') {
                    current = 1;
                }
                if (number.charAt(i) == 'V') {
                    current = 5;
                }
                if (number.charAt(i) == 'X') {
                    current = 10;
                }
                if (number.charAt(i) == 'L') {
                    current = 50;
                }
                if (number.charAt(i) == 'C') {
                    current = 100;
                }
                if (number.charAt(i) == 'D') {
                    current = 500;
                }
                if (number.charAt(i) == 'M') {
                    current = 1000;
                }

                if (lastDigit < current && lastDigit != 0) {
                    current -= lastDigit;
                    result -= lastDigit;
                    result += current;
                    lastDigit = current;
                } else {
                    lastDigit = current;
                    result += current;
                }
                current = 0;
            }
            return String.valueOf(result);
        } else {
            return null;
        }
    }

    public boolean isValidRoman() {
        for (int k = 0; k < number.length(); k++) {
            if (number.charAt(k) != 'I' &&
                    number.charAt(k) != 'V' &&
                    number.charAt(k) != 'X' &&
                    number.charAt(k) != 'L' &&
                    number.charAt(k) != 'C' &&
                    number.charAt(k) != 'D' &&
                    number.charAt(k) != 'M') {
                return false;
            }
        }
        return true;
    }

}

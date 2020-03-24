package part1;

public class Mobile extends Phone {

    @Override
    public void makeCall(String number) {
        System.out.println("Making call " + number);
    }

    @Override
    public void plugin() {
    }

    @Override
    public void on() {
    }

    @Override
    public void off() {
    }

    public static void main(String[] args) {
        Mobile mobile = new Mobile();

        String number = "123456";
        if (mobile.testCall(number)) {
            mobile.makeCall(number);
        }
    }
}

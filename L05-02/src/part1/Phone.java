package part1;

public abstract class Phone extends Electronic {

    abstract void makeCall(String number);

    boolean testCall(String number) {
        return true;
    }
}
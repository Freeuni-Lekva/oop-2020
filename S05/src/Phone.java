public abstract class Phone extends Electronic {
    public abstract void makeCall(String number);

    public void testCall(String number) {
        System.out.printf("Making a test call: %s\n", number);
    }
}

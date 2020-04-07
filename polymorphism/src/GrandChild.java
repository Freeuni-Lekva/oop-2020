public class GrandChild extends Child {
    public GrandChild(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return new StringBuilder(super.getValue())
                .reverse()
                .toString();
    }
}

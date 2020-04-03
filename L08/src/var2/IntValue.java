package var2;

public class IntValue implements Node {
    private int value;

    public IntValue(int value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return "(" + value + ")";
    }
}

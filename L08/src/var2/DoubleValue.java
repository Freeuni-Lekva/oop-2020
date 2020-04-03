package var2;

public class DoubleValue implements Node {
    private double value;

    public DoubleValue(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return "(" + String.valueOf(value) + ")";
    }
}

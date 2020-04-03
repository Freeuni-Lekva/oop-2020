package var3;

public class ValueNode<T extends Number> implements Node {
    private T value;

    public ValueNode(T value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value.doubleValue();
    }

    @Override
    public String toString() {
        return "(" + value.toString() + ")";
    }
}

package var3;

// Implements:
//   toString which delegates getting operator character to new abstract getOperator() method.
//   evaluate which first evaluates left and right nodes and passes their values to doEvaluate method from subclass.
public abstract class BinaryOpNode implements Node {
    private Node left;
    private Node right;

    public BinaryOpNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate() {
        return doEvaluate(left.evaluate(), right.evaluate());
    }

    @Override
    public String toString() {
        return "(" + left.toString() + getOperator() + right.toString() + ")";
    }

    protected abstract double doEvaluate(double left, double right);

    protected abstract char getOperator();
}

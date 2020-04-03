package var3;

public class MultiplyNode extends BinaryOpNode {

    public MultiplyNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public double doEvaluate(double left, double right) {
        return left * right;
    }

    @Override
    public char getOperator() {
        return '*';
    }
}

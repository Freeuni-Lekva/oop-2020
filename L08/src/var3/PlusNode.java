package var3;

public class PlusNode extends BinaryOpNode {

    public PlusNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public double doEvaluate(double left, double right) {
        return left + right;
    }

    @Override
    public char getOperator() {
        return '+';
    }
}

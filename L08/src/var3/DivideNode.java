package var3;

public class DivideNode extends BinaryOpNode {

    public DivideNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public double doEvaluate(double left, double right) {
        return left / right;
    }

    @Override
    protected char getOperator() {
        return '/';
    }
}

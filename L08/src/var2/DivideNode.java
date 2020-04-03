package var2;

public class DivideNode implements Node {
    private Node left;
    private Node right;

    public DivideNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate() {
        return left.evaluate() / right.evaluate();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + '/' + right.toString() + ")";
    }
}

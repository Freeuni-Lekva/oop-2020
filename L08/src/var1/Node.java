package var1;

public class Node {
    private char operator;
    private Node left;
    private Node right;
    private double value;

    public Node(char operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    // Convenience constructor
    public Node(char operator, double left, double right) {
        this(operator, new Node(left), new Node(right));
    }

    public Node(double value) {
        this.operator = '#';
        this.value = value;
    }

    public double evaluate() {
        switch (operator) {
            case '#': return value;
            case '+': return left.evaluate() + right.evaluate();
            case '-': return left.evaluate() - right.evaluate();
            case '/': return left.evaluate() / right.evaluate();
            case '*': return left.evaluate() * right.evaluate();
            default: throw new RuntimeException("Unsupported operation: " + operator);
        }
    }

    public String toString() {
        switch (operator) {
            case '#': return "(" + String.valueOf(value) + ")";
            case '+': return "(" + left.toString() + '+' + right.toString() + ")";
            case '-': return "(" + left.toString() + '-' + right.toString() + ")";
            case '/': return "(" + left.toString() + '/' + right.toString() + ")";
            case '*': return "(" + left.toString() + '*' + right.toString() + ")";
            default: throw new RuntimeException("Unsupported operation: " + operator);
        }
    }
}

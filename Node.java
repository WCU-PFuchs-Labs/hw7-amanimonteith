import java.util.Random;

public class Node {
    private Node lChild;
    private Node rChild;
    private Op op;
    protected int depth;

    // Constructors
    public Node(Binop op, Node lChild, Node rChild) {
        this.op = op;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    public Node(Unop op) {
        this.op = op;
        this.lChild = null;
        this.rChild = null;
    }

    // Evaluate
    public double eval(double[] values) {
        if (op instanceof Binop) {
            double left = lChild.eval(values);
            double right = rChild.eval(values);
            return ((Binop) op).eval(left, right);
        } else if (op instanceof Unop) {
            return ((Unop) op).eval(values);
        } else {
            throw new RuntimeException("Unknown operation type");
        }
    }

    @Override
    public String toString() {
        if (op instanceof Binop) {
            return "(" + lChild.toString() + " " + op.toString() + " " + rChild.toString() + ")";
        } else {
            return op.toString();
        }
    }

    // New HW7 methods

    public void traverse(Collector c) {
        c.collect(this);
        if (lChild != null) lChild.traverse(c);
        if (rChild != null) rChild.traverse(c);
    }

    public boolean isLeaf() {
        return op instanceof Unop;
    }

    public void swapLeft(Node trunk) {
        Node temp = this.lChild;
        this.lChild = trunk.lChild;
        trunk.lChild = temp;
    }

    public void swapRight(Node trunk) {
        Node temp = this.rChild;
        this.rChild = trunk.rChild;
        trunk.rChild = temp;
    }
}

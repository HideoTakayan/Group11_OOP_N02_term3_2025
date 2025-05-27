public class TestArithmetic {
    public static void main(String[] args) {
        Node n1 = new Const(1.1);
        Node n2 = new Const(2.2);
        Node n3 = new Plus(n1, n2);
        Node n4 = new Const(3.3);
        Node n5 = new Plus(n3, n4);

        System.out.println("Ket qua: " + n5.eval());
    }
}

class Node {
    public Node() {
    }

    public double eval() {
        System.out.println("Error: eval Node");
        return 0;
    }
}

class Const extends Node {
    private double value;

    public Const(double d) {
        value = d;
    }

    @Override
    public double eval() {
        return value;
    }
}

class Binop extends Node {
    protected Node lChild, rChild;

    public Binop(Node l, Node r) {
        lChild = l;
        rChild = r;
    }
}

class Plus extends Binop {
    public Plus(Node l, Node r) {
        super(l, r);
    }

    @Override
    public double eval() {
        return lChild.eval() + rChild.eval();
    }
}

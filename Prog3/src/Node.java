public class Node {
    private final int index;
    private final int value;
    private int height;
    private Node leftNode;
    private Node rightNode;

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
        height = 1;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Node{I" + index + "-V" + value + "-H" + height + "}";
    }
}

public class Node {
    private final int index;
    private final int value;
    private int height;

    private int size;
    private Node leftNode;
    private Node rightNode;

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
        height = 1;
        size = 1;
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

    public boolean hasChildren() {
        return rightNode != null || leftNode != null;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void updateHeight() {
        height = Math.max(leftNode == null ? 0 : leftNode.getHeight(),
                rightNode == null ? 0 : rightNode.getHeight()) + 1;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void updateSize() {
        size = (leftNode == null ? 0 : leftNode.getSize()) + (rightNode == null ? 0 : rightNode.getSize()) + 1;
    }

    public void updateSizeAndHeight() {
        this.updateSize();
        this.updateHeight();
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Node{I" + index + "-V" + value + "-H" + height + "-S" + size + "}";
    }
}

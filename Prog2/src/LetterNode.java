public class LetterNode {
    private char value;
    private LetterNode leftNode;
    private LetterNode rightNode;

    public LetterNode() {
    }


    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public LetterNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(LetterNode leftNode) {
        this.leftNode = leftNode;
    }

    public LetterNode getRightNode() {
        return rightNode;
    }

    public boolean hasChildren() {
        return leftNode != null || rightNode != null;
    }

    public void setRightNode(LetterNode rightNode) {
        this.rightNode = rightNode;
    }
}

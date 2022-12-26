public class Tree {
    private Node root;
    private int opCount;
    private int index;

    public Tree(int opCount) {
        this.opCount = opCount;
    }

    public void printRec(Node tmp) {
        System.out.println("I" + tmp.getIndex() + "-V" + tmp.getValue() + "-H" + tmp.getHeight() + ",");
        if (tmp.getLeftNode() != null)
            printRec(tmp.getLeftNode());
        if (tmp.getRightNode() != null)
            printRec(tmp.getRightNode());
    }

    public void build(Node curNode, Node newNode) {
        if (curNode == null) //Root
            root = newNode;
        else { //Insert Node
            if (curNode.getRightNode() != null) {
                build(curNode.getRightNode(), newNode);
            } else {
                curNode.setRightNode(newNode);
            }
            curNode.setHeight(curNode.getHeight() + 1);
        }
        //Rotations
        int balanceFactor = getBalanceFactor(curNode);
        if (balanceFactor > 1) {
            if (getBalanceFactor(curNode.getLeftNode()) < 0) //Double Right Or R-L rotation
                //ToDo
                rotateLeft(curNode);
            else
                rotateRight(curNode);
        } else if (balanceFactor < -1) {

        }
    }

//    public void add(Node curNode, Node newNode) {
//        if (curNode == null)
//            root = newNode;
//        else {
//            if (newNode.getIndex() > curNode.getIndex()) {
//
//            }
//        }
//    }

    public void operate() {
        while (opCount > 0) {

            opCount--;
        }
    }

    public void rotateLeft(Node node) {

    }

    public void rotateRight(Node node) {

    }

    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return node.getLeftNode().getHeight() - node.getRightNode().getHeight();
    }

    public Node getRoot() {
        return root;
    }
}
